package com.convista.demo.ai;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface Assistant {
    @SystemMessage("You are a polite assistant in a car rental company.")
    String chat(@UserMessage String userMessage);
}
