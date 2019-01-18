package models;

public class TipoUtilizador {
	
	private int id;
	private String designacao;
	
	public TipoUtilizador(int id, String designacao) {
		super();
		this.id = id;
		this.designacao = designacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	@Override
	public String toString() {
		return this.designacao;
	}
	
	

}
