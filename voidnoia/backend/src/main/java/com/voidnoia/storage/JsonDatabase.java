package com.voidnoia.storage;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class JsonDatabase {
    private final String DB_PATH = Paths.get("..", "data", "knowledge.json").toString();
    private final String LOGS_DIR = Paths.get("..", "data", "logs").toString();
    private JSONArray knowledge;

    public JsonDatabase() {
        loadDatabase();
        ensureLogsDirectory();
    }

    private void loadDatabase() {
        try {
            String content = Files.readString(Path.of(DB_PATH));
            knowledge = new JSONArray(content);
        } catch (Exception e) {
            knowledge = new JSONArray();
            saveDatabase();
        }
    }

    private void saveDatabase() {
        try {
            Files.writeString(Path.of(DB_PATH), knowledge.toString(2));
        } catch (Exception e) {
            System.err.println("Erro ao salvar banco de dados: " + e.getMessage());
        }
    }

    private void ensureLogsDirectory() {
        try {
            Files.createDirectories(Path.of(LOGS_DIR));
        } catch (Exception e) {
            System.err.println("Erro ao criar diretório de logs: " + e.getMessage());
        }
    }

    public synchronized void addKnowledge(String question, String answer) {
        JSONObject record = new JSONObject();
        record.put("id", UUID.randomUUID().toString());
        record.put("question", question.toLowerCase().trim());
        record.put("answer", answer);
        record.put("createdAt", LocalDateTime.now().toString());
        record.put("lastUsed", LocalDateTime.now().toString());
        knowledge.put(record);
        saveDatabase();
        logTraining(question, answer);
    }

    public Optional<String> findAnswer(String question) {
        String query = question.toLowerCase().trim();
        
        // Busca exata
        for (int i = 0; i < knowledge.length(); i++) {
            JSONObject record = knowledge.getJSONObject(i);
            if (record.getString("question").equals(query)) {
                updateLastUsed(record);
                return Optional.of(record.getString("answer"));
            }
        }
        
        // Busca por similaridade (palavras-chave)
        List<String> queryWords = Arrays.asList(query.split("\\s+"));
        
        List<JSONObject> matches = new ArrayList<>();
        for (int i = 0; i < knowledge.length(); i++) {
            JSONObject record = knowledge.getJSONObject(i);
            String storedQuestion = record.getString("question");
            long matchCount = Arrays.stream(storedQuestion.split("\\s+"))
                                  .filter(queryWords::contains)
                                  .count();
            
            if (matchCount > 0) {
                record.put("matchScore", matchCount);
                matches.add(record);
            }
        }
        
        if (!matches.isEmpty()) {
            matches.sort((a, b) -> Integer.compare(b.getInt("matchScore"), a.getInt("matchScore")));
            JSONObject bestMatch = matches.get(0);
            updateLastUsed(bestMatch);
            return Optional.of(bestMatch.getString("answer"));
        }
        
        return Optional.empty();
    }

    private void updateLastUsed(JSONObject record) {
        record.put("lastUsed", LocalDateTime.now().toString());
        saveDatabase();
    }

    private void logTraining(String question, String answer) {
        try {
            String logFile = Path.of(LOGS_DIR, "training_" + LocalDate.now() + ".log").toString();
            String logEntry = String.format("[%s] Novo conhecimento:\nP: %s\nR: %s\n\n", 
                LocalDateTime.now(), question, answer);
            
            Files.writeString(Path.of(logFile), logEntry, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.err.println("Erro ao registrar log: " + e.getMessage());
        }
    }
}