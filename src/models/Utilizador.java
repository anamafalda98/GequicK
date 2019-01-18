package models;

public class Utilizador {
	
	private String nome;
	private String login;
	private String password;
	private TipoUtilizador tipoUtilizador;
	private int numero;
	private String email;
	
	

	

	public Utilizador(String login, String password, TipoUtilizador tipoUtilizador, String nome, int numero, String email) {
		super();
		this.nome = nome;
		this.login = login;
		this.password = password;
		this.tipoUtilizador = tipoUtilizador;
		this.numero = numero;
		this.email = email;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public TipoUtilizador getTipoUtilizador() {
		return tipoUtilizador;
	}

	public void setTipoUtilizador(TipoUtilizador tipoUtilizador) {
		this.tipoUtilizador = tipoUtilizador;
	}
	

}
