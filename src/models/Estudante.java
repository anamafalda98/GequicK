package models;

public class Estudante {
	
	private String nome;
	private int numeroEstudante;
	
	public Estudante(String nome, int numeroEstudante) {
		super();
		this.nome = nome;
		this.numeroEstudante = numeroEstudante;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumeroEstudante() {
		return numeroEstudante;
	}

	public void setNumeroEstudante(int numeroEstudante) {
		this.numeroEstudante = numeroEstudante;
	}
	
	

}
