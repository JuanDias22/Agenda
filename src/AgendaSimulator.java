import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class AgendaSimulator extends JFrame {
    private Map<String, Contato> contatos;
    private DefaultListModel<String> listModel;
    private JList<String> listaContatos;
    private JTextField nomeField;
    private JTextField numeroField;
    private JTextField emailField;

    private String contatoSelecionado;

    public AgendaSimulator() {
        contatos = new HashMap<>();
        listModel = new DefaultListModel<>();
        listaContatos = new JList<>(listModel);

        setTitle("Agenda Simulator");
        setSize(400, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton adicionarButton = new JButton("Adicionar Contato");

        JButton listarButton = new JButton("Listar Contatos");
        JButton excluirButton = new JButton("Excluir Contato");
        JButton editarButton = new JButton("Editar Contato");
        JButton salvarButton = new JButton("Salvar Edições");

        nomeField = new JTextField();
        numeroField = new JTextField();
        emailField = new JTextField();

        nomeField.setBorder(BorderFactory.createTitledBorder( "Nome do Contato"));
        nomeField.setBackground(Color.CYAN);
        numeroField.setBorder(BorderFactory.createTitledBorder("Número do Contato"));
        numeroField.setBackground(Color.CYAN);
        emailField.setBorder(BorderFactory.createTitledBorder("Email do Contato"));
        emailField.setBackground(Color.CYAN);

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

        panel.add(adicionarButton);
        panel.add(listarButton);
        panel.add(excluirButton);
        panel.add(editarButton);
        panel.add(salvarButton);
        panel.add(nomeField);
        panel.add(numeroField);
        panel.add(emailField);

        add(new JScrollPane(listaContatos), BorderLayout.CENTER);
        add(panel, BorderLayout.EAST);

        setVisible(true);
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
