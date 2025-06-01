import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private JTextField campoPeso;
    private JTextField campoAltura;
    private JLabel resultadoLabel;

    public Main() {
        setTitle("Calculadora de IMC");
        setSize(350, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel pesoLabel = new JLabel("Peso (kg):");
        c.gridx = 0;
        c.gridy = 0;
        add(pesoLabel, c);

        campoPeso = new JTextField();
        c.gridx = 1;
        c.gridy = 0;
        add(campoPeso, c);

        JLabel alturaLabel = new JLabel("Altura (cm):");
        c.gridx = 0;
        c.gridy = 1;
        add(alturaLabel, c);

        campoAltura = new JTextField();
        c.gridx = 1;
        c.gridy = 1;
        add(campoAltura, c);

        JButton botaoCalcular = new JButton("Calcular IMC");
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        add(botaoCalcular, c);

        resultadoLabel = new JLabel(" ");
        resultadoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        add(resultadoLabel, c);

        botaoCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularIMC();
            }
        });
    }

    private void calcularIMC() {
        try {
            double peso = Double.parseDouble(campoPeso.getText().replace(',', '.'));
            double alturaCm = Double.parseDouble(campoAltura.getText().replace(',', '.'));
            double alturaM = alturaCm / 100.0;

            if (peso <= 0 || alturaM <= 0) {
                resultadoLabel.setText("Valores inválidos.");
                return;
            }

            double imc = peso / (alturaM * alturaM);
            String classificacao = classificarIMC(imc);

            resultadoLabel.setText(String.format("IMC: %.2f - %s", imc, classificacao));
        } catch (NumberFormatException ex) {
            resultadoLabel.setText("Digite valores numéricos válidos.");
        }
    }

    private String classificarIMC(double imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc < 24.9) {
            return "Peso normal";
        } else if (imc < 29.9) {
            return "Sobrepeso";
        } else if (imc < 34.9) {
            return "Obesidade grau I";
        } else if (imc < 39.9) {
            return "Obesidade grau II";
        } else {
            return "Obesidade grau III (mórbida)";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main tela = new Main();
            tela.setVisible(true);
        });
    }
}
