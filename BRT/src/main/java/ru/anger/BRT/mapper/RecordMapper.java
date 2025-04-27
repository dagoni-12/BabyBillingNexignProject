package ru.anger.BRT.mapper;

import ru.anger.BRT.DTO.RecordDTO;
import ru.anger.BRT.entity.CallInfo;

public class RecordMapper {

    public static RecordDTO toDTO(CallInfo callInfo) {
        return new RecordDTO(
                callInfo.getCallType(),
                callInfo.getSubscriberMsisdn(),
                callInfo.getPartnerMsisdn(),
                callInfo.getStartTime(),
                callInfo.getEndTime()
        );
    }

    public static CallInfo toEntity(RecordDTO dto) {
        CallInfo callInfo = new CallInfo();
        callInfo.setCallType(dto.getCallType());
        callInfo.setSubscriberMsisdn(dto.getSubscriberMsisdn());
        callInfo.setPartnerMsisdn(dto.getPartnerMsisdn());
        callInfo.setStartTime(dto.getStartTime());
        callInfo.setEndTime(dto.getEndTime());
        return callInfo;
    }
}
