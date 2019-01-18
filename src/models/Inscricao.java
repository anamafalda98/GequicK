package models;

public class Inscricao {
	
	private int numero;
	private String nome;
	private String Email;
	private boolean pagamento;
	private Evento evento;
	
	public Inscricao(int numero, String nome, String email, boolean pagamento, Evento evento) {
		super();
		this.numero = numero;
		this.nome = nome;
		Email = email;
		this.pagamento = pagamento;
		this.evento = evento;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public boolean getPagamento() {
		return pagamento;
	}

	public void setPagamento(boolean pagamento) {
		this.pagamento = pagamento;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	
	
	

}
