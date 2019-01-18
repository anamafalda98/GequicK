package models;

public class Evento {
	
	private int codigo;
	private String designacao;
	private String data;
	private String hora;
	private Sala sala;
	private TipologiaEventos tipologia;
	private String dataLimite;
	private boolean pagamento;
	private int valor;
	private CursoDepartamento responsavel;
	
	
	public Evento(int codigo, String designacao, String data, String hora, Sala sala, TipologiaEventos tipologia,
			String dataLimite, boolean pagamento, int valor, CursoDepartamento responsavel) {
		super();
		this.codigo = codigo;
		this.designacao = designacao;
		this.data = data;
		this.hora = hora;
		this.sala = sala;
		this.tipologia = tipologia;
		this.dataLimite = dataLimite;
		this.pagamento = pagamento;
		this.valor = valor;
		this.responsavel = responsavel;
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



	public String getData() {
		return data;
	}



	public void setData(String data) {
		this.data = data;
	}



	public String getHora() {
		return hora;
	}



	public void setHora(String hora) {
		this.hora = hora;
	}



	public Sala getSala() {
		return sala;
	}



	public void setSala(Sala sala) {
		this.sala = sala;
	}



	public TipologiaEventos getTipologia() {
		return tipologia;
	}



	public void setTipologia(TipologiaEventos tipologia) {
		this.tipologia = tipologia;
	}



	public String getDataLimite() {
		return dataLimite;
	}



	public void setDataLimite(String dataLimite) {
		this.dataLimite = dataLimite;
	}



	public boolean getPagamento() {
		return pagamento;
	}



	public void setPagamento(boolean pagamento) {
		this.pagamento = pagamento;
	}



	public int getValor() {
		return valor;
	}



	public void setValor(int valor) {
		this.valor = valor;
	}



	public CursoDepartamento getResponsavel() {
		return responsavel;
	}



	public void setResponsavel(CursoDepartamento responsavel) {
		this.responsavel = responsavel;
	}



	@Override
	public String toString() {
		return this.designacao;
	}
	
	
	
	
	

}
