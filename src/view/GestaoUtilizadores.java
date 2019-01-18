package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import models.Controlador;
import models.TipoUtilizador;
import models.Utilizador;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GestaoUtilizadores extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblUtilizadores;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtNumero;
	private DefaultTableModel tableModel;
	private DefaultComboBoxModel<TipoUtilizador> dcbm = new DefaultComboBoxModel<TipoUtilizador>();


	public GestaoUtilizadores() {
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
		setBounds(100, 100, 889, 448);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//COMBO BOX
		
				JComboBox<TipoUtilizador> cmbTipoUtilizador = new JComboBox<TipoUtilizador>();
				cmbTipoUtilizador.setFont(new Font("Arial", Font.PLAIN, 14));
				//Preenche a combo box.
				for (TipoUtilizador t : Controlador.getTipoUtilizadores())
				{
					dcbm.addElement(t);
				}
				cmbTipoUtilizador.setBounds(307, 243, 175, 20);
				cmbTipoUtilizador.setModel(dcbm);
				contentPane.add(cmbTipoUtilizador);
				
				
				
		
		//TABELA
		String[] colunas = {"Nome", "Número", "E-mail", "Tipo de Utilizador"};
		tableModel = new DefaultTableModel(colunas,0);
		tblUtilizadores = new JTable();
		tblUtilizadores.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
		tblUtilizadores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int indexLinha = tblUtilizadores.getSelectedRow();
				
				//Verifica se existe algum utilizdor selecionado e preenche os campos respetivos do utilizador selecionado.
				if (indexLinha != -1)
				{
					String nome = (String) tblUtilizadores.getValueAt(indexLinha, 0);
					int numero = (int) tblUtilizadores.getValueAt(indexLinha, 1);
					String email = (String) tblUtilizadores.getValueAt(indexLinha, 2);
					String tipoUtilizador = (String) tblUtilizadores.getValueAt(indexLinha, 3);
					
					txtNome.setText(nome);
					txtNumero.setText(Integer.toString(numero));
					txtEmail.setText(email);
					
					//Seleciona o tipo de utilizador na combo box.
					for (int i = 0; i < cmbTipoUtilizador.getItemCount(); i++)
					{
						TipoUtilizador t = cmbTipoUtilizador.getItemAt(i);
						
						if (t.getDesignacao().equals(tipoUtilizador))
						{
							cmbTipoUtilizador.setSelectedItem(t);
						}
					}
					
				}
			}
		});
		tblUtilizadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblUtilizadores.setFillsViewportHeight(true);
		tblUtilizadores.setBounds(24, 25, 823, 180);
		tblUtilizadores.setModel(tableModel);
		tblUtilizadores.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(tblUtilizadores);
		scrollPane.setBounds(134, 11, 552, 180);
		contentPane.add(scrollPane);
		atualizarTabela();
		
		
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNome.setBounds(152, 221, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblTipoDeUtilizador = new JLabel("Tipo de Utilizador: ");
		lblTipoDeUtilizador.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTipoDeUtilizador.setBounds(152, 246, 143, 17);
		contentPane.add(lblTipoDeUtilizador);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		lblEmail.setBounds(152, 271, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNmero.setBounds(152, 296, 96, 14);
		contentPane.add(lblNmero);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Arial", Font.PLAIN, 14));
		txtNome.setBounds(307, 218, 351, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		txtEmail.setBounds(307, 268, 351, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtNumero = new JTextField();
		txtNumero.setFont(new Font("Arial", Font.PLAIN, 14));
		txtNumero.setBounds(307, 293, 175, 20);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);
		
		
		//EDITAR
		JButton btnAdicionareditar = new JButton("Editar");
		btnAdicionareditar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnAdicionareditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (tblUtilizadores.getSelectedRow() == -1)
				{
					JOptionPane.showMessageDialog(null, "Selecione um utilizador a editar.");
				}
				else
				{
					try
					{
						int indexLinha = tblUtilizadores.getSelectedRow();
						int numero = Integer.parseInt(tblUtilizadores.getValueAt(indexLinha, 1).toString());
						Utilizador utilizador = Controlador.procurarUtilizadorPorNumero(numero);
						utilizador.setEmail(txtEmail.getText());
						utilizador.setNome(txtNome.getText());
						utilizador.setNumero(Integer.parseInt(txtNumero.getText()));
						TipoUtilizador tipoUtilizador = (TipoUtilizador) cmbTipoUtilizador.getSelectedItem();
						utilizador.setTipoUtilizador(tipoUtilizador);
						atualizarTabela();
					}
					catch (NumberFormatException e)
					{
						JOptionPane.showMessageDialog(null, "Introduza apenas algarismos no campo do número.");
					}
				}
			}
		});
		
		btnAdicionareditar.setBounds(307, 343, 126, 43);
		contentPane.add(btnAdicionareditar);
		
		
		
		//REMOVER 
		JButton btnRemover = new JButton("Remover");
		btnRemover.setFont(new Font("Arial", Font.PLAIN, 16));
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(tblUtilizadores.getSelectedRow() == -1)
				{
					JOptionPane.showMessageDialog(null, "Selecione um utilizador a remover.");
				}
				else
				{
					int indexLinha = tblUtilizadores.getSelectedRow();
					int numero = (int) tblUtilizadores.getValueAt(indexLinha, 1);
					Controlador.removerUtilizadorPorNumero(numero);
					tableModel.removeRow(indexLinha);
				}
			}
		});
		btnRemover.setBounds(522, 343, 136, 43);
		contentPane.add(btnRemover);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\mafaa\\Desktop\\iconnnn.png"));
		label.setBounds(835, 366, 36, 35);
		contentPane.add(label);
	}


	private void atualizarTabela() {
		
		tableModel.setRowCount(0);
		
		for (Utilizador u : Controlador.getUtilizadores())
		{
			String nome = u.getNome();
			int numero = u.getNumero();
			String email = u.getEmail();
			String tipoUtilizador = u.getTipoUtilizador().getDesignacao();
			
			Object[] linha = {nome, numero, email, tipoUtilizador};
			tableModel.addRow(linha);
		}
		
	}
}
