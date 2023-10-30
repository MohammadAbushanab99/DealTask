package com.task.deal.services;

import com.task.deal.repository.DealRepository;
import com.task.deal.exception.Exceptions;
import com.task.deal.pojo.Deal;
import com.task.deal.pojo.DealResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DealService extends Exceptions {
    @Autowired
    private DealRepository dealRepository;

    @Autowired
    private DealValidationService dealValidationService;

    private static final Logger logger = LoggerFactory.getLogger(DealService.class);

    public DealResponse createDeal(Deal deal) {
        try {
            validateDeal(deal);
            dealRepository.createDeal(deal);
            return new DealResponse("Deal created successfully", deal);
        } catch (DealValidationException e) {
            logger.error("Deal validation failed: {}", e.getMessage());
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





}
