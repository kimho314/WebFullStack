package com.example.troubleshootingjava.ch7_2.repository.mapper;

import com.example.troubleshootingjava.ch7_2.model.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.tree.TreePath;
import org.springframework.jdbc.core.RowMapper;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Product(rs.getInt("id"), rs.getString("name"));
    }
}
