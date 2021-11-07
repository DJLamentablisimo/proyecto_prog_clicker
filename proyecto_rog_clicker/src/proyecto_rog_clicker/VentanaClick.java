package proyecto_rog_clicker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;




public class VentanaClick extends JFrame {

	private static final long serialVersionUID = -4776141433437148579L;
	private JLabel alternativa;
	private JLabel blanco;
	private static JLabel puntuacion;
	private JPanel superior;
	private JPanel inferior;

	private JList<Edificios> liste;
	
	private JScrollPane sPanel;
	private JScrollPane pMejoras;
	
	private JPanel pApuestas;
	private JLabel apuesta1;
	private JLabel apuesta2;
	private JLabel apuesta3;
	private JLabel apuesta4;
	private JLabel apuesta5;
	private JLabel blanco1;
	private JLabel blanco2;
	private JLabel blanco3;
	private JLabel blanco4;
	private JLabel blanco5;
	
	static int dinero_click = 0;
	static int dinero_por_segundo=1;
	
	public VentanaClick() {
		//Creacion de Logger que utilizaremos para comentar parte del codigo y su funcionamiento.
		Logger logger = Logger.getLogger(VentanaClick.class.getName());
		
		////Creación de ArrayList donde almacenaremos todos los elementos de Edificios
		ArrayList<Edificios> listaEdifs = new ArrayList<>();
		anyadirEdificios("Edificio1", 120, 0, 1, "imagen 1", listaEdifs);
		anyadirEdificios("Edificio2", 200, 0, 2, "imagen 2", listaEdifs);
		anyadirEdificios("Edificio3", 250, 0, 3, "imagen 3", listaEdifs);
		anyadirEdificios("Edificio4", 550, 0, 4, "imagen 4", listaEdifs);
		anyadirEdificios("Edificio5", 1000, 0, 5, "imagen 5", listaEdifs);
		anyadirEdificios("Edificio6", 2300, 0, 6, "imagen 6", listaEdifs);
		anyadirEdificios("Edificio7", 5000, 0, 7, "imagen 7", listaEdifs);
		anyadirEdificios("Edificio8", 7000, 0, 8, "imagen 3", listaEdifs);
		anyadirEdificios("Edificio9", 10000, 0, 9, "imagen 4", listaEdifs);
		anyadirEdificios("Edificio10", 20000, 0, 10, "imagen 5", listaEdifs);
		anyadirEdificios("Edificio11", 25000, 0, 11, "imagen 6", listaEdifs);
		anyadirEdificios("Edificio12", 30000, 0, 12, "imagen 7", listaEdifs);
		anyadirEdificios("Edificio13", 40000, 0, 13, "imagen 3", listaEdifs);
		anyadirEdificios("Edificio14", 50000, 0, 14, "imagen 4", listaEdifs);
		anyadirEdificios("Edificio15", 75000, 0, 15, "imagen 5", listaEdifs);
		anyadirEdificios("Edificio16", 100000, 0, 16, "imagen 6", listaEdifs);
		anyadirEdificios("Edificio17", 500000, 0, 17, "imagen 7", listaEdifs);	
		
		//Creación de componentes de la ventana
		liste = new JList<>();
		liste.setFixedCellHeight(90);
		liste.setFixedCellWidth(600);
		liste.setFont(new Font("Arial", Font.ITALIC, 30));
		liste.setAutoscrolls(true);
		sPanel = new JScrollPane(liste);
		superior = new JPanel();
		
		inferior = new JPanel();
		pMejoras = new JScrollPane();
		pApuestas = new JPanel();
		
		//inferior.add(pApuestas, BorderLayout.EAST);
		
		
		
		apuesta1=new JLabel();
		apuesta1.setBackground(Color.BLUE);
		apuesta2=new JLabel();
		apuesta2.setBackground(Color.RED);
		apuesta3=new JLabel();
		apuesta3.setBackground(Color.GREEN);
		apuesta4=new JLabel();
		apuesta4.setBackground(Color.GRAY);
		apuesta5=new JLabel();
		apuesta5.setBackground(Color.PINK);
		blanco1=new JLabel();
		blanco2=new JLabel();
		blanco3=new JLabel();
		blanco4=new JLabel();
		blanco5=new JLabel();
		pApuestas.setLayout(new GridLayout(5, 2));
		pApuestas.add(apuesta1);
		pApuestas.add(blanco1);
		pApuestas.add(blanco2);
		pApuestas.add(apuesta2);
		pApuestas.add(apuesta3);
		pApuestas.add(blanco3);
		pApuestas.add(blanco4);
		pApuestas.add(apuesta4);
		pApuestas.add(apuesta5);
		pApuestas.add(blanco5);
		
		blanco = new JLabel();
		alternativa = new JLabel();
		puntuacion = new JLabel("Ca$h Money Baby: "+String.valueOf(dinero_click));
		
		puntuacion.setFont(new Font("Castellar", Font.BOLD, 20));
		
		superior.add(blanco, BorderLayout.EAST);
		superior.add(alternativa, BorderLayout.CENTER);
		superior.add(puntuacion, BorderLayout.WEST);
		
		
		//Creación de modelo de JList y asignacion a la JList.
		DefaultListModel<Edificios> dlist = new DefaultListModel<>();
		for(Edificios e : listaEdifs) {
			dlist.addElement(e);
		}
		liste.setModel(dlist);
		
		liste.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
					if(liste.getSelectedValue()!=null) {
						if(!e.getValueIsAdjusting()) {
							Edificios edf = (Edificios) liste.getSelectedValue();
							if(dinero_click >= edf.precio) {
								edf.seteCantidad(edf.incrementar(1));
								dinero_click = (int) (dinero_click- edf.precio);
								dinero_por_segundo = dinero_por_segundo+edf.eProduccion;
								liste.clearSelection();
							}
				else {
						
					System.out.println("no tienes pasta");
					liste.clearSelection();
				}
				logger.info("Incrementa");;
				}
			}
			}
		});
		
		//Añadir imagen al JLabel
		try {
		    Image img = ImageIO.read(getClass().getResource("the wock.png"));
		    alternativa.setIcon(new ImageIcon(img));
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		
		//Añadir funcionalidad al JLabel de la imagen
		alternativa.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				dinero_click=dinero_click+1;
				logger.info("click");
				puntuacion.setText("Ca$h Money Baby: "+String.valueOf(dinero_click));
				
			}
		});
		
		//Añadir todos los paneles a la Ventana
		inferior.add(sPanel, BorderLayout.CENTER);
		inferior.add(pMejoras, BorderLayout.WEST);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(superior, BorderLayout.NORTH);
		getContentPane().add(inferior, BorderLayout.CENTER);
		getContentPane().add(pApuestas, BorderLayout.WEST);
		
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
		HiloDineroPorSegundo a = new HiloDineroPorSegundo();
		a.start();
	}
	private static void anyadirEdificios( String nombre, long precio, long cant, int prod, String image, ArrayList<Edificios> array) {
		Edificios edif = new Edificios(nombre,precio,cant,prod,image);
		array.add(edif);
	}
	
	static class HiloDineroPorSegundo extends Thread{
		@Override
			public void run() {
					try {
						while (true){	
						Thread.sleep(1000);
						dinero_click = dinero_click+dinero_por_segundo;
						puntuacion.setText("Ca$h Money Baby: "+String.valueOf(dinero_click));
					} 
				}
				catch (Exception e) {
					System.out.println(e);

			}
		}
	}
}
