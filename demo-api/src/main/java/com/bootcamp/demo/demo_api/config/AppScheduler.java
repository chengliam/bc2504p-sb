package com.bootcamp.demo.demo_api.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// ! @EnableScheduling -> Spring Manager find a bean with @Scheduled method
@Component
public class AppScheduler {
  // @Scheduled(fixedDelay = 1000) // 5000ms = 5seconds
  // public void sayHello() throws InterruptedException {
  // long startTime = System.currentTimeMillis(); // unix time
  // Thread.sleep(3000L);
  // System.out.println("Hello world!!! startTime:" + startTime);
  // }

  // @Scheduled(fixedRate = 2000)
  // public void sayGoodbye() throws InterruptedException {
  // long startTime = System.currentTimeMillis(); // unix time
  // Thread.sleep(3000L);
  // System.out.println("Goodbye!!! startTime:" + startTime);
  // }


  @Scheduled(cron = "0 43 21 * * 1#2") // Run every weekday at 12 PM and 6 PM.
  public void runTask() {
    System.out.println("Testing cron job");
  }

  public static void main(String[] args) {
    String x = "abc";

    Double d1 = Double.parseDouble(x);


  }
}