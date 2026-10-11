package org.example;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
/** Бот, работающий в режиме long polling
 * Класс реализует интерфейс LongPollingSingleThreadUpdateConsumer,
 * Каждое входящее текстовое сообщение бот пересылает обратно пользователю
 */
public class Functions implements LongPollingSingleThreadUpdateConsumer {
    /** Клиент для отправки запросов в Telegram Bot  */
    private final TelegramClient telegramClient;
    /**
     * Создаёт бота и инициализирует клиента для работы с Telegram Bot
     * @param botToken
     */
    public Functions(String botToken){
        telegramClient = new OkHttpTelegramClient(botToken);

    }
    /**Формирует текст ответа на основе входящего сообщения
     * @param  inputText текст входящего сообщения от пользователя
     * @return текст ответа
     */
    public String writeMessage(String inputText){
        return inputText;
    }
    /**
     * Обрабатывает входящее обновление от Telegram
     * @param update объект обновления, полученный от Telegram Bot
     */
    @Override
    public void consume(Update update){

        if (update.hasMessage() && update.getMessage().hasText()){

            String messageText = update.getMessage().getText();
            long chatID = update.getMessage().getChatId();

            SendMessage message = SendMessage
                    .builder()
                    .chatId(chatID)
                    .text(writeMessage(messageText))
                    .build();

            try{
                telegramClient.execute(message);
            } catch(TelegramApiException e){
                e.printStackTrace();
            }
        }
    }
}
