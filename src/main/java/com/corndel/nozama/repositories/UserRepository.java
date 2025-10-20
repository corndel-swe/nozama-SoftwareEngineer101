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

public class UserRepository {
  public static List<User> findAll() throws SQLException {
    var query = "SELECT id, username, firstName, lastName, email, avatar FROM users";

    try (var con = DB.getConnection();
        var stmt = con.createStatement();
        var rs = stmt.executeQuery(query)) {

      var users = new ArrayList<User>();
      while (rs.next()) {
        var id = rs.getInt("id");
        var username = rs.getString("username");
        var firstName = rs.getString("firstName");
        var lastName = rs.getString("lastName");
        var email = rs.getString("email");
        var avatar = rs.getString("avatar");

        users.add(new User(id, username, firstName, lastName, email, avatar));
      }
      return users;
    }
  };

  public static User findById(int id) throws SQLException {
    // TODO: finish this method
    var query = "SELECT id, username, firstName, lastName, email, avatar FROM users WHERE id ="+id;

    try (var con = DB.getConnection();
         var stmt = con.createStatement();
         var rs = stmt.executeQuery(query);) {


        var username = rs.getString("username");
        var firstName = rs.getString("firstName");
        var lastName = rs.getString("lastName");
        var email = rs.getString("email");
        var avatar = rs.getString("avatar");


      return new User(id, username, firstName, lastName, email, avatar);
    }
  }


    public static User createUser(User user) throws SQLException {
        var query = "INSERT INTO users (username,firstName, lastName, email, avatar, password) VALUES (?,?,?,?,?,?) RETURNING id";

        try (Connection connection = DB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getAvatar());
            preparedStatement.setString(6, user.getPassword());

            System.out.println(preparedStatement);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                user.setId(rs.getInt("id"));
                System.out.println(user);
                return user;
            }
        }

    }

    //DELETE USER
    public static void deleteUser(User user) throws SQLException {
        var query = "DELETE * FROM users WHERE users.id = ?";

        try (Connection connection = DB.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {

            {
                preparedStatement.setInt(0, user.getId());
                preparedStatement.executeUpdate();


            }

        }
  }
}



