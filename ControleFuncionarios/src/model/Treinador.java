package model;

public class Treinador extends Funcionario implements Treina {
    public Treinador(String nome, double salario) {
        super(nome, salario);
    }

    @Override
    public void ensinarTecnologia() {
        System.out.println(getNome() + " está ensinando tecnologia.");
    }

    @Override
    public void motivarEquipe() {
        System.out.println(getNome() + " está motivando a equipe.");
    }

    @Override
    public void mostrarDetalhes() {
        System.out.println("Treinador: " + getNome() + ", Salário: " + getSalario());
    }
}