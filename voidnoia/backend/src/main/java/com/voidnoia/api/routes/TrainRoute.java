package main.java.com.voidnoia.api.routes;

import com.sun.net.httpserver.*;
import main.java.com.voidnoia.core.Chatbot;
import  org.json.JSONObject;
import java.io.*;
import java.util.stream.Collectors;

public class TrainRoute implements HttpHandler {
    private final Chatbot chatbot;
    
    public TrainRoute(Chatbot chatbot) {
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
            String answer = requestJson.getString("answer");
            
            // Treinar o chatbot
            chatbot.learn(question, answer);
            
            // Preparar resposta
            JSONObject responseJson = new JSONObject();
            responseJson.put("status", "success");
            responseJson.put("message", "Conhecimento adicionado com sucesso");
            
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