package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import clases.Edificios;
import clases.Mejoras;
import clases.Usuario;


public class VentanaNewUsuario extends JFrame{

	private static final long serialVersionUID = -6454706567318509276L;
	private JPanel panelcitinio;
	private JPanel panelcitinio2;
	private JLabel nuFoto;
	private JLabel nuNombre;
	private JLabel nuPassword;
	private JTextField nuTextNombre;
	private JTextField nuTextPassword;
	private JButton nuAñadir_Aceptar;
	private JButton nuCancelar;
	
	public VentanaNewUsuario() {
		ClaseContenedora cc = new ClaseContenedora();
		panelcitinio = new JPanel();
		panelcitinio.setLayout(new GridLayout(1,2));
		panelcitinio2 = new JPanel();
		panelcitinio2.setLayout(new GridLayout(5,2));
		nuFoto = new JLabel();
		try {
		    Image img = ImageIO.read(getClass().getResource("fotoempre.jpg"));
		    nuFoto.setIcon(new ImageIcon(img));
		  } catch (Exception ex) {
		    System.out.println(ex);
		}
		nuNombre = new JLabel("Nombre de Usuario: ");
		nuPassword = new JLabel("Contraseña: ");
		nuTextNombre = new JTextField();
		nuTextPassword = new JTextField();
		nuAñadir_Aceptar = new JButton("Crear nuevo usuario");
		nuCancelar = new JButton("Cancelar");
		panelcitinio2.add(nuNombre);
		panelcitinio2.add(nuTextNombre);
		panelcitinio2.add(nuPassword);
		panelcitinio2.add(nuTextPassword);
		panelcitinio2.add(new JLabel());
		panelcitinio2.add(new JLabel());
		panelcitinio2.add(new JLabel());
		panelcitinio2.add(new JLabel());
		panelcitinio2.add(nuCancelar);
		panelcitinio2.add(nuAñadir_Aceptar);
		panelcitinio.add(nuFoto);
		panelcitinio.add(panelcitinio2);

		
		
		nuAñadir_Aceptar.addActionListener(new ActionListener() {
			
			@SuppressWarnings("unlikely-arg-type")
			@Override
			public void actionPerformed(ActionEvent hg) {
				ArrayList<Usuario> lista = cc.sacarUsuarios("Usuario.db");
				if(nuTextNombre.getText().isBlank() || nuTextPassword.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Por favor ponga el nombre de usuario que desea crear y/o su contraseña", "Crear usuario nuevo - Error", JOptionPane.INFORMATION_MESSAGE);
				}else if(lista.contains(nuTextNombre.getText())) {
					JOptionPane.showMessageDialog(null, "Este usuario ya existe", "Crear usuario nuevo - Error", JOptionPane.INFORMATION_MESSAGE);
				}else {
					int opcion = JOptionPane.showConfirmDialog(null, "¿Estas seguro de que quieres crear un usuario con nombre: "+nuTextNombre.getText()+ " y contraseña: "+nuTextPassword.getText()+"?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(opcion==0) {
						cc.guardarDBUsuario("Usuario.db",1, 1, 0, nuTextNombre.getText(), nuTextPassword.getText());
						ArrayList<Edificios> edifs = cc.sacarEdificios("Usuario.db");
						for(Edificios j : edifs) {
							cc.guardaredificiosPersonales("Usuario.db", nuTextNombre.getText(), j.getNombre());
						}
						ArrayList<Mejoras> mej = cc.sacarMejoras("Usuario.db");
						for(Mejoras m : mej) {
							cc.guardarMejorasPersonales("Usuario.db", nuTextNombre.getText(), m.getNombre());
						}
					}if(opcion==1) {
						
					}
				
				
			}
				VentanaUsuario.main(null);
				dispose();
			}
		});
		
	
		nuCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaSeleccionUsuario.main(null);
				dispose();
			}
		});
		add(panelcitinio);
	}
	public static void main(String[] args) {
		VentanaNewUsuario ventana5 = new VentanaNewUsuario();
		ventana5.setTitle("Ventana para añadir nuevo usuario");
		ventana5.setSize(1000, 500);
		ventana5.setLocation(550, 300);
		ventana5.setVisible( true );
		ventana5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana5.setResizable(false);
	}
	

}
