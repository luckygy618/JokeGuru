package com.guoyucao.jokeguru.api;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataService {

    public static ExecutorService networkExecutorService = Executors.newFixedThreadPool(4);
    public static Handler networkingHandler = new Handler(Looper.getMainLooper());
    public NetworkingListener listener;
    private final String baseUrl = "https://v2.jokeapi.dev/joke/";

    public void getAnyJokes() {
        String url = baseUrl + "Any?type=single&amount=10";
        connect(url);
    }

    public void getCustomJokes(String[] categories) {
        String url = baseUrl;
        for (int i = 0; i < categories.length; i++) {
            if (i < categories.length - 1) {
                url += categories[i];
                url += ",";
            }
        }
        url += "?type=single&amount=10";
        connect(url);
    }

    public void connect(String url) {
        networkExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;
                // any code here will run in background thread
                try {
                    String jsonString = "";
                    URL urlObject = new URL(url);
                    httpURLConnection = (HttpURLConnection) urlObject.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("Content-Type", "application/json");

                    InputStream in = httpURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);
                    int inputStreamData = 0;
                    while ((inputStreamData = reader.read()) != -1) {
                        char current = (char) inputStreamData;
                        jsonString += current;
                    } // json is ready
                    // I can send it to somewhere else to decode it

                    final String finalJsonString = jsonString;
                    networkingHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.dataListener(finalJsonString);
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    httpURLConnection.disconnect();
                }


            }
        });
    }


    public interface NetworkingListener {
        void dataListener(String jsonString);
    }

}
