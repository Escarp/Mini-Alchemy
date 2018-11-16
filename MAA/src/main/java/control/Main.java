package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;

import model.FactoryProducer;
import model.entity.AbstractEntity;
import model.tile.AbstractTile;
import util.ButtonUtils;
import util.physics.Vector2;
import view.Camera;
import view.Debug;
import view.ScreenFunctions;

public class Main {
	public static Properties properties = null ;
	public static String artifactId = "" ;
	public static String version = "" ;
	public static String name = "" ;
	public static int screenWidth = 80 ;
	public static int screenHeight = 24 ;
	public static boolean debug = false ;
	public static FactoryProducer producer = new FactoryProducer() ;
	
	public static void main( String[] args ) {
		Debug.logln( "[Game] : [Start]" ) ;
		
		boolean running = true ;
		
		if( args.length > 0 ){
			debug = Boolean.valueOf( args[ 0 ] ) ;
		}
		
		//Initialize properties
		initProperties() ;
		
		//Initialize entities
		Debug.logln( "initEntities : [Start]" , debug ) ;
		List<AbstractEntity> entities = new ArrayList<>() ;
		
			//Adding player
		entities.add( producer.getFactory( "Entity" ).getEntity( "Player" ) ) ;
		AbstractEntity player = entities.get( 0 ) ;
		
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
			
			player.setPosition( new Vector2( 5 , 5 ) ) ;
			
			//Initialize camera
			Camera camera = new Camera( 
					new Vector2( cols - ( cols / 5 ) , rows / 2 )  , 
					player.getPosition() ) ;
			
			//Initialize screen
			ScreenFunctions.initScreen( screen ) ;
			
			Debug.logln( "terminalSize: " + Tsize.toString() , debug ) ;
			
			//Init Map
			initMap( map , 100 , 100 ) ;
			
			for( int i = 5 ; i < 15 ; i++ ){
				map.get( 10 ).get( i ).setWalkable( false ) ;
				map.get( 10 ).get( i ).setCharacter( '!' );
			}
			
			//XXX Main Loop
			while( running ) {
				//XXX Processing stuff
				
				camera.setCenter( player.getPosition() ) ;
				
				Vector2[] visibleMap = camera.getVisibleMapIndexes( map ) ;
				int minX = (int)visibleMap[ 0 ].getX() ;
				int maxX = (int)visibleMap[ 1 ].getX() ;
				
				int minY = (int)visibleMap[ 0 ].getY() ;
				int maxY = (int)visibleMap[ 1 ].getY() ;
				
				//XXX Graphical stuff
				
					//Wipe screen DO NOT MOVE-this needs to be BEFORE drawing
				ScreenFunctions.wipeScreen( screen ) ;	
				
				int mX = minX ;
				int mY = minY ;
				
				for( int y = rows / 4 ; y < rows - ( rows / 4 ) ; y++ ){
					mX = minX ;
					for( int x = 0 ; x < cols - ( cols / 4 ) ; x++ ){
						//TODO: Draw what the camera can see
						
							//Draw map
						if ( mY < maxY && mX < maxX ) {
							
							screen.setCharacter( 
									x , 
									y ,  
									new TextCharacter( 
											map.get( mY ).get( mX )
											.getCharacter() ) ) ;
							
						}
						mX++ ;
							//TODO: Draw items on floor
							
							//TODO: Draw entities
					}
					mY++ ;
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
					Vector2 oldPos = new Vector2( 
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
			Debug.logErr( "Main: catch" , e ) ;
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
			Debug.logln( "properties: " + properties.toString() , debug ) ;
		}
		catch ( Exception e ) {
			Debug.logErr( "Main: initProperties" , e ) ;
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
		Debug.logln( "initMap : initialized " + 
				map.size() * map.get( 0 ).size() + " tiles" , debug ) ;
		Debug.logln( "initMap : [End]" , debug ) ;
	}
}