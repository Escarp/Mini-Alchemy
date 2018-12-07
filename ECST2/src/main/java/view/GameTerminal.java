package view;

import java.io.IOException;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing
		.TerminalEmulatorColorConfiguration;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorPalette;

import model.component.Position;
import model.component.Render;

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
		screen.startScreen() ;
		screen.setCursorPosition( null ) ;
	}
	
	public void wipeScreen() throws IOException {
		//Wipe screen
		screen.clear() ;
		refreshScreen() ;
	}
	
	public void refreshScreen() throws IOException {
		screen.refresh() ;
	}
	
	public void drawRenderAt( Position position , Render render ) {
		TextCharacter c = new TextCharacter(	render.getCharacter() , 
												render.getfColor() , 
												render.getbColor() ) ;
		screen.setCharacter(	position.getX() , 
								position.getY() , 
								c );
	}
	
	public void stop() throws IOException {
		screen.stopScreen() ;
	}
	
	public KeyStroke getInput() throws IOException {
		return screen.readInput() ;
	}
}
