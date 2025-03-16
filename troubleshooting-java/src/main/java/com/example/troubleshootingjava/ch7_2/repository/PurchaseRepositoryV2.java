package com.example.troubleshootingjava.ch7_2.repository;

import com.example.troubleshootingjava.ch7_2.model.Purchase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;

@Repository
public class PurchaseRepositoryV2 {
    private final DataSource dataSource;

    public PurchaseRepositoryV2(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Purchase> findAll() throws SQLException {
        List<Purchase> list = new ArrayList<>();
        String sql = "SELECT * FROM purchase";

        try(Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet result = ps.executeQuery();

            while(result.next()){
                Purchase p = new Purchase(result.getInt("id"), result.getInt("product"), result.getBigDecimal("price"));
                list.add(p);
            }
            return list;
        }
    }
}
