package com.corndel.nozama.exercises;

import com.corndel.nozama.repositories.UserRepository;
import io.javalin.Javalin;

public class D2E2 {
  /**
   * Creates a Javalin app with two endpoints:
   * 
   * - GET /sumup responds with the sum of the integers from 1 to n, where n is
   * provided as a query parameter. If n is not provided, it responds with 0.
   * 
   * - GET /multiply/{x}/{y} responds with the product of x and y.
   * 
   * @see https://tech-docs.corndel.com/javalin/query-params.html
   * @see https://tech-docs.corndel.com/javalin/url-params.html
   * @return a configured Javalin instance
   */
  public static Javalin createApp() {
    var app = Javalin.create();
    app.get(
        "/sumup",
        ctx -> {
            // TODO:
            int total = 0;
            var range = Integer.parseInt(ctx.queryParam("n"));
            for(int i = 0; i <= range; i++){
                total += i;}
            ctx.result(String.valueOf(total));
        });

    app.get(
        "/multiply/{x}/{y}",
        ctx -> {
          // TODO
            var x = Integer.parseInt(ctx.pathParam("x"));
            var y = Integer.parseInt(ctx.pathParam("y"));
            ctx.result(String.valueOf(Math.multiplyFull(x,y)));
        });

    return app;
  }

    public static void main(String[] args) {
        createApp().start(8080);
    }

}
