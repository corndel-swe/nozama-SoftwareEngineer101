package com.corndel.nozama.repositories;

import com.corndel.nozama.DB;
import com.corndel.nozama.models.Product;
import com.corndel.nozama.models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public static String  addProduct (String name, String description, float price, int stock, String url ) throws SQLException {
        String json = "{ \"name\" :" + name +",\"description\" :" + description + ",\"price\" :" + price + ",\"stockQuantity\" :" + stock + ",\"imageURL\" :" + url+"}";


        var query = "INSERT INTO products (name,description,price,stockQuantity, imageURL) VALUES("+name+ "," +description+ "," +price+ ","+stock+ "," +url+ ")";

        try (var con = DB.getConnection();
             var stmt = con.createStatement();
             var rs = stmt.executeQuery(query)){
            return rs.toString();
        }
}
    public static List<Product> findAll() throws SQLException {
        var query = "SELECT id, name, description, price, stockQuantity, imageURL FROM products";

        try (var con = DB.getConnection();
             var stmt = con.createStatement();
             var rs = stmt.executeQuery(query)) {

            var products = new ArrayList<Product>();
            while (rs.next()) {
                var id = rs.getInt("id");
                var name = rs.getString("name");
                var description = rs.getString("description");
                var price = rs.getFloat("price");
                var stockQuantity = rs.getInt("stockQuantity");
                var imageURL = rs.getString("imageURL");

                products.add(new Product(id, name, description, price, stockQuantity, imageURL));
            }
            return products;
        }
    };


};
