package view;

import java.util.ArrayList;
import java.util.List;

import model.entity.AbstractEntity;
import model.tile.AbstractTile;
import util.physics.Vector2d;
import util.physics.Vector2i;

public class Camera {
	private Vector2i dimensions ;
	private Vector2i position ;
	private Vector2i minIndices ;
	private Vector2i maxIndices ;
	
	//Getters
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
		if( minIndices.getY() > map.size() - dimensions.getY() - 1 ) {
			minIndices.setY( map.size() - dimensions.getY() - 1 ) ;
		}
		
		if( maxIndices.getX() < dimensions.getX() ) {
			maxIndices.setX( dimensions.getX() ) ;
		}
		if( maxIndices.getY() < dimensions.getY() ) {
			maxIndices.setY( dimensions.getY() ) ;
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
