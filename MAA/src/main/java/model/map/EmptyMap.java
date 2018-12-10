package model.map;

import java.util.ArrayList;

import model.DAO.loader.TileLoader;

public class EmptyMap extends AMap {
	
	@Override
	public void generateMap( int width , int height ) {
		map = new ArrayList<>() ;
		TileLoader loader = new TileLoader() ;
		
		for( int y = 0 ; y < height ; y++ ){
			map.add( new ArrayList<>() ) ;
			for( int x = 0 ; x < width ; x++ ){
				if( y == 0 || x == 0 || x == width - 1 || y == height - 1 ) {
					map.get( y ).add( loader.load( "wall" ) ) ;
				}
				else{
					map.get( y ).add( loader.load( "floor" ) ) ;
				}
			}
		}
	}
	
}
