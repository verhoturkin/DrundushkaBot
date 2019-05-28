package ru.drundushka.controller;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.drundushka.service.KeyboardService;
import ru.drundushka.service.NonSmokingService;

import java.util.Objects;

public class BotController {
    private final NonSmokingService nonSmokingService;
    private final KeyboardService keyboardService;

    public BotController() {
        nonSmokingService = new NonSmokingService();
        keyboardService = new KeyboardService();
    }

    public SendMessage processUpdate(Update update) {
        String message = update.getMessage().getText();
        if (Objects.equals(message, "Кокес не курит") || Objects.equals(message, "/cokes"))
            return sendCokesSmokingInfo(update.getMessage().getChatId().toString());
        else {
            return sendMsg(update.getMessage().getChatId().toString(), message);
        }
    }

    /**
     * Метод для настройки сообщения и его отправки.
     *
     * @param chatId id чата
     * @param s      Строка, которую необходимот отправить в качестве сообщения.
     */

    private synchronized SendMessage sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        keyboardService.setButtons(sendMessage);
        return sendMessage;
    }

    private synchronized SendMessage sendCokesSmokingInfo(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(nonSmokingService.getNonSmokingTime());
        keyboardService.setButtons(sendMessage);
        return sendMessage;
    }

}
