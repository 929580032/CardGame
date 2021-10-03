package com.xwh;

import com.alibaba.fastjson.JSONObject;
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
        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"private\": " + Boolean.toString(isPrivate) +"\r\n}");
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
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
