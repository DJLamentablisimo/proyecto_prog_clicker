package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import clases.Apuestas;
import clases.Edificios;




public class VentanaClick extends JFrame {

	private static final long serialVersionUID = -4776141433437148579L;
	private JLabel alternativa;
	private JLabel blanco;
	private static JLabel puntuacion;
	private static JLabel produccion;
	private JPanel superior;
	private JPanel inferior;

	static int cooldown;

	private JPanel pMenuSup;
	private HiloDineroPorSegundo hilo;


	private JList<Edificios> liste;
	
	private JScrollPane sPanel;
	private JScrollPane pMejoras;
	private JButton bJuegoExtra;
	
	private JPanel pApuestas;
	private JLabel apuesta1;
	private JLabel blanco1;

	
	static long dinero_total = 100;
	static int dinero_por_segundo=1;  //Esta puesto a cero porque parte sin producción
	static int dinero_click=1;
	
	private JPanel panelProduccion;
	
	public VentanaClick() {
		//Creacion de Logger que utilizaremos para comentar parte del codigo y su funcionamiento.
		Logger logger = Logger.getLogger(VentanaClick.class.getName());
		
		////Creación de ArrayList donde almacenaremos todos los elementos de Edificios
		ArrayList<Edificios> listaEdifs = new ArrayList<>();
		anyadirEdificios("Edificio1", 120, 0, 1, "", listaEdifs);
		anyadirEdificios("Edificio2", 200, 0, 2, "", listaEdifs);
		anyadirEdificios("Edificio3", 250, 0, 3, "", listaEdifs);
		anyadirEdificios("Edificio4", 550, 0, 4, "", listaEdifs);
		anyadirEdificios("Edificio5", 1000, 0, 5, "", listaEdifs);
		anyadirEdificios("Edificio6", 2300, 0, 6, "", listaEdifs);
		anyadirEdificios("Edificio7", 5000, 0, 7, "", listaEdifs);
		anyadirEdificios("Edificio8", 7000, 0, 8, "", listaEdifs);
		anyadirEdificios("Edificio9", 10000, 0, 9, "", listaEdifs);
		anyadirEdificios("Edificio10", 20000, 0, 10, "", listaEdifs);
		anyadirEdificios("Edificio11", 25000, 0, 11, "", listaEdifs);
		anyadirEdificios("Edificio12", 30000, 0, 12, "", listaEdifs);
		anyadirEdificios("Edificio13", 40000, 0, 13, "", listaEdifs);
		anyadirEdificios("Edificio14", 50000, 0, 14, "", listaEdifs);
		anyadirEdificios("Edificio15", 75000, 0, 15, "", listaEdifs);
		anyadirEdificios("Edificio16", 100000, 0, 16, "", listaEdifs);
		anyadirEdificios("Edificio17", 500000, 0, 17, "", listaEdifs);	
		
		//Creación de apuestas
		Apuestas apuesta01= new Apuestas("Préstamo inestable",99, Double.valueOf(dinero_total*0.05).longValue(), "" );
		
		//Creación de componentes de la ventana
		liste = new JList<>();
		liste.setFixedCellHeight(90);
		liste.setFixedCellWidth(600);
		liste.setFont(new Font("Arial", Font.ITALIC, 30));
		
		sPanel = new JScrollPane(liste);
		superior = new JPanel();
		pMenuSup = new JPanel();
		panelProduccion = new JPanel();
		panelProduccion.setLayout(new GridLayout(2,1));
		pMenuSup.setLayout(new GridLayout(1,4));
		inferior = new JPanel();
		inferior.setLayout(new GridLayout(1,3));
		pMejoras = new JScrollPane();
		pApuestas = new JPanel();
		bJuegoExtra=new JButton("Juego extra");
		bJuegoExtra.setBackground(Color.black);
		bJuegoExtra.setForeground(Color.white);
		
		apuesta1=new JLabel(apuesta01.getNombre());
		apuesta1.setBackground(Color.BLUE);
		apuesta1.setOpaque(true);
		apuesta1.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				logger.info(String.valueOf(apuesta01.getPrecio()));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(dinero_total>=100) {
					dinero_total-=apuesta01.getPrecio();
					dinero_total=apuesta01.efecto(apuesta01.getAincremento(),dinero_total);
					
					puntuacion.setText("Ca$h Money Baby: "+String.valueOf(dinero_total));
					logger.info("La apuesta 1 tiene efecto");
				}
			}
		});
		blanco1=new JLabel();
		pApuestas.setLayout(new GridLayout(5, 2));
		pApuestas.add(apuesta1);
		pApuestas.add(blanco1);
		anyadirApuestas("Apuesta2", true, Color.red, pApuestas, "derecha");
		anyadirApuestas("Apuesta3", true, Color.green, pApuestas, "izquierda");
		anyadirApuestas("Apuesta4", true, Color.gray, pApuestas, "derecha");
		anyadirApuestas("Apuesta5", true, Color.pink, pApuestas, "izquierda");

		blanco = new JLabel();
		alternativa = new JLabel();
		
		puntuacion = new JLabel("Ca$h Money Baby: "+String.valueOf(dinero_total));
		puntuacion.setFont(new Font("Castellar", Font.BOLD, 20));
		produccion = new JLabel("Producción por segundo: "+String.valueOf(dinero_por_segundo)+" $/seg");
		produccion.setFont(new Font("Castellar", Font.ITALIC, 11));
		panelProduccion.add(puntuacion);
		panelProduccion.add(produccion);
		
		//Creacion de Menu para la ventana
		JMenuBar menub = new JMenuBar();
		JMenu menu = new JMenu("                      Menu                                                                  ");
		menub.setBackground(Color.black);
		menu.setFont(new Font("Arial", Font.ITALIC,20));
		menu.setForeground(Color.white);
		JMenuItem menuitem1 = new JMenuItem("Cambiar contraseña");
		JMenuItem menuitem2 = new JMenuItem("Reiniciar partida");
		JMenuItem menuitem3 = new JMenuItem("Estadisticas del jugador");
		JMenuItem menuitem4 = new JMenuItem("Guardar progreso");
		JMenuItem menuitem5 = new JMenuItem("Salir");
		
		menub.add(menu);
		menu.add(menuitem1);
		menu.add(menuitem2);
		menu.add(menuitem3);
		menu.add(menuitem4);
		menu.add(menuitem5);
		
		pMenuSup.add(menub);
		pMenuSup.add(new JLabel());
		pMenuSup.add(new JLabel());
		pMenuSup.add(bJuegoExtra);
		
		superior.add(blanco, BorderLayout.EAST);
		superior.add(alternativa, BorderLayout.CENTER);
		superior.add(panelProduccion, BorderLayout.WEST);
		
		
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
							if(dinero_total >= edf.getPrecio()) {
								edf.seteCantidad(edf.incrementar(1));
								dinero_total = (int) (dinero_total- edf.getPrecio());
								dinero_por_segundo = dinero_por_segundo+(int)edf.geteProduccion();
								liste.clearSelection();
							}
				else {
						
					System.out.println("no tienes pasta");
					liste.clearSelection();
				}
				logger.info("Incrementa");
				}
			}
			}
		});
		
		//ActionListeners de los diferentes elementos del JMenu
		
		//Opcion1
		menuitem1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Todavia no tiene funcionalidad porque no tenemos una ventana para los usuarios.
				
			}
		});
		
		//Opcion2
		menuitem2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Estas seguro de que quieres reiniciar la partida y perder todo tu progreso?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(opcion==0) {
					dinero_total = 0;
					dinero_por_segundo=0; 
					dinero_click=1;
				}if(opcion==1) {
				}
			}
		});
		
		//Opcion3
		
		//Opcion4
		menuitem4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		//Opcion5
		menuitem5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
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
				dinero_total=dinero_total+1*dinero_click;
				logger.info("click");
				puntuacion.setText("Ca$h Money Baby: "+String.valueOf(dinero_total));
				
			}
		});
		
		//Añadir todos los paneles a la Ventana
		inferior.add(pApuestas, BorderLayout.WEST);
		inferior.add(sPanel, BorderLayout.CENTER);
		inferior.add(pMejoras, BorderLayout.EAST);
		
		
/*
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(superior, BorderLayout.NORTH);
		
		getContentPane().add(pApuestas, BorderLayout.WEST);
		getContentPane().add(inferior, BorderLayout.CENTER);
*/
		
		setLayout(new BorderLayout());
		add(pMenuSup, BorderLayout.NORTH);
		add(superior, BorderLayout.CENTER);
		add(inferior, BorderLayout.SOUTH);
		//add(pApuestas, BorderLayout.WEST);
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println("Cerrado");
				hilo.interrupt();
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				hilo = new HiloDineroPorSegundo();
				hilo.start();
				
			}
		});

		
	}
	
	public static void main(String[] args) {
		VentanaClick v = new VentanaClick();
		v.setSize(1680, 1020);
		v.setVisible(true);
		v.setTitle("Business Go Boom");
		v.setLocation(150, 10);
		v.setMinimumSize(v.getSize());
		v.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	private static void anyadirEdificios( String nombre, long precio, long cant, int prod, String image, ArrayList<Edificios> array) {
		Edificios edif = new Edificios(nombre,precio,cant,prod,image);
		array.add(edif);
	}
	private static void anyadirApuestas(String nombre, boolean x, Color color, JPanel panel, String lugar) {
		JLabel vacio = new JLabel();
		JLabel apuestita=new JLabel(nombre);
		apuestita.setOpaque(x);
		apuestita.setBackground(color);
		
		if(lugar=="izquierda") {
			panel.add(apuestita);
			panel.add(vacio);
		}else if(lugar=="derecha") {
			panel.add(vacio);
			panel.add(apuestita);
		}
		
		
	}

	
	static class HiloDineroPorSegundo extends Thread{
		@Override
			public void run() {

					try {
						while (true){	
						Thread.sleep(1000);
						dinero_total = dinero_total+dinero_por_segundo;
						puntuacion.setText("Ca$h Money Baby: "+String.valueOf(dinero_total));
						produccion.setText("Producción por segundo: "+String.valueOf(dinero_por_segundo)+" $/seg");
						if(dinero_total>100) {
						
						}
					} 
				}
				catch (Exception e) {
					System.out.println(e);

			}
		}
	}
}
