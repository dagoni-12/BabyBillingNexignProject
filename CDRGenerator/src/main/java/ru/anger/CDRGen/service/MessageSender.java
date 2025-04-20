package ru.anger.CDRGen.service;

import ru.anger.CDRGen.DTO.RecordDTO;

import java.util.List;

public interface MessageSender {
    void send(List<RecordDTO> cdr);
}
