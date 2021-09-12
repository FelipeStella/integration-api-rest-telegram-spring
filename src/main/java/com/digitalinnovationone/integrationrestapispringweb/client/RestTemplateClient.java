package com.digitalinnovationone.integrationrestapispringweb.client;

import com.digitalinnovationone.integrationrestapispringweb.dto.MessageSend;
import com.digitalinnovationone.integrationrestapispringweb.dto.ResultBotTelegram;
import com.digitalinnovationone.integrationrestapispringweb.dto.ResultBotTelegramList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class RestTemplateClient {

    @Value("${telegram.api}")
    private String BASE_PATH;
    @Value("${telegram.token}")
    private String TOKEN;

    private RestTemplate restTemplate;

    public RestTemplateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendMessage(MessageSend msg) {
        //postForLocation returning to uri, get information from the header
        URI uri = restTemplate.postForLocation(BASE_PATH + TOKEN + "/sendMessage", msg);

        //postForLocation returning the object defined in the return
        ResultBotTelegram ret = restTemplate.postForObject(BASE_PATH + TOKEN + "/sendMessage",
                msg, ResultBotTelegram.class);

        //postForLocation returning the object defined in the return with request data, Headers,HTTP status...
        ResponseEntity<ResultBotTelegram> ret2 = restTemplate.postForEntity(BASE_PATH + TOKEN + "/sendMessage",
                msg, ResultBotTelegram.class);

        //postForLocation returning the object defined in the return with request data, Headers,HTTP status...
        //sending Headers
        HttpHeaders headers = headers();
        HttpEntity<MessageSend> request = new HttpEntity<>(msg, headers);
        ResponseEntity<ResultBotTelegram> ret3 = restTemplate.postForEntity(BASE_PATH + TOKEN + "/sendMessage",
                request, ResultBotTelegram.class);

        //exchange
        HttpHeaders headers1 = headers();
        HttpEntity<MessageSend> request1 = new HttpEntity<>(msg, headers);
        ResponseEntity<ResultBotTelegram> ret4 = restTemplate.exchange(BASE_PATH + TOKEN + "/sendMessage",
                HttpMethod.POST, request1, ResultBotTelegram.class);

    }

    public ResultBotTelegramList findUpdates() {
        ResultBotTelegramList forObject = restTemplate.getForObject(BASE_PATH + TOKEN + "/getUpdates", ResultBotTelegramList.class);
        return forObject;
    }

    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("MEU_HEADER","MEU_VALOR");
        return headers;
    }

}
