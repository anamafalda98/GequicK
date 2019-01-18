package models;

public class Instalacao {
	
	private String bloco;
	private int sala;
	private String descricao;
	
	public Instalacao(String bloco, int sala, String descricao) {
		super();
		this.bloco = bloco;
		this.sala = sala;
		this.descricao = descricao;
	}

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public int getSala() {
		return sala;
	}

	public void setSala(int sala) {
		this.sala = sala;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
