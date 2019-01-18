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
import models.CursoDepartamento;

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

public class CursosDepartamentos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblCursosDepartamentos;
	private JTextField txtCodigo;
	private JTextField txtDesignacao;
	private DefaultTableModel tableModel;

	public CursosDepartamentos() {
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
		setBounds(100, 100, 866, 439);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//TABELA
		
		String[] colunas = {"Código", "Designação"};
		tableModel = new DefaultTableModel(colunas, 0);
		tblCursosDepartamentos = new JTable();
		tblCursosDepartamentos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				//Preenche os campos respetivos ao clicar num curso ou departamento.
				int indexLinha = tblCursosDepartamentos.getSelectedRow();
				if (indexLinha != -1)
				{
				int codigo = (int) tblCursosDepartamentos.getValueAt(indexLinha, 0);
				String designacao = (String) tblCursosDepartamentos.getValueAt(indexLinha, 1);
				txtCodigo.setText(Integer.toString(codigo));
				txtDesignacao.setText(designacao);
				}
			}
		});
		tblCursosDepartamentos.setBounds(36, 33, 404, 151);
		tblCursosDepartamentos.setModel(tableModel);
		tblCursosDepartamentos.getColumnModel().getColumn(0).setPreferredWidth(36);
		contentPane.setLayout(null);
		tblCursosDepartamentos.setBorder(new LineBorder(new Color(0,0,0)));
		tblCursosDepartamentos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCursosDepartamentos.setPreferredScrollableViewportSize(new Dimension(500,60));
		tblCursosDepartamentos.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(tblCursosDepartamentos);
		scrollPane.setBounds(32, 10, 786, 253);
		contentPane.add(scrollPane);
		
		
		atualizarTabela();
		
		//REMOVER
		JButton btnRemover = new JButton("Remover");
		btnRemover.setFont(new Font("Arial", Font.PLAIN, 16));
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Verifica se existe algum curso ou departamento selecionado e informa o utilizador caso contrário.
				if (tblCursosDepartamentos.getSelectedRow() == -1)
				{
					JOptionPane.showMessageDialog(null, "Selecione um curso ou departamento a remover.");
				}
				//Remove o curso ou departamento selecionado.
				else
				{
					int indexLinha = tblCursosDepartamentos.getSelectedRow();
					int id = (int) tblCursosDepartamentos.getValueAt(indexLinha, 0);
					Controlador.removerCursoPorId(id);
					tableModel.removeRow(indexLinha);
				}
			}
		});
		btnRemover.setBounds(219, 333, 126, 46);
		contentPane.add(btnRemover);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo: ");
		lblCdigo.setFont(new Font("Arial", Font.PLAIN, 16));
		lblCdigo.setBounds(145, 277, 109, 19);
		contentPane.add(lblCdigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Arial", Font.PLAIN, 14));
		txtCodigo.setBounds(213, 276, 132, 19);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblDesignao = new JLabel("Designa\u00E7\u00E3o: ");
		lblDesignao.setFont(new Font("Arial", Font.PLAIN, 16));
		lblDesignao.setBounds(433, 276, 145, 23);
		contentPane.add(lblDesignao);
		
		txtDesignacao = new JTextField();
		txtDesignacao.setFont(new Font("Arial", Font.PLAIN, 14));
		txtDesignacao.setBounds(531, 277, 163, 20);
		contentPane.add(txtDesignacao);
		txtDesignacao.setColumns(10);
		
		
		//ADICIONAR / EDITAR
		JButton btnAdicionar = new JButton("Adicionar/Editar");
		btnAdicionar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(tblCursosDepartamentos.getSelectedRow() == -1)
				{
					//Verifica se todos os campos estão preenchidos.
					if (txtCodigo.getText().equals("") || txtDesignacao.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
					}
					
					else
					{
						//Verifica cursos ou departamentos repetidos.
						try
						{
							int contCodigo = 0;
							int contDesignacao = 0;
							for (int i = 0; i < tblCursosDepartamentos.getRowCount(); i++)
							{
								int codigo = (int) tblCursosDepartamentos.getValueAt(i, 0);
								String designacao = tblCursosDepartamentos.getValueAt(i, 1).toString();
								if (Integer.parseInt(txtCodigo.getText()) == codigo)
								{
									contCodigo++;
								}
								if (txtDesignacao.getText().equals(designacao))
								{
									contDesignacao++;
								}
							}
							//Adiciona um novo curso ou departamento.
							if (contCodigo == 0 && contDesignacao == 0)
							{
								Controlador.adicionaCursoDepartamento(Integer.parseInt(txtCodigo.getText()), txtDesignacao.getText());
								atualizarTabela();
							}
							else if (contDesignacao == 0 && contCodigo != 0)
							{
								JOptionPane.showMessageDialog(null, "Já existe um curso ou departamento com esse codigo.");
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Já exite um curso ou departamento com essa designaçao.");
							}
						}
						catch(NumberFormatException e)
						{
							JOptionPane.showMessageDialog(null, "Introduza apenas algarismos no campo do código.");
						}
					}
				}
				
				//Edita um curso ou departamento já existente.
				else
				{
					try
					{
						int indexLinha = tblCursosDepartamentos.getSelectedRow();
						CursoDepartamento cursoDepartamento = Controlador.procurarCursoId(Integer.parseInt(tblCursosDepartamentos.getValueAt(indexLinha, 0).toString()));
						cursoDepartamento.setCodigo(Integer.parseInt(txtCodigo.getText()));
						cursoDepartamento.setDesignacao(txtDesignacao.getText());
						atualizarTabela();
					}
					//Avisa o utilizador caso este insira letras no campo do código.
					catch(NumberFormatException e)
					{
						JOptionPane.showMessageDialog(null, "Introduza apenas algarismos no campo do código.");
					}
				}
			}
		});
		btnAdicionar.setBounds(433, 333, 163, 46);
		contentPane.add(btnAdicionar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\mafaa\\Desktop\\iconnnn.png"));
		label.setBounds(812, 357, 36, 35);
		contentPane.add(label);
	}

	/**
	 * Este métedo atualiza a tabela.
	 */
	private void atualizarTabela() {
		
		tableModel.setRowCount(0);
		for (CursoDepartamento cursoDepartamento: Controlador.getCursosDepartamentos())
		{
			int codigo = cursoDepartamento.getCodigo();
			String designacao = cursoDepartamento.getDesignacao();
			
			Object[] linha = {codigo, designacao};
			tableModel.addRow(linha);
		}
		
	}
}
