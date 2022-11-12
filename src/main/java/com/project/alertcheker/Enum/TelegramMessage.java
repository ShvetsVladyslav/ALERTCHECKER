package com.project.alertcheker.Enum;

public enum TelegramMessage {
    START_MESSAGE("\uD83D\uDC4B Привет, это бот для Регламента и Мониторинга, с помощью него можно будет узнать критичным ли являється сервис из алерта\n" +
            "Для проверки критичности - отправьте сообщение с URL алерта\n" +
            "Админ бота @Burnich"),
    ERROR_MESSAGE("Возникла непредвиденная ошибка\n" +
            "Обратитесь к админу @Burnich");

    private final String message;

    TelegramMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
