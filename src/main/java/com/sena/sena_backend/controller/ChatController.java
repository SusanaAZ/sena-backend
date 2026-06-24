package com.sena.sena_backend.controller;

import com.sena.sena_backend.dto.ChatRequest;
import com.sena.sena_backend.dto.ChatResponse;
import com.sena.sena_backend.service.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController
{
    @Autowired
    private ChatbotService chatbotService;

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request)
    {
        return chatbotService.chat(request);
    }

}
