package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
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
import util.physics.AbstractVector2;
import util.physics.Vector2d;
import util.physics.Vector2i;
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
			
			//XXX Main Loop
			while( running ) {
				//XXX Processing stuff
				
				//camera.setCenter( player.getPosition() ) ;
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
						if ( 	indY < maxIndices.getY() && 
								indX < maxIndices.getX() ) {
							if( AbstractVector2.distance( 
									player.getPosition() , 
									new Vector2d( 
											(double)indX , 
											(double)indY ) ) <
								player.getViewRadius() && 
								( lightMap.get( 
										new KeySet( indX , indY ) ) != null &&
								lightMap.get( 
										new KeySet( indX , indY ) )
										.booleanValue() ) ){
								map.get( indY ).get( indX )
								.setDiscovered( true ) ;
								screen.setCharacter( 
										x , 
										y ,  
										new TextCharacter( 
												map.get( indY ).get( indX )
													.getCharacter() ,
												map.get( indY ).get( indX )
													.getForegroundColor() ,
												map.get( indY ).get( indX )
													.getBackgroundColor() ) ) ;
							}
							else{
								if( map.get( indY ).get( indX )
										.isDiscovered() ){
									screen.setCharacter( 
											x , 
											y ,  
											new TextCharacter( 
													map.get( indY ).get( indX )
														.getCharacter() ,
													TextColor.ANSI.WHITE ,
													TextColor.ANSI.BLACK ) ) ;
								}
							}
							
						}
						
							//TODO: Draw items on floor
						
							//Draw entities
						for( AbstractEntity entity : entities ) {
							if( entity.isVisible() ) {
								if( 	entity.getPosition().getX() == indX &&
										entity.getPosition().getY() == indY ) {
									screen.setCharacter( 
											x , 
											y ,  
											new TextCharacter( 
													entity.getCharacter() ,
													entity
														.getForegroundColor() ,
													entity
														.getBackgroundColor() ) 
											) ;
								}
							}
						}
						indX++ ;
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
			Debug.logln( "properties : " + properties.toString() , debug ) ;
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