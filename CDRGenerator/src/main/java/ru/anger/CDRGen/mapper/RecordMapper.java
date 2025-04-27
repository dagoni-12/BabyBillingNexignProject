package ru.anger.CDRGen.mapper;

import ru.anger.CDRGen.DTO.RecordDTO;
import ru.anger.CDRGen.entity.Record;

public class RecordMapper {

    public static RecordDTO toDTO(Record record) {
        return new RecordDTO(
                record.getCallType(),
                record.getSubscriberMsisdn(),
                record.getPartnerMsisdn(),
                record.getStartTime(),
                record.getEndTime()
        );
    }

    public static Record toEntity(RecordDTO dto) {
        Record record = new Record();
        record.setCallType(dto.getCallType());
        record.setSubscriberMsisdn(dto.getSubscriberMsisdn());
        record.setPartnerMsisdn(dto.getPartnerMsisdn());
        record.setStartTime(dto.getStartTime());
        record.setEndTime(dto.getEndTime());
        return record;
    }
}
