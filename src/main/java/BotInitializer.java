import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class BotInitializer {

//    private static final String PROXY_HOST = "fckrkn2.teleproxy.me";
//    private static final int PROXY_PORT = 443;

    public static void main(String[] args) {
        try {

            ApiContextInitializer.init();

            TelegramBotsApi botsApi = new TelegramBotsApi();

            DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);

//            botOptions.setProxyHost(PROXY_HOST);
//            botOptions.setProxyPort(PROXY_PORT);
//            botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS4);

            botsApi.registerBot(new Bot(botOptions));

        } catch (TelegramApiRequestException e) {

        }
    }
}
