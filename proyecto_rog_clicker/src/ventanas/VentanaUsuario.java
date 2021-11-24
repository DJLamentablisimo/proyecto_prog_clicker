package ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import clases.Usuario;

public class VentanaUsuario extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4153408173306883007L;
	private JLabel lUsuario;
	private JTextField tUsuario;
	private JLabel lContraseña;
	private JPasswordField tContraseña;
	private JLabel olvContraseña;
	private JButton botonIni;
	private JPanel panel;
	public static Usuario usuarioActual;
	
	public VentanaUsuario() {
		lUsuario = new JLabel("Usuario:");
		tUsuario = new JTextField();
		lContraseña = new JLabel("Contraseña:");
		tContraseña = new JPasswordField();
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
		ArrayList<Usuario> listaUsuarios = new ArrayList<>();
		Usuario u1 = new Usuario("Jorge","Alonso","JorgeAlonsoSuS","pepelotas1",100,1,1);
		Usuario u2 = new Usuario("David","Barrenechea","DavidBarrenechea","pepelotas2",200,2,2);
		Usuario u3 = new Usuario("Daniel","Galean","DJLamentablisimo","pepelotas3",432,2,1);
		Usuario u4 = new Usuario("Asier","Belloso","BellosoAsier","pepelotas4",500,1,25);
		listaUsuarios.add(u1);
		listaUsuarios.add(u2);
		listaUsuarios.add(u3);
		listaUsuarios.add(u4);
		
		botonIni.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(Usuario u : listaUsuarios) {
					String valorPass = new String(tContraseña.getPassword());
					if(u.getnUsuario().equals(tUsuario.getText()) && u.getContraseña().equals(valorPass)) {
						System.out.println(u);
						usuarioActual = u;
						System.out.println("Buenos dias Sr." + u.getnUsuario());
						VentanaClick.main(null);
						dispose();
					}else {
					}
				}
				
			}
		});
		
		add(panel, BorderLayout.CENTER);
	}
	public static void main(String[] args) {
		System.out.println("Empece");
		VentanaUsuario v = new VentanaUsuario();
		v.setSize(600, 200);
		v.setVisible(true);
		v.setTitle("Business Go Boom");
		v.setMinimumSize(v.getSize());
		v.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
