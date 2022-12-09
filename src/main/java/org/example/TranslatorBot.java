package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import org.example.model.Operable;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TranslatorBot extends TelegramLongPollingBot {


    @Override
    public String getBotUsername() {
        return "farzinabot";
    }

    @Override
    public String getBotToken() {
        return "5710118478:AAHJCl03Eyht3FhJgZuKouGQt-kiu8SWCPE";
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {


        String myLang = "";

        String myWord = "";

        Jedis jedis = new Jedis();
        String RuEnFlags = "\uD83C\uDDF7\uD83C\uDDFA \uD83C\uDDEC\uD83C\uDDE7 ";
        String RuTrFlags = "\uD83C\uDDF7\uD83C\uDDFA \uD83C\uDDF9\uD83C\uDDF7";

        Long chatId = update.getMessage().getChatId();
        Message message = update.getMessage();
        String userName = message.getChat().getFirstName();




        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        if (update.hasMessage()) {
            if (message.getText().equals("/start")) {
                sendMessage.setText("Привет " + userName + " \uD83D\uDE0A");
                execute(sendMessage);
                Thread.sleep(1000);
                sendMessage.setText("Этот бот может переводить слова с Русского на Английский и Турецкий");
                execute(sendMessage);
                Thread.sleep(1200);
                sendMessage.setText("Пожалуйста напишите слова на Русском языке");
                execute(sendMessage);


            } else {

                myWord = message.getText();

                Operable.myDictionary(Operable.addLanguages(71), myWord, RuEnFlags, sendMessage);
                System.out.println(userName+"|"+myWord);
                execute(sendMessage);

                Operable.myDictionary(Operable.addLanguages(90), myWord, RuTrFlags, sendMessage);
                execute(sendMessage);
            }
        } else {

        }

        SendMessage sendMeMsg = new SendMessage();
        sendMeMsg.setChatId(370898987L);
        sendMeMsg.setText("@"+userName + " зашел телеграм бот");
        execute(sendMeMsg);


    }


}
