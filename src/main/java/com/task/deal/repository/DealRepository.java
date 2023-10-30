package com.task.deal.repository;

import com.task.deal.pojo.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Repository
public class DealRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(DealRepository.class);


    public void createDeal(Deal deal) {
        try {
        String sql = "INSERT INTO deals (dealUniqueId, fromCurrencyISOCode, toCurrencyISOCode, dealTimestamp, dealAmountInOrderingCurrency) VALUES (?, ?, ?, ?, ?)";

            int rowsAffected = jdbcTemplate.update(sql, deal.getDealUniqueId(), deal.getFromCurrencyISOCode(), deal.getToCurrencyISOCode(), deal.getDealTimestamp(), deal.getDealAmountInOrderingCurrency());

            if (rowsAffected > 0) {
                logger.info("Data inserted successfully for dealUniqueId: {}", deal.getDealUniqueId());
            } else {
                logger.error("Failed to insert data for dealUniqueId: {}", deal.getDealUniqueId());
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
            logger.error("An error occurred while checking the uniqueness of dealUniqueId: {}", dealUniqueId, e);
            return false;
        }
    }
}