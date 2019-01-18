package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Controlador;
import models.TipoUtilizador;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Registar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUtilizador;
	private DefaultComboBoxModel<String> dcbm = new DefaultComboBoxModel<String>();
	private JTextField txtNome;
	private JTextField txtNumero;
	private JTextField txtEmail;
	private JPasswordField pswPassword;
	private JPasswordField pswRepetePassword;

	public Registar() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				Login login = new Login();
				login.setVisible(true);
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\mafaa\\Desktop\\19621620_10208742207824998_1516590247_n.png"));
		setTitle("GEquicK");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 482, 372);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUtilizador = new JLabel("Utilizador: ");
		lblUtilizador.setFont(new Font("Arial", Font.PLAIN, 16));
		lblUtilizador.setBounds(27, 77, 89, 14);
		contentPane.add(lblUtilizador); 

		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPassword.setBounds(27, 106, 89, 14);
		contentPane.add(lblPassword);

		JLabel lblRepitaAPassword = new JLabel("Repita a Password:");
		lblRepitaAPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		lblRepitaAPassword.setBounds(27, 133, 153, 17);
		contentPane.add(lblRepitaAPassword);
		
		
		

		// COMBO BOX

		JComboBox<String> cmbTipoUtilizador = new JComboBox<String>();
		cmbTipoUtilizador.setFont(new Font("Arial", Font.PLAIN, 14));
		cmbTipoUtilizador.setBounds(173, 160, 136, 20);
		dcbm.addElement("Tipo de Utilizador");
		//Preenche a ComboBox com os tipos de utilizadores existentes.
		for (int i = 0; i < Controlador.getTipoUtilizadores().size(); i++) {

			TipoUtilizador tipoUtilizador = Controlador.getTipoUtilizadores().get(i);
			dcbm.addElement(tipoUtilizador.getDesignacao());

		}
		cmbTipoUtilizador.setModel(dcbm);
		contentPane.add(cmbTipoUtilizador);

		
		
		
		
		
		JButton btnRegistar = new JButton("Registar");
		btnRegistar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnRegistar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				// Verifica se todos os campos estão preenchidos.
				if (txtUtilizador.getText().equals("") || pswPassword.getText().equals("")|| pswRepetePassword.getText().equals("")|| txtNumero.getText().equals("") || txtEmail.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
				}

				else {
					// Verifica se as duas passwords são iguais.
					if (!pswPassword.getText().equals(pswRepetePassword.getText())) {
						JOptionPane.showMessageDialog(null, "As passwords introduzidas não são iguais.");
					}

					else {
						// Verifica se o utilizador introduzido já existe.
						
						try{
							int numero = Integer.parseInt(txtNumero.getText());
							
							boolean utilizadorRepetido = Controlador.verificaUtilizadorRepetido(txtUtilizador.getText());
							boolean numeroRepetido = Controlador.verificaNumeroRepetido(numero);

							if (utilizadorRepetido == true) {
								JOptionPane.showMessageDialog(null, "Este utilizador já existe.");
							
							} 
							else if (numeroRepetido == true)
							{
								JOptionPane.showMessageDialog(null, "Este número já existe.");
							}
							else if (cmbTipoUtilizador.getSelectedItem().equals("Tipo de Utilizador"))
							{
								JOptionPane.showMessageDialog(null, "Escolha um tipo de utilizador.");
							}
							//Regista o novo utilizador e informa o utilizador do mesmo.	
							else {
								TipoUtilizador tipoUtilizador = Controlador.procurarTipoUtilizadorPorDesignacao(cmbTipoUtilizador.getSelectedItem().toString());

								Controlador.adicionaUtilizador(txtUtilizador.getText(), pswPassword.getText(), tipoUtilizador, txtNome.getText(), Integer.parseInt(txtNumero.getText()), txtEmail.getText());

								JOptionPane.showMessageDialog(null, "Utilizador registado com sucesso.");
								Login login = new Login();
								login.setVisible(true);
								dispose();
							}
						}
						//Se forem introduzidas letras no campo de preenchimento do número, informa o utilizador para preencher o campo com algarismos.
						catch (NumberFormatException f)
						{
							JOptionPane.showMessageDialog(null, "Introduza apenas algarismos no espaço para o número.");
						}
						

					}
				}
			}
		});
		btnRegistar.setBounds(173, 285, 121, 23);
		contentPane.add(btnRegistar);

		txtUtilizador = new JTextField();
		txtUtilizador.setFont(new Font("Arial", Font.PLAIN, 14));
		txtUtilizador.setBounds(173, 75, 153, 20);
		contentPane.add(txtUtilizador);
		txtUtilizador.setColumns(10);

		JLabel lblTipoDeUtilizador = new JLabel("Tipo de Utilizador:");
		lblTipoDeUtilizador.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTipoDeUtilizador.setBounds(27, 161, 136, 17);
		contentPane.add(lblTipoDeUtilizador);
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNome.setBounds(27, 45, 67, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Arial", Font.PLAIN, 14));
		txtNome.setBounds(173, 43, 275, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNmero.setBounds(27, 194, 61, 14);
		contentPane.add(lblNmero);
		
		JLabel lblEmail = new JLabel("E-mail: ");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		lblEmail.setBounds(27, 223, 67, 14);
		contentPane.add(lblEmail);
		
		txtNumero = new JTextField();
		txtNumero.setFont(new Font("Arial", Font.PLAIN, 14));
		txtNumero.setBounds(173, 192, 136, 20);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		txtEmail.setBounds(173, 221, 275, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		pswPassword = new JPasswordField();
		pswPassword.setBounds(173, 105, 153, 20);
		contentPane.add(pswPassword);
		
		pswRepetePassword = new JPasswordField();
		pswRepetePassword.setBounds(173, 133, 153, 20);
		contentPane.add(pswRepetePassword);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\mafaa\\Desktop\\iconnnn.png"));
		lblNewLabel.setBounds(428, 290, 36, 35);
		contentPane.add(lblNewLabel);

	}
}
