package com.convista.demo.config;

import com.convista.demo.ai.Assistant;
import com.convista.demo.ai.tools.BookingTools;
import com.convista.demo.persistence.BookingRepository;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiAssistantConfig {
    private final ChatLanguageModel model;
    private final BookingRepository bookingRepository;
    private final InMemoryEmbeddingStore<TextSegment> embeddingStore;

    public AiAssistantConfig(ChatLanguageModel model, BookingRepository bookingRepository, InMemoryEmbeddingStore<TextSegment> embeddingStore) {
        this.model = model;
        this.bookingRepository = bookingRepository;
        this.embeddingStore = embeddingStore;
    }

    @Bean
    Assistant assistant() {
        return AiServices.builder(Assistant.class)
                .chatLanguageModel(model)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .tools(new BookingTools(bookingRepository))
                .contentRetriever(EmbeddingStoreContentRetriever.from(embeddingStore))
                .build();
    }
}
