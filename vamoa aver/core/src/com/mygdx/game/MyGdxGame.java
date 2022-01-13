package com.mygdx.game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Array.ArrayIterator;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.SortedIntList.Iterator;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private Texture dineroCae;
	   private Texture cubo;
	   private Sound sonidoCoger;
	   private Music musicaFondo;
	   private OrthographicCamera camera;
	   private Rectangle cuboJuego;
	   private Array<Rectangle> dinerops;
	   private long ultimoBillete_t;
	   public static int puntos=0;
	   private int fallos=0;
	   private BitmapFont font;
	   private int velocidad=100;
	   
	   public void guardarPuntos(int puntos){
			try {			
				Connection conn = DriverManager.getConnection("jdbc:sqlite:src//Usuario.db");
				Statement stmt = conn.createStatement();
				String sql = String.format("INSERT INTO punto VALUES (%d)", puntos);
				//logger.log(Level.INFO, "Statement: " + sql);
				stmt.executeUpdate(sql);
				stmt.close();
				Statement stmt2 = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM punto");
				while( rs.next() ) { 
					int i = rs.getInt("elpunto");
					System.out.println(i);
				}
				
				stmt2.close();
				rs.close();
				conn.close(); 
				} catch (SQLException e) {
				System.out.println(e);
				}
			
		}

	   private void spawneaDinero() {
		      Rectangle dinero = new Rectangle();
		      dinero.x = MathUtils.random(0, 800-64);
		      dinero.y = 480;
		      dinero.width = 64;
		      dinero.height = 64;
		      dinerops.add(dinero);
		      ultimoBillete_t = TimeUtils.nanoTime();
		      
		   }
	
	@Override
	public void create () {
		
		dineroCae = new Texture("dinero.png");
	    cubo = new Texture("cubo.png");
	    sonidoCoger = Gdx.audio.newSound(Gdx.files.internal("chicling.mp3"));
	    musicaFondo = Gdx.audio.newMusic(Gdx.files.internal("musik.mp3"));
	    musicaFondo.setLooping(true);
	    musicaFondo.play();
	    camera = new OrthographicCamera();
	    camera.setToOrtho(false, 800, 480);
	    batch = new SpriteBatch();
	    cuboJuego = new Rectangle();
	    cuboJuego.x = 800 / 2 - 64 / 2;
	    cuboJuego.y = 20;
	    cuboJuego.width = 64;
	    cuboJuego.height = 64;
	    dinerops = new Array<Rectangle>();
	    font = new BitmapFont();
	    spawneaDinero();
	}

	 @Override
	   public void render() {
		 //Seteamos el color de fondo
	      ScreenUtils.clear(0.3f, 0.1f, 0.2f, 1);

	      //Actualizamos la camara
	      camera.update();

	      //Hacemos que el batch renderice la poscion de la camara
	      batch.setProjectionMatrix(camera.combined);

	      //Ponemos en marcha el batch y hacemps que empiecen a caer los billetes
	      batch.begin();
	      batch.draw(cubo, cuboJuego.x, cuboJuego.y);
	      for(Rectangle dinerop: dinerops) {
	         batch.draw(dineroCae, dinerop.x, dinerop.y);
	         
	      }
	      font.draw(batch, "Puntuación: " + puntos, 10, 15);
	      batch.end();

	     //Hacemos que el cubo se mueva en base a las teclas del usuario y limitamos su movimiento
	      if(Gdx.input.isKeyPressed(Keys.LEFT)) cuboJuego.x -= 200 * Gdx.graphics.getDeltaTime();
	      if(Gdx.input.isKeyPressed(Keys.RIGHT)) cuboJuego.x += 200 * Gdx.graphics.getDeltaTime();
	      if(cuboJuego.x < 0) cuboJuego.x = 0;
	      if(cuboJuego.x > 800 - 64) cuboJuego.x = 800 - 64;

	      //Aquí decidimos cada cuanto crear cada billete
	      if(TimeUtils.nanoTime() - ultimoBillete_t > 1000000000) {
	    	  spawneaDinero();
	    	  }

	      //Vamos moviendo los billetes hacia abajo y hacemos que desaparezcan si se 
	      //salen de la pantalla y se aumenta el contador de fallos, si se llega a 3 se acaba el juego
	      //Además, aumentamos la velocidad de cómo caen
	      for (ArrayIterator<Rectangle> iter = dinerops.iterator(); iter.hasNext(); ) {
	         Rectangle dineroO = iter.next();
	         dineroO.y -= velocidad * Gdx.graphics.getDeltaTime();
	         if(dineroO.y + 64 < 0) { 
	        	 iter.remove();
	        	 
	        	 fallos+=1;
	        	 if(fallos==3){
	        		 
	        		 dispose();
	        		 
	        	 }
	         }
	         if(dineroO.overlaps(cuboJuego)) {
	            sonidoCoger.play();
	            puntos+=1;
	            velocidad+=10;
	            if(fallos>0) {
	            	fallos-=1;
	            }
	            iter.remove();
	         }
	      }
	   }

	   @Override
	   public void dispose() {
	     //Se cierra todo
		  
	      dineroCae.dispose();
	      cubo.dispose();
	      sonidoCoger.dispose();
	      musicaFondo.dispose();
	      guardarPuntos(puntos);
	      batch.dispose();
	      Gdx.app.exit();
	     
	      //System.exit(0);
	   }
}
