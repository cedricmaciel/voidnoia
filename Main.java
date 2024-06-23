

public class Main {

    public static void main(String[] args) {
        System.out.println("Bem-vindo ao projeto Voidnoia - Inteligência Artificial em Java!");

       

       
        IA inteligenciaArtificial = new IA();

        
        String textoParaEnsinar = "Exemplo de texto para ensinar a IA.";
        inteligenciaArtificial.aprenderTexto(textoParaEnsinar);

        
        String caminhoDaFoto = "/caminho/para/sua/foto.jpg";
        inteligenciaArtificial.aprenderFoto(caminhoDaFoto);

        
        String pergunta = "Qual é a resposta para a vida, o universo e tudo mais?";
        String resposta = inteligenciaArtificial.responderPergunta(pergunta);
        System.out.println("Resposta da IA: " + resposta);
    }
}
