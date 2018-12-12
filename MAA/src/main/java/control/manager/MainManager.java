package control.manager;

import java.util.Properties;

import control.Main;
import model.component.Position;
import model.entity.Entity;
import util.ComponentUtils.ComponentType;
import util.EntityUtils.EntityType;
import view.Debug;
import view.GameTerminal;

public class MainManager {
	private SystemsManager systemsManager ;
	private MapManager mapManager ;
	private GameTerminal gTerminal ;
	private String artifactId ;
	private String version ;
	private int screenWidth ;
	private int screenHeight ;
	private int currentLevel ;
	
	public MainManager() {
		//Initialize Systems and Maps
		systemsManager	= new SystemsManager() ;
		mapManager		= new MapManager() ;
		
		//Set current level
		currentLevel = 0 ;
		
		//Initialize properties and build the screen with them
		initProperties() ;
		initTerminal() ;
		
		//End systems initialization passing the screen
		systemsManager.init( gTerminal ) ;
	}
	
	private void initTerminal() {
		Debug.logln( "initTerminal : [Start]" ) ;
		try {
			//Create screen
			gTerminal = new GameTerminal( 
					artifactId + " " + version , screenWidth , screenHeight ) ;
			Debug.logln( "initTerminal : " + gTerminal.toString() ) ;
		}
		catch( Exception e ) {
			Debug.logErr( "MainManager : initTerminal" , e ) ;
		}
		Debug.logln( "initTerminal : [End]" ) ;
	}

	public void initProperties() {
		Debug.logln( "initProperties : [Start]" ) ;
		Properties properties = new Properties() ;
		try {
			//Load properties
			properties.load( Main.class.getClassLoader().getResourceAsStream( 
					"application.properties" ) ) ;
			Debug.logln( "initproperties : " + properties.toString() ) ;
		}
		catch ( Exception e ) {
			Debug.logErr( "Main : initProperties" , e ) ;
		}
		artifactId = properties.getProperty( "artifactId" ) ;
		version = properties.getProperty( "version" ) ;
		screenWidth = Integer.parseInt( properties.getProperty( 
				"screenWidth" ) ) ;
		screenHeight = Integer.parseInt( properties.getProperty( 
				"screenHeight" ) ) ;
		Debug.logln( "initProperties : [End]" ) ;
	}
	
	public boolean input() {
		boolean running = false ;
		try {
			//Get the input from the screen ( blocking ) and pass it to 
			//the systems for processsing
			running = systemsManager.input( 
					mapManager.getLevels().get( currentLevel ).getEntities()
							.values() , 
					gTerminal.getInput() ) ;
		}
		catch( Exception e ) {
			Debug.logErr( "MainManager : input" , e ) ;
		}
		return running ;
	}
	
	public void draw() {
		try {
			//Wipe screen : WARNING : this NEEDS to be here before everything!
			gTerminal.wipeScreen() ;
			
			int minX = gTerminal.getMinXIndex() ;
			int minY = gTerminal.getMinYIndex() ;
			
			int maxX = gTerminal.getMaxXIndex() ;
			int maxY = gTerminal.getMaxYIndex() ;
			
			int index = minX ;
			
			for(	int y = screenHeight / 4 ; 
					y < screenHeight - ( screenHeight / 4 ) ; y++ ) {
				index = minX ;
				for( int x = 0 ; x < screenWidth - ( screenWidth / 5 ) ; x++ ) {
					if( index < maxX && minY < maxY ) {
						mapManager.draw( 
								gTerminal , 
								currentLevel , 
								x , 
								y ,
								index , 
								minY ) ;
						
						index++ ;
						
						for( Entity entity : 
									mapManager.getLevels().get( currentLevel )
										.getEntities().values() ){
							if( 	( (Position)entity.getComponent( 
											ComponentType.POSITION ) )
											.getX() == index && 
									( (Position)entity.getComponent( 
											ComponentType.POSITION ) )
											.getY() == minY ){
								//FIXME : draw wntities
							}
						}
					}
				}
				minY++ ;
			}
			
			//Refresh screen : WARNING : this NEEDS to be here after everything!
			gTerminal.refreshScreen() ;
		}
		catch( Exception e ) {
			Debug.logErr( "MainManager : draw" , e ) ;
		}
	}
	
	public void update() {
		systemsManager.setMap( 
				mapManager.getLevels().get( currentLevel ).getMap() ) ;
		gTerminal.setPosition( ( Position )mapManager
				.getLevels().get( currentLevel )
				.getEntities().get( EntityType.PLAYER )
				.getComponent( ComponentType.POSITION ) ) ;
		gTerminal.calculateIndexes( 
				mapManager.getLevels().get( currentLevel ).getMap() ) ;
	}
	
	public void stop() {
		try {
			//Stop the screen
			gTerminal.stop() ;
		}
		catch( Exception e ) {
			Debug.logErr( "MainManager : stop" , e ) ;
		}
	}
}
