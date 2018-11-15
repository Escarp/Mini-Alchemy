package view;

import java.io.IOException;
import java.util.List;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorColorConfiguration;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorPalette;

import model.entity.AbstractEntity;

public class ScreenFunctions {
	public static void initScreen( Screen screen ) throws IOException{
		//Start screen
		screen.startScreen() ;
		
		//Remove cursor
		screen.setCursorPosition( null ) ;
	}
	
	public static void wipeScreen( Screen screen ) throws IOException{
		//Wipe screen
		screen.clear() ;
		refreshScreen( screen ) ;
	}
	
	public static void refreshScreen( Screen screen ) throws IOException{
		screen.refresh() ;
	}
	
	public static void stopScreen( Screen screen ) throws IOException{
		screen.stopScreen() ;
	}
	
	public static Terminal startTerminal( 
			String terminalName , 
			int width , 
			int height ) throws IOException{
		return new DefaultTerminalFactory()
		.setTerminalEmulatorTitle( terminalName )
		.setTerminalEmulatorColorConfiguration( 
				TerminalEmulatorColorConfiguration.newInstance( 
				TerminalEmulatorPalette.GNOME_TERMINAL ) )
		.setInitialTerminalSize( new TerminalSize( width , height ) )
		.createTerminal() ;
	}
	
	public static void drawEntities( 
			Screen screen , 
			List<AbstractEntity> entities ) throws IOException{
		for( AbstractEntity entity : entities ) {
			entity.drawSelf( screen ) ;
		}
	}
}
