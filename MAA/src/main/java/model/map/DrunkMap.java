package model.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import model.DAO.loader.EntityLoader;
import model.DAO.loader.TileLoader;
import model.component.Position;
import model.entity.Entity;
import model.map.tile.Tile;
import util.ComponentUtils.ComponentType;
import util.EntityUtils.EntityType;
import view.Debug;

public class DrunkMap extends AMap {
	
	//Constructors
	public DrunkMap() {
		EntityLoader el = new EntityLoader() ;
		
		entities = new HashMap<EntityType , Entity>() ;
		entities.put( EntityType.PLAYER , el.load( 
				EntityType.PLAYER.toString() ) ) ;
	}
		
	
	@Override
	public void generateMap( int width , int height ) {
		TileLoader loader = new TileLoader() ;
		
		int curX = width / 2 ;
		int curY = height / 2 ;
		
		Position pos = ( 
				(Position)entities.get( EntityType.PLAYER ).getComponent( 
						ComponentType.POSITION ) ) ;
		
		pos.setX( curX ) ;
		pos.setY( curY ) ;
		
		Random rand = new Random() ;
		
		int curDirX = 0 ;
		int curDirY = 0 ;
		
		switch( rand.nextInt( 4 ) ) {
			case( 0 ) : {
				curDirX = 1 ;
				break ;
			}
			case( 1 ) : {
				curDirX = -1 ;
				break ;					
			}
			case( 2 ) : {
				curDirY = 1 ;
				break ;
			}
			case( 3 ) : {
				curDirY = -1 ;
				break ;
			}
		}
		
		int steps = 1000 ;
		
		map = new ArrayList<>() ;
		
		for( int y = 0 ; y < height ; y++ ) {
			map.add( new ArrayList<Tile>() ) ;
			for( int x = 0 ; x < width ; x++ ) {
				map.get( y ).add( new Tile() ) ;
			}
		}
		
		while( steps > 0 ) {
			
			if( steps == 1000 ) {
				Debug.logDebug( "Generating map : 0%" );
			}
			else if( steps == 750 ) {
				Debug.logDebug( "Generating map : 25%" );
			}
			else if( steps == 500 ) {
				Debug.logDebug( "Generating map : 50%" );
			}
			else if( steps == 250 ) {
				Debug.logDebug( "Generating map : 75%" );
			}
			else if( steps == 1 ) {
				Debug.logDebug( "Generating map : 100%" );
			}
			
			steps-- ;
			
			if( curX >= width ) {
				curX = width - 1 ;
			}
			else if( curX <= 0 ) {
				curX = 1 ;
			}
			
			if( curY >= height ) {
				curY = height - 1 ;
			}
			else if( curY <= 0 ) {
				curY = 1 ;
			}
			
			map.get( curY ).set( 
					curX , loader.load( "floor" ) ) ;
			
			if( rand.nextBoolean() ) {
				curDirX = 0 ;
				curDirY = 0 ;
				switch( rand.nextInt( 4 ) ) {
					case( 0 ) : {
						curDirX = 1 ;
						break ;
					}
					case( 1 ) : {
						curDirX = -1 ;
						break ;					
					}
					case( 2 ) : {
						curDirY = 1 ;
						break ;
					}
					case( 3 ) : {
						curDirY = -1 ;
						break ;
					}
				}
			}
			curX += curDirX ;
			curY += curDirY ;
		}
		
		Debug.logDebug( "Generating walls..." ) ;
		
		for( int y = 1 ; y < height - 1 ; y++ ) {
			for( int x = 1 ; x < width - 1 ; x++ ) {
				if( map.get( y ).get( x ).getType().equals( "floor" ) ) {
					if( map.get( y + 1 ).get( x + 1 ).getType().equals( 
							"VOID" ) ) {
						map.get( y + 1 ).set( 
								x + 1 , loader.load( "wall" ) ) ;
					}
					if( map.get( y + 1 ).get( x - 1 ).getType().equals( 
							"VOID" ) ) {
						map.get( y + 1 ).set( 
								x - 1 , loader.load( "wall" ) ) ;					
					}
					if( map.get( y - 1 ).get( x + 1 ).getType().equals( 
							"VOID" ) ) {
						map.get( y - 1 ).set( 
								x + 1 , loader.load( "wall" ) ) ;
					}
					if( map.get( y - 1 ).get( x - 1 ).getType().equals( 
							"VOID" ) ) {
						map.get( y - 1 ).set(
								x - 1 , loader.load( "wall" ) ) ;
					}
					if( map.get( y ).get( x + 1 ).getType().equals( "VOID" ) ) {
						map.get( y ).set( 
								x + 1 , loader.load( "wall" ) ) ;
					}
					if( map.get( y ).get( x - 1 ).getType().equals( "VOID" ) ) {
						map.get( y ).set( 
								x - 1 , loader.load( "wall" ) ) ;
					}
					if( map.get( y - 1 ).get( x ).getType().equals( "VOID" ) ) {
						map.get( y - 1 ).set( 
								x , loader.load( "wall" ) ) ;
					}
					if( map.get( y + 1 ).get( x ).getType().equals( "VOID" ) ) {
						map.get( y + 1 ).set( 
								x , loader.load( "wall" ) ) ;
					}
				}
			}
		}
	}
}
