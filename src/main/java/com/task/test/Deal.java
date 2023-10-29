package com.task.test;


public class Deal {
    private String dealUniqueId;
    private String fromCurrencyISOCode;
    private String toCurrencyISOCode;
    private String dealTimestamp;
    private String dealAmountInOrderingCurrency;


    public String getDealUniqueId() {
        return dealUniqueId;
    }

    public String getFromCurrencyISOCode() {
        return fromCurrencyISOCode;
    }

    public String getToCurrencyISOCode() {
        return toCurrencyISOCode;
    }

    public String getDealTimestamp() {
        return dealTimestamp;
    }

    public String getDealAmountInOrderingCurrency() {
        return dealAmountInOrderingCurrency;
    }


}
