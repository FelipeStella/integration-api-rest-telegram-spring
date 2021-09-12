package com.digitalinnovationone.integrationrestapispringweb.controller;

import com.digitalinnovationone.integrationrestapispringweb.dto.MessageSend;
import com.digitalinnovationone.integrationrestapispringweb.dto.ResultBotTelegramList;
import com.digitalinnovationone.integrationrestapispringweb.service.TelegramService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/telegram")
public class TelegramController {

    private TelegramService telegramService;
    private ObjectMapper objectMapper;

    public TelegramController(TelegramService telegramService, ObjectMapper objectMapper) {
        this.telegramService = telegramService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity createPost(@RequestBody MessageSend messageRequest) {
        System.out.println("Entered the post");
        telegramService.sendMessage(messageRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<ResultBotTelegramList> findUpdates() {
        ResultBotTelegramList getUpdatesResultBotTelegram = telegramService.findUpdates();
        return ResponseEntity.ok(getUpdatesResultBotTelegram);
    }

}
