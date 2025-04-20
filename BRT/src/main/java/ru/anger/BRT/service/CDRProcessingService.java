package ru.anger.BRT.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anger.BRT.DTO.DataForRatingDTO;
import ru.anger.BRT.DTO.RecordDTO;
import ru.anger.BRT.entity.CallInfo;
import ru.anger.BRT.entity.Subscriber;
import ru.anger.BRT.mapper.CallInfoMapper;
import ru.anger.BRT.mapper.RecordMapper;
import ru.anger.BRT.repositories.CallInfoRepository;
import ru.anger.BRT.repositories.SubscriberRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CDRProcessingService {

    @Autowired
    private CallInfoRepository callInfoRepository;
    @Autowired
    private SubscriberRepository subscriberRepository;
    @Autowired
    HrsClientService hrsClientService;


    public void processCalls(List<RecordDTO> recordDTOs) {
        System.out.println("----------------------GOT THIS MFS----------------------");

        recordDTOs.forEach(record -> {
            System.out.printf("Type: %-5s | From: %-12s | To: %-12s | Start: %-20s | End: %-20s%n",
                    record.getCallType(),
                    record.getStartTime(),
                    record.getEndTime(),
                    record.getReceiver(),
                    record.getCaller()
                    );
        });
        //Collections.reverse(recordDTOs);
        for (RecordDTO callDTO : recordDTOs) {
            processCall(callDTO);
        }
    }

    @Transactional
    public void processCall(RecordDTO recordDTO) {
        CallInfo callInfo = RecordMapper.toEntity(recordDTO);

        Optional<Subscriber> callerOpt = subscriberRepository.findByMsisdn(callInfo.getCaller());
        Optional<Subscriber> receiverOpt = subscriberRepository.findByMsisdn(callInfo.getReceiver());

//        Optional<Subscriber> subscriberOpt = subscriberRepository.findByMsisdn(callInfo.getCaller());
//        if (subscriberOpt.isPresent()) {
//            DataForRatingDTO ratingDTO = CallInfoMapper.toRatingDTO(callInfo, subscriberOpt.get());
//            hrsClientService.sendToHrs(ratingDTO);
//        }

        if (callerOpt.isPresent()) {
//            System.out.println(callInfo);
            callInfoRepository.save(callInfo);
            DataForRatingDTO ratingDTO = CallInfoMapper.toRatingDTO(callInfo, callerOpt.get());
//            hrsClientService.sendToHrs(ratingDTO);
        } else if (receiverOpt.isPresent()) {
//            System.out.println(callInfo);
            callInfoRepository.save(callInfo);
            DataForRatingDTO ratingDTO = CallInfoMapper.toRatingDTO(callInfo, receiverOpt.get());
//            hrsClientService.sendToHrs(ratingDTO);
        }
    }
}
