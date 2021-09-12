package com.digitalinnovationone.integrationrestapispringweb.client;

import com.digitalinnovationone.integrationrestapispringweb.dto.MessageSend;
import com.digitalinnovationone.integrationrestapispringweb.dto.ResultBotTelegram;
import com.digitalinnovationone.integrationrestapispringweb.dto.ResultBotTelegramList;
import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "${telegram.api}${telegram.token}", name = "telegram")
public interface FeingClient {

    @GetMapping("/getUpdates")
    ResponseEntity<ResultBotTelegramList> findUpdates();

    @Headers("MY_HEADER: {my_header}")
    @GetMapping("/getUpdates")
    ResponseEntity<ResultBotTelegramList> findUpdatesWhitHeader(@Param("my_header") String header);

    //return com response entity
    @PostMapping("/sendMessage")
    ResponseEntity<ResultBotTelegram> sendMessageEntity(@RequestBody MessageSend msg);

    //return response object
    @PostMapping("/sendMessage")
    @ResponseBody
    ResultBotTelegram sendMessageObject(@RequestBody MessageSend msg);

    //set header RequestHeader
    @PostMapping(value = "/sendMessage")
    ResultBotTelegram sendMessageSetHeader(@RequestBody MessageSend msg, @RequestHeader("MY_HEADER") String header);

    //RequestMapping informing the type of method
    @RequestMapping(method = RequestMethod.POST, value = "/sendMessage")
    ResultBotTelegram sendMessageByMethod(@RequestBody MessageSend msg);

    //example de path variable
    @GetMapping("/getUpdates/{myTest}")
    ResultBotTelegram examplePathVariable(@PathVariable("myTest") String myTest);

    //Exemplo de request param
    //www.myUrl.com.br/getUpdates?name=givenName
    @GetMapping("/getUpdates")
    ResultBotTelegram exampleRequestParam(@RequestParam("name") String name);

}
