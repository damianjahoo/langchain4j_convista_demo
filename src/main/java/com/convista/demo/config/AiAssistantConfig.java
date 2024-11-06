package com.convista.demo.config;

import com.convista.demo.ai.Assistant;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiAssistantConfig {
    private final ChatLanguageModel model;

    public AiAssistantConfig(ChatLanguageModel model) {
        this.model = model;
    }

    @Bean
    Assistant assistant() {
        return AiServices.builder(Assistant.class)
                .chatLanguageModel(model)
                .build();
    }
}
