package com.digitalinnovationone.integrationrestapispringweb.client;

import com.digitalinnovationone.integrationrestapispringweb.dto.MessageSend;
import com.digitalinnovationone.integrationrestapispringweb.dto.ResultBotTelegram;
import com.digitalinnovationone.integrationrestapispringweb.dto.ResultBotTelegramList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class JavaHttpClient {

    @Value("${telegram.api}")
    private String BASE_PATH;
    @Value("${telegram.token}")
    private String TOKEN;

    private HttpClient httpClient;
    private ObjectMapper objectMapper;

    public JavaHttpClient(ObjectMapper objectMapper) {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = objectMapper;
    }

    public ResultBotTelegramList findUpdates() {
        ResultBotTelegramList resultBotTelegramList = null;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_PATH + TOKEN + "/getUpdates"))
                .GET()
                .build();

        HttpResponse<String> stringHttpResponse = sendRequest(request);

        try {
            resultBotTelegramList = objectMapper.readValue(stringHttpResponse.body(), ResultBotTelegramList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultBotTelegramList;
    }

    public ResultBotTelegram sendMessage(MessageSend msg) {
        ResultBotTelegram resultBotTelegram = null;

        String message = null;
        try {
            message = objectMapper.writeValueAsString(msg);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_PATH + TOKEN + "/sendMessage"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(message))
                .build();

        HttpResponse<String> stringHttpResponse = sendRequest(request);

        try {
            resultBotTelegram = objectMapper.readValue(stringHttpResponse.body(), ResultBotTelegram.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultBotTelegram;
    }

    private HttpResponse<String> sendRequest(HttpRequest request) {
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return httpResponse;
    }

}
