package com.yuosef.demo1.springaiopen.Controller;

import com.yuosef.demo1.springaiopen.Config.ChatClientConfig;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MultiModelChatController {

    private final ChatClient.Builder llamachatClient;
    private final ChatClient.Builder openaichatClient;
    public MultiModelChatController(@Qualifier("llamaChatClient") ChatClient.Builder llamachatClient,
                                    @Qualifier("openAIChatClient") ChatClient.Builder openaichatClient) {
        this.llamachatClient = llamachatClient.defaultSystem(ChatClientConfig.systeMessage).defaultUser("How can you help me");
        this.openaichatClient = openaichatClient;
    }
    @Value("classpath:/promptTemplates/userPromptTemplate.st")
    Resource userpromptTemplate;
    @Value("classpath:/promptTemplates/systemPromptTemplate.st")
    Resource systemPromptTemplate;

    @GetMapping("/openai/chat")
    public String openaichat(@RequestParam("message") String message){
        return openaichatClient.build().prompt(message).call().content();
    }
    @GetMapping("/llama/chat")
    public String llamachat(@RequestParam("message") String message){
        return llamachatClient.build()
                .prompt()
                .user(message)
                .call()
                .content();
    }
    @GetMapping("/llama/chat2")
    public String llamachat(@RequestParam("customerName") String customerName,
                            @RequestParam("customerMessage") String customerMessage){
        return llamachatClient.build()
                .prompt()
                .user(promptUserSpec -> promptUserSpec.text(userpromptTemplate)
                        .param("customerName",customerName)
                        .param("customerMessage",customerMessage))
                .call()
                .content();
    }

    @GetMapping("/llama/chat3")
    public String promptStuffing(@RequestParam("message") String message){
        return llamachatClient.build()
                .prompt()
                .user(message)
                .system(systemPromptTemplate)
                .call()
                .content();
    }
}
