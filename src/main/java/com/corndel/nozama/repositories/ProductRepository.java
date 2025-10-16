package com.corndel.nozama.repositories;

import com.corndel.nozama.DB;
import com.corndel.nozama.models.Product;
import com.corndel.nozama.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public static Product addProduct(Product product) throws SQLException {
        //String json = "{ \"name\" :" + name + ",\"description\" :" + description + ",\"price\" :" + price + ",\"stockQuantity\" :" + stock + ",\"imageURL\" :" + url + "}";
        // String name, String description, float price, int stock, String url
//        var query = "INSERT INTO products (name,description,price,stockQuantity, imageURL) VALUES(" + name + "," + description + "," + price + "," + stock + "," + url + ")";
        var query = "INSERT INTO products (name,description,price,stockQuantity, imageURL) VALUES (?,?,?,?,?) RETURNING id";

        try (Connection connection = DB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query))


        { preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setFloat(3, product.getPrice());
            preparedStatement.setInt(4, product.getStockQuantity());
            preparedStatement.setString(5, product.getImageURL());

            System.out.println(preparedStatement);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                product.setId(rs.getInt("id"));
                System.out.println(product);
                return product;
            }
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

        var querySingleID = "SELECT * FROM products WHERE id =" + id;

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

    public static List<Product> findByCategory(int categoryId) throws SQLException {
        var query = "SELECT products.id, products.name, products.description, products.price, products.stockQuantity, products.imageURL FROM products " +
                "INNER JOIN product_categories ON product_categories.productId = products.id " +
                "INNER JOIN categories ON categories.id = product_categories.categoryId " +
                "WHERE product_categories.categoryId =" + categoryId;

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



    public static void main (String[] args){
        Product producttoAdd = new Product("name", "desc","url", 200.5f, 100);
        try {
            Product addedProduct = addProduct(producttoAdd);
            System.out.println(addedProduct);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

}
