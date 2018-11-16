package view;

import java.util.ArrayList;

import model.tile.AbstractTile;
import util.physics.Vector2;

public class Camera {
	private Vector2 dimensions ;
	private Vector2 center ;
	
	//Getters
	public Vector2 getDimensions() {
		return dimensions;
	}

	public Vector2 getCenter() {
		return center;
	}

	//Setters
	public void setDimensions(Vector2 dimensions) {
		this.dimensions = dimensions;
	}

	public void setCenter(Vector2 center) {
		this.center = center;
	}

	//Constructors
	public Camera( Vector2 dimensions , Vector2 center ) {
		this.dimensions = dimensions ;
		this.center = center ;
	}
	
	//Methods
	public Vector2[] getVisibleMapIndexes( 
			ArrayList<ArrayList<AbstractTile>> map ) {
		/*
		 * minX = center.x - ( dimensions.x / 2 ) || 0
		 * maxX = center.x + ( dimensions.x / 2 ) || map.get(0).size()
		 *
		 * minY = center.y - ( dimensions.y / 2 ) || 0
		 * maxY = center.y + ( dimensions.y / 2 ) || map.size(0)
		 */
		Vector2 minIndices = new Vector2() ;
		Vector2 maxIndices = new Vector2() ;
		
		minIndices.setX( 
				center.getX() - ( dimensions.getX() / 2 ) < 0 ? 
						0 : 
						center.getX() - ( dimensions.getX() / 2 ) ) ;
		
		maxIndices.setX( 
				center.getX() + ( dimensions.getX() / 2 ) > 
				map.get( 0 ).size() ? 
						map.get( 0 ).size() : 
						center.getX() + ( dimensions.getX() / 2 ) ) ;
		
		minIndices.setY( 
				center.getY() - ( dimensions.getY() / 2 ) < 0 ? 
						0 : 
						center.getY() - ( dimensions.getY() / 2 ) ) ;
		
		maxIndices.setY( 
				center.getY() + ( dimensions.getY() / 2 ) > map.size() ? 
						map.size() : 
						center.getY() + ( dimensions.getY() / 2 ) ) ;
		
		return new Vector2[] { minIndices , maxIndices } ;
	}
}
