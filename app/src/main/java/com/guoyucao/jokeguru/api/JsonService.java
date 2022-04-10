package com.guoyucao.jokeguru.api;

import static android.text.TextUtils.indexOf;
import static android.text.TextUtils.substring;
import android.text.TextUtils;

import com.guoyucao.jokeguru.Joke;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonService {

    public ArrayList<Joke> getJokesFromJSON(String json)  {
        ArrayList<Joke> arrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("jokes");
            for (int i = 0 ; i < jsonArray.length();i++){
                String id = jsonArray.getJSONObject(i).getString("id");
                String category = jsonArray.getJSONObject(i).getString("category");
                String joke = jsonArray.getJSONObject(i).getString("joke");
                arrayList.add(new Joke(id,category,joke));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }


}
