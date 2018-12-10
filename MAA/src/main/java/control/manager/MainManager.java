package control.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import control.Main;
import model.DAO.builder.EntityBuilder;
import model.entity.Entity;
import util.EntityUtils.EntityType;
import view.Debug;
import view.GameTerminal;

public class MainManager {
	private SystemsManager systemsManager ;
	private MapManager mapManager ;
	ArrayList<Entity> entities ;
	GameTerminal gTerminal ;
	
	private String artifactId ;
	private String version ;
	private int screenWidth ;
	private int screenHeight ;
	
	public MainManager() {
		systemsManager = new SystemsManager() ;
		mapManager = new MapManager() ;
		
		initProperties() ;
		initTerminal() ;
		
		systemsManager.init( gTerminal ) ;

		EntityBuilder eb = new EntityBuilder() ;
		
		entities = new ArrayList<>() ;
		entities.add( eb.createEntity( EntityType.PLAYER ) ) ;
	}
	
	
	
	private void initTerminal() {
		//Create screen
		try {
			gTerminal = new GameTerminal( 
					artifactId + " " + version , screenWidth , screenHeight ) ;
		}
		catch( IOException e ) {
			Debug.logErr( "MainManager : initTerminal" , e ) ;
		}
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
			running = systemsManager.input( entities , gTerminal.getInput() ) ;
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
			
			mapManager.draw( gTerminal , 0 ) ;
			systemsManager.draw( entities ) ;
			
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
