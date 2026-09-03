package com.yuosef.demo1.springaiopen.Controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MultiModelChatController {

    private final ChatClient llamachatClient;
    private final ChatClient openaichatClient;
    public MultiModelChatController(@Qualifier("llamaChatClient") ChatClient llamachatClient,
                                    @Qualifier("openAIChatClient") ChatClient openaichatClient) {
        this.llamachatClient = llamachatClient;
        this.openaichatClient = openaichatClient;
    }

    @GetMapping("/openai/chat")
    public String openaichat(@RequestParam("message") String message){
        return openaichatClient.prompt(message).call().content();
    }
    @GetMapping("/llama/chat")
    public String llamachat(@RequestParam("message") String message){
        return llamachatClient.prompt(message).call().content();
    }
}
