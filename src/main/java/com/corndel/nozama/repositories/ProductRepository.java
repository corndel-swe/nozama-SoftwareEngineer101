package com.corndel.nozama.repositories;

import java.sql.SQLException;

public class ProductRepository {
    public static String  addProduct (String name, String description, float price, int stock, String url ) throws SQLException {
        String json = "{ \"name\" :" + name +",\"description\" :" + description + ",\"price\" :" + price + ",\"stockQuantity\" :" + stock + ",\"imageURL\" :" + url+"}";


        var query = "INSERT INTO products (name,description,price,stockQuantity, imageURL) VALUES("+name+ "," +description+ "," +price+ ","+stock+ "," +url+ ")";

        return query;





}
};
