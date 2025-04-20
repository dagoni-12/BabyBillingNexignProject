package ru.anger.CDRGen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.anger.CDRGen.DTO.RecordDTO;
import ru.anger.CDRGen.entity.Record;
import ru.anger.CDRGen.entity.Subscriber;
import ru.anger.CDRGen.mapper.RecordMapper;
import ru.anger.CDRGen.repositories.SubscribersRepository;
import ru.anger.CDRGen.repositories.RecordRepository;

import java.time.LocalDateTime;

import java.util.*;

@Service
public class CdrGeneratorService {

    @Autowired
    private SubscribersRepository subscriberRepository;
    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    CdrSender cdrSender;

    private final Random random = new Random();
    private final List<Record> cdrBuffer = new ArrayList<>();

    private static LocalDateTime currentStartTime = LocalDateTime.of(2024, 1, 1, 0, 0);
    private static LocalDateTime endOfYear = currentStartTime.plusYears(1);

    private final Map<Subscriber, LocalDateTime> busySubscriber = new HashMap<>();

    public void generateRecords(int min, int max) {
        List<Subscriber> subscribers = subscriberRepository.findAll();
        checkAndResetYear();

        //кол-во необходимых записей
        int targetCount = min + random.nextInt(max - min + 1);
        System.out.println(targetCount);
        int generated = 0;

        while (generated < targetCount && currentStartTime.isBefore(endOfYear)) {
            System.out.println("-----------------------------------GENERATING NEW RECORDS-----------------------------------");

            //считаем время для очередной записи
            LocalDateTime startTime = currentStartTime;
            int duration = random.nextInt(3600);
            LocalDateTime endTime = startTime.plusSeconds(duration);
            LocalDateTime midnight = startTime.toLocalDate().plusDays(1).atStartOfDay();

            //перед созданием новой записи рефрешим занятых пользователей
            updateBusySubscribers();
            //случайные неодинаковые незанятые пользователи
            Subscriber caller = getRandomSubscriber(subscribers, null);
            Subscriber receiver = getRandomSubscriber(subscribers, caller);
            String callType = random.nextBoolean() ? "01" : "02";

            //добавляем занятых пользователей
            busySubscriber.put(caller, endTime);
            busySubscriber.put(receiver, endTime);

            //проверка на ночной звонок: делим на 2 разные записи если два разных звонка
            List<Record> createdRecords = new ArrayList<>();
            if (endTime.isAfter(midnight)) {
                createdRecords.add(createRandomRecord(callType, caller, receiver, startTime, midnight));
                createdRecords.add(createRandomRecord(callType, caller, receiver, midnight, endTime));
                generated += 2;
            } else {
                createdRecords.add(createRandomRecord(callType, caller, receiver, startTime, endTime));
                generated += 1;
            }
            postCreateInit(createdRecords);
        }
    }

    private void postCreateInit(List<Record> createdRecords) {
        for (Record createdRecord : createdRecords) {
            recordRepository.save(createdRecord);
            cdrBuffer.add(createdRecord);
            checkAndSendCdr();
            updateCurrentTime(createdRecord);
        }
        checkAndResetYear();
    }

    private Record createRandomRecord(String callType, Subscriber caller, Subscriber receiver,
                                      LocalDateTime startTime, LocalDateTime endTime) {
        Record record = new Record();
        record.setCallType(callType);
        record.setCaller(caller.getMsisdn());
        record.setReceiver(receiver.getMsisdn());
        record.setStartTime(startTime);
        record.setEndTime(endTime);

        return record;
    }

    private void updateBusySubscribers() {
        busySubscriber.entrySet().removeIf(entry -> entry.getValue().isBefore(currentStartTime));
    }

    private void checkAndSendCdr() {
        if (cdrBuffer.size() >= 10) {
            List<RecordDTO> cdr = convertToDTO(cdrBuffer);
            cdrSender.send(cdr);
            cdrBuffer.clear();
        }
    }

    private List<RecordDTO> convertToDTO(List<Record> cdrBuffer) {
        List<RecordDTO> cdr = new ArrayList<>();
        for (Record record : cdrBuffer) {
            RecordDTO recordDTO = RecordMapper.toDTO(record);
            cdr.add(recordDTO);
        }
        return cdr;
    }

    private List<Record> createCdr() {
        List<Record> cdr = new ArrayList<>();
        for (int i = 0; i < 10 && !cdrBuffer.isEmpty(); i++) {
            cdr.add(cdrBuffer.remove(cdrBuffer.size() - 1));
        }

        return cdr;
    }

    private void updateCurrentTime(Record record) {
        LocalDateTime lastEndTime = record.getStartTime();
        currentStartTime = lastEndTime.plusSeconds(random.nextInt(3000));

        if (currentStartTime.isAfter(endOfYear)) {
            currentStartTime = endOfYear;
        }
    }

    private Subscriber getRandomSubscriber(List<Subscriber> subscribers, Subscriber exclude) {
        Subscriber subscriber;
        do {
            subscriber = subscribers.get(random.nextInt(subscribers.size()));
        } while (subscriber.equals(exclude) || busySubscriber.containsKey(subscriber));
        return subscriber;
    }

    private void checkAndResetYear() {
        if (currentStartTime.isAfter(endOfYear)) {
            currentStartTime = currentStartTime.plusYears(1)
                    .withMonth(1).withDayOfMonth(1).withHour(0).withMinute(0);
            endOfYear = currentStartTime.plusYears(1);
        }
    }
}
