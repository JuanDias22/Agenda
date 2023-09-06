import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



class Contato {
    private String nome;
    private String telefone;
    private String email;

    public Contato(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }
    public String getNome() {
        return nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public String getEmail() {
        return email;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

class Agenda {
    private List<Contato> contatos;
    public Agenda() {
        contatos = new ArrayList<>();
    }
    public void adicionarContato(Contato contato) {
        contatos.add(contato);
    }
    public void excluirContato(Contato contato) {
        contatos.remove(contato);
    }
    public void listarContatos() {
        System.out.println("Lista de Contatos:");
        for (Contato contato : contatos) {
            System.out.println("Nome: " + contato.getNome());
            System.out.println("Telefone: " + contato.getTelefone());
            System.out.println("Email: " + contato.getEmail());

        }
    }
    public Contato buscarContato(String nome) {
        for (Contato contato : contatos) {
            if (contato.getNome().equals(nome)) {
                return contato;
            }
        }
        return null; // Contato nao encontrado
    }

    public void editarContato(String nome, Contato novoContato) {
        Contato contatoExistente = buscarContato(nome);
        if (contatoExistente != null) {
            contatoExistente.setNome(novoContato.getNome());
            contatoExistente.setTelefone(novoContato.getTelefone());
            contatoExistente.setEmail(novoContato.getEmail());
        }
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();
        JFrame janela = new JFrame("Agenda");
        Painel meuPainel = new Painel();


        while (true) {
            System.out.println("Escolha uma opcao:");

            System.out.println("1. Adicionar contato");

            System.out.println("2. Excluir contato");

            System.out.println("3. Listar contatos");

            System.out.println("4. Buscar contato");

            System.out.println("5. Editar contato");

            System.out.println("6. Sair");




            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha apos a leitura do numero



            switch (opcao) {
                case 1:
                    System.out.println("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.println("Telefone: ");
                    String telefone = scanner.nextLine();
                    System.out.println("Email: ");
                    String email = scanner.nextLine();
                    Contato novoContato = new Contato(nome, telefone, email);
                    agenda.adicionarContato(novoContato);
                    System.out.println("Contato adicionado.");

                    break;
                case 2:
                    System.out.println("Nome do contato a ser excluido: ");

                    String nomeExclusao = scanner.nextLine();
                    Contato contatoExclusao = agenda.buscarContato(nomeExclusao);
                    if (contatoExclusao != null) {
                        agenda.excluirContato(contatoExclusao);
                        System.out.println("Contato excluido.");
                    } else {

                        System.out.println("Contato nao encontrado.");
                    }
                    break;
                case 3:
                    agenda.listarContatos();
                    break;
                case 4:
                    System.out.println("Nome do contato a ser buscado: ");
                    String nomeBusca = scanner.nextLine();
                    Contato contatoBusca = agenda.buscarContato(nomeBusca);
                    if (contatoBusca != null) {
                        System.out.println("Contato encontrado:");
                        System.out.println("Nome: " + contatoBusca.getNome());
                        System.out.println("Telefone: " + contatoBusca.getTelefone());
                        System.out.println("Email: " + contatoBusca.getEmail());
                        System.out.println("---------------------------");
                    } else {
                        System.out.println("---------------------------");
                        System.out.println("Contato nao encontrado.");
                        System.out.println("---------------------------");
                    }
                    break;
                case 5:
                    System.out.println("Nome do contato a ser editado: ");
                    String nomeEdicao = scanner.nextLine();
                    Contato contatoEdicao = agenda.buscarContato(nomeEdicao);
                    if (contatoEdicao != null) {
                        System.out.println("Novo nome: ");
                        String novoNome = scanner.nextLine();
                        System.out.println("Novo telefone: ");
                        String novoTelefone = scanner.nextLine();
                        System.out.println("Novo email: ");
                        String novoEmail = scanner.nextLine();
                        Contato novoContatoEdicao = new Contato(novoNome, novoTelefone, novoEmail);
                        agenda.editarContato(nomeEdicao, novoContatoEdicao);
                        System.out.println("Contato editado.");
                    } else {
                        System.out.println("Contato nao encontrado.");
                    }
                    break;
                case 6:
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }

    private static class Painel {
    }
}