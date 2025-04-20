package ru.anger.BRT.mapper;

import ru.anger.BRT.DTO.RecordDTO;
import ru.anger.BRT.entity.CallInfo;

public class RecordMapper {

    public static RecordDTO toDTO(CallInfo callInfo) {
        return new RecordDTO(
                callInfo.getCallType(),
                callInfo.getCaller(),
                callInfo.getReceiver(),
                callInfo.getStartTime(),
                callInfo.getEndTime()
        );
    }

    public static CallInfo toEntity(RecordDTO dto) {
        CallInfo callInfo = new CallInfo();
        callInfo.setCallType(dto.getCallType());
        callInfo.setCaller(dto.getCaller());
        callInfo.setReceiver(dto.getReceiver());
        callInfo.setStartTime(dto.getStartTime());
        callInfo.setEndTime(dto.getEndTime());
        return callInfo;
    }
}
