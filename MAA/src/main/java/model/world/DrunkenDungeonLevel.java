package model.world;

import java.util.ArrayList;
import java.util.Random;

import model.tile.AbstractTile;
import model.tile.Floor;
import model.tile.VoidTile;
import util.physics.Vector2i;

public class DrunkenDungeonLevel extends AbstractLevel {
	
	@Override
	public void generateMap( int rows , int cols ) {
		Vector2i cursor = new Vector2i( (int)cols / 2 , (int)rows / 2 ) ;
		Random random = new Random() ;
		Vector2i cursorDir = new Vector2i() ;
		int steps = 1000 ;
		
		for( int y = 0 ; y < rows ; y++ ) {
			map.add( new ArrayList<AbstractTile>() ) ;
			for( int x = 0 ; x < cols ; x++ ) {
				map.get( y ).add( 
						producer.getFactory( "tile" ).getTile( "void" ) ) ;
				map.get( y ).get( x ).setCharacter( ' ' ) ;
			}
		}
		
		while( steps > 0 ) {
			steps-- ;
			
			if( cursor.getX() >= cols ){
				cursor.setX( cols - 1 );
			}
			else if( cursor.getX() <= 0 ){
				cursor.setX( 1 );
			}
			
			if( cursor.getY() >= rows ){
				cursor.setY( rows - 1 );
			}
			else if( cursor.getY() <= 0 ){
				cursor.setY( 1 );
			}
			
			map.get( cursor.getY() ).set( 
					cursor.getX() , 
					producer.getFactory( "tile" ).getTile( "floor" ) ) ;
			
			if( random.nextBoolean() ){
				cursorDir.setX( 0 ) ;
				cursorDir.setY( 0 ) ;
				switch( random.nextInt( 4 ) ){
					case( 0 ) : {
						cursorDir.setX( 1 ) ;
						break ;
					}
					case( 1 ) : {
						cursorDir.setY( 1 ) ;
						break ;
					}
					case( 2 ) : {
						cursorDir.setX( -1 ) ;
						break ;
					}
					case( 3 ) : {
						cursorDir.setY( -1 ) ;
						break ;
					}
				}
			}
			cursor.add( cursorDir ) ;
		}
		
		for( int y = 0 ; y < map.size() ; y ++ ){
			for( int x = 0 ; x < map.get( 0 ).size() ; x ++ ){
				if( map.get( y ).get( x ) instanceof Floor ){
					if( map.get( y - 1 ).get( x ) instanceof VoidTile ) {
						map.get( y - 1 ).set( 
								x , 
								producer
								.getFactory( "tile" ).getTile( "wall" ) ) ;
					}
					if( map.get( y + 1 ).get( x ) instanceof VoidTile ) {
						map.get( y + 1 ).set( 
								x , 
								producer
								.getFactory( "tile" ).getTile( "wall" ) ) ;
					}
					if( map.get( y - 1 ).get( x + 1 ) instanceof VoidTile ) {
						map.get( y - 1 ).set( 
								x + 1 , 
								producer
								.getFactory( "tile" ).getTile( "wall" ) ) ;
					}
					if( map.get( y - 1 ).get( x - 1 ) instanceof VoidTile ) {
						map.get( y - 1 ).set( 
								x - 1 , 
								producer
								.getFactory( "tile" ).getTile( "wall" ) ) ;
					}
					if( map.get( y ).get( x + 1 ) instanceof VoidTile ) {
						map.get( y ).set( 
								x + 1 , 
								producer
								.getFactory( "tile" ).getTile( "wall" ) ) ;
					}
					if( map.get( y ).get( x - 1 ) instanceof VoidTile ) {
						map.get( y ).set( 
								x - 1 , 
								producer
								.getFactory( "tile" ).getTile( "wall" ) ) ;
					}
					if( map.get( y + 1 ).get( x + 1 ) instanceof VoidTile ) {
						map.get( y + 1 ).set( 
								x + 1 , 
								producer
								.getFactory( "tile" ).getTile( "wall" ) ) ;
					}
					if( map.get( y + 1 ).get( x - 1 ) instanceof VoidTile ) {
						map.get( y + 1 ).set( 
								x - 1 , 
								producer
								.getFactory( "tile" ).getTile( "wall" ) ) ;
					}
				}
			}
		}
	}
}
