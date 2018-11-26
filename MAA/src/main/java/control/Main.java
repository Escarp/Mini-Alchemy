package control;

import java.util.ArrayList;
import java.util.Properties;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;

import model.FactoryProducer;
import model.entity.Player;
import model.world.AbstractLevel;
import util.ButtonUtils;
import util.physics.Vector2i;
import view.Camera;
import view.Debug;
import view.ScreenFunctions;

public class Main {
	public static Properties	properties		= null ;
	public static String		artifactId		= "" ;
	public static String		version			= "" ;
	public static String		name			= "" ;
	public static int			screenWidth		= 80 ;
	public static int			screenHeight	= 24 ;
	public static boolean		debug			= false ;
	
	public static void main( String[] args ) {
		Debug.logln( "[Game] : [Start]" ) ;
		
		boolean running = true ;
		FactoryProducer producer	= new FactoryProducer() ;
		
		if( args.length > 0 ){
			debug = Boolean.valueOf( args[ 0 ] ) ;
		}
		
		//Initialize properties
		initProperties() ;
		
		//Initialize map and entities
		ArrayList<AbstractLevel> levels = new ArrayList<AbstractLevel>() ;
		levels.add( producer.getFactory( "level" ).getLevel( "drunk" ) ) ;
		levels.get( 0 ).generateMap( 100 , 100 ) ;
		levels.get( 0 ).initEntities() ;
		
		//Initialize Player
		Player player = (Player) levels.get( 0 ).getEntities().get( 0 ) ;
		player.setViewRadius( 8 ) ;
		player.setPosition( new Vector2i( 100 / 2 , 100 / 2 ) ) ;
		
		try( 	Terminal terminal = ScreenFunctions.startTerminal( 
						name , screenWidth , screenHeight );
				Screen screen = new TerminalScreen( terminal ) ) {
			//Initialize camera
			Camera camera = new Camera( 
					new Vector2i( 
							screenWidth - ( screenWidth / 5 ) , 
							screenHeight / 2 ) , 
					null ) ;
			//Initialize screen
			ScreenFunctions.initScreen( screen ) ;
			
			//XXX Main Loop
			while( running ) {
				//XXX Processing stuff
				camera.setPosition( new Vector2i( 
						(int)( player.getPosition().getX() - 
								( camera.getDimensions().getX() / 2 ) ) , 
						(int)( player.getPosition().getY() - 
								( camera.getDimensions().getY() / 2 ) ) ) ) ;
				camera.setIndices( levels.get( 0 ).getMap() ) ;
				camera.createLightMap( player , levels.get( 0 ).getMap() ) ;
				camera.setVisibleEntities( levels.get( 0 ).getEntities() ) ;
				
				Vector2i minIndexes = camera.getMinIndexes() ;
				Vector2i maxIndexes = camera.getMaxIndexes() ;
				int indX = minIndexes.getX() ;
				int indY = minIndexes.getY() ;
				
				//XXX Graphical stuff
				//Wipe screen DO NOT MOVE-this needs to be BEFORE drawing
				ScreenFunctions.wipeScreen( screen ) ;	
				
				for( 	int y = screenHeight / 4 ; 
						y < screenHeight - ( screenHeight / 4 ) ; 
						y++ ) {
					indX = minIndexes.getX() ;
					for( 	int x = 0 ; 
							x < screenWidth - ( screenWidth / 5 ) ; 
							x++ ) {
						//Draw what the camera can see
						//Draw map
						ScreenFunctions.drawMap( 
								screen , x , y , 
								camera.getLighMap() , maxIndexes , minIndexes , 
								levels.get( 0 ).getMap() , 
								indX , indY , player ) ;
						//TODO : Draw items on floor
						//Draw entities
						ScreenFunctions.drawEntities( screen , x , y , 
								levels.get( 0 ).getEntities() , indX , indY ) ;
						indX++ ;
						//TODO : Draw GUI
					}
					indY++ ;
				}
				//Refresh screen DO NOT MOVE-this needs to be AFTER drawing
				ScreenFunctions.refreshScreen( screen ) ;
				
				//XXX Input stuff
				boolean doRepeat = false ;
				do{
					doRepeat = false ;
					//Get input ( blocking )
					KeyStroke input = screen.readInput() ;
					
					//EXIT ( check escape or input closed )
					if( ButtonUtils.areButtonsPressed( 
							input , KeyType.Escape , KeyType.EOF ) ) {
						running = false ;
						doRepeat = false ;
						break ;
					}
					//Player movement
					Vector2i oldPos = new Vector2i( 
							player.getPosition().getX() , 
							player.getPosition().getY() ) ;
					player.move( input , levels.get( 0 ).getMap() ) ;
					if( oldPos.equals( player.getPosition() ) ){
						doRepeat = true ;
					}
				}while( doRepeat ) ;
			}
			//End screen
			ScreenFunctions.stopScreen( screen ) ;
		}
		catch ( Exception e ) {
			Debug.logErr( "Main : catch" , e ) ;
		}
		finally{
			Debug.logln( "[Game] : [End]" ) ;
		}
	}
	
	public static void initProperties(){
		Debug.logln( "initProperties : [Start]" , debug ) ;
		properties = new Properties() ;
		try {
			properties.load( Main.class.getClassLoader().getResourceAsStream( 
					"project.properties" ) ) ;
			Debug.logln( "initproperties : " + properties.toString() , debug ) ;
		}
		catch ( Exception e ) {
			Debug.logErr( "Main : initProperties" , e ) ;
		}
		
		artifactId = properties.getProperty( "artifactId" ) ;
		version = properties.getProperty( "version" ) ;
		name = artifactId + " " + version ;
		screenWidth = Integer.parseInt( properties.getProperty( 
				"screenWidth" ) ) ;
		screenHeight = Integer.parseInt( properties.getProperty( 
				"screenHeight" ) ) ;
		
		Debug.logln( "initProperties : [End]" , debug ) ;
	}
}