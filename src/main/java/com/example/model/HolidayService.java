package com.example.model;

import com.example.nextpublicholiday.Holiday;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class HolidayService {
    private static final String API_URL = "https://date.nager.at/api/v3/NextPublicHolidays/CA";
    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;

    public HolidayService() {
        this.httpClient = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public List<Holiday> fetchNextPublicHolidays(String date) {
        // You can append the date parameter to the API URL or use it as needed
        String apiUrlWithDate = API_URL + "?date=" + date;

        Request request = new Request.Builder()
                .url(apiUrlWithDate)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            String responseBody = response.body().string();
            return parseJsonResponse(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
            return List.of(); // Handle exceptions properly
        }
    }

    private List<Holiday> parseJsonResponse(String responseBody) throws IOException {
        return objectMapper.readValue(responseBody, new TypeReference<List<Holiday>>() {});
    }
}
