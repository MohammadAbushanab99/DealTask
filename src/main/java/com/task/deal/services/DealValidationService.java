package com.task.deal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DealValidationService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Autowired
    private CurrencyValidationService currencyValidationService;

    @Autowired
    private AmountValidationService amountValidationService;

    private static final Logger logger = LoggerFactory.getLogger(DealValidationService.class);


    public boolean isValidDealUniqueId(String dealUniqueId) {
        if(!dealUniqueId.isEmpty()) {
            String regexPattern = "^[a-zA-Z0-9]{10}$";

            Pattern pattern = Pattern.compile(regexPattern);
            Matcher matcher = pattern.matcher(dealUniqueId);

            return matcher.matches();
        }
        return false;
    }

    public boolean isValidCurrencyCode(String currencyCode) {
        if(!currencyCode.isEmpty()) {
            return currencyValidationService.isValidCurrencyCode(currencyCode);
        }
        return false;
    }

    public boolean isValidTimestampFormat(String dealTimestamp) {
        if(!dealTimestamp.isEmpty()) {
            try {
                LocalDateTime parsedDateTime = LocalDateTime.parse(dealTimestamp, DATE_TIME_FORMATTER);
                return true;
            } catch (DateTimeParseException e) {
                logger.error("Invalid timestamp format: {}", e.getMessage());
                return false;
            }
        }
        return false;
    }


    public boolean isValidDealAmount(String dealAmount) {
        if(!dealAmount.isEmpty())
           return amountValidationService.isValidAmount(dealAmount);

        return false;
    }
}
