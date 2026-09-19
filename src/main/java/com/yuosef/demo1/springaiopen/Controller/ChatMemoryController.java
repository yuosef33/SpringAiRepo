package com.yuosef.demo1.springaiopen.Controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api4")
public class ChatMemoryController {
    private final ChatClient.Builder openaichatClient;
    public ChatMemoryController(@Qualifier("ChatMemoryChatClient") ChatClient.Builder openaichatClient) {
        this.openaichatClient = openaichatClient;
    }
    @GetMapping("/openai/chat")
    public ResponseEntity<String> openaichat(@RequestParam("message") String message){
        return ResponseEntity.ok(openaichatClient.build()
                .prompt(message)
                        .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID,"default"))
                .call().content());
    }
}
