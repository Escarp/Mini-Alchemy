package model.world;

import java.util.ArrayList;

import com.googlecode.lanterna.TextColor;

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
	
	public void generateEmptyMap(  
			int rows, 
			int cols) {
		Debug.logln( "initMap : [Start]" , Main.debug ) ;
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
		
		for( int i = 5 ; i < 15 ; i++ ){
			map.get( 10 ).get( i ).setWalkable( false ) ;
			map.get( 10 ).get( i ).setCharacter( (char) 126 );
			map.get( 10 ).get( i ).setForegroundColor( 
					TextColor.ANSI.RED );
			map.get( 10 ).get( i ).setBackgroundColor( 
					TextColor.ANSI.BLACK );
		}
		
		for( int i = 20 ; i < 45 ; i++ ){
			map.get( 10 ).set( i, producer.getFactory( "TILE" )
					.getTile( "WALL" ) ) ;
		}
		
		map.get( 20 ).set( 20 , producer.getFactory( "TILE" )
				.getTile( "WALL" ) ) ;
		
		Debug.logln( "initMap : initialized " + 
				map.size() * map.get( 0 ).size() + " tiles" , Main.debug ) ;
		Debug.logln( "initMap : [End]" , Main.debug ) ;
	}
}
