package model.world;

import java.util.ArrayList;

import control.Main;
import model.FactoryProducer;
import model.entity.AbstractEntity;
import model.tile.AbstractTile;
import view.Debug;

public abstract class AbstractLevel {
	protected ArrayList<AbstractEntity> entities ;
	protected ArrayList<ArrayList<AbstractTile>> map ;
	protected FactoryProducer producer = new FactoryProducer() ;
	
	//Getters
	public ArrayList<AbstractEntity> getEntities() {
		return entities;
	}
	public ArrayList<ArrayList<AbstractTile>> getMap() {
		return map;
	}
	
	//Setters
	public void setEntities(ArrayList<AbstractEntity> entities) {
		this.entities = entities;
	}
	public void setMap(ArrayList<ArrayList<AbstractTile>> map) {
		this.map = map;
	}
	
	//Constructors
	public AbstractLevel(){
		entities = new ArrayList<>() ;
		map = new ArrayList<ArrayList<AbstractTile>>() ;
	}
	
	//Methods
	public void initEntities(){
		Debug.logln( "initEntities : [Start]" , Main.debug ) ;
		entities.add( producer.getFactory( "entity" ).getEntity( "player" ) ) ;
		Debug.logln( "initEntities : initialized " + entities.size() + 
				" entities" , Main.debug ) ;
		Debug.logln( "initEntities : [End]" , Main.debug ) ;
	}
	
	public void generateEmptyMap(int rows, int cols) {
		for( int y = 0 ; y < rows ; y++ ){
			map.add( new ArrayList<AbstractTile>() ) ;
			for( int x = 0 ; x < cols ; x++ ){
				if( x == 0 || x == cols - 1 ){
					map.get( y ).add( producer.getFactory( "TILE" )
							.getTile( "WALL" ) ) ;
				}
				else if( y == 0 || y == rows - 1 ){
					map.get( y ).add( producer.getFactory( "TILE" )
							.getTile( "WALL" ) ) ;
				}
				else
				{
					map.get( y ).add( producer.getFactory( "TILE" )
							.getTile( "FLOOR" ) ) ;
				}
			}
		}
	}
	
	public abstract void generateMap( int rows , int cols ) ;
}
