package com.project.alertcheker.Controller;

import com.project.alertcheker.Entity.AlertData;
import com.project.alertcheker.Service.AlertService;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
@Getter
public class TelegramController extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(TelegramController.class);
    private Message requestMessage = new Message();
    private final SendMessage response = new SendMessage();
    private final AlertService alertService;

    private final String botUsername;
    private final String botToken;

    public TelegramController(
            TelegramBotsApi telegramBotsApi,
            @Value("${telegram-bot.name}") String botUsername,
            @Value("${telegram-bot.token}") String botToken,
            AlertService service) throws TelegramApiException {
        this.botUsername = botUsername;
        this.botToken = botToken;
        this.alertService = service;

        telegramBotsApi.registerBot(this);
    }
    @Override
    public String getBotUsername() {
        return this.botUsername;
    }

    @Override
    public String getBotToken() {
        return this.botToken;
    }

    @Override
    public void onUpdateReceived(Update request) {
        requestMessage = request.getMessage();
        response.setChatId(requestMessage.getChatId().toString());

        if (request.hasMessage() && requestMessage.hasText()) {
            logger.info("Working onUpdateReceived, request text[{}]", request.getMessage().getText());


            if (requestMessage.getText().equals("/start")) {
                try {
                    logger.info("Start Command");
                    response.setText("Старт работы");
                    execute(response);
                    logger.info("START OK");
                }catch (TelegramApiException e) {
                    logger.error("Get {0} exception", e);
                    throw new RuntimeException(e);
                }
            }
            else {
                try {
                    AlertData entity = alertService.getData(requestMessage.getText());
                    response.setText(entity.toString());
                    logger.info("Working, text[{}]", response.getText());
                    execute(response);
                    logger.info("OK");
                } catch (Exception e) {
                    logger.error("Get {0} exception", e);
                    throw new RuntimeException(e);
                }
            }

        }
    }
}
