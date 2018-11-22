package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorColorConfiguration;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorPalette;

import model.entity.AbstractEntity;
import model.entity.Player;
import model.tile.AbstractTile;
import util.KeySet;
import util.physics.AbstractVector2;
import util.physics.Vector2d;
import util.physics.Vector2i;

public class ScreenFunctions {
	public static void drawMap( Screen screen , int x , int y , 
			HashMap<KeySet , Boolean> lightMap , 
			Vector2i maxIndices , Vector2i minIndices , 
			ArrayList<ArrayList<AbstractTile>> map , int indX , int indY , 
			Player player ){
		if ( 	indY < maxIndices.getY() && 
				indX < maxIndices.getX() ) {
			
			if( lightMap.get( new KeySet( indX , indY ) ) != null &&
						lightMap.get( new KeySet( indX , indY ) )
						.booleanValue() ){
				
			map.get( indY ).get( indX ).setDiscovered( true ) ;
			ScreenFunctions.drawChar( screen , x , y , map , indX , indY ) ;
			
			}
			else{
				if( map.get( indY ).get( indX ).isDiscovered() ){
					ScreenFunctions.drawChar( 
							screen , x , y , map , indX , indY , 
							TextColor.ANSI.WHITE , 
							TextColor.ANSI.BLACK ) ;
				}
			}
			
		}
	}
	
	public static void drawEntities( 
			Screen screen , int x , int y , 
			List<AbstractEntity> entities , int indX , int indY ){
		for( AbstractEntity entity : entities ) {
			if( entity.isVisible() ) {
				if( 	entity.getPosition().getX() == indX &&
						entity.getPosition().getY() == indY ) {
					ScreenFunctions.drawChar( screen , x , y , 
							entity , indX , indY ) ;
				}
			}
		}
	}
	
	public static void drawChar( 
			Screen screen ,
			int x , 
			int y , 
			ArrayList<ArrayList<AbstractTile>> map , 
			int indX , 
			int indY ){
		screen.setCharacter( 
				x , 
				y ,  
				new TextCharacter( 
						map.get( indY ).get( indX )
							.getCharacter() ,
						map.get( indY ).get( indX )
							.getForegroundColor() ,
						map.get( indY ).get( indX )
							.getBackgroundColor() ) ) ;
	}
	
	public static void drawChar( 
			Screen screen ,
			int x , 
			int y , 
			AbstractEntity entity , 
			int indX , 
			int indY ){
		screen.setCharacter( 
				x , 
				y ,  
				new TextCharacter( 
						entity.getCharacter() ,
						entity.getForegroundColor() ,
						entity.getBackgroundColor() ) ) ;
	}
	
	public static void drawChar( 
			Screen screen ,
			int x , 
			int y , 
			ArrayList<ArrayList<AbstractTile>> map , 
			int indX , 
			int indY , 
			TextColor fg , 
			TextColor bg ){
		screen.setCharacter( 
				x , 
				y ,  
				new TextCharacter( 
						map.get( indY ).get( indX )
							.getCharacter() ,
						fg , 
						bg ) ) ;
	}
	
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

	public static void drawMap( 
			Screen screen , 
			ArrayList<ArrayList<AbstractTile>> map ) {
		for( int y = 0 ; y < screen.getTerminalSize().getRows() ; y++ ){
			for( int x = 0 ; x < screen.getTerminalSize().getColumns() ; x++ ){
				screen.setCharacter( x , y , new TextCharacter( 
						map.get( y ).get( x ).getCharacter() ) ) ;
			}
		}
	}
}
