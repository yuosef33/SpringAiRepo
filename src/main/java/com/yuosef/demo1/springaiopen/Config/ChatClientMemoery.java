package com.yuosef.demo1.springaiopen.Config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientMemoery {

    @Bean
    ChatMemory chatMemory(JdbcChatMemoryRepository jdbcChatMemoryRepository){
       return MessageWindowChatMemory.builder().maxMessages(10).chatMemoryRepository(jdbcChatMemoryRepository).build();
    }

    @Bean("ChatMemoryChatClient")
    public ChatClient.Builder openAIChatClient(OpenAiChatModel openAiChatModel, ChatMemory chatMemory){
        Advisor loggeradvisor= new SimpleLoggerAdvisor();
        Advisor advisorMemoery = MessageChatMemoryAdvisor.builder(chatMemory).build();
        return ChatClient.builder(openAiChatModel).defaultAdvisors(List.of(loggeradvisor,advisorMemoery));
    }
}
