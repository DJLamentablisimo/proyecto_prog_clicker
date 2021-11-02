package proyecto_rog_clicker;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class VentanaClick extends JFrame {

	private static final long serialVersionUID = -4776141433437148579L;
	private JButton boton;
	private JButton cerdito;
	private JLabel alternativa;//Hecho por Asier
	private JLabel blanco;
	private JLabel puntuacion;
	private JPanel superior;
	private JPanel inferior;//Hecho por Asier
	private JPanel inferior1;//Hecho por Asier
	private JPanel inferior2;//Hecho por Asier
	private JPanel inferior3;//Hecho por Asier
	private JList<Edificios> liste;//Hecho por Asier
	private JScrollPane sPanel;//Hecho por Asier
	
	int dinero_click = 0;
	
	public VentanaClick() {
		boton = new JButton("Comprar");
		ArrayList<Edificios> listaEdifs = new ArrayList<>();//Hecho por Asier
		Edificios e1 = new Edificios("Edificio1", 120, 0, 1, "imagen 1"); 
		Edificios e2 = new Edificios("Edificio2", 200, 0, 2, "imagen 2");//Hecho por Asier
		Edificios e3 = new Edificios("Edificio3", 250, 0, 3, "imagen 3");//Hecho por Asier
		Edificios e4 = new Edificios("Edificio4", 550, 0, 4, "imagen 4");//Hecho por Asier
		Edificios e5 = new Edificios("Edificio5", 1000, 0, 5, "imagen 5");//Hecho por Asier
		Edificios e6 = new Edificios("Edificio6", 2300, 0, 6, "imagen 6");//Hecho por Asier
		Edificios e7 = new Edificios("Edificio7", 5000, 0, 7, "imagen 7");//Hecho por Asier
		Edificios e8 = new Edificios("Edificio8", 7000, 0, 8, "imagen 3");//Hecho por Asier
		Edificios e9 = new Edificios("Edificio9", 10000, 0, 9, "imagen 4");//Hecho por Asier
		Edificios e10 = new Edificios("Edificio10", 20000, 0, 10, "imagen 5");//Hecho por Asier
		Edificios e11 = new Edificios("Edificio11", 25000, 0, 11, "imagen 6");//Hecho por Asier
		Edificios e12 = new Edificios("Edificio12", 30000, 0, 12, "imagen 7");//Hecho por Asier
		Edificios e13 = new Edificios("Edificio13", 40000, 0, 13, "imagen 3");//Hecho por Asier
		Edificios e14 = new Edificios("Edificio14", 50000, 0, 14, "imagen 4");//Hecho por Asier
		Edificios e15 = new Edificios("Edificio15", 75000, 0, 15, "imagen 5");//Hecho por Asier
		Edificios e16 = new Edificios("Edificio16", 100000, 0, 16, "imagen 6");//Hecho por Asier
		Edificios e17 = new Edificios("Edificio17", 500000, 0, 17, "imagen 7");//Hecho por Asier
		listaEdifs.add(e1);//Hecho por Asier
		listaEdifs.add(e2);//Hecho por Asier
		listaEdifs.add(e3);//Hecho por Asier
		listaEdifs.add(e4);//Hecho por Asier
		listaEdifs.add(e5);//Hecho por Asier
		listaEdifs.add(e6);//Hecho por Asier
		listaEdifs.add(e7);//Hecho por Asier
		listaEdifs.add(e8);//Hecho por Asier
		listaEdifs.add(e9);//Hecho por Asier
		listaEdifs.add(e10);//Hecho por Asier
		listaEdifs.add(e11);//Hecho por Asier
		listaEdifs.add(e12);//Hecho por Asier
		listaEdifs.add(e13);//Hecho por Asier
		listaEdifs.add(e14);//Hecho por Asier
		listaEdifs.add(e15);//Hecho por Asier
		listaEdifs.add(e16);//Hecho por Asier
		listaEdifs.add(e17);//Hecho por Asier
		
		boton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Edificios xdd = (Edificios) liste.getSelectedValue();
				xdd.seteCantidad(xdd.incrementar(1));
				System.out.println(xdd);
					
				
			}
		});
		
		
		liste = new JList<>();//Hecho por Asier
		sPanel = new JScrollPane(liste);//Hecho por Asier
		superior = new JPanel();
		inferior = new JPanel();//Hecho por Asier
		inferior1 = new JPanel();//Hecho por Asier
		inferior2 = new JPanel();//Hecho por Asier
		inferior2.add(sPanel);//Hecho por Asier
		inferior3 = new JPanel();//Hecho por Asier
		inferior3.add(boton);
		inferior.add(inferior1, BorderLayout.EAST);//Hecho por Asier
		inferior.add(inferior2, BorderLayout.CENTER);//Hecho por Asier
		inferior.add(inferior3, BorderLayout.WEST);//Hecho por Asier
		
		DefaultListModel<Edificios> dlist = new DefaultListModel<>();//Hecho por Asier
		for(Edificios e : listaEdifs) {//Hecho por Asier
			dlist.addElement(e);//Hecho por Asier
		}
		liste.setModel(dlist);//Hecho por Asier
		
		cerdito = new JButton();
		blanco = new JLabel();
		alternativa = new JLabel();//Hecho por Asier
		puntuacion = new JLabel("Ca$h Money Baby: "+String.valueOf(dinero_click));
		
		puntuacion.setFont(new Font("Castellar", Font.BOLD, 20));
		try {
		    Image img = ImageIO.read(getClass().getResource("the wock.png"));
		    alternativa.setIcon(new ImageIcon(img));//Hecho por Asier
		    //cerdito.setIcon(new ImageIcon(img));
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		//Alternativa 1 - Boton
		cerdito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dinero_click=dinero_click+1;
				puntuacion.setText("Ca$h Money Baby: "+String.valueOf(dinero_click));
			}
		});
		//Alternativa 2 - Label //Hecho por Asier
		alternativa.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				dinero_click=dinero_click+1;
				System.out.println(dinero_click);
				puntuacion.setText("Ca$h Money Baby: "+String.valueOf(dinero_click));
				
			}
		});
		getContentPane().setLayout(new BorderLayout());
		
		
		superior.add(blanco, BorderLayout.EAST);
		superior.add(alternativa, BorderLayout.CENTER);
		//superior.add(cerdito, BorderLayout.CENTER);
		superior.add(puntuacion, BorderLayout.WEST);
		getContentPane().add(superior, BorderLayout.NORTH);
		getContentPane().add(inferior, BorderLayout.SOUTH);//Hecho por Asier
		
	}
	
	public static void main(String[] args) {
		VentanaClick v = new VentanaClick();
		v.setSize(1680, 1020);
		v.setVisible(true);
		v.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		v.setAlwaysOnTop(true);
		v.setTitle("Business Go Boom");
		v.setLocation(150, 10);
		v.setMinimumSize(v.getSize());
	}
}
