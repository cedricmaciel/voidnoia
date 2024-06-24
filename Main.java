import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        //  a janela principal
        JFrame frame = new JFrame("Voidnoia - Inteligência Artificial");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Label para instruções
        JLabel label = new JLabel("Digite o texto para ensinar a IA:");
        label.setBounds(20, 20, 360, 25);
        frame.add(label);

        // Caixa de texto para entrada de dados
        JTextField textField = new JTextField();
        textField.setBounds(20, 50, 360, 25);
        frame.add(textField);

        // Botão para confirmar a inserção 
        JButton button = new JButton("Ensinar Texto");
        button.setBounds(20, 90, 150, 25);
        frame.add(button);

      
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoParaEnsinar = textField.getText();

                // Criar instância da classe IA e chamar o método aprenderTexto
                IA inteligenciaArtificial = new IA();
                inteligenciaArtificial.aprenderTexto(textoParaEnsinar);

                // Mostrar mensagem de confirmação
                JOptionPane.showMessageDialog(frame, "Texto ensinado com sucesso!");
            }
        });

        //  janela visível
        frame.setVisible(true);

        
        IA inteligenciaArtificial = new IA();

        // Código para aprender uma foto (tem que colocar ainda)
        String caminhoDaFoto = ""; // Substitua pelo caminho real da foto
        inteligenciaArtificial.aprenderFoto(caminhoDaFoto);

        // Código para responder a uma pergunta
        String pergunta = "Quem te criou?";
        String resposta = inteligenciaArtificial.responderPergunta(pergunta);
        System.out.println("Resposta da IA: " + resposta);
    }
}
