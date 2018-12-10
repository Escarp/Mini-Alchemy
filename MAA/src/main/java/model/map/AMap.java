package model.map;

import java.util.ArrayList;

import model.entity.Entity;
import model.map.tile.Tile;

public abstract class AMap {
	protected ArrayList<ArrayList<Tile>> map ;
	protected ArrayList<Entity> entities ;

	//Getters
	public ArrayList<ArrayList<Tile>> getMap() {
		return map;
	}
	public ArrayList<Entity> getEntities() {
		return entities ;
	}

	//Setters
	public void setMap( ArrayList<ArrayList<Tile>> map ) {
		this.map = map;
	}
	
	//Methods
	public abstract void generateMap( int width , int height ) ;
}
