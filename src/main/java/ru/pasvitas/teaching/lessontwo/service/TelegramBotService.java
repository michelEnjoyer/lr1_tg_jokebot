package ru.pasvitas.teaching.lessontwo.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.AnswerCallbackQuery;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pasvitas.teaching.lessontwo.model.Joke;


import java.util.List;
import java.util.Random;
@Service
public class TelegramBotService {

    private final TelegramBot telegramBot;
    private final JokeService jokeService;

    @Autowired
    public TelegramBotService(TelegramBot telegramBot, JokeService jokeService) {
        this.telegramBot = telegramBot;
        this.jokeService = jokeService;
        this.telegramBot.setUpdatesListener(updates -> {
            updates.forEach(this::processUpdate);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }, Throwable::printStackTrace);
    }

    private void processUpdate(Update update) {
        if (update.message() != null && update.message().text() != null && update.message().text().equals("/start")) {
            sendStartMessage(update.message().chat().id());
        } else if (update.callbackQuery() != null) {
            handleCallbackQuery(update.callbackQuery());
        }
    }
    //изменение 1
    private void sendStartMessage(Long chatId) {
        SendMessage request = new SendMessage(chatId, "Привет! Я могу отправить тебе случайную шутку.");
        request.replyMarkup(new InlineKeyboardMarkup(new InlineKeyboardButton("Получить шутку").callbackData("/joke")));
        this.telegramBot.execute(request);
    }

    private void handleCallbackQuery(com.pengrad.telegrambot.model.CallbackQuery callbackQuery) {
        String data = callbackQuery.data();
        if ("/joke".equals(data)) {
            sendRandomJoke(callbackQuery.message().chat().id());
            telegramBot.execute(new AnswerCallbackQuery(callbackQuery.id()));
        }
    }

    private void sendRandomJoke(Long chatId) {
        List<Joke> jokes = jokeService.getAllJokes();
        if (!jokes.isEmpty()) {
            Joke randomJoke = jokes.get(new Random().nextInt(jokes.size()));
            SendMessage request = new SendMessage(chatId, randomJoke.getTextJoke());
            this.telegramBot.execute(request);
        } else {
            SendMessage request = new SendMessage(chatId, "К сожалению, нет доступных шуток в данный момент.");
            this.telegramBot.execute(request);
        }
    }
}