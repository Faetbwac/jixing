package jixing.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;

public class FindForecastAction {

    private String dealResponseResult(InputStream inputStream) {
        StringBuilder html = new StringBuilder(); // 存储处理结果
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            html.append(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return html.toString();
    }

    public JSONObject get_weather(String city_id) {
        try {
            URL url = new URL("http://t.weather.itboy.net/api/weather/city/" + city_id);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5 * 1000);
            connection.setReadTimeout(5 * 1000);
            connection.connect();
            // 获得服务器的响应码
            int response = connection.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                String html = dealResponseResult(inputStream);
                JSONObject json = JSONObject.parseObject(html);
                JSONObject data = json.getJSONObject("data");
                return data;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
