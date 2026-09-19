package com.yuosef.demo1.springaiopen.Controller;

import com.yuosef.demo1.springaiopen.Config.ChatClientConfig;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/apii")
public class StreamController {

    private final ChatClient.Builder chatClient;

    public StreamController(@Qualifier("openAIChatClient") ChatClient.Builder chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/stream")
    public Flux<String> stream(@RequestParam("message") String message){
        return chatClient.build()
                .prompt()
                .user(message).stream().content();
    }
}
