package view;

import java.util.ArrayList;
import java.util.List;

import model.entity.AbstractEntity;
import model.tile.AbstractTile;
import util.physics.Vector2;

public class Camera {
	private Vector2 dimensions ;
	private Vector2 position ;
	private Vector2 minIndices ;
	private Vector2 maxIndices ;
	
	//Getters
	public Vector2 getDimensions() {
		return dimensions;
	}

	public Vector2 getPosition() {
		return position;
	}
	
	public Vector2 getMinIndices() {
		return minIndices ;
	}
	
	public Vector2 getMaxIndices() {
		return maxIndices ;
	}

	//Setters
	public void setDimensions(Vector2 dimensions) {
		this.dimensions = dimensions;
	}

	public void setPosition(Vector2 center) {
		this.position = center;
	}
	
	public void setMinIndices( Vector2 minIndices ) {
		this.minIndices = minIndices ;
	}
	
	public void setMaxIndices( Vector2 maxIndices ) {
		this.maxIndices = maxIndices ;
	}

	//Constructors
	public Camera( Vector2 dimensions , Vector2 center ) {
		this.dimensions = dimensions ;
		this.position = center ;
	}
	
	public void setIndices( ArrayList<ArrayList<AbstractTile>> map ) {
		/*FIXME : find a way to remove the +5 and the -1 or to have them 
		 * dynamic. This only works if terminal is 100*30. This seems to be a 
		 * rounding problem.
		 */
		
		minIndices = new Vector2() ;
		maxIndices = new Vector2() ;
		
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
		if( minIndices.getX() > map.get( 0 ).size() - dimensions.getX() + 5 ) {
			minIndices.setX( map.get( 0 ).size() - dimensions.getX() + 5 ) ;
		}
		if( minIndices.getY() > map.size() - dimensions.getY() - 1 ) {
			minIndices.setY( map.size() - dimensions.getY() - 1 ) ;
		}
		
		if( maxIndices.getX() < dimensions.getX() ) {
			maxIndices.setX( dimensions.getX() ) ;
		}
		if( maxIndices.getY() < dimensions.getY() ) {
			maxIndices.setY( dimensions.getY() + 1 ) ;
		}
	}
	
	//Methods
	public void setVisibleEntities( List<AbstractEntity> entities ) {
		for( AbstractEntity entity : entities ) {
			if( 	( entity.getPosition().getY() > minIndices.getY() &&
					entity.getPosition().getY() < maxIndices.getY() ) &&
					( entity.getPosition().getX() > minIndices.getX() &&
					entity.getPosition().getX() < maxIndices.getX() ) ) {
				entity.setVisible( true ) ;
			}
		}
	}
}
