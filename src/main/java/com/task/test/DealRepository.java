package com.task.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DealRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createDeal(Deal deal) {
        try {
        String sql = "INSERT INTO deals (dealUniqueId, fromCurrencyISOCode, toCurrencyISOCode, dealTimestamp, dealAmountInOrderingCurrency) VALUES (?, ?, ?, ?, ?)";

            int rowsAffected = jdbcTemplate.update(sql, deal.getDealUniqueId(), deal.getFromCurrencyISOCode(), deal.getToCurrencyISOCode(), deal.getDealTimestamp(), deal.getDealAmountInOrderingCurrency());

            if (rowsAffected > 0) {
                System.err.println("Data inserted successfully.");
            } else {
                System.err.println("Failed to insert data.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isDealUniqueIdUnique(String dealUniqueId) {

        String query = "SELECT COUNT(*) FROM deals WHERE dealUniqueId = ?";
        try {
            int count = jdbcTemplate.queryForObject(query, Integer.class, dealUniqueId);
            return count == 0;
        } catch (DataAccessException e) {
            return false;
        }
    }
}