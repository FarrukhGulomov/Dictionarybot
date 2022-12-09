package org.example;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;
import org.apache.commons.pool2.proxy.CglibProxySource;
import org.example.model.DefItem;
import org.example.model.Operable;
import org.example.model.TrItem;
import org.example.model.YandexDictionary;

import org.json.JSONArray;
import org.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class ExampleMain {


    @SneakyThrows
    public static void main(String[] args) {
        Jedis jedis = new Jedis();

        jedis.set("Farruh", "20206840");
        jedis.clientSetname("farr");
        jedis.rename("Farruh", "Gulomov");


    }


}
