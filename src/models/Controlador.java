package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Controlador {

	private static ArrayList<Utilizador> utilizadores = new ArrayList<Utilizador>();
	private static ArrayList<TipoUtilizador> tipoUtilizadores = new ArrayList<TipoUtilizador>();
	private static ArrayList<CursoDepartamento> cursosDepartamentos = new ArrayList<CursoDepartamento>();
	private static ArrayList<TipologiaEventos> tipologiasEventos = new ArrayList<TipologiaEventos>();
	private static ArrayList<Instalacao> instalacoes = new ArrayList<Instalacao>();
	private static ArrayList<Evento> eventos = new ArrayList<Evento>();
	private static ArrayList<Inscricao> inscricoes = new ArrayList<Inscricao>();
	private static Utilizador utilizador;

	
	
	//GETTERS E SETTERS
	public static ArrayList<Inscricao> getInscricoes() {
		return inscricoes;
	}

	public static void setInscricoes(ArrayList<Inscricao> inscricoes) {
		Controlador.inscricoes = inscricoes;
	}

	public static ArrayList<Evento> getEventos() {
		return eventos;
	}

	public static void setEventos(ArrayList<Evento> eventos) {
		Controlador.eventos = eventos;
	}
	
	public static Utilizador getUtilizador() {
		return utilizador;
	}

	public static void setUtilizador(Utilizador utilizador) {
		Controlador.utilizador = utilizador;
	}
	
	public static ArrayList<CursoDepartamento> getCursosDepartamentos() {
		return cursosDepartamentos;
	}

	public static void setCursosDepartamentos(ArrayList<CursoDepartamento> cursosDepartamentos) {
		Controlador.cursosDepartamentos = cursosDepartamentos;
	}

	public static ArrayList<Utilizador> getUtilizadores() {
		return utilizadores;
	}

	public static ArrayList<Instalacao> getInstalacoes() {
		return instalacoes;
	}

	public static void setInstalacoes(ArrayList<Instalacao> instalacoes) {
		Controlador.instalacoes = instalacoes;
	}

	public static void setUtilizadores(ArrayList<Utilizador> utilizadores) {
		Controlador.utilizadores = utilizadores;
	}

	public static ArrayList<TipoUtilizador> getTipoUtilizadores() {
		return tipoUtilizadores;
	}

	public static void setTipoUtilizadores(ArrayList<TipoUtilizador> tipoUtilizadores) {
		Controlador.tipoUtilizadores = tipoUtilizadores;
	}
	
	public static ArrayList<TipologiaEventos> getTipologiasEventos() {
		return tipologiasEventos;
	}

	public static void setTipologiasEventos(ArrayList<TipologiaEventos> tipologiasEventos) {
		Controlador.tipologiasEventos = tipologiasEventos;
	}


	
	
	
	//FICHEIROS
	
	/**
	 * 
	 * Este metedo carrega o conteúdo dos ficheiros para os respetivos array
	 * lists.
	 * 
	 * @throws FileNotFoundException
	 */
	public static void CarregarFicheiros() throws FileNotFoundException {

		//TIPO DE UTILIZADOR
		File file = new File("tipoutilizadores.txt");
		Scanner inputTipoUtilizadores = new Scanner(file);
		while (inputTipoUtilizadores.hasNextLine()) {
			String linha = inputTipoUtilizadores.nextLine();
			String[] linhaSplit = linha.split("#");

			TipoUtilizador tipoUtilizador = new TipoUtilizador(Integer.parseInt(linhaSplit[0]), linhaSplit[1]);
			tipoUtilizadores.add(tipoUtilizador);
		}
		inputTipoUtilizadores.close();

		
		//UTILIZADORES
		file = new File("utilizadores.txt");
		Scanner inputUtilizadores = new Scanner(file);

		while (inputUtilizadores.hasNextLine()) {
			String linha = inputUtilizadores.nextLine();
			String[] linhaSplit = linha.split("#");

			for (TipoUtilizador tipo : tipoUtilizadores) {
				if (tipo.getId() == Integer.parseInt(linhaSplit[2])) {
					Utilizador utilizador = new Utilizador(linhaSplit[0], linhaSplit[1], tipo, linhaSplit[3], Integer.parseInt(linhaSplit[4]), linhaSplit[5]);
					utilizadores.add(utilizador);
				}

			}

		}
		inputUtilizadores.close();
		
		//CURSOS E DEPARTAMENTOS
		
		file = new File("cursosdepartamentos.txt");
		Scanner inputCursosDepartamentos = new Scanner(file);
		
		while (inputCursosDepartamentos.hasNextLine())
		{
			String linha = inputCursosDepartamentos.nextLine();
			String[] linhaSplit = linha.split("#");
			
			CursoDepartamento cursoDepartamento = new CursoDepartamento(Integer.parseInt(linhaSplit[0]), linhaSplit[1]);
			cursosDepartamentos.add(cursoDepartamento);
		}
		inputCursosDepartamentos.close();
		
		//TIPOLOGIAS EVENTOS
		
		file = new File("tipologias.txt");
		Scanner inputTipologiaEvento = new Scanner(file);
		
		while (inputTipologiaEvento.hasNextLine())
		{
			String linha = inputTipologiaEvento.nextLine();
			String[] linhaSplit = linha.split("#");
			
			TipologiaEventos tipologiaEventos = new TipologiaEventos(Integer.parseInt(linhaSplit[0]), linhaSplit[1]);
			tipologiasEventos.add(tipologiaEventos);
		}
		inputTipologiaEvento.close();
		
		//INSTALAÇÕES
		
		file = new File("instalacoes.txt");
		Scanner inputInstalacoes = new Scanner(file);
		
		while (inputInstalacoes.hasNextLine())
		{
			String linha = inputInstalacoes.nextLine();
			String[] linhaSplit = linha.split("#");
			
			Instalacao i = new Instalacao(linhaSplit[0], Integer.parseInt(linhaSplit[1]), linhaSplit[2]);
			instalacoes.add(i);
			
		}
		inputInstalacoes.close();
		
		//EVENTOS
		
		file = new File("eventos.txt");
		@SuppressWarnings("resource")
		Scanner inputEventos = new Scanner(file);
		
		while(inputEventos.hasNextLine())
		{
			String linha = inputEventos.nextLine();
			String[] linhaSplit = linha.split("#");
			int codigo = Integer.parseInt(linhaSplit[0]);
			String designacao = linhaSplit[1];
			String data = linhaSplit[2];
			String hora = linhaSplit[3];
			String[] sala = linhaSplit[4].split("-");
			Sala s = new Sala(sala[0], Integer.parseInt(sala[1]));
			TipologiaEventos tipologia = Controlador.procurarTipologiaId(Integer.parseInt(linhaSplit[5]));
			String dataLimite = linhaSplit[6];
			boolean pagamento = false;
			if (linhaSplit[7].equals("1"))
			{
				pagamento = true;
			}
			int valor = Integer.parseInt(linhaSplit[8]);
			int idResponsavel = Integer.parseInt(linhaSplit[9]);
			CursoDepartamento responsavel = Controlador.procurarCursoId(idResponsavel);
			Evento evento = new Evento(codigo, designacao, data, hora, s, tipologia, dataLimite, pagamento, valor, responsavel);
			eventos.add(evento);
			
			
		}
		
		//INSCRICOES
		
		file = new File("inscricoes.txt");
		Scanner inputInscricoes = new Scanner(file);
		while (inputInscricoes.hasNextLine())
		{
			String linha = inputInscricoes.nextLine();
			String[] linhaSplit = linha.split("#");
			int numero = Integer.parseInt(linhaSplit[0]);
			String nome = linhaSplit[1];
			String email = linhaSplit[2];
			boolean pagamento =  false;
			if (Integer.parseInt(linhaSplit[3]) == 1)
			{
				pagamento = true;
			}
			for (Evento e: eventos)
			{
				if (e.getCodigo() == Integer.parseInt(linhaSplit[4]))
				{
					Inscricao inscricao = new Inscricao(numero, nome, email, pagamento, e);
					inscricoes.add(inscricao);
				}
			}
		}
		inputInscricoes.close();
	}

	/**
	 * Este metedo guarda o conteúdo armazenado nos array lists para os ficheiros respetivos.
	 * @throws FileNotFoundException
	 */
	public static void GuardarFicheiros() throws FileNotFoundException
	{
		
		//UTILIZADORES
		
		File file = new File("utilizadores.txt");
		PrintWriter outUtilizadores = new PrintWriter(file);
		for (Utilizador utilizador: utilizadores)
		{
			String linha = utilizador.getLogin() + "#" + utilizador.getPassword() + "#" + utilizador.getTipoUtilizador().getId() + "#" + utilizador.getNome() + "#" + utilizador.getNumero() + "#" + utilizador.getEmail();
			outUtilizadores.println(linha);
		}
		outUtilizadores.close();
		
		
		//TIPO UTILIZADORES
		
		file = new File("tipoutilizadores.txt");
		PrintWriter outTipoUtilizadores = new PrintWriter(file);
		
		for (TipoUtilizador u: tipoUtilizadores)
		{
			String linha = u.getId() + "#" + u.getDesignacao();
			outTipoUtilizadores.println(linha);
		}
		outTipoUtilizadores.close();
		
		
		//CURSOS DEPARTAMENTOS
		
		file = new File("cursosdepartamentos.txt");
		PrintWriter outCursosDepartamentos = new PrintWriter(file);
		for (CursoDepartamento c: cursosDepartamentos)
		{
			outCursosDepartamentos.println(c.getCodigo() + "#" + c.getDesignacao());
		}
		outCursosDepartamentos.close();
		
		
		//TIPOLOGIAS EVENTOS
		
		file = new File("tipologias.txt");
		PrintWriter outTipologias = new PrintWriter(file);
		for (TipologiaEventos t: tipologiasEventos)
		{
			outTipologias.println(t.getCodigo() + "#" + t.getDesignacao());
		}
		outTipologias.close();
		
		
		
		//INSTALAÇÕES
		
		file = new File("instalacoes.txt");
		PrintWriter outInstalacoes = new PrintWriter(file);
		
		for (Instalacao i: instalacoes)
		{
			outInstalacoes.println(i.getBloco() + "#" + i.getSala() + "#" + i.getDescricao());
		}
		outInstalacoes.close();
		
		
		//EVENTOS
		
		file = new File("eventos.txt");
		PrintWriter outEventos = new PrintWriter(file);
		
		for (Evento e: eventos)
		{
			
			int codigo = e.getCodigo();
			String designacao = e.getDesignacao();
			String data = e.getData();
			String hora = e.getHora();
			String sala = e.getSala().getBloco() + "-" + e.getSala().getNumeroSala();
			int tipologia = e.getTipologia().getCodigo();
			String dataLimite = e.getDataLimite();
			int pagamento = 0;
			if (e.getPagamento() == true)
			{
				pagamento = 1;
			}
			int valor = e.getValor();
			int responsavel = e.getResponsavel().getCodigo();
			
			outEventos.println(codigo + "#" + designacao + "#" +  data + "#" +  hora + "#" + sala + "#" +  tipologia + "#" + dataLimite + "#" + pagamento + "#" + valor + "#" + responsavel);
		}
		outEventos.close();
		
		//INSCRIÇÕES
		
		file = new File("inscricoes.txt");
		PrintWriter outInscricoes = new PrintWriter(file);
		
		for (Inscricao i: inscricoes)
		{
			int numero = i.getNumero();
			String nome = i.getNome();
			String email = i.getEmail();
			int pagamento = 0;
			if (i.getPagamento() == true)
			{
				pagamento = 1;
			}
			int evento = i.getEvento().getCodigo();
			
			outInscricoes.println(numero + "#" + nome + "#" + email + "#" + pagamento + "#" + evento);
		}
		
		outInscricoes.close();
	}


	
	
	//VERIFICA

	/**
	 * 
	 *Este metedo verifica se um dado utilizador e uma dada password
	 *existem em sistema. Retorna 0 se o utilizador inserido não
	 *existir. Retorna 1 se o utilizador existir mas a password
	 *introduzida estiver incorreta. Retorna 2 se o utilizador existir
	 *e a password estiver correta.
	 * @param utilizador
	 * @param password
	 * @return
	 * 
	 */
	public static int Login(String utilizador, String password) {
		int login = 0;

		for (int i = 0; i < utilizadores.size(); i++) {

			Utilizador u = utilizadores.get(i);

			if (u.getLogin().equals(utilizador) && !u.getPassword().equals(password)) {
				login = 1;
			}

			else if (u.getLogin().equals(utilizador) && u.getPassword().equals(password)) {
				login = 2;
				break;
			}
		}

		return login;
	}
	
	/**
	 * Este metedo verifica se o utilizador introduzido ja existe. Retorna true se o utilizador já existir. Retorna false se o utilizador não existir.
	 * @param utilizador
	 * @return
	 */
	public static boolean verificaUtilizadorRepetido(String utilizador)

	{
		boolean repetido = false;
		for(Utilizador u : utilizadores)
		{
			if (u.getLogin().equals(utilizador))
			{
				repetido = true;
			}
		}
		return repetido;
	}
	
	/**
	 * Este metedo procura uma tipologia de evento pela sua designação e devolve o seu codigo. Se a tipologia não existir, devolve -1.
	 * @param designacao
	 * @return codigo
	 */
	public static int verificaTipologiaEventosPorNumero(String designacao)
	{
		for (TipologiaEventos t: tipologiasEventos)
		{
			if (t.getDesignacao().equals(designacao))
			{
				return t.getCodigo();
			}
		}
		return -1;
	}
	
	/**
	 * Este metodo verifica se já existe um utilizador com o número inserido.
	 * @param numero
	 * @return
	 */
	public static boolean verificaNumeroRepetido(int numero) {
		boolean repetido = false;
		
		for (Utilizador u: utilizadores)
		{
			if (u.getNumero() == numero)
			{
				repetido = true;
			}
		}
		return repetido;
	}
	
	/**
	 * Este metodo verifica se já existe um evento com o código inserido.
	 * @param codigo
	 * @return
	 */
	public static boolean verificaCodigoEventoRepetido(int codigo) {

		boolean repetido = false;
		for (Evento e: eventos)
		{
			if (e.getCodigo() == codigo)
			{
				repetido = true;
			}
		}
		return repetido;
	}
	
	/**
	 * Este metodo verifica se já existe um evento com a designação inserida.
	 * @param designacao
	 * @return
	 */
	public static boolean verificaDesignacaoEventoRepetida(String designacao) {

		boolean repetido = false;
		for (Evento e: eventos)
		{
			if (e.getDesignacao().equals(designacao))
			{
				repetido = true;
			}
		}
		return repetido;
	}
	
	/**
	 * Este metodo verifica se o número inserido já existe num determinado evento.
	 * @param numero
	 * @param evento
	 * @return
	 */
	public static boolean verificaNumeroInscricaoRepetido(int numero, Evento evento) {

		boolean repetido = false;
		for (Inscricao i: inscricoes)
		{
			if (i.getNumero() == numero && i.getEvento().getDesignacao().equals(evento.getDesignacao()))
			{
				repetido = true;
			}
		}
		return repetido;
	}
	
	
	
	
	
	// ADICIONAR
	
	/**
	 * Este metedo adiciona um novo curso ou departamento ao arraylist dos cursos e departamentos. Devolve um objeto da classe CursoDepartamento.
	 * @param codigo
	 * @param designacao
	 * @return
	 */
	public static CursoDepartamento adicionaCursoDepartamento(int codigo, String designacao)
	{
		CursoDepartamento cursoDepartamento = new CursoDepartamento(codigo, designacao);
		cursosDepartamentos.add(cursoDepartamento);
		return cursoDepartamento;
	}
	
	/**
	 * Este metodo adiciona uma nova tipologia de evento ao array list das tipologias de eventos. Devolve um objeto da classe TipologiaEventos.
	 * @param codigo
	 * @param designacao
	 * @return
	 */
	public static TipologiaEventos adicionarTipologiaEventos(int codigo, String designacao)
	{
		TipologiaEventos tipologia = new TipologiaEventos(codigo, designacao);
		tipologiasEventos.add(tipologia);
		return tipologia;
	}

	/**
	 * Este metodo adiciona um novo utilizador ao array list dos utilizadores.
	 * Devolve um objeto da classe Utilizador.
	 * @param utilizador
	 * @param password
	 * @param tipoUtilizador
	 * @return
	 */
	public static Utilizador adicionaUtilizador(String utilizador, String password, TipoUtilizador tipoUtilizador, String nome, int numero, String email)
	{
		Utilizador novoUtilizador = new Utilizador(utilizador, password, tipoUtilizador, nome, numero, email);
		utilizadores.add(novoUtilizador);
		return novoUtilizador;
	}
	
	/**
	 * Este metodo adiciona uma nova instalação ao array list das intalações.
	 * @param bloco
	 * @param sala
	 * @param descricao
	 */
	public static void adicionaInstalacao(String bloco, int sala, String descricao) {

		Instalacao i = new Instalacao(bloco, sala, descricao);
		instalacoes.add(i);
		
	}
	
	/**
	 *  Este metodo adiciona um novo evento ao array list dos eventos.
	 * @param codigo
	 * @param designacao
	 * @param data
	 * @param hora
	 * @param sala
	 * @param tipologia
	 * @param dataLimite
	 * @param pagamento
	 * @param valor
	 */
	public static void adicionarEvento(int codigo, String designacao, String data, String hora, Sala sala,
			TipologiaEventos tipologia, String dataLimite, boolean pagamento, int valor, CursoDepartamento responsavel) {
		
		Evento e = new Evento(codigo, designacao, data, hora, sala, tipologia, dataLimite, pagamento, valor, responsavel);
		eventos.add(e);
		
	}
	
	/**
	 * Este metodo adiciona uma nova inscrição ao array list das inscrições.
	 * @param numero
	 * @param nome
	 * @param email
	 * @param pagamento
	 * @param evento
	 */
	public static void adicionarInscricao(int numero, String nome, String email, boolean pagamento, Evento evento) {
		
		Inscricao inscricao = new Inscricao(numero, nome, email, pagamento, evento);
		inscricoes.add(inscricao);
		
	}
	
	
	
	//PROCURAR
	
	/**
	 * Este metedo devolve um objeto da classe CursoDepartamento, introduzindo o seu id.
	 * Se não existir nenhum curso ou departamento com o id introduzido, devolve null.
	 * @param codigo
	 * @return
	 */
	public static CursoDepartamento procurarCursoId(int codigo)
	{
		for (CursoDepartamento c : cursosDepartamentos)
		{
			if (c.getCodigo() == codigo)
			{
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Este metodo devole um objeto da classe TipologiaEventos, introduzindo o seu id.
	 * Se não existir nenhuma tipologia com o id introduzido, devolve null.
	 * @param codigo
	 * @return
	 */
	public static TipologiaEventos procurarTipologiaId(int codigo)
	{
		for (TipologiaEventos tipologia : tipologiasEventos)
			{
			if (tipologia.getCodigo() == codigo)
			{
				return tipologia;
			}
		}
		return null;
	}

	/**
	 * Este metodo procura um utilizador pelo login do mesmo.
	 * @param login
	 * @return
	 */
	public static Utilizador procurarUtilizadorPorLogin(String login) {
		
		for (Utilizador utilizador: utilizadores)
		{
			if (utilizador.getLogin().equals(login))
			{
				return utilizador;
			}
		}
		return null;
	}
	
	/**
	 * Este metodo procura um utilizador pelo número do mesmo.
	 * @param numero
	 * @return
	 */
	public static Utilizador procurarUtilizadorPorNumero(int numero) {

		for (Utilizador u: utilizadores)
		{
			if (u.getNumero() == numero)
			{
				return u;
			}
		}
		return null;
	}

	/**
	 * Este metodo procura uma instalação com um bloco e uma sala inseridas.
	 * @param bloco
	 * @param sala
	 * @return
	 */
	public static Instalacao procurarInstalacao(String bloco, int sala) {

		for (Instalacao i: instalacoes)
		{
			if (i.getBloco().equals(bloco) && i.getSala() == sala)
			{
				return i;
			}
		}
		return null;
	}
	
	/**
	 * Este metodo procura o evento pelo nome e devolve o mesmo. Se não existir um evento com o nome dado, devolve null.
	 * @param nomeEvento
	 * @return
	 */
	public static Evento procurarEventoPorNome(String nomeEvento) {
		
		for (Evento e: eventos)
		{
			if (e.getDesignacao().equals(nomeEvento))
			{
				return e;
			}
		}
		return null;
	}
	
	/**
	 * Este metodo procura um tipo de utilizador pela sua designação.
	 * @param designacao
	 * @return
	 */
	public static TipoUtilizador procurarTipoUtilizadorPorDesignacao(String designacao) {

		for (TipoUtilizador t: tipoUtilizadores)
		{
			if (t.getDesignacao().equals(designacao))
			{
				return t;
			}
		}
		return null;
	}
	
	/**
	 * Este metodo procura uma tipologia de evento pela sua designação.
	 * @param designacao
	 * @return
	 */
	public static TipologiaEventos procurarTipologiaDesignacao(String designacao) {

		for (TipologiaEventos t: tipologiasEventos)
		{
			if (t.getDesignacao().equals(designacao))
			{
				return t;
			}
		}
		return null;
	}
	
	
	/**
	 * Este metodo procura um curso ou departamento pela sua designação.
	 * @param designacao
	 * @return
	 */
	public static CursoDepartamento procurarCursoDesignacao(String designacao) {

		for (CursoDepartamento c: cursosDepartamentos)
		{
			if (c.getDesignacao().equals(designacao))
			{
				return c;
			}
		}
		return null;
	}
	
	
	
	//REMOVER
	
	/**
	 * Este metodo remove um curso ou departamento introduzindo o seu id.
	 * @param id
	 */
	public static void removerCursoPorId(int id) {

		for (int i = 0; i < cursosDepartamentos.size(); i++)
		{
			CursoDepartamento curso = cursosDepartamentos.get(i);
			
			if (curso.getCodigo() == id)
			{
				cursosDepartamentos.remove(curso);
			}
		}
	}
	
	
	/**
	 * Este metodo remove uma tipologia de evento introduzindo o seu id.
	 * @param id
	 */
	public static void removerTipologiaPorId(int id)
	{
  		for (int i = 0; i < tipologiasEventos.size(); i++)
  		{
  			TipologiaEventos tipologia = tipologiasEventos.get(i);
  			
  			if (tipologia.getCodigo() == id)
  			{
  				tipologiasEventos.remove(tipologia);
  			}
  		}
	}

	/**
	 * Este metodo remove uma instalação introduzindo o seu bloco e a sua sala.
	 * @param bloco
	 * @param sala
	 */
	public static void removerInstalacaoPorBlocoSala(String bloco, int sala) {


		for (int i = 0; i < instalacoes.size(); i++)
		{
			Instalacao instalacao = instalacoes.get(i);
			
			if (instalacao.getBloco().equals(bloco) && instalacao.getSala() == sala)
			{
				instalacoes.remove(instalacao);
			}
		}
		
	}

	/**
	 * Este metodo remove um utilizador introduzindo o seu número.
	 * @param numero
	 */
	public static void removerUtilizadorPorNumero(int numero) {


		for (int i = 0; i < utilizadores.size(); i++)
		{
			Utilizador u = utilizadores.get(i);
			
			if (u.getNumero() == numero)
			{
				utilizadores.remove(u);
			}
		}
		
	}

	


}
