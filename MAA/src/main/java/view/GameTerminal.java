package view;

import java.io.IOException;
import java.util.ArrayList;

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
import model.map.tile.Tile;

public class GameTerminal {
	private Screen screen ;
	private Terminal terminal ;
	
	//Indices
	private int minXIndex ;
	private int minYIndex ;
	private int maxXIndex ;
	private int maxYIndex ;
	
	//Dimensions
	private int dimX ;
	private int dimY ;
	
	private int posX ;
	private int posY ;
	
	//Getters
	public int getMinXIndex() {
		return minXIndex;
	}

	public int getMinYIndex() {
		return minYIndex;
	}

	public int getMaxXIndex() {
		return maxXIndex;
	}

	public int getMaxYIndex() {
		return maxYIndex;
	}

	public int getDimX() {
		return dimX;
	}

	public int getDimY() {
		return dimY;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	//Setters
	public void setMinXIndex( int minXIndex ) {
		this.minXIndex = minXIndex;
	}

	public void setMinYIndex( int minYIndex ) {
		this.minYIndex = minYIndex;
	}

	public void setMaxXIndex( int maxXIndex ) {
		this.maxXIndex = maxXIndex;
	}

	public void setMaxYIndex( int maxYIndex ) {
		this.maxYIndex = maxYIndex;
	}

	public void setDimX( int dimX ) {
		this.dimX = dimX;
	}

	public void setDimY( int dimY ) {
		this.dimY = dimY;
	}

	public void setPosX( int posX ) {
		this.posX = posX;
	}

	public void setPosY( int posY ) {
		this.posY = posY;
	}

	//Constructors
	public GameTerminal( String name , int width , int height ) 
			throws IOException {
		//Create terminal with selected specifics
		terminal = new DefaultTerminalFactory()
				.setTerminalEmulatorTitle( name )
				.setTerminalEmulatorColorConfiguration( 
						TerminalEmulatorColorConfiguration.newInstance( 
								TerminalEmulatorPalette
										.WINDOWS_XP_COMMAND_PROMPT ) )
				.setInitialTerminalSize( new TerminalSize( width , height ) )
				.createTerminal() ;
		screen = new TerminalScreen( terminal ) ;
		
		//Start the screen and remove cursor
		screen.startScreen() ;
		screen.setCursorPosition( null ) ;
		
		//Set the dimensions : to put in properties?
		dimX = ( (int)( width / 5 ) ) * 4 + 5 ;
		dimY = ( (int)( height / 4 ) ) * 2 ;
		
		//Set the position and indices
		minXIndex = 0 ;
		minYIndex = 0 ;
		
		maxXIndex = 0 ;
		maxYIndex = 0 ;
	}
	
	//Methods
	public void wipeScreen() throws IOException {
		//Wipe screen
		screen.clear() ;
		refreshScreen() ;
	}
	
	public void refreshScreen() throws IOException {
		//refresh screen
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

	public void drawCharAt( char character , int x , int y ) {
		screen.setCharacter( x , y , new TextCharacter( character ) ) ;
	}
	
	public void setPosition( Position pos ) {
		posX = pos.getX() - ( dimX / 2 ) ;
		posY = pos.getY() - ( dimY / 2 ) ;
	}
	
	public void calculateIndexes( ArrayList<ArrayList<Tile>> map ) {
		//Min Indexes
		if( posX < 0 ) {
			minXIndex = 0 ;
		}
		else {
			minXIndex = posX ;
		}
		
		if( posY < 0 ) {
			minYIndex = 0 ;
		}
		else {
			minYIndex = posY ;
		}
		
		//Max Indexes
		if( posX + dimX > map.get( 0 ).size() ) {
			maxXIndex = map.get( 0 ).size() ;
		}
		else {
			maxXIndex = posX + dimX ;
		}
		
		if( posY + dimY > map.size() ) {
			maxYIndex = map.size() ;
		}
		else {
			maxYIndex = posY + dimY ;
		}
		
		//Safeguards
		if( minXIndex > map.get( 0 ).size() - dimX ) {
			minXIndex = map.get( 0 ).size() - dimX ;
		}
		if( minYIndex > map.size() - dimY ) {
			minYIndex = map.size() - dimY ;
		}
		
		if( maxXIndex < dimX ) {
			maxXIndex = dimX ;
		}
		if( maxYIndex < dimY ) {
			maxYIndex = dimY ;
		}
	}
	
	@Override
	public String toString() {
		return "GameTerminal[screen=" + screen.getTerminalSize() + "]" ;
	}
}
