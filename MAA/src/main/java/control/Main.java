package control;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorColorConfiguration;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorPalette;

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
						new DefaultTerminalFactory()
						.setTerminalEmulatorTitle( "Mini Alchemy" )
						.setTerminalEmulatorColorConfiguration( 
								TerminalEmulatorColorConfiguration.newInstance( 
								TerminalEmulatorPalette.GNOME_TERMINAL ) )
						.createTerminal() ;
				Screen screen = new TerminalScreen( terminal ) ) {
			
			//Start screen
			screen.startScreen() ;
			
			//Remove cursor
			screen.setCursorPosition( null ) ;
			
			while( running ) {
				//Processing stuff
				
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
				KeyStroke input = screen.readInput() ;
				
					//EXIT ( check escape or input closed )
				if( ButtonUtils.isButtonPressed( input , KeyType.Escape ) ||
						ButtonUtils.isButtonPressed( input , KeyType.EOF ) ) {
					running = false ;
				}
				
					//Player movement
				boolean right = 
						ButtonUtils.isButtonPressed( 
								input , 
								KeyType.ArrowRight ) || 
						ButtonUtils.isButtonPressed( 
								input , 
								'9' ) ||
						ButtonUtils.isButtonPressed( 
								input , 
								'3' ) ||
						ButtonUtils.isButtonPressed( 
								input , 
								'6' ) ;
				
				boolean left = 
						ButtonUtils.isButtonPressed( 
								input , 
								KeyType.ArrowLeft ) || 
						ButtonUtils.isButtonPressed( 
								input , 
								'7' ) ||
						ButtonUtils.isButtonPressed( 
								input , 
								'1' ) ||
						ButtonUtils.isButtonPressed( 
								input , 
								'4' ) ;
				
				boolean up = 
						ButtonUtils.isButtonPressed( 
								input , 
								KeyType.ArrowUp ) || 
						ButtonUtils.isButtonPressed( 
								input , 
								'7' ) ||
						ButtonUtils.isButtonPressed( 
								input , 
								'9' ) ||
						ButtonUtils.isButtonPressed( 
								input , 
								'8' ) ;
				
				boolean down = 
						ButtonUtils.isButtonPressed( 
								input , 
								KeyType.ArrowDown ) || 
						ButtonUtils.isButtonPressed( 
								input , 
								'1' ) ||
						ButtonUtils.isButtonPressed( 
								input , 
								'3' ) ||
						ButtonUtils.isButtonPressed( 
								input , 
								'2' ) ;
				
				int dirX = ( right ? 1 : 0 ) - ( left ? 1 : 0 ) ;
				
				int dirY = ( down ? 1 : 0 ) - ( up ? 1 : 0 ) ;
				
				player.move( dirX , dirY ) ;
			}
			
			//End screen
			screen.stopScreen() ;
		}
		catch ( Exception e ) {
			Debug.logErr( "Main: catch" , e ) ;
		}
	}
}