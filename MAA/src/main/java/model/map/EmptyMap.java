package model.map;

import java.util.ArrayList;
import java.util.HashMap;

import model.DAO.loader.EntityLoader;
import model.DAO.loader.TileLoader;
import model.entity.Entity;
import util.EntityUtils.EntityType;

public class EmptyMap extends AMap {
	//Constructors
	public EmptyMap() {
		EntityLoader loader = new EntityLoader() ;
		
		entities = new HashMap<EntityType , Entity>() ;
		entities.put( 
				EntityType.PLAYER , loader.load( 
						EntityType.PLAYER.toString() ) ) ;
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
					map.get( y ).add( loader.load( "wall.json" ) ) ;
				}
				else{
					map.get( y ).add( loader.load( "floor.json" ) ) ;
				}
			}
		}
	}
}
