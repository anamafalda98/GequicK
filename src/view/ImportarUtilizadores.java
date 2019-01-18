package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import models.Controlador;
import models.Instalacao;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ImportarUtilizadores extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblInstalacoes;
	private JTextField txtBloco;
	private JTextField txtSala;
	private JTextField txtDescricao;
	private DefaultTableModel tableModel;

	public ImportarUtilizadores() {
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
		setBounds(100, 100, 655, 425);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		//TABELA
		
		
		String[] colunas = {"Bloco", "Sala", "Descrição"};
		tableModel = new DefaultTableModel(colunas, 0);
		tblInstalacoes = new JTable();
		tblInstalacoes.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblInstalacoes.addMouseListener(new MouseAdapter() {
			@Override
			//Preenche os campos respetivos ao selecionar um item da tabela.
			public void mouseClicked(MouseEvent arg0) {
				
				int indexLinha = tblInstalacoes.getSelectedRow();
				if (indexLinha != -1)
				{
					String bloco = (String) tblInstalacoes.getValueAt(indexLinha, 0);
					int sala = (int) tblInstalacoes.getValueAt(indexLinha, 1);
					String descricao = (String) tblInstalacoes.getValueAt(indexLinha, 2);
					
					txtBloco.setText(bloco);
					txtSala.setText(Integer.toString(sala));
					txtDescricao.setText(descricao);
					
					
				}
			}
		});
		tblInstalacoes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblInstalacoes.setFillsViewportHeight(true);
		tblInstalacoes.setBounds(31, 25, 809, 160);
		tblInstalacoes.setModel(tableModel);
		JScrollPane scrollPane = new JScrollPane(tblInstalacoes);
		scrollPane.setBounds(10, 11, 390, 180);
		contentPane.add(scrollPane);
		atualizarTabela();
		
		
		
		
		
		
		JLabel lblNewLabel = new JLabel("Bloco: ");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(60, 212, 59, 14);
		contentPane.add(lblNewLabel);
		
		txtBloco = new JTextField();
		txtBloco.setFont(new Font("Arial", Font.PLAIN, 14));
		txtBloco.setBounds(143, 210, 150, 20);
		contentPane.add(txtBloco);
		txtBloco.setColumns(10);
		
		JLabel lblSala = new JLabel("Sala:");
		lblSala.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSala.setBounds(60, 245, 46, 14);
		contentPane.add(lblSala);
		
		txtSala = new JTextField();
		txtSala.setFont(new Font("Arial", Font.PLAIN, 14));
		txtSala.setBounds(143, 243, 150, 20);
		contentPane.add(txtSala);
		txtSala.setColumns(10);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setFont(new Font("Arial", Font.PLAIN, 16));
		lblDescrio.setBounds(60, 279, 86, 18);
		contentPane.add(lblDescrio);
		
		txtDescricao = new JTextField();
		txtDescricao.setFont(new Font("Arial", Font.PLAIN, 14));
		txtDescricao.setBounds(143, 277, 150, 20);
		contentPane.add(txtDescricao);
		txtDescricao.setColumns(10);
		
		
		//ADICIONAR / EDITAR
		JButton btnAdicionareditar = new JButton("Adicionar/Editar");
		btnAdicionareditar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnAdicionareditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Verifica se algum item da tabela está selecionado.
				if (tblInstalacoes.getSelectedRow() == -1)
				{
					//Verifica se todos os campos estão preenchidos, caso contrário avisa o utilizador.
					if (txtBloco.getText().equals("") || txtDescricao.getText().equals("") || txtSala.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
					}
					
					else
					{
						int cont = 0;
						//Verifica se a instalação a ser inserida é repetida, caso for, avisa o utilizador.
						for (int i = 0; i < tblInstalacoes.getRowCount(); i++)
						{
							String bloco = (String) tblInstalacoes.getValueAt(i, 0);
							int sala = (int) tblInstalacoes.getValueAt(i, 1);
							
							if (txtBloco.getText().equals(bloco) && Integer.parseInt(txtSala.getText()) == sala)
							{
								cont++;
							}
						}
						
						//Adiciona uma nova instalação.
						if (cont == 0)
						{
							try
							{
								Controlador.adicionaInstalacao(txtBloco.getText(), Integer.parseInt(txtSala.getText()), txtDescricao.getText());
								atualizarTabela();
							}
							//Avisa o utilizador caso este insira letras no campo da sala.
							catch (NumberFormatException f)
							{
								JOptionPane.showMessageDialog(null, "Insira apensa algarismos no campo da sala.");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Já existe uma instalação com esse bloco e sala");
						}
					}
				}
				
				else
				{
					//Edita a instalação selecionada.
					try
					{
						int indexLinha = tblInstalacoes.getSelectedRow();
						Instalacao instalacao = Controlador.procurarInstalacao((String) tblInstalacoes.getValueAt(indexLinha, 0), Integer.parseInt(tblInstalacoes.getValueAt(indexLinha, 1).toString()));
						instalacao.setBloco(txtBloco.getText());
						instalacao.setSala(Integer.parseInt(txtSala.getText()));
						instalacao.setDescricao(txtDescricao.getText());
						atualizarTabela();
					}
					//Avisa o utilizador caso este insira letras no campo da sala.
					catch(NumberFormatException f)
					{
						JOptionPane.showMessageDialog(null, "Introduza apenas algarismos no campo da sala.");
					}
				}
			}
		});
		btnAdicionareditar.setBounds(215, 323, 150, 43);
		contentPane.add(btnAdicionareditar);
		
		
		//REMOVER 
		JButton btnRemover = new JButton("Remover");
		btnRemover.setFont(new Font("Arial", Font.PLAIN, 16));
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Verifica se existe alguma instalação selecionada, caso contrário avisa o utilizador.
				if (tblInstalacoes.getSelectedRow() == -1)
				{
					JOptionPane.showMessageDialog(null, "Selecione uma instalação a remover.");
				}
				//Remove a instalação selecionada.
				else
				{
					int indexLinha = tblInstalacoes.getSelectedRow();
					String bloco = (String) tblInstalacoes.getValueAt(indexLinha, 0);
					int sala = (int) tblInstalacoes.getValueAt(indexLinha, 1);
					Controlador.removerInstalacaoPorBlocoSala(bloco, sala);
					tableModel.removeRow(indexLinha);
				}
			}
		});
		btnRemover.setBounds(15, 323, 123, 43);
		contentPane.add(btnRemover);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\mafaa\\Desktop\\iconnnn.png"));
		label.setBounds(593, 340, 36, 35);
		contentPane.add(label);
	}

	//Atualiza a tabela.
	private void atualizarTabela() {
		
		tableModel.setRowCount(0);
		
		for (Instalacao i: Controlador.getInstalacoes())
		{
			String bloco = i.getBloco();
			int sala = i.getSala();
			String descricao = i.getDescricao();
			
			Object[] linha = {bloco, sala, descricao};
			tableModel.addRow(linha);
		}
		
	}
}
