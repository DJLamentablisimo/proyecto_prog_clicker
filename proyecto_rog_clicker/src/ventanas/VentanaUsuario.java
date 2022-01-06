package ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import clases.Edificios;
import clases.Usuario;

public class VentanaUsuario extends JFrame{
	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger("ClaseContenedora");
	private static final long serialVersionUID = 4153408173306883007L;
	private JLabel lUsuario;
	private JTextField tUsuario;
	private JLabel lContraseña;
	private JPasswordField tContraseña;
	private JButton newUsuario;
	private JButton botonIni;
	private JPanel panel;
	public static Usuario usuarioActual;
	public ArrayList<Usuario> listaUsuarios;
	
	public VentanaUsuario() {
		ClaseContenedora cc = new ClaseContenedora();
		lUsuario = new JLabel("Usuario:");
		tUsuario = new JTextField();
		lContraseña = new JLabel("Contraseña:");
		tContraseña = new JPasswordField();
		newUsuario = new JButton("Crear nuevo Usuario");
		botonIni = new JButton("Iniciar sesion");
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,2));
		panel.add(new JLabel());
		panel.add(new JLabel());
		panel.add(lUsuario);
		panel.add(tUsuario);
		panel.add(lContraseña);
		panel.add(tContraseña);
		panel.add(newUsuario);
		panel.add(botonIni);
		listaUsuarios= cc.sacarUsuarios();
		
		
		
		botonIni.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(Usuario u : listaUsuarios) {
					String valorPass = new String(tContraseña.getPassword());
					if(u.getnUsuario().equals(tUsuario.getText()) && u.getContraseña().equals(valorPass)) {
						usuarioActual = u;
						System.out.println("Buenos dias Sr." + u.getnUsuario());
						ArrayList<Edificios> lyt = cc.sacarEdificios();
						for(int i = 0; i<lyt.size(); i++) {
							cc.añadirCantidades(u.getnUsuario(), i);
						}
						VentanaClick.main(null);
						dispose();
					}else {
					}
				}
				
			}
		});
		newUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String valorPass = new String(tContraseña.getPassword());
				ArrayList<String> noms = new ArrayList<>();
				for(Usuario u : listaUsuarios) {
					noms.add(u.getnUsuario());
				}
				if(tUsuario.getText().isBlank() || valorPass.isBlank()) {
					JOptionPane.showMessageDialog(null, "Por favor ponga el nombre de usuario que desea crear y/o su contraseña", "Crear usuario nuevo - Error", JOptionPane.INFORMATION_MESSAGE);
				}else if(noms.contains(tUsuario.getText())) {
					JOptionPane.showMessageDialog(null, "Este usuario ya existe", "Crear usuario nuevo - Error", JOptionPane.INFORMATION_MESSAGE);
				}else {
					int opcion = JOptionPane.showConfirmDialog(null, "¿Estas seguro de que quieres crear un usuario con nombre: "+tUsuario.getText()+ " y contraseña: "+valorPass+"?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(opcion==0) {
						cc.guardarDBUsuario(1, 1, 0, tUsuario.getText(), valorPass);
						ArrayList<Edificios> edifs = cc.sacarEdificios();
						for(Edificios j : edifs) {
							cc.guardaredificiosPersonales(tUsuario.getText(), j.getNombre());
						}
						listaUsuarios = cc.sacarUsuarios();
					}if(opcion==1) {
						
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
		v.setLocation(550, 300);
		v.setVisible(true);
		v.setTitle("Business Go Boom");
		v.setMinimumSize(v.getSize());
		v.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
