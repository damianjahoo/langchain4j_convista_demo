package com.convista.demo.api;

import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    private final ChatLanguageModel chatLanguageModel;

    public ChatController(ChatLanguageModel chatLanguageModel) {
        this.chatLanguageModel = chatLanguageModel;
    }

    @MessageMapping("/chat")
    @SendToUser("/queue/reply")
    public String chat(@Payload String userMessage) {
        return chatLanguageModel.generate(userMessage);
    }
}
