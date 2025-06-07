package com.voidnoia.core;

import com.voidnoia.storage.JsonDatabase;
import java.util.Optional;

public class Chatbot {
    private final JsonDatabase database;
    
    public Chatbot(JsonDatabase database) {
        this.database = database;
    }
    
    public String processQuestion(String question) {
        // 1. Tentar encontrar resposta no banco de dados
        Optional<String> answer = database.findAnswer(question);
        if (answer.isPresent()) {
            return answer.get();
        }
        
        // 2. Processamento NLP básico
        String processedQuestion = question.toLowerCase().trim();
        
        if (processedQuestion.contains("como") && processedQuestion.contains("criar")) {
            return "Para criar algo, você precisa primeiro definir os requisitos. Que tipo de projeto você está desenvolvendo?";
        }
        
        if (processedQuestion.contains("erro") || processedQuestion.contains("problema")) {
            return "Parece que você está enfrentando um problema. Poderia me dar mais detalhes sobre o erro?";
        }
        
        // 3. Resposta padrão para perguntas desconhecidas
        return "NOT_UNDERSTOOD";
    }
    
    public void learn(String question, String answer) {
        database.addKnowledge(question, answer);
    }
}