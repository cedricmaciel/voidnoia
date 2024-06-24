import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Cria a janela principal
        JFrame frame = new JFrame("Voidnoia - Inteligência Artificial");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

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

        // Adicione um JTextArea para exibir textos aprendidos
        JTextArea textArea = new JTextArea();
        textArea.setBounds(20, 130, 360, 100);
        textArea.setEditable(false);
        frame.add(textArea);

        // Código para perguntas
        JLabel perguntaLabel = new JLabel("Faça uma pergunta:");
        perguntaLabel.setBounds(20, 240, 150, 25);
        frame.add(perguntaLabel);

        JTextField perguntaField = new JTextField();
        perguntaField.setBounds(160, 240, 220, 25);
        frame.add(perguntaField);

        JButton perguntaButton = new JButton("Perguntar");
        perguntaButton.setBounds(160, 270, 100, 25);
        frame.add(perguntaButton);

        IA inteligenciaArtificial = new IA();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoParaEnsinar = textField.getText();
                inteligenciaArtificial.aprenderTexto(textoParaEnsinar);

                JOptionPane.showMessageDialog(frame, "Texto ensinado com sucesso!");

                // Limpar a caixa de texto
                textField.setText("");

                // Atualizar área de texto
                textArea.setText(String.join("\n", inteligenciaArtificial.getTextosAprendidos()));
            }
        });

        perguntaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pergunta = perguntaField.getText();
                String resposta = inteligenciaArtificial.responderPergunta(pergunta);
                JOptionPane.showMessageDialog(frame, resposta);
            }
        });

        // Carregar textos aprendidos na inicialização
        textArea.setText(String.join("\n", inteligenciaArtificial.getTextosAprendidos()));

        frame.setVisible(true);
    }
}
