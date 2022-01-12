package ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import clases.Edificios;
import clases.Usuario;

public class VentanaUsuario extends JFrame{
	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger("ClaseContenedora");
	private static final long serialVersionUID = 4153408173306883007L;
	private JPasswordField passwordo;
	private JButton botonIni;
	private JPanel panel;
	private JPanel panelInf;
	public static Usuario usuarioActual;
	public ArrayList<Usuario> listaUsuarios;
	private DefaultTableModel mDatos;  
	private JTable tDatos; 
	
	@SuppressWarnings("serial")
	public VentanaUsuario() {
		setSize(600, 400);
		ClaseContenedora cc = new ClaseContenedora();
		passwordo = new JPasswordField();
		botonIni = new JButton("Iniciar sesion");
		tDatos = new JTable();
		listaUsuarios= cc.sacarUsuarios("Usuario.db");
		panel = new JPanel();
		Vector<String> cabeceras = new Vector<String>( Arrays.asList( "Usuario") );
		mDatos = new DefaultTableModel( new Vector<Vector<Object>>(),  cabeceras ) {
			public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
			
		};
		for (Usuario p : listaUsuarios) {
			mDatos.addRow( new Object[] { p.getnUsuario() } );
		}
		tDatos.setModel( mDatos );
		tDatos.getColumnModel().getColumn(0).setMinWidth(getWidth()-20);
		tDatos.getColumnModel().getColumn(0).setMaxWidth(getWidth()-20);
		panel.add(new JLabel("Usuarios del Business Clicker"));
		panel.add(tDatos);
		panelInf = new JPanel();
		panelInf.setLayout(new GridLayout(2,2));
		panelInf.add(new JLabel("Contraseña:".toUpperCase()));
		panelInf.add(new JLabel(""));
		panelInf.add(passwordo);
		panelInf.add(botonIni);
		
		
		
		
		botonIni.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int column = 0;
				int fila = tDatos.getSelectedRow();
				String usuarito = tDatos.getModel().getValueAt(fila, column).toString();
				for(Usuario u : listaUsuarios) {
					String valorPass = new String(passwordo.getPassword());
					if(u.getnUsuario().equals(usuarito) && u.getContraseña().equals(valorPass)) {
						usuarioActual = u;
						ArrayList<Edificios> lyt = cc.sacarEdificios("Usuario.db");
						for(int i = 0; i<lyt.size(); i++) {
							cc.añadirCantidades("Usuario.db",u.getnUsuario(), i);
						}
						VentanaClick.main(null);
						dispose();
					}else {
					}
				}
				
			}
		});
		
		
		add(panel, BorderLayout.CENTER);
		add(panelInf, BorderLayout.SOUTH);
	}
	public static void main(String[] args) {
		System.out.println("Empece");
		VentanaUsuario v = new VentanaUsuario();
		v.setLocation(550, 300);
		v.setVisible(true);
		v.setTitle("Business Go Boom");
		v.setResizable(false);
		v.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
