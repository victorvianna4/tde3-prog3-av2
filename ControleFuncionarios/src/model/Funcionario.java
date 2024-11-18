package model;

public abstract class Funcionario {
    private static int idCounter = 1;
    private int id;
    private String nome;
    private double salario;

    public Funcionario(String nome, double salario) {
        this.id = idCounter++;
        this.nome = nome;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public abstract void mostrarDetalhes();

	public void setId(int id2) {
		// TODO Auto-generated method stub
		
	}
}

