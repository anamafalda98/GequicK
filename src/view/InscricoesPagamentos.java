package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import models.Controlador;
import models.Evento;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InscricoesPagamentos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblPagamentos;
	private JTextField txtNumero;
	private JTextField txtNome;
	private JTextField txtEmail;
	private DefaultTableModel tableModel;

	public InscricoesPagamentos() {
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
		setBounds(100, 100, 885, 417);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// TABELA

		String[] colunas = { "Designação", "Data", "Hora", "Sala", "Tipologia", "Data limite", "Pagamento", "Valor" };
		tableModel = new DefaultTableModel(colunas, 0);
		tblPagamentos = new JTable();
		tblPagamentos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblPagamentos.setFillsViewportHeight(true);
		tblPagamentos.setBounds(23, 25, 824, 151);
		tblPagamentos.setModel(tableModel);
		JScrollPane scrollPane = new JScrollPane(tblPagamentos);
		scrollPane.setBounds(57, 14, 784, 167);
		contentPane.add(scrollPane);
		atualizarTabela();

		JLabel lblNmeroEstudante = new JLabel("N\u00FAmero:");
		lblNmeroEstudante.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNmeroEstudante.setBounds(231, 192, 71, 16);
		contentPane.add(lblNmeroEstudante);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNome.setBounds(231, 229, 56, 16);
		contentPane.add(lblNome);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		lblEmail.setBounds(231, 268, 56, 16);
		contentPane.add(lblEmail);

		txtNumero = new JTextField();
		txtNumero.setFont(new Font("Arial", Font.PLAIN, 14));
		txtNumero.setBounds(314, 189, 189, 22);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Arial", Font.PLAIN, 14));
		txtNome.setBounds(314, 227, 310, 22);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		txtEmail.setBounds(314, 266, 310, 22);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		JCheckBox checkPago = new JCheckBox("Pago");
		checkPago.setBackground(new Color(204, 204, 204));
		checkPago.setFont(new Font("Arial", Font.PLAIN, 16));
		checkPago.setBounds(231, 307, 113, 25);
		contentPane.add(checkPago);

		JButton btnAdicionar = new JButton("Inscrever");
		btnAdicionar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Verifica se está algum evento selecionado, caso contrario avisa o utilizador.
				if (tblPagamentos.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Escolha um evento.");
					
					//Verifica se todos os campos estão preenchidos, caso contrário avisa o utilizador.
				} else {
					if (txtNumero.getText().equals("") || txtNome.getText().equals("") || txtEmail.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
						
						//Adiciona uma nova inscrição.
					} else {
						int indexLinha = tblPagamentos.getSelectedRow();
						String nomeEvento = (String) tblPagamentos.getValueAt(indexLinha, 0);
						Evento evento = Controlador.procurarEventoPorNome(nomeEvento);
						boolean pagamento = false;
						if (checkPago.isSelected()) {
							pagamento = true;
						}
						try
						{
							int numero = Integer.parseInt(txtNumero.getText());
							boolean numeroInscricaoRepetido = Controlador.verificaNumeroInscricaoRepetido(numero, evento);
							if (numeroInscricaoRepetido == true)
							{
								JOptionPane.showMessageDialog(null, "Já existe uma inscrição com este número no evento selecionado");
							}
							else
							{
								Controlador.adicionarInscricao(Integer.parseInt(txtNumero.getText()), txtNome.getText(), txtEmail.getText(), pagamento, evento);
								JOptionPane.showMessageDialog(null, "Inscricao efetuada com sucesso!");
							}
						}
						//Avisa o utilizador caso este insira letras no campo do número.
						catch(NumberFormatException f)
						{
							JOptionPane.showMessageDialog(null, "Introduza apenas algarismos no campo do número.");
						}
					}
				}
			}
		});
		btnAdicionar.setBounds(406, 321, 113, 42);
		contentPane.add(btnAdicionar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\mafaa\\Desktop\\iconnnn.png"));
		label.setBounds(831, 335, 36, 35);
		contentPane.add(label);
	}

	//Atualiza a tabela.
	private void atualizarTabela() {

		tableModel.setRowCount(0);
		for (Evento evento : Controlador.getEventos()) {
			String designacao = evento.getDesignacao();
			String data = evento.getData();
			String hora = evento.getHora();
			String sala = evento.getSala().getBloco() + evento.getSala().getNumeroSala();
			String tipologia = evento.getTipologia().getDesignacao();
			String dataLimite = evento.getDataLimite();
			String pagamento = "";
			if (evento.getPagamento() == true) {
				pagamento = "PAGO";
			} else {
				pagamento = "NÃO PAGO";
			}
			int valor = evento.getValor();
			Object[] linha = { designacao, data, hora, sala, tipologia, dataLimite, pagamento, valor };
			tableModel.addRow(linha);

		}

	}
}
