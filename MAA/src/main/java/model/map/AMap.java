package model.map;

import java.util.ArrayList;
import java.util.HashMap;

import model.entity.Entity;
import model.map.tile.Tile;
import util.EntityUtils.EntityType;

public abstract class AMap {
	protected ArrayList<ArrayList<Tile>> map ;
	protected HashMap<EntityType , Entity> entities ;

	//Getters
	public ArrayList<ArrayList<Tile>> getMap() {
		return map;
	}
	public HashMap<EntityType , Entity> getEntities() {
		return entities ;
	}

	//Setters
	public void setMap( ArrayList<ArrayList<Tile>> map ) {
		this.map = map;
	}
	
	//Methods
	public abstract void generateMap( int width , int height ) ;
}
