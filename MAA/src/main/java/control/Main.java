package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
import view.Debug;
import view.ScreenFunctions;

public class Main {
	public static Properties properties = null ;
	
	public static void initProperties(){
		properties = new Properties() ;
		try {
			properties.load( Main.class.getClassLoader().getResourceAsStream( 
					"project.properties" ) ) ;
		}
		catch ( Exception e ) {
			Debug.logErr( "Main: initProperties" , e ) ;
		}
	}
	
	public static void main( String[] args ) {
		boolean running = true ;
		
		//Initialize properties
		initProperties() ;
		
		//Adding player
		List<AbstractEntity> entities = new ArrayList<>() ;
		entities.add( 
				new FactoryProducer()
				.getFactory( "Entity" )
				.getEntity( "Player" )
				 ) ;
		AbstractEntity player = entities.get( 0 ) ;
		player.setX( 5 ) ;
		player.setY( 5 ) ;
		
		ArrayList<ArrayList<AbstractTile>> map = 
				new ArrayList<ArrayList<AbstractTile>>() ;
		
		try( 	Terminal terminal = ScreenFunctions.startTerminal( 
						properties.getProperty( "artifactId" ) + " " + 
						properties.getProperty( "version" ) ) ;
				Screen screen = new TerminalScreen( terminal ) ) {
			
			//Initialize screen
			ScreenFunctions.initScreen( screen ) ;
			
			for( int y = 0 ; y < screen.getTerminalSize().getRows() ; y++ ){
				map.add( new ArrayList<AbstractTile>() ) ;
				for( 
						int x = 0 ; 
						x < screen.getTerminalSize().getColumns() ; 
						x++ ){
					if( 
							x == 0 || 
							x == screen.getTerminalSize().getColumns() - 1 ){
						map.get( y ).add( 
								new FactoryProducer()
								.getFactory( "TILE" )
								.getTile( "WALL" ) ) ;
					}
					else if( 
							y == 0 || 
							y == screen.getTerminalSize().getRows() - 1 ){
						map.get( y ).add( 
								new FactoryProducer()
								.getFactory( "TILE" )
								.getTile( "WALL" ) ) ;
					}
					else
					{
						map.get( y ).add( 
								new FactoryProducer()
								.getFactory( "TILE" )
								.getTile( "FLOOR" ) ) ;
					}
				}
			}
			
			for( int i = 5 ; i < 15 ; i++ ){
				map.get( 10 ).get( i ).setWalkable( false ) ;
				map.get( 10 ).get( i ).setCharacter( '%' );
			}
			
			while( running ) {
				//XXX Processing stuff
				
				//XXX Graphical stuff
				
					//Wipe screen DO NOT MOVE this needs to be BEFORE drawing
				ScreenFunctions.wipeScreen( screen ) ;	
				
					//Draw map
				for( int y = 0 ; y < screen.getTerminalSize().getRows() ; y++ ){
					for(
							int x = 0 ; 
							x < screen.getTerminalSize().getColumns() ; 
							x++ ){
						screen.setCharacter( 
								x , 
								y , 
								new TextCharacter( 
										map.get( y ).get( x ).getCharacter() ) ) ;
					}
				}
				
				//TODO: Draw items on floor
				
					//Draw entities
				ScreenFunctions.drawEntities( screen , entities ) ;
				
					//Refresh screen DO NOT MOVE this needs to be AFTER drawing
				ScreenFunctions.refreshScreen( screen ) ;
				
				//XXX Input stuff
				
					//Get input (blocking)
				KeyStroke input = screen.readInput() ;
				
					//Player movement
				player.move( input , map ) ;
				
					//EXIT ( check escape or input closed )
				if( ButtonUtils.isButtonPressed( input , KeyType.Escape ) ||
						ButtonUtils.isButtonPressed( input , KeyType.EOF ) ) {
					running = false ;
				}
				
			}
			
			//End screen
			ScreenFunctions.stopScreen( screen ) ;
		}
		catch ( Exception e ) {
			Debug.logErr( "Main: catch" , e ) ;
		}
	}
}