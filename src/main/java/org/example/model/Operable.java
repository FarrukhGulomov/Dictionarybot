package org.example.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

public class Operable {
    @SneakyThrows
    public static void myDictionary(String myLang, String myWord, String Flags, SendMessage sendMessage) {
        Jedis jedis = new Jedis();
        String myKey = "dict.1.1.20221129T184836Z.51ae82e09681170e.d6c9d27d86691b7f100ce65d1cb7abf0a768e915";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        URL url = new URL("https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key="
                + myKey + "&lang=" + myLang + "&text=" + myWord);

        URLConnection connection = url.openConnection();
        connection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = reader.readLine();
        String res = "";
        while (line != null) {
            res += line;
            line = reader.readLine();
        }

        JSONObject jsonObject = new JSONObject(res);
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);
        String allText = "";

        YandexDictionary[] yandexDictionaries = gson.fromJson(jsonArray.toString(), YandexDictionary[].class);
        for (YandexDictionary dictionary : yandexDictionaries) {
            for (DefItem defItem : dictionary.getDef()) {
                for (int i = 0; i < defItem.getTr().size(); i++) {
                    System.out.println(defItem.getTr().get(i).getText());
                    if ((defItem.getText()).toLowerCase().equals(myWord.toLowerCase())) {
                        allText += defItem.getTr().get(i).getText() + "|";
                    } else {
                        allText = "";
                        return;
                    }


                }
                for (TrItem trItem : defItem.getTr()) {
                    if (!allText.equals("")) {
                        jedis.set(myLang, allText);
                    }

                }
            }
        }


        if (!(jedis.get(myLang).equals(null)) && !allText.equals("")) {
            sendMessage.setText(Flags + jedis.get(myLang));

        } else {
            sendMessage.setText("Я не знаю этого слова на"+Flags);
            return;
        }


    }

    @SneakyThrows
    public static String addLanguages(int num) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Jedis jedis = new Jedis();
        String myKey = "dict.1.1.20221129T184836Z.51ae82e09681170e.d6c9d27d86691b7f100ce65d1cb7abf0a768e915";
        String myLang = "";
        URL langUrl = new URL("https://dictionary.yandex.net/api/v1/dicservice.json/getLangs?key=" + myKey);
        URLConnection urlConnection = langUrl.openConnection();

        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        String[] languages = gson.fromJson(reader, String[].class);

        for (int i = 0; i < languages.length; i++) {
            jedis.set(String.valueOf(i), languages[i]);
        }
        myLang = jedis.get(String.valueOf(num));   // 71 - 90 "ru-en"

        return myLang;
    }
}
