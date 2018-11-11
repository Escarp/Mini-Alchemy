package control;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import model.entity.AbstractEntity;
import model.factory.FactoryProducer;
import util.ButtonUtils;
import view.Debug;

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
		
		try( 
				Terminal terminal = 
						new DefaultTerminalFactory().createTerminal() ;
				Screen screen = new TerminalScreen( terminal ) ) {
			
			screen.startScreen() ;
			
			while( running ) {
				//Processing stuff
				
					//Advanced math B)
				
				//Graphical stuff
				
					//Wipe screen
				screen.clear() ;
				screen.refresh() ;
				
					//Draw entities
				for( AbstractEntity entity : entities ) {
					entity.drawSelf( screen ) ;
				}
				
					//Refresh screen
				screen.refresh() ;
				
				//Input stuff
				
					//Get input (blocking)
				KeyStroke key = screen.readInput() ;
				
					//EXIT
				if( ButtonUtils.isButtonPressed( key , KeyType.Escape ) ) {
					running = false ;
				}
				
					//Player movement
				player.move( 
						( ButtonUtils.isButtonPressed( 
								key , 
								KeyType.ArrowRight ) ? 1 : 0 ) - 
						( ButtonUtils.isButtonPressed( 
								key , 
								KeyType.ArrowLeft ) ? 1 : 0 ) ,
						( ButtonUtils.isButtonPressed( 
								key , 
								KeyType.ArrowDown ) ? 1 : 0 ) - 
						( ButtonUtils.isButtonPressed( 
								key , 
								KeyType.ArrowUp ) ? 1 : 0 ) ) ;
				
			}
			
			//End
			screen.stopScreen() ;
		}
		catch ( Exception e ) {
			Debug.logErr( "Main: catch" , e ) ;
		}
	}
}