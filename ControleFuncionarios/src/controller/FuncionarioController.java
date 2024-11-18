package controller;

import model.*;

import java.io.*;
import java.util.*;

public class FuncionarioController {

    private List<Funcionario> funcionarios = new ArrayList<>();
    private final String arquivo = "funcionarios.txt";
    private int proximoId = 1;
    private Set<Integer> idsDisponiveis = new TreeSet<>();

    public FuncionarioController() {
        carregarFuncionariosDoArquivo();
    }

    private void carregarFuncionariosDoArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                String tipo = dados[1];
                String nome = dados[2];
                double salario = Double.parseDouble(dados[3].replace(",", "."));

                Funcionario funcionario = criarFuncionario(tipo, nome, salario);
                if (funcionario != null) {
                    funcionario.setId(id);
                    funcionarios.add(funcionario);
                    proximoId = Math.max(proximoId, id + 1);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Criando um novo...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarFuncionariosNoArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            for (Funcionario funcionario : funcionarios) {
                writer.write(funcionario.getId() + ";" +
                        funcionario.getClass().getSimpleName() + ";" +
                        funcionario.getNome() + ";" +
                        String.format("%.2f", funcionario.getSalario()) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Funcionario criarFuncionario(String tipo, String nome, double salario) {
        return switch (tipo) {
            case "Desenvolvedor" -> new Desenvolvedor(nome, salario);
            case "Gerente" -> new Gerente(nome, salario);
            case "Treinador" -> new Treinador(nome, salario);
            case "GerenteDesenvolvedor" -> new GerenteDesenvolvedor(nome, salario);
            default -> null;
        };
    }

    private int gerarNovoId() {
        if (!idsDisponiveis.isEmpty()) {
            return idsDisponiveis.iterator().next();
        }
        return proximoId++;
    }

    public void cadastrarFuncionario(Funcionario funcionario) {
        funcionario.setId(gerarNovoId());
        idsDisponiveis.remove(funcionario.getId());
        funcionarios.add(funcionario);
        salvarFuncionariosNoArquivo();
    }

    public List<Funcionario> listarFuncionarios() {
        return new ArrayList<>(funcionarios);
    }

    public void atualizarFuncionario(int id, String nome, double salario) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId() == id) {
                funcionario.setNome(nome);
                funcionario.setSalario(salario);
                salvarFuncionariosNoArquivo();
                return;
            }
        }
        System.out.println("Funcionário com ID " + id + " não encontrado.");
    }

    public void excluirFuncionario(int id) {
        if (funcionarios.removeIf(funcionario -> funcionario.getId() == id)) {
            idsDisponiveis.add(id);
            salvarFuncionariosNoArquivo();
        } else {
            System.out.println("Funcionário com ID " + id + " não encontrado.");
        }
    }
}
