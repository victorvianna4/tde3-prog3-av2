package view;

import controller.FuncionarioController;
import model.*;

import java.util.Scanner;

public class FuncionarioView {
    private FuncionarioController controller;
    private Scanner scanner = new Scanner(System.in);

    public FuncionarioView(FuncionarioController controller) {
        this.controller = controller;
    }

    public void iniciar() {
        while (true) {
            System.out.println("1. Cadastrar Funcionário");
            System.out.println("2. Listar Funcionários");
            System.out.println("3. Atualizar Funcionário");
            System.out.println("4. Excluir Funcionário");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> atualizar();
                case 4 -> excluir();
                case 5 -> System.exit(0);
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrar() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Salário: ");
        double salario = scanner.nextDouble();

        System.out.println("Tipo de funcionário (1- Desenvolvedor, 2- Gerente, 3- Treinador, 4- GerenteDesenvolvedor): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        Funcionario funcionario = switch (tipo) {
            case 1 -> new Desenvolvedor(nome, salario);
            case 2 -> new Gerente(nome, salario);
            case 3 -> new Treinador(nome, salario);
            case 4 -> new GerenteDesenvolvedor(nome, salario);
            default -> null;
        };

        if (funcionario != null) {
            controller.cadastrarFuncionario(funcionario);
            System.out.println("Funcionário cadastrado com sucesso!");
        } else {
            System.out.println("Tipo inválido!");
        }
    }

    private void listar() {
        for (Funcionario f : controller.listarFuncionarios()) {
            System.out.println(f.getId() + ";" + f.getClass().getSimpleName() + ";" + f.getNome() + ";" + f.getSalario());
        }
    }

    private void atualizar() {
        System.out.print("ID do funcionário: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo salário: ");
        double salario = scanner.nextDouble();

        controller.atualizarFuncionario(id, nome, salario);
        System.out.println("Funcionário atualizado!");
    }

    private void excluir() {
        System.out.print("ID do funcionário: ");
        int id = scanner.nextInt();

        controller.excluirFuncionario(id);
        System.out.println("Funcionário excluído!");
    }
}
