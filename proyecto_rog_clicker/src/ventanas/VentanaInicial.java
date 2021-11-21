package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VentanaInicial extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7221418200995586265L;
	private JLabel icono;
	@SuppressWarnings("static-access")
	public static void Salir(int miliseg){
	   Thread mith = Thread.currentThread();
	   try {
	      mith.sleep(miliseg);
	   } catch(InterruptedException ie) {
	      System.err.println("Capturada InterruptedException: "+ie);
	   }
	}
	public VentanaInicial() {
		JPanel o = new JPanel();
		o.setBackground(Color.BLACK);
		
		icono = new JLabel();
		Image img;
		try {
			img = ImageIO.read(getClass().getResource("bisnes.png"));
			icono.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		icono.setFont(new Font("Arial", Font.BOLD,30));
		icono.setForeground(Color.RED);
		o.add(icono);
		add(o, BorderLayout.CENTER);
		
		JPanel i = new JPanel();
		i.setBackground(Color.BLACK);
		JLabel neew = new JLabel("By Jorge Alonso, David Barrenechea, Daniel Galean y Asier Belloso Sainz", SwingConstants.RIGHT);
		neew.setForeground(Color.white);
		i.add(neew);
		add(i,BorderLayout.SOUTH);
		
        Thread u = new hilo();
        u.start();
       
	}
	public static void main(String[] args) {
		VentanaInicial b = new VentanaInicial();
		b.setTitle("JUEGO");
		b.setSize(900, 900);
		b.setLocation( 520, 100 );
		b.setVisible( true );
		b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.setResizable(false);
		
		
	
	}
	class hilo extends Thread{
		public void run() {
			Salir(2000);
			VentanaUsuario.main(null);
			dispose();
		}
	}
}
