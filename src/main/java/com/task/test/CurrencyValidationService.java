package com.task.test;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class CurrencyValidationService {
    private static final Set<String> validCurrencyCodes = new HashSet<>(Arrays.asList(
            "USD", "EUR", "JOD", "GBP","KWD","OMR"
    ));

    public boolean isValidCurrencyCode(String currencyCode) {
        return validCurrencyCodes.contains(currencyCode.toUpperCase());
    }
}
