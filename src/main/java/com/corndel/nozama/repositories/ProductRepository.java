package com.corndel.nozama.repositories;

import com.corndel.nozama.DB;
import com.corndel.nozama.models.Product;
import com.corndel.nozama.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public static Product addProduct(String name, String description, float price, int stock, String url) throws SQLException {
        String json = "{ \"name\" :" + name + ",\"description\" :" + description + ",\"price\" :" + price + ",\"stockQuantity\" :" + stock + ",\"imageURL\" :" + url + "}";

//        var query = "INSERT INTO products (name,description,price,stockQuantity, imageURL) VALUES(" + name + "," + description + "," + price + "," + stock + "," + url + ")";
        var query = "INSERT INTO products (name,description,price,stockQuantity, imageURL) VALUES (?,?,?,?,?)";

        try (Connection connection = DB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query))



        { preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setFloat(3, price);
            preparedStatement.setInt(4, stock);
            preparedStatement.setString(5, url);

            try ( var rs = preparedStatement.executeQuery(query)) {

                var id = rs.getInt("id");
                return new Product(id, name, description, price, stock, url);}
            }

}


    public static List<Product> findAll() throws SQLException {
        var query = "SELECT id, name, description, price, stockQuantity, imageURL FROM products";
            try (var con = DB.getConnection();
                 var stmt = con.createStatement();
                 var rs = stmt.executeQuery(query)){

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

    public static List<Product> findByCategory(String categoryName) throws SQLException {
        var query = "SELECT id, name, description, price, stockQuantity, imageURL FROM products INNER JOIN product_categories ON product_categories.productId = products.id INNER JOIN categories ON categories.id = product_categories.categoryId WHERE categories.name =" + categoryName;

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
    }

};
