package org.example.servertgbot;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BotRestController {

    /*@PostMapping("/callback/update")
    public BotApiMethod<?> receiveUpdate(@RequestBody Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());
        message.setText("Извините, бот сейчас в ремонте");
        return message;
    }*/

    @PostMapping("/")
    public String post(@RequestBody String update) {
        return "Метод POST!";
    }
    @GetMapping("/")
    public String get() {
        return "Метод GET!";
    }
}
