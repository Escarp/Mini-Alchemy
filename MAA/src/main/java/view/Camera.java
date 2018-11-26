package view;

import java.util.ArrayList;
import java.util.HashMap;

import model.entity.AbstractEntity;
import model.entity.Player;
import model.tile.AbstractTile;
import util.KeySet;
import util.physics.AbstractVector2;
import util.physics.Vector2i;

public class Camera {
	private Vector2i dimensions ;
	private Vector2i position ;
	private Vector2i minIndexes ;
	private Vector2i maxIndexes ;
	private HashMap<KeySet , Boolean> lightMap ;
	
	//Getters
	public HashMap<KeySet , Boolean> getLighMap(){
		return lightMap ;
	}
	
	public Vector2i getDimensions() {
		return dimensions;
	}

	public Vector2i getPosition() {
		return position;
	}
	
	public Vector2i getMinIndexes() {
		return minIndexes ;
	}
	
	public Vector2i getMaxIndexes() {
		return maxIndexes ;
	}

	//Setters
	public void setLightMap( HashMap<KeySet , Boolean> lightMap ){
		this.lightMap = lightMap ;
	}
	
	public void setDimensions(Vector2i dimensions) {
		this.dimensions = dimensions;
	}

	public void setPosition(Vector2i center) {
		this.position = center;
	}
	
	public void setMinIndexes( Vector2i minIndexes ) {
		this.minIndexes = minIndexes ;
	}
	
	public void setMaxIndexes( Vector2i maxIndexes ) {
		this.maxIndexes = maxIndexes ;
	}

	//Constructors
	public Camera( Vector2i dimensions , Vector2i center ) {
		this.dimensions = dimensions ;
		this.position = center ;
	}
	
	//Methods
	public void setIndices( ArrayList<ArrayList<AbstractTile>> map ) {
		minIndexes = new Vector2i() ;
		maxIndexes = new Vector2i() ;
		
		//MinIndices
		if( position.getX() < 0 ) {
			minIndexes.setX( 0 ) ;
		}
		else {
			minIndexes.setX( position.getX() ) ;
		}
		
		if( position.getY() < 0 ) {
			minIndexes.setY( 0 ) ;
		}
		else {
			minIndexes.setY( position.getY() ) ;
		}
		
		//MaxIndices
		if( position.getX() + dimensions.getX() > map.get( 0 ).size() ) {
			maxIndexes.setX( map.get( 0 ).size() ) ;
		}
		else {
			maxIndexes.setX( position.getX() + dimensions.getX() ) ;
		}
		
		if( position.getY() + dimensions.getY() > map.size() ) {
			maxIndexes.setY( map.size() ) ;
		}
		else {
			maxIndexes.setY( position.getY() + dimensions.getY() ) ;
		}
		
		//Safeguards
		if( minIndexes.getX() > map.get( 0 ).size() - dimensions.getX() ) {
			minIndexes.setX( map.get( 0 ).size() - dimensions.getX() ) ;
		}
		if( minIndexes.getY() > map.size() - dimensions.getY() ) {
			minIndexes.setY( map.size() - dimensions.getY() ) ;
		}
		
		if( maxIndexes.getX() < dimensions.getX() ) {
			maxIndexes.setX( dimensions.getX() ) ;
		}
		if( maxIndexes.getY() < dimensions.getY() ) {
			maxIndexes.setY( dimensions.getY() ) ;
		}
	}
	
	public void setVisibleEntities( ArrayList<AbstractEntity> entities ) {
		for( AbstractEntity entity : entities ) {
			if( 	( entity.getPosition().getY() > minIndexes.getY() &&
					entity.getPosition().getY() < maxIndexes.getY() ) &&
					( entity.getPosition().getX() > minIndexes.getX() &&
					entity.getPosition().getX() < maxIndexes.getX() && 
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
	
	public HashMap<KeySet , Boolean> createLightMap( 
			Player player , ArrayList<ArrayList<AbstractTile>> map ){
		lightMap = new HashMap<>() ;
		ArrayList<Vector2i> maxRange = new ArrayList<>() ;
		for( int y = 0 ; y < map.size() ; y++ ){
			for( int x = 0 ; x < map.get( 0 ).size() ; x++ ){
				Vector2i currPos = new Vector2i( x , y ) ;
				if( AbstractVector2.distance( 
						player.getPosition() , 
						currPos ) <
					player.getViewRadius() ){
					maxRange.add( currPos ) ;
				}
			}
		}
		
		if( maxRange.size() > 0 ){
			for( Vector2i pos : maxRange ){
				lightMap.putAll( drawLine( player.getPosition() , pos , map ) ) ;
			}
		}
		
		return lightMap ;
	}

	private HashMap<KeySet , Boolean> drawLine( 
			Vector2i start , Vector2i stop , 
			ArrayList<ArrayList<AbstractTile>> map ){
		HashMap<KeySet , Boolean> results = new HashMap<KeySet , Boolean>() ;
		
		int d = 0;
		 
        int dx = Math.abs( stop.getX() - start.getX().intValue() ) ;
        int dy = Math.abs( stop.getY() - start.getY().intValue() ) ;
 
        int dx2 = 2 * dx ; // slope scaling factors to
        int dy2 = 2 * dy ; // avoid floating point
 
        // increment direction
        int ix = start.getX().intValue() < stop.getX() ? 1 : -1 ; 
        int iy = start.getY().intValue() < stop.getY() ? 1 : -1 ;
 
        int x = start.getX().intValue() ;
        int y = start.getY().intValue() ;
 
        if ( dx >= dy ) {
            while ( true ) {
            	if(  map.get( y ).get( x ) != null && 
            			map.get( y ).get( x ).isPassable() ){
            		results.put( new KeySet( x , y ) , true ) ;
            	}
            	else{
            		results.put( new KeySet( x , y ) , true ) ;
            		break ;
            	}
                if ( x == stop.getX() ){
                    break;
                }
                x += ix ;
                d += dy2 ;
                if ( d >= dx ) {
                    y += iy ;
                    d -= dx2 ;
                }
            }
        } else {
            while ( true ) {
            	if( map.get( y ).get( x ) != null && 
            			map.get( y ).get( x ).isPassable() ){
            		results.put( new KeySet( x , y ) , true ) ;
            	}
            	else{
            		results.put( new KeySet( x , y ) , true ) ;
            		break ;
            	}
                if ( y == stop.getY() ){
                    break ;
                }
                y += iy ;
                d += dx2 ;
                if ( d >= dy ) {
                    x += ix ;
                    d -= dy2 ;
                }
            }
        }
		return results;
	}
	
	/*
	public HashMap<KeySet , Boolean> createLightMap( 
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

	private HashMap<KeySet , Boolean> recursiveFOV( 
			Vector2i start , 
			Vector2i direction , 
			int strenght , 
			ArrayList<ArrayList<AbstractTile>> map ){
		HashMap<KeySet , Boolean> lightMap = new HashMap<KeySet , Boolean>() ;
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
	*/
}
