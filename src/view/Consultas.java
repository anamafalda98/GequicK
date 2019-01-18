package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import models.Controlador;
import models.CursoDepartamento;
import models.Evento;
import models.Inscricao;
import models.TipologiaEventos;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Consultas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblConsultas;
	private DefaultComboBoxModel<String> dcbmTipologia = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> dcbmCursosDepartamentos = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> dcbmEventos = new DefaultComboBoxModel<String>();
	private DefaultTableModel tableModelEventos;
	private DefaultTableModel tableModelEstudantes;
	private JTable tblEstudantes;

	public Consultas() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				//Abre a janela principal ao fechar esta.
				JanelaPrincipal janela = new JanelaPrincipal(Controlador.getUtilizador().getNome());
				janela.setVisible(true);
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\mafaa\\Desktop\\19621620_10208742207824998_1516590247_n.png"));
		setTitle("GEquicK");
	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 933, 406);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//RADIO BUTTONS
		JRadioButton rdbtnEventos = new JRadioButton("Eventos");
		rdbtnEventos.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbtnEventos.setBackground(new Color(204, 204, 204));
		
		rdbtnEventos.setBounds(56, 7, 109, 23);
		contentPane.add(rdbtnEventos);
		rdbtnEventos.setSelected(true);
		
		JRadioButton rdbtnEstudantes = new JRadioButton("Estudantes");
		rdbtnEstudantes.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbtnEstudantes.setBackground(new Color(204, 204, 204));
	
		rdbtnEstudantes.setBounds(667, 7, 109, 23);
		contentPane.add(rdbtnEstudantes);
		
		
		
		
		
		
		
		//COMBO BOX
		JComboBox<String> cmbTipologia = new JComboBox<String>();
		cmbTipologia.setFont(new Font("Arial", Font.PLAIN, 14));
		dcbmTipologia.addElement("TODAS");
		//Preenche a combo box.
		for (TipologiaEventos t: Controlador.getTipologiasEventos())
		{
			dcbmTipologia.addElement(t.getDesignacao());
		}
		cmbTipologia.setBounds(178, 42, 174, 22);
		cmbTipologia.setModel(dcbmTipologia);
		contentPane.add(cmbTipologia);
		
		
		
		JComboBox<String> cmbCursoDepartamento = new JComboBox<String>();
		cmbCursoDepartamento.setFont(new Font("Arial", Font.PLAIN, 14));
		dcbmCursosDepartamentos.addElement("TODOS");
		//Preenche a combo box.
		for (CursoDepartamento c: Controlador.getCursosDepartamentos())
		{
			dcbmCursosDepartamentos.addElement(c.getDesignacao());
		}
		cmbCursoDepartamento.setBounds(178, 94, 174, 22);
		cmbCursoDepartamento.setModel(dcbmCursosDepartamentos);
		contentPane.add(cmbCursoDepartamento);
		
		
		
		JComboBox<String> cmbEvento = new JComboBox<String>();
		cmbEvento.setFont(new Font("Arial", Font.PLAIN, 14));
		cmbEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Ao carregar no radio button dos estudantes, atualiza a tabela dos estudantes.
				if (rdbtnEstudantes.isSelected())
				{
					atualizarTabelaEstudantes(cmbEvento.getSelectedItem().toString());
				}
			}
		});
		
		dcbmEventos.addElement("TODOS");
		//Preenche a combo box.
		for (Evento e: Controlador.getEventos())
		{
			dcbmEventos.addElement(e.getDesignacao());
		}
		cmbEvento.setBounds(633, 43, 174, 21);
		cmbEvento.setModel(dcbmEventos);
		contentPane.add(cmbEvento);
		
		
		
		
		
		//TABELA
		
		String[] colunasEventos = {"Designação", "Data", "Hora", "Sala", "Tipologia", "Responsável", "Pago"};
		String[] colunasEstudantes = {"Numero", "Nome", "Pago"};
		tblConsultas = new JTable();
		tableModelEventos = new DefaultTableModel(colunasEventos, 0);
		tblConsultas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblConsultas.setFillsViewportHeight(true);
		tblConsultas.setBounds(10, 145, 525, 186);
		tblConsultas.setModel(tableModelEventos);
		JScrollPane scrollPaneConsultas = new JScrollPane(tblConsultas);
		scrollPaneConsultas.setBounds(10, 144, 534, 180);
		contentPane.add(scrollPaneConsultas);
		
		
		tblEstudantes = new JTable();
		tableModelEstudantes = new DefaultTableModel(colunasEstudantes, 0);
		tblEstudantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblEstudantes.setFillsViewportHeight(true);
		tblEstudantes.setBounds(574, 145, 289, 186);
		tblEstudantes.setModel(tableModelEstudantes);
		JScrollPane scrollPaneEstudantes = new JScrollPane(tblEstudantes);
		scrollPaneEstudantes.setBounds(554, 144, 353, 180);
		contentPane.add(scrollPaneEstudantes);
		tblEstudantes.setVisible(false);
		scrollPaneEstudantes.setVisible(false);
		
		
		
		
		//Atualiza a tabela conforme o radio button selecionado.
		if (rdbtnEventos.isSelected())
		{
		
			atualizarTabelaEventos(cmbCursoDepartamento.getSelectedItem().toString(), cmbTipologia.getSelectedItem().toString());
		}
		if (rdbtnEstudantes.isSelected())
		{
			atualizarTabelaEstudantes(cmbEvento.getSelectedItem().toString());
		}
		
		JLabel lblTipologia = new JLabel("Tipologia:");
		lblTipologia.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTipologia.setBounds(66, 45, 74, 19);
		contentPane.add(lblTipologia);
		
		JLabel lblCursoDepartamento = new JLabel("Curso / Departamento:");
		lblCursoDepartamento.setFont(new Font("Arial", Font.PLAIN, 16));
		lblCursoDepartamento.setBounds(10, 96, 169, 19);
		contentPane.add(lblCursoDepartamento);
		
		JLabel lblEvento = new JLabel("Evento:");
		lblEvento.setFont(new Font("Arial", Font.PLAIN, 16));
		lblEvento.setBounds(572, 42, 76, 23);
		contentPane.add(lblEvento);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\mafaa\\Desktop\\iconnnn.png"));
		label.setBounds(879, 324, 36, 35);
		contentPane.add(label);
		
		
		//Ao carregar no radio button dos eventos, atualiza e mostra a tabela dos eventos, escondendo a tabela dos estudantes.
		rdbtnEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tblConsultas.setVisible(true);
				scrollPaneConsultas.setVisible(true);
				atualizarTabelaEventos(cmbCursoDepartamento.getSelectedItem().toString(), cmbTipologia.getSelectedItem().toString());
				rdbtnEstudantes.setSelected(false);
				tblEstudantes.setVisible(false);
				scrollPaneEstudantes.setVisible(false);
			}
		});
		
		//Ao carregar no radio button dos estudantes, atualiza e mostra a tabela dos estudantes, escondendo a tabela dos eventos.
		rdbtnEstudantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tblEstudantes.setVisible(true);
				scrollPaneEstudantes.setVisible(true);
				atualizarTabelaEstudantes(cmbEvento.getSelectedItem().toString());
				rdbtnEventos.setSelected(false);
				tblConsultas.setVisible(false);
				scrollPaneConsultas.setVisible(false);
			}
		});
		
		//Ao escolher uma tipologia, atualiza a tabela dos eventos.
		cmbTipologia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (rdbtnEventos.isSelected())
				{
					atualizarTabelaEventos(cmbCursoDepartamento.getSelectedItem().toString(), cmbTipologia.getSelectedItem().toString());
				}
			}
		});
		
		//Ao selecionar um curso, atualiza a tabela dos eventos.
		cmbCursoDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (rdbtnEventos.isSelected())
				{
					atualizarTabelaEventos(cmbCursoDepartamento.getSelectedItem().toString(), cmbTipologia.getSelectedItem().toString());
				}
			}
		});
		
		
		
		
	}
	//Atualiza a tabela dos eventos.
	private void atualizarTabelaEventos(String cursoDepartamento, String tipologia) {
		
		tableModelEventos.setRowCount(0);
		for(Evento evento: Controlador.getEventos())
		{
			//Verifica os itens selecionados nas combo boxes e atualiza a tabela conforme.
			if((cursoDepartamento.equals("TODOS") && tipologia.equals("TODAS")) 
					|| (cursoDepartamento.equals(evento.getResponsavel().getDesignacao()) && tipologia.equals("TODAS")) 
					|| (cursoDepartamento.equals("TODOS") && tipologia.equals(evento.getTipologia().toString())) 
					|| (cursoDepartamento.equals(evento.getResponsavel().toString()) && tipologia.equals(evento.getTipologia().toString())))
			{
				String designacao = evento.getDesignacao();
				String data = evento.getData();
				String hora = evento.getHora();
				String sala = evento.getSala().getBloco() + evento.getSala().getNumeroSala();
				String tipologiaEvento = evento.getTipologia().getDesignacao();
				String responsavel = evento.getResponsavel().getDesignacao();
				String pagamento = "";
				if (evento.getPagamento() == true)
				{
					pagamento = "Sim";
				}
				else
				{
					pagamento = "Não";
				}
				Object[] linha = {designacao, data, hora, sala, tipologiaEvento, responsavel, pagamento};
				tableModelEventos.addRow(linha);
				
			}
		}
	}
	
	//Atualiza a tabela dos estudantes.
	private void atualizarTabelaEstudantes(String evento) {
	
		tableModelEstudantes.setRowCount(0);
		for (Inscricao inscricao: Controlador.getInscricoes())
		{
			Evento e = inscricao.getEvento();
			if (evento.equals(e.getDesignacao()) || evento.equals("TODOS"))
			{
				int numero = inscricao.getNumero();
				String nome = inscricao.getNome();
				String pagamento = "";
				if (inscricao.getPagamento() == true)
				{
					pagamento = "SIM";
				}
				else
				{
					pagamento = "NÃO";
				}
				Object[] linha = {numero, nome, pagamento};
				tableModelEstudantes.addRow(linha);
			}
		}
		
		
		}
}
