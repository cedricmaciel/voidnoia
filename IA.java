import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IA {
    private static final String DATABASE_DIR = "banco_de_dados";
    private static final String TEXT_FILE = DATABASE_DIR + "/textos.txt";
    private Map<String, String> knowledgeBase;

    public IA() {
        // Cria a pasta do banco de dados se não existir
        File databaseDir = new File(DATABASE_DIR);
        if (!databaseDir.exists()) {
            databaseDir.mkdirs();
        }

        // Cria o arquivo de textos se não existir
        File textFile = new File(TEXT_FILE);
        try {
            if (!textFile.exists()) {
                textFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo de textos: " + e.getMessage());
        }

        // Inicializa a base de conhecimento
        knowledgeBase = new HashMap<>();
        carregarBaseDeConhecimento();
    }

    public void aprenderTexto(String texto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEXT_FILE, true))) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            writer.write(timestamp + " - " + texto);
            writer.newLine();
            System.out.println("Aprendendo com o texto: " + texto);
            atualizarBaseDeConhecimento(texto);
        } catch (IOException e) {
            System.out.println("Erro ao salvar texto: " + e.getMessage());
        }
    }

    public void aprenderFoto(String caminhoDaFoto) {
        // Lógica para aprender a foto (não implementada)
        System.out.println("Aprendendo com a foto: " + caminhoDaFoto);
    }

    public String responderPergunta(String pergunta) {
        // Implementa lógica de correspondência de palavras-chave
        String perguntaLower = pergunta.toLowerCase();
        for (Map.Entry<String, String> entry : knowledgeBase.entrySet()) {
            if (perguntaLower.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "Não sei a resposta para isso.";
    }

    public List<String> getTextosAprendidos() {
        try {
            return Files.readAllLines(Paths.get(TEXT_FILE));
        } catch (IOException e) {
            System.out.println("Erro ao ler textos: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private void carregarBaseDeConhecimento() {
        // Carregar textos do arquivo e atualizar a base de conhecimento
        try {
            List<String> textos = Files.readAllLines(Paths.get(TEXT_FILE));
            for (String texto : textos) {
                atualizarBaseDeConhecimento(texto);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar textos: " + e.getMessage());
        }
    }

    private void atualizarBaseDeConhecimento(String texto) {
        // Logica simples para extrair informações-chave
        // Aqui, você pode adicionar regras mais sofisticadas para analisar e extrair informações dos textos
        String textoLower = texto.toLowerCase();
        if (textoLower.contains("quem te criou")) {
            knowledgeBase.put("quem te criou", "Fui criado por um desenvolvedor habilidoso.");
        }
        if (textoLower.contains("qual é o seu nome")) {
            knowledgeBase.put("qual é o seu nome", "Meu nome é Voidnoia.");
        }
        if (textoLower.contains("como você funciona")) {
            knowledgeBase.put("como você funciona", "Eu uso algoritmos de inteligência artificial para aprender e responder perguntas.");
        }
        if (textoLower.contains("qual é a cor do céu")) {
            knowledgeBase.put("qual é a cor do céu", "O céu é azul");
        }
    }
}
