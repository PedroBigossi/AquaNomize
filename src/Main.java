import javax.swing.*;
import java.awt.*;
    import java.awt.event.*;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.text.DecimalFormat;

public class Main {
    private static Usuario user;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                criarTelaCadastro();
            }
        });
    }

    public static void criarTelaCadastro() {
        /*
        Função com objetivo de criar a tela Inicial do aplicativo que irá solicitar o nome do usuario para a criação
        do objeto Usuario user da classe Usuario
         */
        JFrame frame = new JFrame("Cadastro de Usuário");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(2, 3));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 3));

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();

        panel.add(lblNome);
        panel.add(txtNome);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnCadastrar = new JButton("Cadastrar");

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarAcao(frame, txtNome.getText());
            }
        });
        btnCadastrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(btnCadastrar);
        frame.add(panel);
        frame.add(buttonPanel);
        frame.setVisible(true);
        txtNome.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    cadastrarAcao(frame, txtNome.getText());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    private static void cadastrarAcao(JFrame frame, String nome) {
                /*
        Função com objetivo de verificar se o campo nome da função acima está preenchido e cria o objeto user baseado no nome inserido pelo
        usuario da aplicação
         */
        if (!nome.isEmpty()) {
            frame.dispose();
            user = new Usuario(nome);
            criarTelaPrincipal();
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
        }
    }


    public static void criarTelaPrincipal() {
                /*
        Menu principal da aplicação, onde será disposto as 5 opções principais do aplicativo e o usuario irá inserir o numero respectivo à função que
        deseja utilizar da aplicação
                 */
        JFrame frame = new JFrame("Menu Principal");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 400);
        frame.setLayout(new GridLayout(4, 1));
        frame.setLocationRelativeTo(null);
        JPanel opcoesPanel = new JPanel();
        opcoesPanel.setLayout(new GridLayout(0, 1));

        String[] opcoes = {
                "1. Registrar atividade",
                "2. Listar Atividades",
                "3. Mostrar o calculo de consumo de água das atividades registradas",
                "4. Calcular a conta de água",
                "5. Dicas de uso consumo inteligente de água",
                "6. Limpar Atividades Registradas",
                "7. Sair do Aplicativo"
        };

        for (String opcao : opcoes) {
            JLabel lblOpcao = new JLabel(opcao);
            opcoesPanel.add(lblOpcao);
        }

        frame.add(opcoesPanel, BorderLayout.CENTER);

        JLabel lblOpcoes = new JLabel("Escolha uma opção:");
        frame.add(lblOpcoes);

        JTextField txtOpcao = new JTextField(1);
        txtOpcao.setHorizontalAlignment(JTextField.CENTER);
        frame.add(txtOpcao);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(e -> verificarOpcao(txtOpcao.getText(), user));
        buttonPanel.add(btnConfirmar);

        frame.add(buttonPanel);

        txtOpcao.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    verificarOpcao(txtOpcao.getText(), user);
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        frame.setVisible(true);
    }

    private static void verificarOpcao(String opcao, Usuario user) {
                /*
        Função com objetivo de verificar qual a alternativa do menu principal o usuario escolheu e chama o metodo respectivo
        para aquela opção
         */
        switch (opcao) {
            case "1":
                registroAtividadesJanela(user);
                break;
            case "2":
                listarAtividades(user);
                break;
            case "3":
                calcularConsumoAgua(user);
                break;
            case "4":
                calcularValorContaAgua(user);
                break;
            case "5":
                exibirDicasUsoAgua();
                break;
            case "6":
                limparAtividades(user);
                break;
            case "7":
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida.");
        }
    }

    private static void registroAtividadesJanela(Usuario user) {
                /*
        abre uma janela da aplicação com o propósito do usuario registrar atividades feitas no dia a dia voltadas para o consumo de agua, que são salvas como
        objetos na classe Atividades através de um metodo da classe Usuario
         */
        JFrame frame = new JFrame("Registrar Atividade que consome água");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(4, 2));
        frame.setLocationRelativeTo(null);

        JLabel lblDescricao = new JLabel("Descrição da atividade:");
        JTextField txtDescricao = new JTextField();
        JLabel lblConsumoPorMin = new JLabel("Consumo por Minuto (Litros):");
        JTextField txtConsumoPorMin = new JTextField();
        JLabel lblDuracao = new JLabel("Duração (minutos):");
        JTextField txtDuracao = new JTextField();

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String descricao = txtDescricao.getText();
                    float consumoPorMin = Float.parseFloat(txtConsumoPorMin.getText());
                    int duracao = Integer.parseInt(txtDuracao.getText());

                    user.adicionarAtividade(descricao, consumoPorMin, duracao);
                    JOptionPane.showMessageDialog(null, "Atividade registrada com sucesso!");
                    frame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira valores numéricos válidos para consumo por minuto e duração.");
                }
            }
        });

        frame.add(lblDescricao);
        frame.add(txtDescricao);
        frame.add(lblConsumoPorMin);
        frame.add(txtConsumoPorMin);
        frame.add(lblDuracao);
        frame.add(txtDuracao);
        frame.add(btnRegistrar);

        txtDuracao.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnRegistrar.doClick();
                }
            }
        });

        frame.setVisible(true);
    }

    private static void listarAtividades(Usuario user) {
                /*
        Abre uma janela a partir do menu principal afim de listar as atividades registradas na função registrarAtividadesJanela()
         */
        StringBuilder atividadesList = new StringBuilder();
        List<Atividades> atividades = user.getAtividades();

        if (atividades.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há atividades registradas.");
            return;
        }

        atividadesList.append("Atividades do Usuário:\n");

        for (Atividades atividade : atividades) {
            atividadesList.append("Descrição : ").append(atividade.getDescricao()).append("\n");
            atividadesList.append("Consumo por minuto: ").append(atividade.getConsumoPorMin()).append("\n");
            atividadesList.append("Duração: ").append(atividade.getDuracao()).append(" minutos\n");
            atividadesList.append("--------------------\n");
        }

        JOptionPane.showMessageDialog(null, atividadesList.toString());
    }

    private static void calcularConsumoAgua(Usuario user) {
        List<Atividades> atividades = user.getAtividades();
        float consumoTotal = 0;

        if (atividades.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há atividades registradas.");
            return;
        }

        StringBuilder consumoAgua = new StringBuilder();
        consumoAgua.append("<html>"); // Iniciando o uso de HTML para formatação

        consumoAgua.append("Consumo de água por atividade:<br>");

        DecimalFormat df = new DecimalFormat("#.##");

        for (Atividades atividade : atividades) {
            float consumoAtividade = atividade.getConsumoPorMin() * atividade.getDuracao();
            consumoTotal += consumoAtividade;

            consumoAgua.append("Atividade: ").append(atividade.getDescricao()).append("<br>");
            consumoAgua.append("Consumo: ").append(consumoAtividade).append(" litros<br>");
            consumoAgua.append("--------------------<br>");
        }
        consumoAgua.append("Consumo total de água: ").append(consumoTotal).append(" litros<br>");
        consumoAgua.append("<hr>");
        if (consumoTotal * 30 > 8000) {
            consumoAgua.append("<font color='#FF0000'>ALERTA: Alto consumo de água!</font>");
            JOptionPane.showMessageDialog(null, consumoAgua.toString(), "Alerta", JOptionPane.ERROR_MESSAGE);
        } else {
            consumoAgua.append("<font color='#000080'>Parabéns! Você realizou um bom consumo de água!</font>");
            JOptionPane.showMessageDialog(null, consumoAgua.toString(), "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void exibirDicasUsoAgua() {
        /*
        Exibe dicas sobre o uso de agua em uma nova janela para que o usuario possa seguir tais dicas
         */
        String dicas = """
                Dicas para um uso inteligente da água:
                1. Tome banhos mais curtos.
                2. Feche a torneira enquanto escova os dentes.
                3. Utilize máquina de lavar roupa e louça apenas com a carga completa.
                4. Conserte vazamentos e goteiras.
                5. Reutilize a água da chuva para regar plantas.
                6. Use a vassoura em vez da mangueira para limpar calçadas.
                7. Instale redutores de vazão nas torneiras.
                8. Opte por plantas nativas e adaptadas ao clima local no seu jardim.
                """;

        JOptionPane.showMessageDialog(null, dicas);
    }

    private static void calcularValorContaAgua(Usuario user) {
        List<Atividades> atividades = user.getAtividades();
        float consumoTotal = 0;

        if (atividades.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há atividades registradas.");
            return;
        }


        for (Atividades atividade : atividades) {
            consumoTotal += atividade.getConsumoPorMin() * atividade.getDuracao();
        }

        // Calculando o valor da conta de água com base no consumo total
        float valorContaAgua = consumoTotal / 220 * 70.00f;


        DecimalFormat df = new DecimalFormat("#.##");
        String valorFormatado = df.format(valorContaAgua).replace(",", ".");
        valorContaAgua = Float.parseFloat(valorFormatado);

        // Exibindo o valor da conta de água para o usuário
        JOptionPane.showMessageDialog(null, "O valor estimado da sua conta de água é: R$ " + valorContaAgua);
    }

    private static void limparAtividades(Usuario user) {
        user.limparAtividades();
        JOptionPane.showMessageDialog(null, "Atividades limpas com sucesso!");
    }

}