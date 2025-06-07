package com.voidnoia.api;

import com.sun.net.httpserver.*;
import com.voidnoia.core.Chatbot;
import com.voidnoia.storage.JsonDatabase;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.concurrent.*;

public class ApiServer {
    private final Chatbot chatbot;
    private HttpServer server;
    
    public ApiServer(Chatbot chatbot) {
        this.chatbot = chatbot;
    }
    
    public void start(int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        
        // Configuração de rotas
        server.createContext("/api/chat", new ChatRoute(chatbot));
        server.createContext("/api/train", new TrainRoute(chatbot));
        
        // Configuração do executor
        server.setExecutor(Executors.newFixedThreadPool(10));
        
        // Inicia o servidor
        server.start();
        System.out.println("Servidor iniciado na porta " + port);
    }
    
    public void stop() {
        if (server != null) {
            server.stop(0);
            System.out.println("Servidor parado");
        }
    }
}