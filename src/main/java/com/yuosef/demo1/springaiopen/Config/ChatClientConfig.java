package com.yuosef.demo1.springaiopen.Config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {
    @Bean
    public ChatClient openAIChatClient(OpenAiChatModel openAiChatModel){
        return ChatClient.create(openAiChatModel);
    }
    /*
    this bean created for another local ai docker model we needed to configure it manually
    because we can configure just one auto for openai which is the upper bean
    */
    @Bean
    public ChatClient llamaChatClient(){
        OpenAiChatModel localLlamaModel = OpenAiChatModel.builder()
                .options(OpenAiChatOptions.builder()
                        .baseUrl("http://localhost:12434/engines/v1")
                        .apiKey("")
                        .model("ai/llama3.2")
                        .build())
                .build();
        return ChatClient.create(localLlamaModel);
    }
}
