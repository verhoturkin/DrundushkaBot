package ru.drundushka;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.drundushka.controller.BotController;
import ru.drundushka.service.NonSmokingService;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

public class Bot extends TelegramLongPollingBot {

    private final BotController controller;

    public Bot(DefaultBotOptions options) {
        super(options);
        this.controller = new BotController();
    }

    /**
     * Метод для приема сообщений.
     *
     * @param update Содержит сообщение от пользователя.
     */

    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = controller.processUpdate(update);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {

        }

    }

    /**
     * Метод возвращает имя бота, указанное при регистрации.
     *
     * @return имя бота
     */

    public String getBotUsername() {
        return "";
    }

    /**
     * Метод возвращает token бота для связи с сервером Telegram
     *
     * @return token для бота
     */

    public String getBotToken() {
        return "";
    }
}

