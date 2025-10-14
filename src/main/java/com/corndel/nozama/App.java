package com.corndel.nozama;

import com.corndel.nozama.models.Product;
import com.corndel.nozama.repositories.ProductRepository;
import com.corndel.nozama.repositories.UserRepository;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;


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
          Product body = ctx.bodyAsClass(Product.class);
          Product product = ProductRepository.addProduct(body.getName(),body.getDescription(),body.getPrice(), body.getStockQuantity(), body.getImageURL());
          ctx.status(HttpStatus.ACCEPTED);
          ctx.json(product);

      });
  };

  public Javalin javalinApp() {
    return app;
  }
}
