package com.task.deal.pojo;

import com.task.deal.pojo.Deal;

public class DealResponse {
    private String status;
    private Deal deal;

    public DealResponse(String status, Deal deal) {
        this.status = status;
        this.deal = deal;
    }

    public DealResponse() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }
}