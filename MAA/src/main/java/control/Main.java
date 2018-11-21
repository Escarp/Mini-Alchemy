package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;

import model.FactoryProducer;
import model.entity.AbstractEntity;
import model.entity.Player;
import model.tile.AbstractTile;
import util.ButtonUtils;
import util.KeySet;
import util.physics.Vector2d;
import util.physics.Vector2i;
import view.Camera;
import view.Debug;
import view.ScreenFunctions;

public class Main {
	public static Properties properties		= null ;
	public static String artifactId			= "" ;
	public static String version			= "" ;
	public static String name				= "" ;
	public static int screenWidth			= 80 ;
	public static int screenHeight			= 24 ;
	public static boolean debug				= false ;
	public static FactoryProducer producer	= new FactoryProducer() ;
	
	public static void main( String[] args ) {
		Debug.logln( "[Game] : [Start]" ) ;
		
		boolean running = true ;
		
		if( args.length > 0 ){
			debug = Boolean.valueOf( args[ 0 ] ) ;
		}
		
		//Initialize properties
		initProperties() ;
		
		//Initialize entities
		//TODO : move to level class
		Debug.logln( "initEntities : [Start]" , debug ) ;
		List<AbstractEntity> entities = new ArrayList<>() ;
		
			//Adding player
			//not this tho
		entities.add( producer.getFactory( "Entity" ).getEntity( "Player" ) ) ;
		Player player = (Player)entities.get( 0 ) ;
		player.setViewRadius( 6 );
		
		Debug.logln( "initEntities : initialized " + entities.size() + 
				" entities" , debug ) ;
		Debug.logln( "initEntities : [End]" , debug ) ;
		
		ArrayList<ArrayList<AbstractTile>> map = 
				new ArrayList<ArrayList<AbstractTile>>() ;
		
		try( 	Terminal terminal = ScreenFunctions.startTerminal( 
						name , 
						screenWidth , 
						screenHeight );
				Screen screen = new TerminalScreen( terminal ) ) {
			
			TerminalSize Tsize = screen.getTerminalSize() ;
			int rows = Tsize.getRows() ;
			int cols = Tsize.getColumns() ;
			Debug.logln( "terminalSize: " + Tsize.toString() , debug ) ;
			
			player.setPosition( new Vector2d( 100d / 2 , 100d / 2 ) ) ;
			
			//Initialize camera
			Camera camera = new Camera( 
					new Vector2i( 
							cols - ( cols / 5 ) , 
							rows / 2 ) , 
					new Vector2i() ) ;
			
			//Initialize screen
			ScreenFunctions.initScreen( screen ) ;
			
			//Init Map
			initMap( map , 100 , 100 ) ;
			
			//XXX Main Loop
			while( running ) {
				//XXX Processing stuff
				
				camera.setPosition( new Vector2i( 
						(int)( player.getPosition().getX() - 
								( camera.getDimensions().getX() / 2 ) ) , 
						(int)( player.getPosition().getY() - 
								( camera.getDimensions().getY() / 2 ) ) ) ) ;
				
				camera.setIndices( map ) ;
				
				camera.createLightMap( player , map ) ;
				
				HashMap<KeySet , Boolean> lightMap = 
						(HashMap<KeySet, Boolean>) camera.getLighMap() ;
				
				camera.setVisibleEntities( entities ) ;
				
				Vector2i minIndices = camera.getMinIndices() ;
				Vector2i maxIndices = camera.getMaxIndices() ;
				
				//XXX Graphical stuff
				
					//Wipe screen DO NOT MOVE-this needs to be BEFORE drawing
				ScreenFunctions.wipeScreen( screen ) ;	
				
				int indX = (int)minIndices.getX() ;
				int indY = (int)minIndices.getY() ;
				
				for( int y = rows / 4 ; y < rows - ( rows / 4 ) ; y++ ) {
					indX = (int)minIndices.getX() ;
					for( int x = 0 ; x < cols - ( cols / 5 ) ; x++ ) {
						//Draw what the camera can see
						
							//Draw map
						ScreenFunctions.drawMap( 
								screen , x , y , 
								lightMap , maxIndices , minIndices , map , 
								indX , indY , player ) ;
						
							//TODO : Draw items on floor
						
							//Draw entities
						ScreenFunctions.drawEntities( screen , x , y , 
								entities , indX , indY ) ;
						
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
					Vector2d oldPos = new Vector2d( 
							player.getPosition().getX() , 
							player.getPosition().getY() ) ;
					player.move( input , map ) ;
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
	
	private static void initMap( 
			//FIXME : to be removed
			ArrayList<ArrayList<AbstractTile>> map , 
			int rows, 
			int cols) {
		Debug.logln( "initMap : [Start]" , debug ) ;
		for( int y = 0 ; y < rows ; y++ ){
			map.add( new ArrayList<AbstractTile>() ) ;
			for( int x = 0 ; x < cols ; x++ ){
				if( x == 0 || x == cols - 1 ){
					map.get( y ).add( producer.getFactory( "TILE" )
							.getTile( "WALL" ) ) ;
				}
				else if( y == 0 || y == rows - 1 ){
					map.get( y ).add( producer.getFactory( "TILE" )
							.getTile( "WALL" ) ) ;
				}
				else
				{
					map.get( y ).add( producer.getFactory( "TILE" )
							.getTile( "FLOOR" ) ) ;
				}
			}
		}
		
		for( int i = 5 ; i < 15 ; i++ ){
			map.get( 10 ).get( i ).setWalkable( false ) ;
			map.get( 10 ).get( i ).setCharacter( (char) 126 );
			map.get( 10 ).get( i ).setForegroundColor( 
					TextColor.ANSI.RED );
			map.get( 10 ).get( i ).setBackgroundColor( 
					TextColor.ANSI.BLACK );
		}
		
		for( int i = 20 ; i < 45 ; i++ ){
			map.get( 10 ).set( i, producer.getFactory( "TILE" )
					.getTile( "WALL" ) ) ;
		}
		
		map.get( 20 ).set( 20 , producer.getFactory( "TILE" )
				.getTile( "WALL" ) ) ;
		
		Debug.logln( "initMap : initialized " + 
				map.size() * map.get( 0 ).size() + " tiles" , debug ) ;
		Debug.logln( "initMap : [End]" , debug ) ;
	}
}