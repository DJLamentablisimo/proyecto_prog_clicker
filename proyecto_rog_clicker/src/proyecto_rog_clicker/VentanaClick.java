package proyecto_rog_clicker;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaClick extends JFrame {

	private static final long serialVersionUID = -4776141433437148579L;
	private JButton cerdito;
	private JLabel blanco;
	private JLabel puntuacion;
	private JPanel superior;
	int dinero_click = 0;
	int mult_click = 1;
	
	public VentanaClick() {
		
		superior = new JPanel();
		cerdito = new JButton();
		blanco = new JLabel();
		puntuacion = new JLabel("Ca$h Money Baby: "+String.valueOf(dinero_click));
		puntuacion.setFont(new Font("Castellar", Font.BOLD, 20));
		try {
		    Image img = ImageIO.read(getClass().getResource("the wock.png"));
		    cerdito.setIcon(new ImageIcon(img));
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		cerdito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dinero_click=dinero_click+1;
				System.out.println(dinero_click);
				puntuacion.setText("Ca$h Money Baby: "+String.valueOf(dinero_click));
			}
		});
		getContentPane().setLayout(new BorderLayout());
		
		this.setSize(1680, 1020);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setTitle("Business Go Boom");
		this.setLocation(150, 10);
		this.setMinimumSize(this.getSize());
		getContentPane().add(superior, BorderLayout.NORTH);
		superior.add(blanco, BorderLayout.EAST);
		superior.add(cerdito, BorderLayout.CENTER);
		superior.add(puntuacion, BorderLayout.WEST);
	}
	
	public static void main(String[] args) {
		VentanaClick v = new VentanaClick();
	}
}
