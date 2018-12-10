package model.map;

import java.util.ArrayList;

import model.map.tile.Tile;

public abstract class AMap {
	protected ArrayList<ArrayList<Tile>> map ;

	public ArrayList<ArrayList<Tile>> getMap() {
		return map;
	}

	public void setMap( ArrayList<ArrayList<Tile>> map ) {
		this.map = map;
	}
	
	public abstract void generateMap( int width , int height ) ;
}
