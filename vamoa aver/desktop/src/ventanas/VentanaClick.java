package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mygdx.game.desktop.DesktopLauncher;

import clases.Apuestas;
import clases.Edificios;
import clases.Mejoras;
import clases.Usuario;
import ventanas.VentanaClick.FondoFlechaIzq;
import ventanas.VentanaClick.MyCellRenderer;




public class VentanaClick extends JFrame {

	private static final long serialVersionUID = -4776141433437148579L;
	private JLabel alternativa;
	private JLabel blanco;
	private static JLabel puntuacion;
	private static JLabel produccion;
	private JPanel superior;
	private JPanel inferior;

	static int cooldown;
	static int dinero_click= VentanaUsuario.usuarioActual.getDinero_click_personal();
	static int dinero_por_segundo= VentanaUsuario.usuarioActual.getDinero_por_segundo_personal();
	static long dinero_total = VentanaUsuario.usuarioActual.getDinero_total_personal();
	public static int ult_puntuacion;
	

	private JPanel pMenuSup;
	private HiloDineroPorSegundo hilo;


	private JList<Edificios> liste;
	private ArrayList<Edificios> listaEdifs;
	
	private JScrollPane sPanel;
	//private JScrollPane pMejorasScroll;
	private JButton bJuegoExtra;
	
	private JPanel pApuestas;
	private JPanel pMejoras;
	private JLabel apuesta1;
	private JLabel apuesta2;
	private JLabel apuesta3;
	private JLabel apuesta4;
	private JLabel apuesta5;
	private JLabel mejora1;
	private JLabel mejora2;
	private JLabel mejora3;
	private JLabel blanco1;
	private JLabel blanco2;
	private JLabel blanco3;
	private JLabel blanco4;
	private JLabel blanco5;
	private JLabel blanco6;
	private JLabel blanco7;
	private JLabel blanco8;
	
	private JPanel panelProduccion;
	
	private static VentanaClick ventana;
	
	public long dineroAlComenzar = 0;
	
	public VentanaClick() {
		ClaseContenedora cc = new ClaseContenedora();
		
		//Creación de ArrayList donde almacenaremos todos los elementos de Edificios
		listaEdifs = cc.sacarEdificios("Usuario.db");
		for(Edificios ewe : listaEdifs) {
			dineroAlComenzar=dineroAlComenzar+(ewe.geteCantidad()*ewe.geteProduccion());
		}
		dinero_por_segundo = dinero_por_segundo + (int) dineroAlComenzar;
		//Creacion de Logger que utilizaremos para comentar parte del codigo y su funcionamiento.
		Logger logger = Logger.getLogger(VentanaClick.class.getName());
		
		//Creación de apuestas
		
			//Te suma o resta cantidad de dinero
			Apuestas apuesta01= new Apuestas("Préstamo inestable",1, Double.valueOf(dinero_total*0.05).longValue(), "" );
			//Te da o te quita x7 potencia de clicks
			Apuestas apuesta02= new Apuestas("El ratoncito loco",100, Double.valueOf(dinero_click*7).longValue(), "" );
			//Te aumenta o te reduce el dinero que ganas por segundo
			Apuestas apuesta03= new Apuestas("Inversión en NFTs",450, Double.valueOf(dinero_por_segundo*0.5).longValue(), "" );
			//Eliges la cantidad de capital a apostar, pudiendo ganar un 15% extra de ese dinero o perderlo todo en el proceso
			Apuestas apuesta04 = new Apuestas("A ver si hay huevos", 0, Double.valueOf(dinero_total).longValue(),"");
			
		//Creación de mejoras
			
			Mejoras mejora01 = new Mejoras("Invierte en acciones con eToro",1,2,0,0);
			Mejoras mejora02 = new Mejoras("Se tu propio jefe",1,0,10,0);
			Mejoras mejora03 = new Mejoras("Loteria del Estado",1,0,0,2);
		
		//Creación de componentes de la ventana
		liste = new JList<>();
		liste.setFixedCellHeight(90);
		liste.setFixedCellWidth(600);
		liste.setFont(new Font("Arial", Font.ITALIC, 30));  
		
		sPanel = new JScrollPane(liste);
		superior = new FondoPanel();
		pMenuSup = new JPanel();
		panelProduccion = new JPanel();
		panelProduccion.setLayout(new GridLayout(2,1));
		panelProduccion.setBorder(BorderFactory.createLineBorder(Color.red));
		pMenuSup.setLayout(new GridLayout(1,4));
		inferior = new JPanel();
		inferior.setLayout(new GridLayout(1,3));
		//pMejorasScroll = new JScrollPane();
		pApuestas = new JPanel();
		pMejoras = new JPanel();
		bJuegoExtra=new JButton("Juego extra");
		bJuegoExtra.setBackground(Color.black);
		bJuegoExtra.setForeground(Color.white);
		
		//Activación del juego
		bJuegoExtra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DesktopLauncher.main(null);
				
			}
		});
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////// MODIFICACIÓN DE LA APUESTA 1 CON SU EVENTO DE RATON ////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////
		apuesta1=new JLabel(apuesta01.getNombre(), SwingConstants.CENTER);
		apuesta1.setBackground(Color.BLUE);
		apuesta1.setOpaque(true);
		String textoApuesta1="50% de posibilidades de recibir o perder un 5% de tu capital";
		apuesta1.setToolTipText(textoApuesta1);
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
		blanco1=new FondoFlechaIzq();
		pApuestas.setLayout(new GridLayout(5, 2));
		pApuestas.add(apuesta1);
		pApuestas.add(blanco1);
		
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////// MODIFICACIÓN DE LA APUESTA 2 CON SU EVENTO DE RATON ////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////
		apuesta2=new JLabel(apuesta02.getNombre(), SwingConstants.CENTER);
		apuesta2.setBackground(Color.RED);
		apuesta2.setOpaque(true);
		String textoApuesta2="50% de posibilidades de recibir o perder siete veces tu valor por click";
		apuesta2.setToolTipText(textoApuesta2);
		apuesta2.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				logger.info(String.valueOf(apuesta02.getPrecio()));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(dinero_total>=apuesta02.getPrecio()) {
					dinero_total-=apuesta02.getPrecio();
					dinero_click=(int) apuesta02.efecto(apuesta02.getAincremento(),dinero_click);
					
					logger.info("La apuesta 2 tiene efecto");
				}
			}
		});
		
		
		blanco2=new FondoFlechaDer();
		pApuestas.add(blanco2);
		pApuestas.add(apuesta2);
		
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////// MODIFICACIÓN DE LA APUESTA 3 CON SU EVENTO DE RATON ////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////
		apuesta3=new JLabel(apuesta03.getNombre(), SwingConstants.CENTER);
		apuesta3.setBackground(Color.GREEN);
		apuesta3.setOpaque(true);
		String textoApuesta3="50% de posibilidades de duplicarte o reducirte a la mitad tus ingresos por segundo";
		apuesta3.setToolTipText(textoApuesta3);
		apuesta3.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				logger.info(String.valueOf(apuesta03.getPrecio()));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(dinero_total>=apuesta03.getPrecio()) {
					dinero_total-=apuesta03.getPrecio();
					dinero_por_segundo=(int) apuesta03.efecto(apuesta03.getAincremento(),dinero_por_segundo);
					
					produccion.setText("Producción por segundo: "+String.valueOf(dinero_por_segundo)+" $/seg");
					
					logger.info("La apuesta 3 tiene efecto");
					
				}
			}
		});
		
		
		blanco3=new FondoFlechaIzq();
		pApuestas.add(apuesta3);
		pApuestas.add(blanco3);
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////// MODIFICACIÓN DE LA APUESTA 4 CON SU EVENTO DE RATON ////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////
		apuesta4=new JLabel(apuesta04.getNombre(), SwingConstants.CENTER);
		apuesta4.setBackground(Color.GRAY);
		apuesta4.setOpaque(true);
		String textoApuesta4="Eliges la cantidad de capital a apostar, pudiendo ganar un 15% extra de ese dinero o perderlo todo en el proceso";
		apuesta4.setToolTipText(textoApuesta4);
		apuesta4.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {			
				dinero_total-=apuesta04.getPrecio();
				String opcion = JOptionPane.showInputDialog(ventana, "Cantidad a apostar", "");	
				int opcionInt =  Integer.parseInt(opcion);
				int valorRNG = (int) Math.floor(Math.random()*2);
				if (valorRNG==0) {
					dinero_total += opcionInt*0.3;
				}
				if (valorRNG ==1) {
					dinero_total -= opcionInt;
				}
					
				puntuacion.setText("Ca$h Money Baby: "+String.valueOf(dinero_total));
				logger.info("La apuesta 4 tiene efecto");
			}
		});
		
		
		blanco4=new FondoFlechaDer();
		pApuestas.add(blanco4);
		pApuestas.add(apuesta4);
		
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		////////// MODIFICACIÓN DE LA APUESTA 5 CON SU EVENTO DE RATON ////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////
		apuesta5=new JLabel("Apuesta maligna", SwingConstants.CENTER);
		apuesta5.setBackground(Color.PINK);
		apuesta5.setOpaque(true);
		String textoApuesta5="¿̷̳̻͔̮̓̌͋͂͂́͆͐?̴̰̽̍̚¿̸̲͇͉̂̊̒͗͜͠͝ͅ¿̶̦̬̮̓̍?̷̠̩̽̒͌͑̈́¿̶̥̱͍͈̜̩͇̀̅͌̑͆̉̕?̸̨̫̱͖̝͈̭̼̃͒̂̅̇̒?̶̣͔͒̽̽͠¿̶̨̛͎͔̺̆͗̾̓̍͐̿?̸̛̠͕̊̈́̎͘͜¿̷̳̻͔̮̓̌͋͂͂́͆͐?̴̰̽̍̚¿̸̲͇͉̂̊̒͗͜͠͝ͅ¿̶̦̬̮̓̍?̷̠̩̽̒͌͑̈́¿̶̥̱͍͈̜̩͇̀̅͌̑͆̉̕?̸̨̫̱͖̝͈̭̼̃͒̂̅̇̒?̶̣͔͒̽̽͠¿̶̨̛͎͔̺̆͗̾̓̍͐̿?̸̛̠͕̊̈́̎͘͜¿̷̳̻͔̮̓̌͋͂͂́͆͐?̴̰̽̍̚¿̸̲͇͉̂̊̒͗͜͠͝ͅ¿̶̦̬̮̓̍?̷̠̩̽̒͌͑̈́¿̶̥̱͍͈̜̩͇̀̅͌̑͆̉̕¿̷̳̻͔̮̓̌͋͂͂́͆͐?̴̰̽̍̚¿̸̲͇͉̂̊̒͗͜͠͝ͅ¿̶̦̬̮̓̍?̷̠̩̽̒͌͑̈́¿̶̥̱͍͈̜̩͇̀̅͌̑͆̉̕?̸̨̫̱͖̝͈̭̼̃͒̂̅̇̒?̶̣͔͒̽̽͠¿̶̨̛͎͔̺̆͗̾̓̍͐̿?̸̛̠͕̊̈́̎͘͜¿̷̳̻͔̮̓̌͋͂͂́͆͐?̴̰̽̍̚¿̸̲͇͉̂̊̒͗͜͠͝ͅ¿̶̦̬̮̓̍?̷̠̩̽̒͌͑̈́¿̶̥̱͍͈̜̩͇̀̅͌̑͆̉̕?̸̨̫̱͖̝͈̭̼̃͒̂̅̇̒?̶̣͔͒̽̽͠¿̶̨̛͎͔̺̆͗̾̓̍͐̿?̸̛̠͕̊̈́̎͘͜¿̷̳̻͔̮̓̌͋͂͂́͆͐?̴̰̽̍̚¿̸̲͇͉̂̊̒͗͜͠͝ͅ¿̶̦̬̮̓̍?̷̠̩̽̒͌͑̈́¿̶̥̱͍͈̜̩͇̀̅͌̑͆̉̕?̸̨̫̱͖̝͈̭̼̃͒̂̅̇̒?̶̣͔͒̽̽͠¿̶̨̛͎͔̺̆͗̾̓̍͐̿?̸̛̠͕̊̈́̎͘͜¿̷̳̻͔̮̓̌͋͂͂́͆͐?̴̰̽̍̚¿̸̲͇͉̂̊̒͗͜͠͝ͅ¿̶̦̬̮̓̍?̷̠̩̽̒͌͑̈́¿̶̥̱͍͈̜̩͇̀̅͌̑͆̉̕?̸̨̫̱͖̝͈̭̼̃͒̂̅̇̒?̶̣͔͒̽̽͠¿̶̨̛͎͔̺̆͗̾̓̍͐̿?̸̛̠͕̊̈́̎͘͜¿̷̳̻͔̮̓̌͋͂͂́͆͐?̴̰̽̍̚¿̸̲͇͉̂̊̒͗͜͠͝ͅ¿̶̦̬̮̓̍?̷̠̩̽̒͌͑̈́¿̶̥̱͍͈̜̩͇̀̅͌̑͆̉̕?̸̨̫̱͖̝͈̭̼̃͒̂̅̇̒?̶̣͔͒̽̽͠¿̶̨̛͎͔̺̆͗̾̓̍͐̿?̸̛̠͕̊̈́̎͘͜¿̷̳̻͔̮̓̌͋͂͂́͆͐?̴̰̽̍̚¿̸̲͇͉̂̊̒͗͜͠͝ͅ¿̶̦̬̮̓̍?̷̠̩̽̒͌͑̈́¿̶̥̱͍͈̜̩͇̀̅͌̑͆̉̕?̸̨̫̱͖̝͈̭̼̃͒̂̅̇̒?̶̣͔͒̽̽͠¿̶̨̛͎͔̺̆͗̾̓̍͐̿?̸̛̠͕̊̈́̎͘͜¿̷̳̻͔̮̓̌͋͂͂́͆͐?̴̰̽̍̚¿̸̲͇͉̂̊̒͗͜͠͝ͅ¿̶̦̬̮̓̍?̷̠̩̽̒͌͑̈́¿̶̥̱͍͈̜̩͇̀̅͌̑͆̉̕?̸̨̫̱͖̝͈̭̼̃͒̂̅̇̒?̶̣͔͒̽̽͠¿̶̨̛͎͔̺̆͗̾̓̍͐̿?̸̛̠͕̊̈́̎͘͜?̸̨̫̱͖̝͈̭̼̃͒̂̅̇̒?̶̣͔͒̽̽͠¿̶̨̛͎͔̺̆͗̾̓̍͐̿?̸̛̠͕̊̈́̎͘͜¿̷̳̻͔̮̓̌͋͂͂́͆͐?̴̰̽̍̚¿̸̲͇͉̂̊̒͗͜͠͝ͅ¿̶̦̬̮̓̍?̷̠̩̽̒͌͑̈́¿̶̥̱͍͈̜̩͇̀̅͌̑͆̉̕?̸̨̫̱͖̝͈̭̼̃͒̂̅̇̒?̶̣͔͒̽̽͠¿̶̨̛͎͔̺̆͗̾̓̍͐̿?̸̛̠͕̊̈́̎͘͜";
		apuesta5.setToolTipText(textoApuesta5);
		apuesta5.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {			
				abrirNavegador(null);
				logger.info("La apuesta 5 tiene efecto");
			}
		});
		
		blanco5=new FondoFlechaIzq();
		pApuestas.add(apuesta5);
		pApuestas.add(blanco5);
		
		
		
		
		blanco = new JLabel();
		alternativa = new JLabel();
		
		
		puntuacion = new JLabel("Ca$h Money Baby: "+String.valueOf(dinero_total)+"  ");
		puntuacion.setFont(new Font("Castellar", Font.BOLD, 20));
		produccion = new JLabel("Producción por segundo: "+String.valueOf(dinero_por_segundo)+" $/seg");
		produccion.setFont(new Font("Castellar", Font.ITALIC, 11));
		panelProduccion.add(puntuacion);
		panelProduccion.add(produccion);
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////// MODIFICACIÓN DEL JMENU CON LAS 6 DIFERENTES OPCIONES ///////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////
		JMenuBar menub = new JMenuBar();
		JMenu menu = new JMenu("                      Menu                                                                  ");
		menub.setBackground(Color.black);
		menu.setFont(new Font("Arial", Font.ITALIC,20));
		menu.setForeground(Color.white);
		JMenuItem menuitem1 = new JMenuItem("Cambiar contraseña");
		JMenuItem menuitem2 = new JMenuItem("Reiniciar partida");
		JMenuItem menuitem3 = new JMenuItem("Estadisticas del jugador");
		JMenuItem menuitem4 = new JMenuItem("Guardar progreso");
		JMenuItem menuitem5 = new JMenuItem("Cambiar usuario");
		JMenuItem menuitem6 = new JMenuItem("Salir");
		
		menub.add(menu);
		menu.add(menuitem1);
		menu.add(menuitem2);
		menu.add(menuitem3);
		menu.add(menuitem4);
		menu.add(menuitem5);
		menu.add(menuitem6);
		
		pMenuSup.add(menub);
		pMenuSup.add(new JLabel());
		pMenuSup.add(new JLabel());
		pMenuSup.add(bJuegoExtra);
		
		
		 	////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////// MODIFICACIÓN DE LA MEJORA 1 CON SU EVENTO DE RATON ////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////
		
		mejora1=new JLabel(mejora01.getNombre(), SwingConstants.CENTER);
		mejora1.setBackground(Color.BLUE);
		mejora1.setOpaque(true);
		String textoMejora1="Duplica el dinero por segundo que ganas";
		mejora1.setToolTipText(textoMejora1);
		mejora1.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				logger.info(String.valueOf(mejora01.getPrecio()));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(dinero_total>=100) {
					dinero_total-=mejora01.getPrecio();
					dinero_por_segundo=(int) mejora01.efecto(mejora01.getIncrementoDps(),dinero_por_segundo);
					
					puntuacion.setText("Producción por segundo: "+String.valueOf(dinero_por_segundo)+" $/seg");
					logger.info("La mejora 1 se ha aplicado correctamente");
					pMejoras.remove(mejora1);
					mejora1.setOpaque(false);
					setVisible(false);
					setVisible(true);									
					
				}
			}
		});	
		pMejoras.setLayout(new GridLayout(5, 2));
		pMejoras.add(mejora1);	
		 
		 
		////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////// AÑADIR ELEMENTOS DEL PANEL SUPERIOR ////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////
		superior.add(blanco, BorderLayout.EAST);
		superior.add(alternativa, BorderLayout.CENTER);
		superior.add(panelProduccion, BorderLayout.WEST);
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////// CREACION DE MODELO DE JLIST Y ASIGNACION DE LA JLIST ///////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////
		DefaultListModel<Edificios> dlist = new DefaultListModel<>();
		for(Edificios e : listaEdifs) {
			dlist.addElement(e);
		}
		liste.setModel(dlist);
		
		// se establece el renderer para los elementos de la lista
        liste.setCellRenderer(new MyCellRenderer());
        
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
							}else {
								System.out.println("no tienes pasta");
								liste.clearSelection();
							}
							logger.info("Incrementa");
				}
			}
			}
		});
		////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////// ACTIONLISTENERS DE LOS DIFERENTES ELEMENTOS DEL JMENU //////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////// OPCION 1 ///////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////
		menuitem1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String cont = JOptionPane.showInputDialog(ventana, "Ponga su contraseña: ", "");
				if(cont.equals(VentanaUsuario.usuarioActual.getContraseña())) {
					String cont2 = JOptionPane.showInputDialog(ventana, "Ponga su nueva contraseña: ", "");
					cc.cambiarContraseñaBD("Usuario.db",VentanaUsuario.usuarioActual.getnUsuario(), cont, cont2);
				}
				
			}
		});
		

		////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////// OPCION 2 ///////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////
		menuitem2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Estas seguro de que quieres reiniciar la partida y perder todo tu progreso?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(opcion==0) {
					cc.actualizarPartida("Usuario.db",VentanaUsuario.usuarioActual.getnUsuario(),0,1,1);	
					cc.actualizarPersonalEdifi("Usuario.db",VentanaUsuario.usuarioActual.getnUsuario(),0);
					ClaseContenedora classo = new ClaseContenedora();
					ArrayList<Usuario> lista = classo.sacarUsuarios("Usuario.db");
					for(Usuario u : lista) {
						if(u.getnUsuario().equals(VentanaUsuario.usuarioActual.getnUsuario())) {
							dinero_click = u.getDinero_click_personal();
							dinero_por_segundo = u.getDinero_por_segundo_personal();
							dinero_total = u.getDinero_total_personal();
						}
					}
			
					hilo.interrupt();
				
				}if(opcion==1) {
				}
			}
		});
		

		////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////// OPCION 3 ///////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////
		

		////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////// OPCION 4 ///////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////
		menuitem4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cc.actualizarPartida("Usuario.db",VentanaUsuario.usuarioActual.getnUsuario(),dinero_total,dinero_click,dinero_por_segundo);	
				for(int i = 0; i<listaEdifs.size(); i++) {
					cc.actualizarPersonalEdifi2("Usuario.db",VentanaUsuario.usuarioActual.getnUsuario(), listaEdifs.get(i).getNombre(),(int) listaEdifs.get(i).geteCantidad());
				}				
			}
		});

		////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////// OPCION 5 ///////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////
		menuitem5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaUsuario.main(null);
				dinero_click=0;
				dinero_por_segundo=0;
				hilo.interrupt();
				dispose();
			}
		});

		////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////// OPCION 6 ///////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////
		menuitem6.addActionListener(new ActionListener() {
			
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
				dinero_total=dinero_total+dinero_click;
				logger.info("click");
				puntuacion.setText("Ca$h Money Baby: "+String.valueOf(dinero_total)+"  ");
				
			}
		});
		
		//Añadir todos los paneles a la Ventana
		inferior.add(pApuestas, BorderLayout.WEST);
		inferior.add(sPanel, BorderLayout.CENTER);
		inferior.add(pMejoras, BorderLayout.EAST);
			
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

	private void abrirNavegador(java.awt.event.ActionEvent evt) {                                         
        try {
            Desktop.getDesktop().browse(new URI("https://youtu.be/dQw4w9WgXcQ"));

        } catch (URISyntaxException ex) {
            System.out.println(ex);

        }catch(IOException e){
            System.out.println(e);

        }
      
 }         
	
	static class HiloDineroPorSegundo extends Thread{
		@Override
			public void run() {

					try {
						while (true){	
						//VentanaClick v= new VentanaClick();
						Thread.sleep(1000);
						dinero_total = dinero_total+dinero_por_segundo;
						puntuacion.setText("Ca$h Money Baby: "+String.valueOf(dinero_total)+"  ");
						//for(int i=0; i=v.listaEdifs.; i++)
						//dinero_por_segundo=liste
						produccion.setText("Producción por segundo: "+String.valueOf(dinero_por_segundo)+" $/seg");
						
					} 
				}
				catch (Exception e) {
					System.out.println(e);

			}
		}
	}
	// este modelo de lista proporciona un método
    // addAll para añadir una lista de personas directamente
    // DefaultListModel no contiene este método en Java 8
    class MyListModel extends DefaultListModel<Edificios> {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public MyListModel(Collection<Edificios> edif) {
            add(edif);
        }

        public void add(Collection<Edificios> edif) {
            for (Edificios e : edif) {
                addElement(e);
            }
        }

    }
	// Esta clase define el renderer para los elementos visuales de la tabla
    // El renderer debe implementar la interfaz ListCellRender.
    // En este caso se extiende un JLabel como componente a visualizar
    class MyCellRenderer extends JLabel implements ListCellRenderer<Edificios> {

        /**
         *
         */
        private static final long serialVersionUID = 1L;
        private ImageIcon icono;

        // constructor del renderer que carga los iconos a utilizar
        public MyCellRenderer() {
            icono = new ImageIcon(getClass().getResource("bank_PNG28.png"));

        }

        // este es el método que se llama para obtener el componente
        // que se debe pintar en cada elemento de la lista
        public Component getListCellRendererComponent(JList<? extends Edificios> list, Edificios value, int index,
                boolean isSelected, boolean hasFocus) {
            // se establece el valor del texto mostrado en el JLabel de la celda
        	setText(value.toString());
        	setFont(new Font("Arial", Font.ITALIC, 27));
            setIcon(icono);
            // se devuelve el componente visual. siempre se devuelve this
            // porque se evita crear uno nuevo para cada elemento de la lista
            return this;
        }

    }

	////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////ESTA CLASE EXTERNA TIENE LA UNICA FUNCIONALIDAD /////////////////////////////////////////
	////////////////////DE CREAR UN JPANEL CON UNA IMAGEN DE FONDO//////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    class FondoPanel extends JPanel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 401471556325269633L;
		private Image imagen;
		
		@Override
		public void paint(Graphics g) {
			imagen = new ImageIcon(getClass().getResource("/ventanas/eurosistem.jpg")).getImage();
			g.drawImage(imagen, 0, 0, getWidth(), getHeight(), ventana);
			setOpaque(false);
			super.paint(g);
		}

	}
	class FondoFlechaIzq extends JLabel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 401471556325269633L;
		private Image imagen;
		
		@Override
		public void paint(Graphics g) {
			imagen = new ImageIcon(getClass().getResource("/ventanas/izqflecha.png")).getImage();
			g.drawImage(imagen, 0, 0, getWidth(), getHeight(), ventana);
			setOpaque(false);
			super.paint(g);
		}

	}
	class FondoFlechaDer extends JLabel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 401471556325269633L;
		private Image imagen;
		
		@Override
		public void paint(Graphics g) {
			imagen = new ImageIcon(getClass().getResource("/ventanas/derflecha.png")).getImage();
			g.drawImage(imagen, 0, 0, getWidth(), getHeight(), ventana);
			setOpaque(false);
			super.paint(g);
		}

	}

}
