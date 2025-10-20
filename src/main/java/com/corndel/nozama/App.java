package com.corndel.nozama;

import com.corndel.nozama.models.Product;
import com.corndel.nozama.models.User;
import com.corndel.nozama.repositories.ProductRepository;
import com.corndel.nozama.repositories.UserRepository;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

import java.sql.SQLException;


public class App {
  private Javalin app;

  public static void main(String[] args) {
    var app = new App().javalinApp();
    app.start(8080);
  }

  public App() {
    app = Javalin.create();
    app.get(
        "/",
        ctx -> {
          var users = UserRepository.findAll();
          ctx.json(users);
        });
    app.get(
        "/users/{userId}",
        ctx -> {
          var id = Integer.parseInt(ctx.pathParam("userId"));
          var user = UserRepository.findById(id);
          ctx.status(HttpStatus.IM_A_TEAPOT).json(user);
        });


    app.post("/users",
            ctx ->{
                try {
                    User body = ctx.bodyAsClass(User.class);
                    User user = UserRepository.createUser(body);
                    ctx.status(HttpStatus.ACCEPTED);
                    ctx.json(user);
                } catch (SQLException e){
                    ctx.status(500).json("Database error: " + e.getMessage());
                }
            });
//DELETE A USER
    app.post("/users/{userId}", ctx -> {
        try {
        User body = ctx.bodyAsClass(User.class);
        UserRepository.deleteUser(body);
        ctx.json(body);
        System.out.println("User has been deleted");
        } catch (Exception e) {
            throw new RuntimeException(e);}
        });

    //GET PRODUCT ID
    app.get("/products/{productId}", ctx-> {
        var id = Integer.parseInt(ctx.pathParam("productId"));
        var productId = ProductRepository.findById(id);
        ctx.json(String.valueOf(productId));});

    //GET PRODUCTS
    app.get("/products", ctx-> {
        ctx.json(ProductRepository.findAll());

    });

    //POST PRODUCTS
      app.post("/products", ctx -> {
          try {
              Product body = ctx.bodyAsClass(Product.class);
              Product product = ProductRepository.addProduct(body);
              ctx.status(HttpStatus.ACCEPTED);
              ctx.json(product);
          } catch (SQLException e){
              ctx.status(500).json("Database error: " + e.getMessage());
          }

      });
      app.get("/products/category/{categoryId}", ctx ->{
          var id = ctx.pathParam("categoryId");
          var category = ProductRepository.findByCategory(Integer.parseInt(id));
          ctx.status(HttpStatus.ACCEPTED).json(category);

      });
  };

  public Javalin javalinApp() {
    return app;
  }
}
