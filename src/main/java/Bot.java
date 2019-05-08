import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

public class Bot extends TelegramLongPollingBot {

    public Bot(DefaultBotOptions options) {
        super(options);

    }

    /**
     * Метод для приема сообщений.
     *
     * @param update Содержит сообщение от пользователя.
     */

    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        if (Objects.equals(message, "Кокес не курит") || Objects.equals(message, "/cokes"))
            sendCokesSmokingInfo(update.getMessage().getChatId().toString());
        else {
            sendMsg(update.getMessage().getChatId().toString(), message);
        }

    }

    /**
     * Метод для настройки сообщения и его отправки.
     *
     * @param chatId id чата
     * @param s      Строка, которую необходимот отправить в качестве сообщения.
     */

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);

        try {
            setButtons(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
        }
    }

    public synchronized void sendCokesSmokingInfo(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(getNonSmokingTime());
        try {
            setButtons(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
//            log.log(Level.SEVERE, "Exception: ", e.toString());
        }
    }

    private String getNonSmokingTime() {
        Date now = new Date();
        Date last = new GregorianCalendar(2018, Calendar.SEPTEMBER, 26).getTime();

        double noSmokingTime = (now.getTime() - last.getTime()) / (1000.0 * 60 * 60 * 24);
        double cigCost = (115 + (107.25 + 143) / 2) / 2;
        double startingCosts = 2990 + 1990 + 390 * 2 + 100;
        double zhizhCosts = 500;
        double tempedCosts = 650 + 200 + 270 + 690 + 200 + 200 + 250 + 250 + 100 + 300;
        double savedMoney = noSmokingTime * cigCost * 1.25; // Модификатор на 1.15 пачки в день
        double totalSavings = (savedMoney - startingCosts - tempedCosts - zhizhCosts * (noSmokingTime / 30));
        double daySavings = (savedMoney - startingCosts - tempedCosts - zhizhCosts * (noSmokingTime / 30)) / noSmokingTime;


        return String.format("\uD83D\uDEAD" + " Кокес не курит: %d дней%n" +
                          " Не выкурено сигарет ~ %d%n" +
                          " Экономия только на сигах ~ %.2f руб.%n" +
                        " Стартовые расходы ~ %.2f руб.%n" +
                        " Расходы на жыжу в месяц ~ %d руб./мес.%n" +
                         " Расходы на всякую поебень ~ %.2f руб.%n" +
                        " Экономия общая ~ %.2f руб.%n" +
                         "Экономия в день ~ %.2f руб.%n" +
                        "Экономия в час ~ %.2f руб.%n" +
                         "Экономия в минуту ~ %.2f руб.%n" +
                        "Экономия в секунду ~ %.5f руб.",
                Math.round(noSmokingTime),
                Math.round(noSmokingTime * 20 * 1.25),
                savedMoney,
                startingCosts,
                Math.round(zhizhCosts),
                tempedCosts,
                totalSavings,
                daySavings,
                (daySavings / 24),
                (daySavings / (24 * 60)),
                (daySavings / (24 * 60 * 60)));


//        console.log("Экономия в день ~" + daySavings.toFixed(2) + " руб.");
//        console.log("Экономия в час ~" + (daySavings / 24).toFixed(2) + " руб.");
//        console.log(
//                "Экономия в минуту ~" + (daySavings / (24 * 60)).toFixed(2) + " руб."
//        );
//        console.log(
//                "Экономия в секунду ~" + (daySavings / (24 * 60 * 60)).toFixed(5) + " руб."
//        );*/


    }

    /**
     * Метод возвращает имя бота, указанное при регистрации.
     *
     * @return имя бота
     */

    public String getBotUsername() {
        return "@DrundushkaBot";
    }

    /**
     * Метод возвращает token бота для связи с сервером Telegram
     *
     * @return token для бота
     */

    public String getBotToken() {
        return "890734954:AAFGZo9QUGIb0upBpPKnFC-dEv3aWvLGpnA";
    }

    public synchronized void setButtons(SendMessage sendMessage) {
        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("Кокес не курит"));

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
    }
}

