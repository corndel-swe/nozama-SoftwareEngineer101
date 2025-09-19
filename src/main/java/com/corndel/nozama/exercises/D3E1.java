package com.corndel.nozama.exercises;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class D3E1 {
  // This is our counter:
  public static Counter counter = new Counter();

  /**
   * Creates a Javalin app with two endpoints.
   *
   * @see https://tech-docs.corndel.com/javalin/routing.html
   * @return a configured Javalin instance
   */
  public static Javalin createApp() {
    var app = Javalin.create();
    app.get("/counter", CounterController::getCounter);
    app.put("/counter/increment", CounterController::increment);
    return app;
  }
}

class CounterController {
  /**
   * Responds with the current counter as a JSON object, e.g. { "count": 3 }.
   */
  public static void getCounter(Context ctx) {
    // TODO
  }

  /**
   * Increases the counter by 1 and then responds with the count.
   */
  public static void increment(Context ctx) {
    // TODO
  }
}

class Counter {
  private int count;

  public Counter() {
    count = 0;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }
}
