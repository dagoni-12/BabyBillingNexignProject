package ru.anger.BRT.mapper;

import ru.anger.BRT.DTO.DataForRatingDTO;
import ru.anger.BRT.entity.CallInfo;
import ru.anger.BRT.entity.Subscriber;

public class CallInfoMapper {

    public static DataForRatingDTO toRatingDTO(CallInfo callInfo, Subscriber subscriber, boolean partnerIsRomashkaSubscriber) {
        DataForRatingDTO dto = new DataForRatingDTO();

        dto.setMsisdn(subscriber.getMsisdn());
        dto.setPartnerIsRomashkaSubscriber(partnerIsRomashkaSubscriber);
        dto.setTariffId(subscriber.getTariff_id());
        dto.setCallType(callInfo.getCallType());
        dto.setStartTime(callInfo.getStartTime());
        dto.setEndTime(callInfo.getEndTime());
        dto.setIncludedMinutes(subscriber.getIncludedMinutes());
        return dto;
    }
}
