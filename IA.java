import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class IA {
    private static final String DATABASE_DIR = "banco_de_dados";
    private static final String TEXT_FILE = DATABASE_DIR + "/textos.txt";

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
    }

    public void aprenderTexto(String texto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEXT_FILE, true))) {
            writer.write(texto);
            writer.newLine();
            System.out.println("Aprendendo com o texto: " + texto);
        } catch (IOException e) {
            System.out.println("Erro ao salvar texto: " + e.getMessage());
        }
    }

    public void aprenderFoto(String caminhoDaFoto) {
        // Lógica para aprender a foto (não implementada)
        System.out.println("Aprendendo com a foto: " + caminhoDaFoto);
    }

    public String responderPergunta(String pergunta) {
        String resposta = "Não vou falar";

        try {
            List<String> textos = Files.readAllLines(Paths.get(TEXT_FILE));
            if (!textos.isEmpty()) {
                resposta = "Aprendi o seguinte texto recentemente: " + textos.get(textos.size() - 1);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler textos: " + e.getMessage());
        }

        return resposta;
    }
}
