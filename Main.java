

public class Main {

    public static void main(String[] args) {
        System.out.println("Bem-vindo ao projeto Voidnoia - Inteligência Artificial em Java!");

       

       
        IA inteligenciaArtificial = new IA();

        
        String textoParaEnsinar = "";
        inteligenciaArtificial.aprenderTexto(textoParaEnsinar);

        
        String caminhoDaFoto = "";
        inteligenciaArtificial.aprenderFoto(caminhoDaFoto);

        
        String pergunta = "";
        String resposta = inteligenciaArtificial.responderPergunta(pergunta);
        System.out.println("Resposta da IA: " + resposta);
    }
}
