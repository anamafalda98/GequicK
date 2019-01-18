package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Controlador;
import models.CursoDepartamento;
import models.Instalacao;
import models.Sala;
import models.TipologiaEventos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegistoEventos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtDesignacao;
	private JTextField txtValor;
	private DefaultComboBoxModel<String> dcmBloco = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> dcmTipologia = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> dcmResponsavel = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> dcmSala = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> dcmDia = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> dcmMes = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> dcmAno = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> dcmHora = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> dcmMinutos = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> dcmDiaLimite = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> dcmMesLimite = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> dcmAnoLimite = new DefaultComboBoxModel<String>();

	public RegistoEventos() {
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
		setBounds(100, 100, 445, 533);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo: ");
		lblCdigo.setFont(new Font("Arial", Font.PLAIN, 16));
		lblCdigo.setBounds(25, 26, 98, 23);
		contentPane.add(lblCdigo);
		
		JLabel lblDesignao = new JLabel("Designa\u00E7\u00E3o:");
		lblDesignao.setFont(new Font("Arial", Font.PLAIN, 16));
		lblDesignao.setBounds(25, 58, 111, 35);
		contentPane.add(lblDesignao);
		
		JLabel lblNewLabel = new JLabel("Data: ");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(25, 92, 72, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setFont(new Font("Arial", Font.PLAIN, 16));
		lblHora.setBounds(25, 125, 46, 14);
		contentPane.add(lblHora);
	
		JLabel lblBloco = new JLabel("Bloco: ");
		lblBloco.setFont(new Font("Arial", Font.PLAIN, 16));
		lblBloco.setBounds(25, 158, 56, 14);
		contentPane.add(lblBloco);
		
		JLabel lblSala = new JLabel("Sala:");
		lblSala.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSala.setBounds(25, 185, 46, 14);
		contentPane.add(lblSala);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(162, 28, 197, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtDesignacao = new JTextField();
		txtDesignacao.setBounds(162, 66, 197, 20);
		contentPane.add(txtDesignacao);
		txtDesignacao.setColumns(10);
		
		
		//COMBO BOXES
		
		
		//BLOCO
		
		JComboBox<String> cmbBloco = new JComboBox<String>();
		cmbBloco.setBounds(162, 156, 93, 20);
		dcmBloco.addElement("Bloco");
		//Preenche a combo box com os blocos.
		for (int i = 0; i < Controlador.getInstalacoes().size(); i++)
		{
			Instalacao instalacao = Controlador.getInstalacoes().get(i);
			int cont  = 0;
			//Verifica se o bloco a adicionar não é repetido.
			for (int j = 0; j < dcmBloco.getSize(); j++)
			{
				
				if (dcmBloco.getElementAt(j).toString().equals(instalacao.getBloco()))
				{
					cont++;
				}
			}
			if (cont == 0)
			{
				dcmBloco.addElement(instalacao.getBloco());
			}
			
		}
		cmbBloco.setModel(dcmBloco);
		contentPane.add(cmbBloco);
		
		
		
		
		//TIPOLOGIA
		
		JComboBox<String> cmbTipologia = new JComboBox<String>();
		cmbTipologia.setBounds(162, 230, 129, 20);
		dcmTipologia.addElement("Tipologia de Evento");
		
		//Preenche a combo box.
		
		for (TipologiaEventos t: Controlador.getTipologiasEventos())
		{
			dcmTipologia.addElement(t.getDesignacao());
		}
		cmbTipologia.setModel(dcmTipologia);
		contentPane.add(cmbTipologia);
		
		
		//RESPONSAVEL
		
		JComboBox<String> cmbResponsavel = new JComboBox<String>();
		cmbResponsavel.setBounds(162, 261, 129, 20);
		dcmResponsavel.addElement("Responsável");
		//Preenche a combo box.
		for (CursoDepartamento c: Controlador.getCursosDepartamentos())
		{
			dcmResponsavel.addElement(c.getDesignacao());
		}
		cmbResponsavel.setModel(dcmResponsavel);
		contentPane.add(cmbResponsavel);
		
		
		
		//SALA
		
		JComboBox<String> cmbSala = new JComboBox<String>();
		cmbSala.setBounds(162, 183, 92, 20);
		dcmSala.addElement("Sala");
		//Preenche a combo box.
		for (Instalacao i: Controlador.getInstalacoes())
		{
			dcmSala.addElement(Integer.toString(i.getSala()));
		}
		cmbSala.setModel(dcmSala);
		contentPane.add(cmbSala);
		
		
		
		//DIA
		
		JComboBox<String> cmbDia = new JComboBox<String>();
		cmbDia.setBounds(162, 94, 56, 20);
		dcmDia.addElement("Dia");
		//Preenche a combo box.
		for (int i = 0; i < 31; i++)
		{
			dcmDia.addElement(Integer.toString(i + 1));
		}
		cmbDia.setModel(dcmDia);
		contentPane.add(cmbDia);
		
		
		
		//MES
		
		JComboBox<String> cmbMes = new JComboBox<String>();
		cmbMes.setBounds(235, 94, 56, 20);
		dcmMes.addElement("Mês");
		//Preenche a combo box.
		for (int i = 0; i < 12; i++)
		{
			dcmMes.addElement(Integer.toString(i + 1));
		}
		cmbMes.setModel(dcmMes);
		contentPane.add(cmbMes);
		
		
		
		
		//ANO
		
		JComboBox<String> cmbAno = new JComboBox<String>();
		cmbAno.setBounds(303, 94, 56, 20);
		dcmAno.addElement("Ano");
		//Preenche a combo box.
		for (int i = 0; i < 4; i++)
		{
			dcmAno.addElement(Integer.toString(2017 + i));
		}
		cmbAno.setModel(dcmAno);
		contentPane.add(cmbAno);
		
		
		
		//HORA
		
		JComboBox<String> cmbHora = new JComboBox<String>();
		cmbHora.setBounds(162, 123, 56, 20);
		dcmHora.addElement("Hora");
		//Preenche a combo box.
		for (int i = 00; i < 24; i++)
		{
			dcmHora.addElement(Integer.toString(i));
		}
		cmbHora.setModel(dcmHora);
		contentPane.add(cmbHora);
		
		
		//MINUTOS
		
		JComboBox<String> cmbMinutos = new JComboBox<String>();
		cmbMinutos.setBounds(237, 123, 56, 20);
		dcmMinutos.addElement("Minutos");
		//Preenche a combo box.
		for (int i = 0; i < 60; i++)
		{
			dcmMinutos.addElement(Integer.toString(i));
		}
		cmbMinutos.setModel(dcmMinutos);
		contentPane.add(cmbMinutos);
		
		
		//DIA LIMITE
		
		JComboBox<String> cmbDiaLimite = new JComboBox<String>();
		cmbDiaLimite.setBounds(188, 297, 56, 20);
		dcmDiaLimite.addElement("Dia");
		//Preenche a combo box.
		for (int i = 0; i < 31; i++)
		{
			dcmDiaLimite.addElement(Integer.toString(i + 1));
		}
		cmbDiaLimite.setModel(dcmDiaLimite);
		contentPane.add(cmbDiaLimite);
		
		
		//MES LIMITE
		
		JComboBox<String> cmbMesLimite = new JComboBox<String>();
		cmbMesLimite.setBounds(256, 297, 56, 20);
		dcmMesLimite.addElement("Mês");
		//Preenche a combo box.
		for (int i = 0; i < 12; i++)
		{
			dcmMesLimite.addElement(Integer.toString(i + 1));
		}
		cmbMesLimite.setModel(dcmMesLimite);
		contentPane.add(cmbMesLimite);
		
		
		
		//ANO LIMITE
		
		JComboBox<String> cmbAnoLimite = new JComboBox<String>();
		cmbAnoLimite.setBounds(324, 297, 56, 20);
		dcmAnoLimite.addElement("Ano");
		//Preenche a combo box.
		for (int i = 0; i < 4; i++)
		{
			dcmAnoLimite.addElement(Integer.toString(2017 + i));
		}
		cmbAnoLimite.setModel(dcmAnoLimite);
		contentPane.add(cmbAnoLimite);
		
		
		JLabel lblTipologia = new JLabel("Tipologia: ");
		lblTipologia.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTipologia.setBounds(25, 228, 84, 23);
		contentPane.add(lblTipologia);
		
		JLabel lblResponsvel = new JLabel("Respons\u00E1vel: ");
		lblResponsvel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblResponsvel.setBounds(25, 259, 98, 23);
		contentPane.add(lblResponsvel);
		
		
		
		JLabel lblDataLimiteInscrioes = new JLabel("Data limite inscri\u00E7\u00F5es:");
		lblDataLimiteInscrioes.setFont(new Font("Arial", Font.PLAIN, 16));
		lblDataLimiteInscrioes.setBounds(25, 295, 166, 23);
		contentPane.add(lblDataLimiteInscrioes);
		
		JLabel lblEventoPag = new JLabel("Evento pago:");
		lblEventoPag.setFont(new Font("Arial", Font.PLAIN, 16));
		lblEventoPag.setBounds(25, 331, 147, 23);
		contentPane.add(lblEventoPag);
		
		JCheckBox checkPago = new JCheckBox("");
		checkPago.setBounds(162, 331, 97, 23);
		contentPane.add(checkPago);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setFont(new Font("Arial", Font.PLAIN, 16));
		lblValor.setBounds(25, 383, 46, 14);
		contentPane.add(lblValor);
		
		
		//SLIDER
		JSlider sldValor = new JSlider();
		sldValor.setValue(50);
		sldValor.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			//Introduz os valores na caixa de texto ao mexer o slider.
			public void mouseDragged(MouseEvent arg0) {
				
				txtValor.setText(String.valueOf(sldValor.getValue()));
			}
		});
		sldValor.setPaintTicks(true);
		sldValor.setMinimum(1);
		sldValor.setMaximum(100);
		sldValor.setBounds(162, 383, 93, 26);
		contentPane.add(sldValor);
		
		txtValor = new JTextField();
		txtValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (!txtValor.getText().equals(""))
				{
					try{
						
						//Verifica se o valor introduzido esta entre 0 e 100.
						if (Integer.parseInt(txtValor.getText()) <= 0 && Integer.parseInt(txtValor.getText()) >= 100)
						{
							JOptionPane.showMessageDialog(null, "Introduza um número entre 1 e 100");
						}
						//Atualiza o slider com o valor introduzido.
						else
						{
							sldValor.setValue(Integer.parseInt(txtValor.getText()));
						}
					}
					//Avisa o utilizador caso este esteja a inserir letras no campo do valor.
					catch (NumberFormatException e)
					{
						JOptionPane.showMessageDialog(null, "Insira apenas algarismos");
					}
				}
			}
		});
		txtValor.setBounds(279, 381, 101, 28);
		contentPane.add(txtValor);
		txtValor.setColumns(10);
		
		JButton btnRegistarEvento = new JButton("Registar Evento");
		btnRegistarEvento.setFont(new Font("Arial", Font.PLAIN, 16));
		btnRegistarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				//Verifica se em todas as combo boxes foi selecionado um item, se não, avisa o utilizador.
				if (txtCodigo.getText().equals("") || txtDesignacao.getText().equals("") || txtValor.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
				}
				else if (cmbDia.getSelectedItem().toString().equals("Dia") || cmbMes.getSelectedItem().toString().equals("Mês") || cmbAno.getSelectedItem().toString().equals("Ano"))
				{
					JOptionPane.showMessageDialog(null, "Preencha a data do evento.");
				}
				else if (cmbHora.getSelectedItem().toString().equals("Hora") || cmbMinutos.getSelectedItem().toString().equals("Minutos"))
				{
					JOptionPane.showMessageDialog(null, "Preencha a hora do evento.");
				}
				else if (cmbBloco.getSelectedItem().toString().equals("Bloco"))
				{
					JOptionPane.showMessageDialog(null, "Escolha um bloco.");
				}
				else if (cmbSala.getSelectedItem().toString().equals("Sala"))
				{
					JOptionPane.showMessageDialog(null, "Escolha uma sala.");
				}
				else if (cmbTipologia.getSelectedItem().toString().equals("Tipologia de Evento"))
				{
					JOptionPane.showMessageDialog(null, "Escolha uma tipologia de evento.");
				}
				else if (cmbResponsavel.getSelectedItem().toString().equals("Responsável"))
				{
					JOptionPane.showMessageDialog(null, "Escolha um responsável.");
				}
				else if (cmbDiaLimite.getSelectedItem().toString().equals("Dia") || cmbMesLimite.getSelectedItem().toString().equals("Mês") || cmbAnoLimite.getSelectedItem().toString().equals("Ano"))
				{
					JOptionPane.showMessageDialog(null, "Preencha a data limite das inscrições para o evento.");
				}
				else
				{
					try
					{
						int codigo = Integer.parseInt(txtCodigo.getText());
						String designacao = txtDesignacao.getText();
						
						//Verifica se já existe um evento com o código e designação introduzidos.
						boolean codigoRepetido = Controlador.verificaCodigoEventoRepetido(codigo);
						boolean designacaoRepetida = Controlador.verificaDesignacaoEventoRepetida(designacao);
						
						
						
						if (codigoRepetido == true)
						{
							JOptionPane.showMessageDialog(null, "Já existe um evento com este código");
						}
						else if (designacaoRepetida == true)
						{
							JOptionPane.showMessageDialog(null, "Já existe um evento com essa designação.");
						}
						//Adiciona o novo evento e informa o utilizador do mesmo.
						else
						{
					
							String data = cmbDia.getSelectedItem().toString() + "-" + cmbMes.getSelectedItem().toString() + "-" + cmbAno.getSelectedItem().toString();
							String hora = cmbHora.getSelectedItem().toString() + ":" + cmbMinutos.getSelectedItem().toString();
							Sala sala = new Sala(cmbBloco.getSelectedItem().toString(), Integer.parseInt(cmbSala.getSelectedItem().toString()));
							TipologiaEventos tipologia = Controlador.procurarTipologiaDesignacao(cmbTipologia.getSelectedItem().toString());
							String dataLimite = cmbDiaLimite.getSelectedItem().toString() + "-" + cmbMesLimite.getSelectedItem().toString() + "-" + cmbAnoLimite.getSelectedItem().toString();
							boolean pagamento = false;
							if (checkPago.isSelected())
							{
								pagamento = true;
							}
							int valor = Integer.parseInt(txtValor.getText());
							CursoDepartamento responsavel = Controlador.procurarCursoDesignacao(cmbResponsavel.getSelectedItem().toString());
							Controlador.adicionarEvento(codigo, designacao, data, hora, sala, tipologia, dataLimite, pagamento, valor, responsavel);
							JOptionPane.showMessageDialog(null, "Evento registado com sucesso!");
					}
					}
					//Avisa o utilizador caso este introduza letras no campo do código.
					catch(NumberFormatException e)
					{
						JOptionPane.showMessageDialog(null, "Insira apensa algarismos no campo do codigo");
					}
				}
				
			}
		});
		btnRegistarEvento.setBounds(36, 442, 166, 35);
		contentPane.add(btnRegistarEvento);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\mafaa\\Desktop\\iconnnn.png"));
		label.setBounds(391, 451, 36, 35);
		contentPane.add(label);
		
		
	}
}
