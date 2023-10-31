package com.task.deal.pojo;


public class Deal {
    private String dealUniqueId;
    private String fromCurrencyISOCode;
    private String toCurrencyISOCode;
    private String dealTimestamp;
    private String dealAmountInOrderingCurrency;

    public Deal(String dealUniqueId, String fromCurrencyISOCode, String toCurrencyISOCode, String dealTimestamp, String dealAmountInOrderingCurrency) {
        this.dealUniqueId = dealUniqueId;
        this.fromCurrencyISOCode = fromCurrencyISOCode;
        this.toCurrencyISOCode = toCurrencyISOCode;
        this.dealTimestamp = dealTimestamp;
        this.dealAmountInOrderingCurrency = dealAmountInOrderingCurrency;
    }


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
