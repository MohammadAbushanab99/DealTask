package com.task.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AmountValidationService {
    private static final double MIN_AMOUNT = 0.0;
    private static final double MAX_AMOUNT = 1000000.0;
    private static final int MAX_DECIMAL_PLACES = 2;

    private static final Logger logger = LoggerFactory.getLogger(AmountValidationService.class);


    public boolean isValidAmount(String dealAmount) {
        if (isValidNumericFormat(dealAmount)) {
            try {
                double amount = Double.parseDouble(dealAmount);

                if (amount >= MIN_AMOUNT && amount <= MAX_AMOUNT) {
                    int decimalPlaces = getDecimalPlaces(dealAmount);
                    return decimalPlaces <= MAX_DECIMAL_PLACES;
                }
            } catch (NumberFormatException e) {
                logger.error("Invalid amount format: {}", e.getMessage());
                return false;
            }
        }

        return false;
    }

    private boolean isValidNumericFormat(String dealAmount) {
        return dealAmount.matches("^\\d+(\\.\\d+)?$");
    }

    private int getDecimalPlaces(String dealAmount) {
        int dotIndex = dealAmount.indexOf(".");
        return dotIndex == -1 ? 0 : dealAmount.length() - dotIndex - 1;
    }
}
