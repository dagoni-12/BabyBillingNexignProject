package ru.anger.CDRGen.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.anger.CDRGen.DTO.RecordDTO;

import java.util.List;

@Service
public class CdrSender implements MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${exchange.name}")
    private String exchangeName;

    @Override
    public void send(List<RecordDTO> cdr) {
        System.out.println("Sending this records:");

        cdr.forEach(record -> {
            System.out.printf("Type: %-5s | From: %-12s | To: %-12s | Start: %-20s | End: %-20s%n",
                    record.getCallType(),
                    record.getSubscriberMsisdn(),
                    record.getPartnerMsisdn(),
                    record.getStartTime(),
                    record.getEndTime());
        });
        rabbitTemplate.convertAndSend(exchangeName, "message.cdr", cdr);
    }
}
