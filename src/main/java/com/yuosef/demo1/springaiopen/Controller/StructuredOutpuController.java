package com.yuosef.demo1.springaiopen.Controller;

import com.yuosef.demo1.springaiopen.Model.CountryCities;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/apiii")
public class StructuredOutpuController {


    private final ChatClient.Builder chatClient;

    public StructuredOutpuController(@Qualifier("openAIChatClient") ChatClient.Builder chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/countries")
    public ResponseEntity<List<CountryCities>> chatBeanList(@RequestParam("message") String message){
        List<CountryCities> countryCities= chatClient.build()
                                    .prompt()
                                    .user(message).call().entity(new ParameterizedTypeReference<List<CountryCities>>() {
                                    });
        return ResponseEntity.ok(countryCities);
    }
}
