package org.example.servertgbot;

import org.example.configuration.TelegramBotConfig;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class ServerBot extends TelegramWebhookBot {
    private final TelegramBotConfig config;
    private final BotRestController restController;
    public ServerBot(TelegramBotConfig config, BotRestController restController) {
        super(config.getToken());
        this.config = config;
        this.restController = restController;
        try {
            //ДЛЯ LONGPOLLING
            //TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            //api.registerBot(this);
            //ДЛЯ WEBHOOK
            SetWebhook setWebhook = SetWebhook.builder().url(config.getUrl()).build();
            this.setWebhook(setWebhook);

        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return restController.receiveUpdate(update);
    }

    @Override
    public String getBotPath() {
        return "/update";
    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }
}
