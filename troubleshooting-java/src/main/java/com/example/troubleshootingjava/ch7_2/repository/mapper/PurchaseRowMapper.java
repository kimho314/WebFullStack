package com.example.troubleshootingjava.ch7_2.repository.mapper;

import com.example.troubleshootingjava.ch7_2.model.Purchase;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class PurchaseRowMapper implements RowMapper<Purchase> {

    @Override
    public Purchase mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Purchase(rs.getInt("id"), rs.getInt("product"), rs.getBigDecimal("price"));
    }
}
