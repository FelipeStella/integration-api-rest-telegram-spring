package com.digitalinnovationone.integrationrestapispringweb.service;

import com.digitalinnovationone.integrationrestapispringweb.client.FeingClient;
import com.digitalinnovationone.integrationrestapispringweb.client.JavaHttpClient;
import com.digitalinnovationone.integrationrestapispringweb.client.RestTemplateClient;
import com.digitalinnovationone.integrationrestapispringweb.dto.MessageSend;
import com.digitalinnovationone.integrationrestapispringweb.dto.ResultBotTelegram;
import com.digitalinnovationone.integrationrestapispringweb.dto.ResultBotTelegramList;
import org.springframework.stereotype.Service;

@Service
public class TelegramService {

    private RestTemplateClient restTemplateClient;
    private FeingClient feingClient;
    private JavaHttpClient javaHttpClient;

    public TelegramService(RestTemplateClient restTemplateClient, FeingClient feingClient, JavaHttpClient javaHttpClient) {
        this.restTemplateClient = restTemplateClient;
        this.feingClient = feingClient;
        this.javaHttpClient = javaHttpClient;
    }

    public void sendMessage(MessageSend messageRequest) {
        //HTTP CLIENT
        ResultBotTelegram resultBotTelegramResponseEntity = javaHttpClient.sendMessage(messageRequest);

        //RestTemplate
        restTemplateClient.sendMessage(messageRequest);

        //FeingClient
        ResultBotTelegram resultBotTelegram = feingClient.sendMessageObject(messageRequest);

    }

    public ResultBotTelegramList findUpdates() {
        //HTTP CLIENT
        ResultBotTelegramList resultBotTelegramList = javaHttpClient.findUpdates();

        //RestTemplate
        ResultBotTelegramList resultBotTelegramList1 = restTemplateClient.findUpdates();

        //FeingClient
        feingClient.findUpdates();
        return resultBotTelegramList;
    }
}
