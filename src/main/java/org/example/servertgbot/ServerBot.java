package org.example.servertgbot;

import lombok.extern.log4j.Log4j2;
import org.example.configuration.TelegramBotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Log4j2
@Component
public class ServerBot extends TelegramLongPollingBot {

    TelegramBotConfig config;

    public ServerBot(TelegramBotConfig config) {
        super(config.getToken());
        this.config = config;
        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(this);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String getBotUsername() {
        return "MustHaveCase_bot";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            executeMessage(update.getMessage().getChatId());
        }
        if (update.hasCallbackQuery()) {
           executeMessage(update.getCallbackQuery().getMessage().getChatId());
        }
    }


    private void executeMessage(Long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Сейчас мне делают апргрейд \uD83D\uDCAA⚙\uFE0F, напишите чуть позже \uD83D\uDE43");
        message.setReplyMarkup(new ReplyKeyboardRemove(true));
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(String.format("НЕ УДАЛОСЬ ОТПРАВИТЬ СООБЩЕНИЕ ПОЛЬЗОВАТЕЛЮ %s, ПО ПРИЧИНЕ - %s",chatId, e.getMessage()));
        }
    }
}
