package com.project.alertcheker.Controller;

import com.project.alertcheker.Entity.AlertData;
import com.project.alertcheker.Enum.TelegramMessage;
import com.project.alertcheker.Service.AlertService;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Getter
@Component
@Slf4j
public class TelegramController extends TelegramLongPollingBot {
    private Message requestMessage = new Message();
    private final SendMessage response = new SendMessage();
    private final AlertService alertService;
    private final String botUsername;
    private final String botToken;
    public TelegramController(
            @Value("${telegram-bot.name}") String botUsername,
            @Value("${telegram-bot.token}") String botToken,
            AlertService service) throws TelegramApiException {
        this.botUsername = botUsername;
        this.botToken = botToken;
        this.alertService = service;
        log.info("Telegram bot created");
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
    @SneakyThrows
    public void onUpdateReceived(Update request) {
        log.info("Update recieved");
        requestMessage = request.getMessage();
        response.setChatId(requestMessage.getChatId().toString());

        if (request.hasMessage() && requestMessage.hasText()) {
            log.info("Working onUpdateReceived, request text[{}]", request.getMessage().getText());

            if (requestMessage.getText().equals("/start")) {
                try {
                    log.info("Start Command");
                    response.setText(TelegramMessage.START_MESSAGE.getMessage());
                    execute(response);
                    log.info("START OK");
                }catch (TelegramApiException e) {
                    log.error("Get {0} exception", e);
                    response.setText(TelegramMessage.ERROR_MESSAGE.getMessage());
                    execute(response);
                    throw new RuntimeException(e);
                }
            }
            else {
                try {
                    List<AlertData> entity = alertService.getAlertData(requestMessage.getText());
                    response.setText(alertService.parseListData(entity));
                    log.info("Working, text[{}]", response.getText());
                    execute(response);
                    log.info("OK");
                } catch (Exception e) {
                    log.error("Get {0} exception", e);
                    response.setText(TelegramMessage.ERROR_MESSAGE.getMessage());
                    execute(response);
                    throw new RuntimeException(e);
                }
            }
        }
    }
}