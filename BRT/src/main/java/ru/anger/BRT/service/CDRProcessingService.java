package ru.anger.BRT.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anger.BRT.DTO.ChargeResultDTO;
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
    @Autowired
    BalanceService balanceService;


    public void processCalls(List<RecordDTO> recordDTOs) {
        for (RecordDTO callDTO : recordDTOs) {
            processCall(callDTO);
        }
    }

    public void processCall(RecordDTO recordDTO) {
        CallInfo callInfo = RecordMapper.toEntity(recordDTO);

        Optional<Subscriber> subscriberMsisdnOpt = subscriberRepository.findByMsisdn(callInfo.getSubscriberMsisdn());
        Optional<Subscriber> partnerMsisdnOpt = subscriberRepository.findByMsisdn(callInfo.getPartnerMsisdn());
        if (subscriberMsisdnOpt.isEmpty()) {
            return;
        }

        Subscriber subscriber = subscriberMsisdnOpt.get();
        callInfoRepository.save(callInfo);

        boolean partnerIsRomashkaSubscriber = partnerMsisdnOpt.isPresent();
        DataForRatingDTO ratingDTO = CallInfoMapper.toRatingDTO(callInfo, subscriberMsisdnOpt.get(), partnerIsRomashkaSubscriber);

        ChargeResultDTO result = hrsClientService.calculateCharge(ratingDTO);
        balanceService.applyCharge(subscriber.getMsisdn(), result);

        if (result.isShouldUpdateLastPaymentDate()) {
            subscriber.setLastPaymentDate(callInfo.getStartTime().toLocalDate());
        }

        subscriberRepository.save(subscriber);
    }
}
