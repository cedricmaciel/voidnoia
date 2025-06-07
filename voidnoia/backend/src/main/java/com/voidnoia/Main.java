package com.voidnoia;

import com.voidnoia.api.ApiServer;
import com.voidnoia.core.Chatbot;
import com.voidnoia.storage.JsonDatabase;

public class Main {
    public static void main(String[] args) {
        try {
            JsonDatabase database = new JsonDatabase();
            Chatbot chatbot = new Chatbot(database);
            ApiServer server = new ApiServer(chatbot);
            
            int port = 0;
            server.start(port);
            
            System.out.println("Chatbot Voidnoia está rodando na porta " + port);
            System.out.println("Acesse http://localhost:" + port);
            
            // Adiciona shutdown hook para parar o servidor graciosamente
            Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
        } catch (Exception e) {
            System.err.println("Erro ao iniciar o servidor: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}