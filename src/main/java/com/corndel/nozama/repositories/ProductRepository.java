package com.corndel.nozama.repositories;

import com.corndel.nozama.DB;
import com.corndel.nozama.models.Product;
import java.sql.SQLException;

public class ProductRepository {
    public static String addProduct(String name, String description, float price, int stock, String url) throws SQLException {
        String json = "{ \"name\" :" + name + ",\"description\" :" + description + ",\"price\" :" + price + ",\"stockQuantity\" :" + stock + ",\"imageURL\" :" + url + "}";

        var query = "INSERT INTO products (name,description,price,stockQuantity, imageURL) VALUES(" + name + "," + description + "," + price + "," + stock + "," + url + ")";

        return query;
    }


    public static Product findById(int id) throws SQLException {

        var querySingleID = "SELECT * FROM users WHERE id =" + id;

        try (var con = DB.getConnection();
             var stmt = con.createStatement();
             var rs = stmt.executeQuery(querySingleID);) {

            var name = rs.getString("name");
            var description = rs.getString("description");
            var price = rs.getFloat("price");
            var stock = rs.getInt("stockQuantity");
            var url = rs.getString("imageURL");

            return new Product(id,name,description,price,stock,url);

        }

    }

};
