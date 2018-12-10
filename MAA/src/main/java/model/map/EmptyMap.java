package model.map;

import java.util.ArrayList;

import model.DAO.builder.EntityBuilder;
import model.DAO.loader.TileLoader;
import util.EntityUtils.EntityType;

public class EmptyMap extends AMap {
	//Constructors
	public EmptyMap() {
		EntityBuilder eb = new EntityBuilder() ;
		
		entities = new ArrayList<>() ;
		entities.add( eb.loadEntity( EntityType.PLAYER ) ) ;
	}
	
	//Methods
	@Override
	public void generateMap( int width , int height ) {
		//Creates a box with floors and walls
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
