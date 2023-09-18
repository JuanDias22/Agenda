import javax.swing.*; //Importação de biblioteca
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class AgendaSimulator extends JFrame { //JFrame -> janela de aplicativo Swing
    private Map<String, Contato> contatos; //Map -> armazena contatos usando nomes como chaves
    private DefaultListModel<String> listModel; //Modelo de lista que armazena os nomes dos contatos para exibição em uma lista na interface.
    private JList<String> listaContatos; //JList exibe os nomes dos contatos
    private JTextField nomeField; //JTextField -> interação do user pelo teclado
    private JTextField numeroField; //JTextField -> interação do user pelo teclado
    private JTextField emailField; //JTextField -> interação do user pelo teclado
    private String contatoSelecionado; //Variável que armazena o nome do contato selecionado na lista.


    public AgendaSimulator() { //Construtor

        contatos = new HashMap<>(); //HashMap é um conjunto de pares de chave-valor, para cada elemento (valor) salvo num HashMap deve existir uma chave única atrelada a ele
        listModel = new DefaultListModel<>(); //DefaultiListModel -> utilizado para criar um modelo de lista padrão
        listaContatos = new JList<>(listModel);

        setTitle("Agenda Simulator");
        setSize(500, 400); // largura x altura
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ação padrão para fechar programa após fechar janela
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(); // Cria um novo painel Swing chamado 'panel' para organizar componentes na interface gráfica.
        panel.setLayout(new GridBagLayout()); // Usar o GridBagLayout para maior controle de posicionamento
        panel.setBackground(Color.WHITE);

        GridBagConstraints constraints = new GridBagConstraints(); //Gerenciador de layout mais flexível e complexo
        GridBagConstraints constraints1 = new GridBagConstraints();
        constraints1.fill = GridBagConstraints.HORIZONTAL;
        constraints.fill = GridBagConstraints.HORIZONTAL; //.fil = preencher
        constraints1.insets = new Insets(5, 5, 5, 5);
        constraints.insets = new Insets(5, 5, 5, 5); // Espaçamento interno dos botões

        JButton adicionarButton = new JButton("Adicionar Contato");
        JButton listarButton = new JButton("Listar Contatos");
        JButton excluirButton = new JButton("Excluir Contato");
        JButton editarButton = new JButton("Editar Contato");
        JButton salvarButton = new JButton("Salvar Edições");

        adicionarButton.setPreferredSize(new Dimension(140, 30)); // Definir o tamanho dos botões
        listarButton.setPreferredSize(new Dimension(140, 30));
        excluirButton.setPreferredSize(new Dimension(140, 30));
        editarButton.setPreferredSize(new Dimension(140, 30));
        salvarButton.setPreferredSize(new Dimension(140, 30));

        nomeField = new JTextField();
        numeroField = new JTextField();
        emailField = new JTextField();

        adicionarButton.setBackground(Color.LIGHT_GRAY); //Definir cor dos botões
        listarButton.setBackground(Color.LIGHT_GRAY);
        excluirButton.setBackground(Color.LIGHT_GRAY);
        editarButton.setBackground(Color.LIGHT_GRAY);
        salvarButton.setBackground(Color.LIGHT_GRAY);

        nomeField.setBorder(BorderFactory.createTitledBorder("Nome do Contato")); //Adicionar borda no panel
        numeroField.setBorder(BorderFactory.createTitledBorder("Número do Contato"));
        emailField.setBorder(BorderFactory.createTitledBorder("Email do Contato"));


        panel.add(adicionarButton); //Adiciona botão
        panel.add(listarButton);
        panel.add(excluirButton);
        panel.add(editarButton);
        panel.add(salvarButton);
        panel.add(nomeField); //Adiciona painel
        panel.add(numeroField);
        panel.add(emailField);

        constraints1.gridy = 0; //Constraints (restrições) mantém os dados do usuário restritos
        constraints1.gridwidth = 2; // Expandir em duas colunas
        panel.add(nomeField, constraints1);

        constraints1.gridy = 1;
        panel.add(numeroField, constraints1);

        constraints1.gridy = 2;
        panel.add(emailField, constraints1);

        constraints.gridy = 3;
        panel.add(adicionarButton, constraints);

        constraints.gridy = 4;
        panel.add(listarButton, constraints);

        constraints.gridy = 5;
        panel.add(excluirButton, constraints);

        constraints.gridy = 6;
        panel.add(editarButton, constraints);

        constraints.gridy = 7;
        panel.add(salvarButton, constraints);


        add(new JScrollPane(listaContatos), BorderLayout.CENTER);
        add(panel, BorderLayout.EAST);

        setVisible(true);




        adicionarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String numero = numeroField.getText();
                String email = emailField.getText();
                if (!nome.isEmpty() && !numero.isEmpty() && !email.isEmpty()) {
                    Contato contato = new Contato(nome, numero, email);
                    contatos.put(nome, contato);
                    listModel.addElement(nome);
                    nomeField.setText("");
                    numeroField.setText("");
                    emailField.setText("");
                }
            }
        });

        listarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder lista = new StringBuilder("Contatos:\n");
                for (String nome : contatos.keySet()) {
                    Contato contato = contatos.get(nome);
                    lista.append("Nome: ").append(contato.getNome()).append("\n");
                    lista.append("Número: ").append(contato.getNumero()).append("\n");
                    lista.append("Email: ").append(contato.getEmail()).append("\n\n");
                }
                JOptionPane.showMessageDialog(null, lista.toString());
            }
        });

        excluirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listaContatos.getSelectedIndex();
                if (selectedIndex >= 0) {
                    String nomeExclusao = listModel.get(selectedIndex);
                    contatos.remove(nomeExclusao);
                    listModel.remove(selectedIndex);
                    JOptionPane.showMessageDialog(null, "Contato excluído: " + nomeExclusao);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um contato para excluir.");
                }
            }
        });

        editarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listaContatos.getSelectedIndex();
                if (selectedIndex >= 0) {
                    contatoSelecionado = listModel.get(selectedIndex);
                    Contato contato = contatos.get(contatoSelecionado);
                    nomeField.setText(contato.getNome());
                    numeroField.setText(contato.getNumero());
                    emailField.setText(contato.getEmail());
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um contato para editar.");
                }
            }
        });

        salvarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (contatoSelecionado != null) {
                    String nome = nomeField.getText();
                    String numero = numeroField.getText();
                    String email = emailField.getText();
                    Contato contato = new Contato(nome, numero, email);
                    contatos.put(nome, contato);
                    listModel.setElementAt(nome, listModel.indexOf(contatoSelecionado));
                    contatos.remove(contatoSelecionado);
                    contatoSelecionado = null;
                    nomeField.setText("");
                    numeroField.setText("");
                    emailField.setText("");
                }
            }
        });

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AgendaSimulator();
            }
        });
    }

    private class Contato {
        private String nome;
        private String numero;
        private String email;

        public Contato(String nome, String numero, String email) {
            this.nome = nome;
            this.numero = numero;
            this.email = email;
        }

        public String getNome() {
            return nome;
        }

        public String getNumero() {
            return numero;
        }

        public String getEmail() {
            return email;
        }
    }
}
