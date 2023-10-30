package com.task.deal.controller;

import com.task.deal.services.DealService;
import com.task.deal.pojo.Deal;
import com.task.deal.pojo.DealResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/deals")
public class DealController {
    @Autowired
    private DealService dealService;

    private static final Logger logger = LoggerFactory.getLogger(DealController.class);


    @PostMapping
    public ResponseEntity<DealResponse> createDealRequest(@RequestBody Deal deal) {
        DealResponse response = dealService.createDeal(deal);
        if (response.getStatus().equals("Deal created successfully")) {
            logger.info("Deal created successfully. Request: {}", deal);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            logger.error("Failed to create deal. Request: {}. Error: {}", deal, response.getStatus());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


}

