package org.example.servertgbot;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.configuration.TelegramBotConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({TelegramBotConfig.class})
public class ServerTGbotApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
        SpringApplication.run(ServerTGbotApplication.class, args);
    }

}
