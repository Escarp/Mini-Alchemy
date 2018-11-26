package model.entity;

import java.util.ArrayList;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;

import model.tile.AbstractTile;
import util.physics.Vector2i;

public abstract class AbstractEntity {
	protected Vector2i position ;
	protected Vector2i direction ;
	protected int speed ;
	protected char character ;
	protected TextColor foregroundColor ;
	protected TextColor backgroundColor ;
	protected boolean visible ;
	
	//Getters
	public boolean isVisible(){
		return visible ;
	}
	
	public Vector2i getPosition(){
		return position ;
	}
	
	public Vector2i getDirection(){
		return direction ;
	}

	public int getSpeed() {
		return speed;
	}

	public char getCharacter() {
		return character;
	}

	public TextColor getForegroundColor() {
		return foregroundColor;
	}

	public TextColor getBackgroundColor() {
		return backgroundColor;
	}

	//Setters
	public void setVisible( boolean visible ){
		this.visible = visible ;
	}
	
	public void setPosition( Vector2i position ) {
		this.position = position ;
	}
	
	public void setDirection( Vector2i direction ){
		this.direction = direction ;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setCharacter(char character) {
		this.character = character;
	}

	public void setForegroundColor(TextColor foregroundColor) {
		this.foregroundColor = foregroundColor;
	}

	public void setBackgroundColor(TextColor backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public abstract void move() ;
	
	public abstract void move( KeyStroke input ) ;
	
	public abstract void move( 
			KeyStroke input , 
			ArrayList<ArrayList<AbstractTile>> map ) ;
	
	public void move( int x , int y ) {
		position.setX( x ) ;
		position.setY( y ) ;
	}
	
	public void move( int x , int y , int speed ) {
		Vector2i res = new Vector2i( x , y ) ;
		res.multiply( speed ) ;
		position.add( res ) ;
	}
	
	public void move( Vector2i position ) {
		position.multiply( speed ) ;
		this.position.setX( position.getX() ) ;
		this.position.setY( position.getY() ) ;
	}
	
	public void move( Vector2i direction , int speed ) {
		direction.multiply( speed ) ;
		position.add( direction ) ;
	}
	
	public void moveAndCollide( 
			Vector2i direction , 
			ArrayList<ArrayList<AbstractTile>> map ){
		for( int i = 1 ; i < speed + 1 ; i++ ){
			if( 
					map.get( Vector2i.add( position , direction )
							.getY() )
					.get( Vector2i.add( position , direction ).getX() )
					.isPassable() 
					&& 
					map.get( Vector2i.add( position , direction )
							.getY() )
					.get( Vector2i.add( position , direction ).getX() )
					.isWalkable() ){
				move( direction , 1 ) ;
			}
		}
	}
	
	public void moveAndCollide( ArrayList<ArrayList<AbstractTile>> map ){
		for( int i = 1 ; i < speed + 1 ; i++ ){
			if( 
					map.get( Vector2i.add( position , direction )
							.getY() )
					.get( Vector2i.add( position , direction ).getX() )
					.isPassable() 
					&& 
					map.get( Vector2i.add( position , direction )
							.getY() )
					.get( Vector2i.add( position , direction ).getX() )
					.isWalkable() ){
				move( direction , 1 ) ;
			}
		}
	}
	
	public abstract void update() ;
}
