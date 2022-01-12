package ventanas;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaSeleccionUsuario extends JFrame{
 
	private static final long serialVersionUID = -5746138941479938049L;
	private JButton boton;
	private JButton boton2;
	private JPanel panelito;
	
	public VentanaSeleccionUsuario() {
		setSize(600, 400);
		panelito = new JPanel();
		panelito.setLayout(new GridLayout(2,1));
		boton = new JButton("Partida Nueva");
		boton.setFont(new Font("Arial", Font.BOLD,40));
		boton2 = new JButton("Continuar Partida");
		boton2.setFont(new Font("Arial", Font.BOLD,40));
		panelito.add(boton);
		panelito.add(boton2);
		
		boton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaNewUsuario.main(null);
				dispose();
			}
		});
		
		boton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaUsuario.main(null);
				dispose();
			}
		});
		add(panelito);
		
	}
	public static void main(String[] args) {
		VentanaSeleccionUsuario v = new VentanaSeleccionUsuario();
		v.setLocation(550, 300);
		v.setVisible(true);
		v.setTitle("Business Go Boom");
		v.setResizable(false);;
		v.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
