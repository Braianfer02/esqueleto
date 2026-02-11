package com.flashpage.app.api.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flashpage.app.api.dto.ChatRequest;

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {

    @PostMapping
    public ResponseEntity<?> handle(@RequestBody ChatRequest request) {

        System.out.println("Mensaje recibido: " + request.getMessage());

        return ResponseEntity.ok(
                Map.of(
                        "status", "ok",
                        "reply", "Mensaje recibido correctamente"
                )
        );
    }
}
