package control.manager;

import java.io.IOException;
import java.util.Properties;

import control.Main;
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
		systemsManager	= new SystemsManager() ;
		mapManager		= new MapManager() ;
		
		currentLevel = 0 ;
		
		initProperties() ;
		initTerminal() ;
		
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
		catch( IOException e ) {
			Debug.logErr( "MainManager : initTerminal" , e ) ;
		}
		Debug.logln( "initTerminal : [End]" ) ;
	}

	public void initProperties() {
		Debug.logln( "initProperties : [Start]" ) ;
		Properties properties = new Properties() ;
		
		try {
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
			running = systemsManager.input( 
					mapManager.getLevels().get( currentLevel ).getEntities() , 
					gTerminal.getInput() ) ;
		}
		catch( IOException e ) {
			Debug.logErr( "MainManager : input" , e ) ;
		}
		
		return running ;
	}
	
	public void draw() {
		try {
			//Wipe screen : WARNING : this NEEDS to be here before everything!
			gTerminal.wipeScreen() ;
			
			mapManager.draw( gTerminal , currentLevel ) ;
			systemsManager.draw( 
					mapManager.getLevels().get( currentLevel ).getEntities() ) ;
			
			//Refresh screen : WARNING : this NEEDS to be here after everything!
			gTerminal.refreshScreen() ;
		}
		catch( IOException e ) {
			Debug.logErr( "MainManager : draw" , e ) ;
		}
	}
	
	public void update() {
		//TODO
	}
	
	public void stop() {
		try {
			gTerminal.stop() ;
		}
		catch( IOException e ) {
			Debug.logErr( "MainManager : stop" , e ) ;
		}
	}
}
