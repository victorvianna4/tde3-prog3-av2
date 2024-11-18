package model;

public class Desenvolvedor extends Funcionario implements Desenvolve {
    public Desenvolvedor(String nome, double salario) {
        super(nome, salario);
    }

    @Override
    public void codar() {
        System.out.println(getNome() + " está codando.");
    }

    @Override
    public void resolverProblemas() {
        System.out.println(getNome() + " está resolvendo problemas.");
    }

    @Override
    public void mostrarDetalhes() {
        System.out.println("Desenvolvedor: " + getNome() + ", Salário: " + getSalario());
    }
}
