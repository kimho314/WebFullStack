package com.example.troubleshootingjava.ch7_2.repository;

import com.example.troubleshootingjava.ch7_2.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryV2 {
    private final DataSource dataSource;

    public ProductRepositoryV2(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Product findProduct(int id) throws SQLException {
        String sql = "SELECT * FROM product WHERE id = ?";

        Connection con = dataSource.getConnection(); // db connection을 열었짐나 닫지를 않아서 문제를 발생시킨다. try-with-resources문으로 합쳐서 connection이 자동으로 닫도록 해야한다.
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();

            if(result.next()){
                Product p = new Product(result.getInt("id"), result.getString("name"));
                return p;
            }
        }
        return null;
    }
}
