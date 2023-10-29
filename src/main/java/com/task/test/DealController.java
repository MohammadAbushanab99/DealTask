package com.task.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deals")
public class DealController {
    @Autowired
    private DealService dealService;

    @PostMapping
    public ResponseEntity<DealResponse> createDeal(@RequestBody Deal deal) {
        DealResponse response = dealService.createDeal(deal);
        if (response.getStatus().equals("Deal created successfully")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


}

