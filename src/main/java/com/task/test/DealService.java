package com.task.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DealService {
    @Autowired
    private DealRepository dealRepository;

    @Autowired
    private DealValidationService dealValidationService;

    public DealResponse createDeal(Deal deal) {
        try {
            validateDeal(deal);
            dealRepository.createDeal(deal);
            return new DealResponse("Deal created successfully", deal);
        } catch (DealValidationException e) {
            return new DealResponse(e.getMessage(),null);
        }
    }

    private void validateDeal(Deal deal) {
        if (!dealValidationService.isValidDealUniqueId(deal.getDealUniqueId()) || !dealRepository.isDealUniqueIdUnique(deal.getDealUniqueId())) {
            throw new DealValidationException("Invalid DealUniqueId format");
        }

        if (!dealValidationService.isValidCurrencyCode(deal.getFromCurrencyISOCode()) || !dealValidationService.isValidCurrencyCode(deal.getToCurrencyISOCode())) {
            throw new DealValidationException("Invalid currency code");
        }

        if (!dealValidationService.isValidTimestampFormat(deal.getDealTimestamp())) {
            throw new DealValidationException("Invalid timestamp format");
        }

        if (!dealValidationService.isValidDealAmount(deal.getDealAmountInOrderingCurrency())) {
            throw new DealValidationException("Invalid deal amount");
        }
    }


    class DealValidationException extends RuntimeException {
        public DealValidationException(String message) {
            super(message);
        }
    }


}
