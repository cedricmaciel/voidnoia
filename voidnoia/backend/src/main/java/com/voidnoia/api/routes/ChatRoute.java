package com.voidnoia.api.routes;

import com.sun.net.httpserver.*;
import com.voidnoia.core.Chatbot;
import org.json.JSONObject;
import java.io.*;

public class ChatRoute implements HttpHandler {
    private final Chatbot chatbot;
    
    public ChatRoute(Chatbot chatbot) {
        this.chatbot = chatbot;
    }
    
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!"POST".equals(exchange.getRequestMethod())) {
            sendResponse(exchange, 405, "Método não permitido");
            return;
        }
        
        try {
            // Ler o corpo da requisição
            String requestBody = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))
                .lines().collect(Collectors.joining("\n"));
            
            JSONObject requestJson = new JSONObject(requestBody);
            String question = requestJson.getString("question");
            
            // Processar a pergunta
            String response = chatbot.processQuestion(question);
            
            // Preparar resposta
            JSONObject responseJson = new JSONObject();
            responseJson.put("answer", response);
            
            sendResponse(exchange, 200, responseJson.toString());
        } catch (Exception e) {
            sendResponse(exchange, 400, "Requisição inválida: " + e.getMessage());
        }
    }
    
    private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}