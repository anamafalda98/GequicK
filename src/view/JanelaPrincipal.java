package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Controlador;
import models.Utilizador;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JanelaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel contentPane_1;


	public JanelaPrincipal(String nome) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\mafaa\\Desktop\\19621620_10208742207824998_1516590247_n.png"));
		setTitle("GEquicK");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				//Guarda o conteúdo dos arrays nos respetivos ficheiros e informa o utilizador se o ficheiro não existir.
				try {
					Controlador.GuardarFicheiros();
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "O ficheiro não existe.");
				}
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 585, 380);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		mnMenu.setForeground(Color.DARK_GRAY);
		mnMenu.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		menuBar.add(mnMenu);
		
		JMenuItem mntmRegistoEvento = new JMenuItem("Registo de Eventos");
		mntmRegistoEvento.setFont(new Font("Arial", Font.PLAIN, 15));
		mntmRegistoEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Verifica se o utilizador tem permissões para aceder ao registo de eventos.
				Utilizador u = Controlador.getUtilizador();
				if (u.getTipoUtilizador().getDesignacao().equals("Secretariado"))
				{
					JOptionPane.showMessageDialog(null, "Não tem permissões para aceder a este conteúdo.");
				}
				//Abre a janela de registo de eventos.
				else
				{
					RegistoEventos registo = new RegistoEventos();
					registo.setVisible(true);
					dispose();
				}
			}
		});
		mnMenu.add(mntmRegistoEvento);
		
		JMenuItem mntmImportaoDeUtilizadores = new JMenuItem("Importa\u00E7\u00E3o de Utilizadores");
		mntmImportaoDeUtilizadores.setFont(new Font("Arial", Font.PLAIN, 15));
		mntmImportaoDeUtilizadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Verifica se o utilizador tem permissões para aceder à importação de utilizadores.
				Utilizador u = Controlador.getUtilizador();
				if (u.getTipoUtilizador().getDesignacao().equals("Gestor de Eventos") || u.getTipoUtilizador().getDesignacao().equals("Secretariado"))
				{
					JOptionPane.showMessageDialog(null, "Não tem permissões para aceder a este conteúdo.");
				}
				//Abre a janela de importação de utilizadores.
				else
				{
					ImportarUtilizadores importar = new ImportarUtilizadores();
					importar.setVisible(true);
					dispose();
				}
			}
		});
		
		//Abre a janela das inscrições e pagamentos.
		JMenuItem mntmInscriesEPagamentos = new JMenuItem("Inscri\u00E7\u00F5es e Pagamentos");
		mntmInscriesEPagamentos.setFont(new Font("Arial", Font.PLAIN, 15));
		mntmInscriesEPagamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				InscricoesPagamentos i = new InscricoesPagamentos();
				i.setVisible(true);
				dispose();
				
			}
		});
		
		//Abre a janela das consultas.
		JMenuItem mntmConsultas = new JMenuItem("Consultas");
		mntmConsultas.setFont(new Font("Arial", Font.PLAIN, 15));
		mntmConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Consultas consultas = new Consultas();
				consultas.setVisible(true);
				dispose();
			}
		});
		mnMenu.add(mntmConsultas);
		mnMenu.add(mntmInscriesEPagamentos);
		mnMenu.add(mntmImportaoDeUtilizadores);
		
		JMenu mnConfiguraes = new JMenu("Configura\u00E7\u00F5es");
		mnConfiguraes.setFont(new Font("Arial", Font.PLAIN, 15));
		mnMenu.add(mnConfiguraes);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Cursos e Departamentos");
		mntmNewMenuItem.setFont(new Font("Arial", Font.PLAIN, 15));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Verifica se o utilizador tem permissões para aceder aos cursos e departamentos.
				Utilizador u = Controlador.getUtilizador();
				if (u.getTipoUtilizador().getDesignacao().equals("Gestor de Eventos") || u.getTipoUtilizador().getDesignacao().equals("Secretariado"))
				{
					JOptionPane.showMessageDialog(null, "Não tem permissões para aceder a este conteúdo.");
				}
				//Abre a janela dos cursos e departamentos.
				else
				{
					CursosDepartamentos cursosDepartamentos = new CursosDepartamentos();
					cursosDepartamentos.setVisible(true);
					dispose();
				}
				
			}
		});
		
		JMenuItem mntmTipologiasDeEventos = new JMenuItem("Tipologias de Eventos");
		mntmTipologiasDeEventos.setFont(new Font("Arial", Font.PLAIN, 15));
		mntmTipologiasDeEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Verifica se o utilizador tem permissões para aceder às tipologias de eventos.
				Utilizador u = Controlador.getUtilizador();
				if (u.getTipoUtilizador().getDesignacao().equals("Gestor de Eventos") || u.getTipoUtilizador().getDesignacao().equals("Secretariado"))
				{
					JOptionPane.showMessageDialog(null, "Não tem permissões para aceder a este conteúdo.");
				}
				//Abre a janela das tipologias de eventos.
				else
				{
					TipologiasEventos tipologia = new TipologiasEventos();
					tipologia.setVisible(true);
					dispose();
				}
			}
		});
		mnConfiguraes.add(mntmTipologiasDeEventos);
		mnConfiguraes.add(mntmNewMenuItem);
		
		JMenuItem mntmUtilizadores = new JMenuItem("Utilizadores");
		mntmUtilizadores.setFont(new Font("Arial", Font.PLAIN, 15));
		mntmUtilizadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				//Verifica se o utilizador tem permissões para aceder aos utilizadores.
				Utilizador u = Controlador.getUtilizador();
				if (u.getTipoUtilizador().getDesignacao().equals("Gestor de Eventos") || u.getTipoUtilizador().getDesignacao().equals("Secretariado"))
				{
					JOptionPane.showMessageDialog(null, "Não tem permissões para aceder a este conteúdo.");
				}
				//Abre a janela dos utilizadores.
				else
				{
					GestaoUtilizadores g = new GestaoUtilizadores();
					g.setVisible(true);
					dispose();
				}
			}
		});
		mnConfiguraes.add(mntmUtilizadores);
		contentPane_1 = new JPanel();
		contentPane_1.setBackground(Color.LIGHT_GRAY);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);
		
		JLabel lblHello = new JLabel("Bem vindo(a), " + nome);
		lblHello.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblHello.setBounds(241, 29, 294, 42);
		contentPane_1.add(lblHello);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\mafaa\\Desktop\\Sem t\u00EDtulo-33.png"));
		lblNewLabel.setBounds(12, 171, 357, 134);
		contentPane_1.add(lblNewLabel);
	}
}
