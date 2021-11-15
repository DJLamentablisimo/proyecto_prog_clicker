package ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaUsuario extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4153408173306883007L;
	private JLabel lUsuario;
	private JTextField tUsuario;
	private JLabel lContraseña;
	private JTextField tContraseña;
	private JLabel olvContraseña;
	private JButton botonIni;
	private JPanel panel;
	
	public VentanaUsuario() {
		lUsuario = new JLabel("Usuario:");
		tUsuario = new JTextField();
		lContraseña = new JLabel("Contraseña:");
		tContraseña = new JTextField();
		olvContraseña = new JLabel("¿¿¿Has olvidado tu contraseña???");
		botonIni = new JButton("Iniciar sesion");
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,2));
		panel.add(new JLabel());
		panel.add(new JLabel());
		panel.add(lUsuario);
		panel.add(tUsuario);
		panel.add(lContraseña);
		panel.add(tContraseña);
		panel.add(olvContraseña);
		panel.add(botonIni);
		
		add(panel, BorderLayout.CENTER);
	}
	public static void main(String[] args) {
		VentanaUsuario v = new VentanaUsuario();
		v.setSize(600, 400);
		v.setVisible(true);
		v.setTitle("Business Go Boom");
		v.setMinimumSize(v.getSize());
		v.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
