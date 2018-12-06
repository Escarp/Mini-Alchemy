package view;

import java.io.IOException;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing
		.TerminalEmulatorColorConfiguration;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorPalette;

public class GameTerminal {
	private Screen screen ;
	private Terminal terminal ;
	
	public GameTerminal( String name , int width , int height ) 
			throws IOException {
		terminal = new DefaultTerminalFactory()
				.setTerminalEmulatorTitle( name )
				.setTerminalEmulatorColorConfiguration( 
						TerminalEmulatorColorConfiguration.newInstance( 
								TerminalEmulatorPalette
										.WINDOWS_XP_COMMAND_PROMPT ) )
				.setInitialTerminalSize( new TerminalSize( width , height ) )
				.createTerminal() ;
		screen = new TerminalScreen( terminal ) ;
	}
	
	public void wipeScreen( Screen screen ) throws IOException {
		//Wipe screen
		screen.clear() ;
		refreshScreen( screen ) ;
	}
	
	public void refreshScreen( Screen screen ) throws IOException {
		screen.refresh() ;
	}
}
