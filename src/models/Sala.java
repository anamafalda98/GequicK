package models;

public class Sala {
	
	private String bloco;
	private int numeroSala;
	
	public Sala(String bloco, int numeroSala) {
		super();
		this.bloco = bloco;
		this.numeroSala = numeroSala;
	}
	public String getBloco() {
		return bloco;
	}
	public void setBloco(String bloco) {
		this.bloco = bloco;
	}
	public int getNumeroSala() {
		return numeroSala;
	}
	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}
	@Override
	public String toString() {
		String linha = this.bloco + this.numeroSala;
		return linha;
	}
	
	
	

}
