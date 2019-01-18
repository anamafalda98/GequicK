package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Controlador;
import models.Utilizador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUtilizador;
	private JPasswordField pswPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Carrega os arrays com o conteudo dos ficheiros.
					Controlador.CarregarFicheiros();
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\mafaa\\Desktop\\19621620_10208742207824998_1516590247_n.png"));
		
		
		
		String newstring = new SimpleDateFormat("dd").format(new Date());
		System.out.println(newstring); // 2011-01-18
		
		
		setTitle("GEquicK");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 427, 372);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 16));
		lblUsername.setBounds(76, 127, 104, 14);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPassword.setBounds(76, 167, 104, 14);
		contentPane.add(lblPassword);

		txtUtilizador = new JTextField();
		txtUtilizador.setFont(new Font("Arial", Font.PLAIN, 14));
		txtUtilizador.setBounds(174, 125, 160, 20);
		contentPane.add(txtUtilizador);
		txtUtilizador.setColumns(10);
		
		pswPassword = new JPasswordField();
		pswPassword.setBounds(174, 165, 160, 20);
		contentPane.add(pswPassword);

		//Abre a janela do registo.
		JButton btnRegistar = new JButton("Registar");
		btnRegistar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnRegistar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Registar registar = new Registar();
				registar.setVisible(true);
				dispose();

			}
		});
		btnRegistar.setBounds(76, 257, 104, 23);
		contentPane.add(btnRegistar);

		//BOTAO ENTRAR
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnEntrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {

				//Verifica se ambos os campos do login e da password estão preenchidos.
				if (txtUtilizador.getText().equals("") || pswPassword.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha ambos os campos.");
				}

				//Verifica as credenciais.
				else {

					int login = Controlador.Login(txtUtilizador.getText(), pswPassword.getText());
					
					if (login == 0)
					{
						JOptionPane.showMessageDialog(null, "O utilizador introduzido não existe em sistema.");
					}
					else if (login == 1)
					{
						JOptionPane.showMessageDialog(null, "A password introduzida está incorreta.");
					}
					else
					{
						//Define o utilizador que vai entrar no sistema e abre a janela principal.
						Utilizador utilizador = Controlador.procurarUtilizadorPorLogin(txtUtilizador.getText());
						Controlador.setUtilizador(utilizador);
						String nome = utilizador.getNome();
						JanelaPrincipal janela = new JanelaPrincipal(nome);
						janela.setVisible(true);
						dispose();
						
					}
					
					
				}

			}
		});
		btnEntrar.setBounds(246, 257, 89, 23);
		contentPane.add(btnEntrar);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\mafaa\\Desktop\\Sem t\u00EDtulo-1.png"));
		lblNewLabel.setBounds(24, 0, 350, 303);
		contentPane.add(lblNewLabel);
		
		
	}
}
