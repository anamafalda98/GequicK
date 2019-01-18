package models;

public class CursoDepartamento {
	
	private int codigo;
	private String designacao;
	
	public CursoDepartamento(int codigo, String designacao) {
		super();
		this.codigo = codigo;
		this.designacao = designacao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
