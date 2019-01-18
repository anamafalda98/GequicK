package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import models.Controlador;
import models.TipologiaEventos;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TipologiasEventos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblTipologias;
	private JTextField txtCodigo;
	private JTextField txtDesignacao;
	private DefaultTableModel tableModel;

	public TipologiasEventos() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				//Abre a janela principal ao fechar esta.
				JanelaPrincipal janela = new JanelaPrincipal(Controlador.getUtilizador().getNome());
				janela.setVisible(true);
			}
		});
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\mafaa\\Desktop\\19621620_10208742207824998_1516590247_n.png"));
		setTitle("GEquicK");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 557, 411);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// TABELA

		String[] colunas = { "Código", "Designação" };
		tableModel = new DefaultTableModel(colunas, 0);
		tblTipologias = new JTable();
		tblTipologias.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				//Preenche os campos respetivos da tipologia selecionada.
				int indexLinha = tblTipologias.getSelectedRow();
				if (indexLinha != -1) {
					int codigo = (int) tblTipologias.getValueAt(indexLinha, 0);
					String designacao = (String) tblTipologias.getValueAt(indexLinha, 1);
					txtCodigo.setText(Integer.toString(codigo));
					txtDesignacao.setText(designacao);

				}
			}
		});
		tblTipologias.setBounds(26, 26, 816, 154);
		tblTipologias.setModel(tableModel);
		tblTipologias.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblTipologias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblTipologias.setPreferredScrollableViewportSize(new Dimension(500, 60));
		tblTipologias.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(tblTipologias);
		scrollPane.setBounds(37, 11, 393, 180);
		contentPane.add(scrollPane);
		atualizarTabela();

		// REMOVER
		JButton btnRemover = new JButton("Remover");
		btnRemover.setFont(new Font("Arial", Font.PLAIN, 16));
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				//Verifica se existe alguma tipologia selecionada, caso contrário avisa o utilizador.
				if (tblTipologias.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selecione uma tipologia.");
					//Remove a tipologia selecionada.
				} else {
					int indexLinha = tblTipologias.getSelectedRow();
					int id = (int) tblTipologias.getValueAt(indexLinha, 0);
					Controlador.removerTipologiaPorId(id);
					tableModel.removeRow(indexLinha);

				}
			}
		});
		btnRemover.setBounds(21, 307, 126, 43);
		contentPane.add(btnRemover);

		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setFont(new Font("Arial", Font.PLAIN, 16));
		lblCdigo.setBounds(35, 212, 59, 18);
		contentPane.add(lblCdigo);

		JLabel lblDesignao = new JLabel("Designa\u00E7\u00E3o: ");
		lblDesignao.setFont(new Font("Arial", Font.PLAIN, 16));
		lblDesignao.setBounds(35, 248, 112, 18);
		contentPane.add(lblDesignao);

		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Arial", Font.PLAIN, 14));
		txtCodigo.setBounds(137, 210, 180, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtDesignacao = new JTextField();
		txtDesignacao.setFont(new Font("Arial", Font.PLAIN, 14));
		txtDesignacao.setBounds(137, 246, 180, 20);
		contentPane.add(txtDesignacao);
		txtDesignacao.setColumns(10);

		// ADICIONAR

		JButton btnAdicionareditar = new JButton("Adicionar/Editar");
		btnAdicionareditar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnAdicionareditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (tblTipologias.getSelectedRow() == -1) {
					// Verifica se todos os campos estão preenchidos.
					if (txtCodigo.getText().equals("") || txtDesignacao.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
					} else {
						// Verifica tipologias de eventos repetidas e avisa o utilizador caso existam.
						try {
							int contCodigo = 0;
							int contDesignacao = 0;
							for (int i = 0; i < tblTipologias.getRowCount(); i++) {
								int codigo = (int) tblTipologias.getValueAt(i, 0);
								String designacao = tblTipologias.getValueAt(i, 1).toString();
								if (Integer.parseInt(txtCodigo.getText()) == codigo) {
									contCodigo++;
								}
								if (txtDesignacao.getText().equals(designacao)) {
									contDesignacao++;
								}
							}

							// Adiciona uma nova tipologia de eventos.
							if (contCodigo == 0 && contDesignacao == 0) {
								Controlador.adicionarTipologiaEventos(Integer.parseInt(txtCodigo.getText()),
										txtDesignacao.getText());
								atualizarTabela();
							} else if (contDesignacao == 0 && contCodigo != 0) {
								JOptionPane.showMessageDialog(null,
										"Já existe uma tipologia de eventos com esse código.");

							} else {
								JOptionPane.showMessageDialog(null,
										"Já existe uma tipologia de eventos com essa designação.");
							}
						} catch (NumberFormatException f) {
							JOptionPane.showMessageDialog(null, "Introduza apenas algarismos no campo do código.");
						}
					}
				} else {
					//Edita a tipologia selecionada.
					try
					{
						int indexLinha = tblTipologias.getSelectedRow();
						TipologiaEventos tipologia = Controlador.procurarTipologiaId(Integer.parseInt(tblTipologias.getValueAt(indexLinha, 0).toString()));
						tipologia.setCodigo(Integer.parseInt(txtCodigo.getText()));
						tipologia.setDesignacao(txtDesignacao.getText());
						atualizarTabela();
					}
					//Caso o utilizador insira letras no campo do código, avisa o mesmo.
					catch (NumberFormatException e)
					{
						JOptionPane.showMessageDialog(null, "Introduza apenas algarismos no campo do código.");
					}
				}
			}
		});
		btnAdicionareditar.setBounds(215, 307, 152, 43);
		contentPane.add(btnAdicionareditar);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\mafaa\\Desktop\\iconnnn.png"));
		label.setBounds(478, 315, 36, 35);
		contentPane.add(label);
	}

	//Atualiza a tabela.
	private void atualizarTabela() {
		tableModel.setRowCount(0);
		for (TipologiaEventos tipologia : Controlador.getTipologiasEventos()) {
			int codigo = tipologia.getCodigo();
			String designacao = tipologia.getDesignacao();

			Object[] linha = { codigo, designacao };
			tableModel.addRow(linha);
		}
	}
}
