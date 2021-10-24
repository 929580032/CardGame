package com.xwh;

import com.alibaba.fastjson.JSONObject;
import com.xwh.game.Card;
import com.xwh.game.PvpMain;
import okhttp3.*;

import java.io.IOException;

public class NetworkInterfaceUtils {
    private static OkHttpClient client = new OkHttpClient().newBuilder().build();

    public static String login(String student_id, String password) {
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("student_id",student_id)
                .addFormDataPart("password",password)
                .build();
        Request request = new Request.Builder()
                .url("http://172.17.173.97:8080/api/user/login")
                .method("POST", body)
                .build();
        JSONObject jsonObject = null;
        try {
            Response response = client.newCall(request).execute();
            jsonObject = JSONObject.parseObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject data = jsonObject.getJSONObject("data");
        return data.getString("token");
    }

    public static String createGame(String token, boolean isPrivate) {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"private\": " + isPrivate +"\r\n}");
        Request request = new Request.Builder()
                .url("http://172.17.173.97:9000/api/game")
                .method("POST", body)
                .addHeader("Authorization", token)
                .addHeader("Content-Type", "application/json")
                .build();
        JSONObject jsonObject = null;
        try {
            Response response = client.newCall(request).execute();
            jsonObject = JSONObject.parseObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject data = jsonObject.getJSONObject("data");
        return data.getString("uuid");
    }

    public static void joinGame(String token, String uuid) {
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("http://172.17.173.97:9000/api/game/" + uuid)
                .method("POST", body)
                .addHeader("Authorization", token)
                .build();
        try {
            Response response = client.newCall(request).execute();
//            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getLastOperation(String token, String uuid) {
        Request request = new Request.Builder()
                .url("http://172.17.173.97:9000/api/game/" + uuid + "/last")
                .method("GET", null)
                .addHeader("Authorization", token)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Card doPlayerOperation(String token, String uuid, PvpMain pvpMain) {
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("http://172.17.173.97:9000/api/game/" + uuid)
                .method("PUT", body)
                .addHeader("Authorization", token)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String jsonString = response.body().string();
//            System.out.println(jsonString);
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            JSONObject data = jsonObject.getJSONObject("data");
            String last_code = data.getString("last_code");
            String[] s = last_code.split(" ");
            return new Card(pvpMain, s[2], true, pvpMain.cardsPlacement);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void doPlayerOperation(String token, String uuid, String cardName) {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"type\": 1,\r\n  \"card\": \"" + cardName + "\"\r\n}");
        Request request = new Request.Builder()
                .url("http://172.17.173.97:9000/api/game/" + uuid)
                .method("PUT", body)
                .addHeader("Authorization", token)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
//            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
