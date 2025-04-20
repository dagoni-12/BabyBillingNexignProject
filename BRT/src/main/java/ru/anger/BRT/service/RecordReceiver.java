package ru.anger.BRT.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anger.BRT.DTO.RecordDTO;

import java.util.List;

@Service
public class RecordReceiver {

    @Autowired
    CDRProcessingService cdrProcessingService;

    @RabbitListener(queues = {"${receiverQueue.name}"})
    public void receiveRecords(List<RecordDTO> RecordDTOs) {
        cdrProcessingService.processCalls(RecordDTOs);
    }
}