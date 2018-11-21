package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.entity.AbstractEntity;
import model.entity.Player;
import model.tile.AbstractTile;
import util.KeySet;
import util.physics.Direction;
import util.physics.Vector2i;

public class Camera {
	private Vector2i dimensions ;
	private Vector2i position ;
	private Vector2i minIndices ;
	private Vector2i maxIndices ;
	private Map<KeySet , Boolean> lightMap ;
	
	//Getters
	public Map<KeySet , Boolean> getLighMap(){
		return lightMap ;
	}
	
	public Vector2i getDimensions() {
		return dimensions;
	}

	public Vector2i getPosition() {
		return position;
	}
	
	public Vector2i getMinIndices() {
		return minIndices ;
	}
	
	public Vector2i getMaxIndices() {
		return maxIndices ;
	}

	//Setters
	public void setLightMap( Map<KeySet , Boolean> lightMap ){
		this.lightMap = lightMap ;
	}
	
	public void setDimensions(Vector2i dimensions) {
		this.dimensions = dimensions;
	}

	public void setPosition(Vector2i center) {
		this.position = center;
	}
	
	public void setMinIndices( Vector2i minIndices ) {
		this.minIndices = minIndices ;
	}
	
	public void setMaxIndices( Vector2i maxIndices ) {
		this.maxIndices = maxIndices ;
	}

	//Constructors
	public Camera( Vector2i dimensions , Vector2i center ) {
		this.dimensions = dimensions ;
		this.position = center ;
	}
	
	//Methods
	public void setIndices( ArrayList<ArrayList<AbstractTile>> map ) {
		minIndices = new Vector2i() ;
		maxIndices = new Vector2i() ;
		
		//MinIndices
		if( position.getX() < 0 ) {
			minIndices.setX( 0 ) ;
		}
		else {
			minIndices.setX( position.getX() ) ;
		}
		
		if( position.getY() < 0 ) {
			minIndices.setY( 0 ) ;
		}
		else {
			minIndices.setY( position.getY() ) ;
		}
		
		//MaxIndices
		if( position.getX() + dimensions.getX() > map.get( 0 ).size() ) {
			maxIndices.setX( map.get( 0 ).size() ) ;
		}
		else {
			maxIndices.setX( position.getX() + dimensions.getX() ) ;
		}
		
		if( position.getY() + dimensions.getY() > map.size() ) {
			maxIndices.setY( map.size() ) ;
		}
		else {
			maxIndices.setY( position.getY() + dimensions.getY() ) ;
		}
		
		//Safeguards
		if( minIndices.getX() > map.get( 0 ).size() - dimensions.getX() ) {
			minIndices.setX( map.get( 0 ).size() - dimensions.getX() ) ;
		}
		if( minIndices.getY() > map.size() - dimensions.getY() ) {
			minIndices.setY( map.size() - dimensions.getY() ) ;
		}
		
		if( maxIndices.getX() < dimensions.getX() ) {
			maxIndices.setX( dimensions.getX() ) ;
		}
		if( maxIndices.getY() < dimensions.getY() ) {
			maxIndices.setY( dimensions.getY() ) ;
		}
	}
	
	public void setVisibleEntities( List<AbstractEntity> entities ) {
		for( AbstractEntity entity : entities ) {
			if( 	( entity.getPosition().getY() > minIndices.getY() &&
					entity.getPosition().getY() < maxIndices.getY() ) &&
					( entity.getPosition().getX() > minIndices.getX() &&
					entity.getPosition().getX() < maxIndices.getX() && 
					lightMap.get( entity.getPosition() ) != null ) ) {
				entity.setVisible( true ) ;
			}
			else if( entity instanceof Player ){
				entity.setVisible( true ) ;
			}
			else{
				entity.setVisible( false ) ;
			}
		}
	}
	
	public Map<KeySet , Boolean> createLightMap( 
			Player player , ArrayList<ArrayList<AbstractTile>> map ){
		lightMap = new HashMap<>() ;
		for( int i = -1 ; i <= 1 ; i++ ){
			for( int j = -1 ; j <= 1 ; j++ ){
				lightMap.putAll( recursiveFOV( 
						new Vector2i(
								player.getPosition().getX().intValue() , 
								player.getPosition().getY().intValue() ) , 
						new Vector2i( j , i ) , 
						player.getViewRadius() , 
						map ) ) ;
			}
		}
		return lightMap ;
	}

	private Map<KeySet , Boolean> recursiveFOV( 
			Vector2i start , 
			Vector2i direction , 
			int strenght , 
			ArrayList<ArrayList<AbstractTile>> map ){
		Map<KeySet , Boolean> lightMap = new HashMap<KeySet , Boolean>() ;
		if( strenght <= 0 ){
			lightMap.put( new KeySet( start.getX() , start.getY() ) , true ) ;
			return lightMap ;
		}
		else{
			if( 	( start.getX() >= 0 && start.getX() < map.get(0).size() ) && 
					( start.getY() >= 0 && start.getY() < map.size() ) ){
				if( map.get( start.getY() ).get( start.getX() ).isPassable() ){
					//this direction
					lightMap.putAll( recursiveFOV( 
							Vector2i.add( start , direction ) , 
							direction , 
							strenght - 1 , 
							map ) ) ;
					
					//switch direction
					
					//if up
					if( direction.getY() < 0 ){
						//if right
						if( direction.getX() > 0 ){
							lightMap.putAll( recursiveFOV( 
									Vector2i.add( start , Direction.TOP ) , 
									direction , 
									strenght - 1 , 
									map ) ) ;
						}
						//else if left
						else if( direction.getX() < 0 ){
							lightMap.putAll( recursiveFOV( 
									Vector2i.add( start , Direction.LEFT ) , 
									direction , 
									strenght - 1 , 
									map ) ) ;
						}
						else{
							lightMap.putAll( recursiveFOV( 
									Vector2i.add( start , Direction.TOP_LEFT ) , 
									direction , 
									strenght - 1 , 
									map ) ) ;
						}
					}
					//else if down
					else if( direction.getY() > 0 ){
						//if right
						if( direction.getX() > 0 ){
							lightMap.putAll( recursiveFOV( 
									Vector2i.add( start , Direction.RIGHT ) , 
									direction , 
									strenght - 1 , 
									map ) ) ;
						}
						//else if left
						else if( direction.getX() < 0 ){
							lightMap.putAll( recursiveFOV( 
									Vector2i.add( start , Direction.BOTTOM ) , 
									direction , 
									strenght - 1 , 
									map ) ) ;
						}
						else{
							lightMap.putAll( recursiveFOV( 
									Vector2i.add( 
											start , Direction.BOTTOM_RIGHT ) , 
									direction , 
									strenght - 1 , 
									map ) ) ;
						}
					}	
					//else if right
					else if( direction.getX() > 0 ){
						lightMap.putAll( recursiveFOV( 
								Vector2i.add( start , Direction.TOP_RIGHT ) , 
								direction , 
								strenght - 1 , 
								map ) ) ;
					}
					//else if left
					else if( direction.getX() < 0 ){
						lightMap.putAll( recursiveFOV( 
								Vector2i.add( start , Direction.BOTTOM_LEFT ) , 
								direction , 
								strenght - 1 , 
								map ) ) ;
					}
				}
				lightMap.put( 
						new KeySet( start.getX() , start.getY() ) , true ) ;
			}
		}
		return lightMap ;
	}
}
