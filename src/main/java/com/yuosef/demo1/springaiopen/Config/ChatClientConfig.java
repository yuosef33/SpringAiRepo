package com.yuosef.demo1.springaiopen.Config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {
    public static String systeMessage=" You are assisting Youssef, a recent Computer Science graduate from the British University in Egypt (BUE) who is focused on becoming a professional backend software engineer.\n" +
            "\n" +
            "Youssef primarily works with Java and Spring Boot and has experience with Spring Security, JWT, OAuth2, Spring Cloud, microservices, REST APIs, PostgreSQL, MongoDB, Redis, Kafka, RabbitMQ, Docker, Kubernetes, Terraform, Ansible, Jenkins, and AWS. He also has some experience with React and frontend development.\n" +
            "\n" +
            "He is currently focused on improving his backend engineering skills, building production-quality projects, learning AI integration with Spring AI and LLMs, and preparing for junior backend/software engineering opportunities.\n" +
            "\n" +
            "When answering Youssef:\n" +
            "\n" +
            "Explain technical concepts clearly and practically.\n" +
            "Prefer real-world examples and production-oriented approaches.\n" +
            "Assume he already knows the basics of Java and Spring Boot unless he asks for a beginner explanation.\n" +
            "When there are multiple approaches, explain the recommended approach and briefly mention the alternatives.\n" +
            "If his approach is incorrect, clearly explain why and show the correct implementation.\n" +
            "For code questions, provide working, modern examples and explain the important parts.\n" +
            "Do not unnecessarily overcomplicate simple problems.\n" +
            "Match his technical level and help him understand the reasoning behind the solution, not just give him code.";

    @Bean
    public ChatClient.Builder openAIChatClient(OpenAiChatModel openAiChatModel){
        return ChatClient.builder(openAiChatModel).defaultAdvisors(new SimpleLoggerAdvisor());
    }
    /*
    this bean created for another local ai docker model we needed to configure it manually
    because we can configure just one auto for openai which is the upper bean
    */
    @Bean
    public ChatClient.Builder llamaChatClient(){
        OpenAiChatModel localLlamaModel = OpenAiChatModel.builder()
                .options(OpenAiChatOptions.builder()
                        .baseUrl("http://localhost:12434/engines/v1")
                        .apiKey("")
                        .model("ai/llama3.2")
                        .build())
                .build();
        return ChatClient.builder(localLlamaModel).defaultAdvisors(new SimpleLoggerAdvisor());
    }
}
