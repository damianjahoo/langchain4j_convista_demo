package com.convista.demo.api;

import com.convista.demo.ai.Assistant;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    private final Assistant assistant;

    public ChatController(Assistant assistant) {
        this.assistant = assistant;
    }

    @MessageMapping("/chat")
    @SendToUser("/queue/reply")
    public String chat(@Payload String userMessage) {
        return assistant.chat(userMessage);
    }
}
