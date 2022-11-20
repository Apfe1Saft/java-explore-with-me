package ru.practicum.explorewithme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StatisticApp {
    static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(StatisticApp.class, args);

    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }

    public static void stop() {
        context.close();
    }

}
