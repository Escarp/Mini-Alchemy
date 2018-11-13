package control;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;
import model.entity.AbstractEntity;
import model.factory.FactoryProducer;
import util.ButtonUtils;
import view.Debug;
import view.ScreenFunctions;

public class Main {
	public static void main( String[] args ) {
		boolean running = true ;
		
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
		
		try( 	Terminal terminal = ScreenFunctions.startTerminal() ;
				Screen screen = new TerminalScreen( terminal ) ) {
			
			//Initialize screen
			ScreenFunctions.initScreen( screen ) ;
			
			while( running ) {
				//XXX Processing stuff
				
				//XXX Graphical stuff
				
					//Wipe screen DO NOT MOVE this needs to be BEFORE drawing
				ScreenFunctions.wipeScreen( screen ) ;	
				
				//TODO: Draw map
				//TODO: Draw items on floor
				
					//Draw entities
				ScreenFunctions.drawEntities( screen , entities ) ;
				
					//Refresh screen DO NOT MOVE this needs to be AFTER drawing
				ScreenFunctions.refreshScreen( screen ) ;
				
				//XXX Input stuff
				
					//Get input (blocking)
				KeyStroke input = screen.readInput() ;
				
					//Player movement
				player.move( input ) ;
				
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