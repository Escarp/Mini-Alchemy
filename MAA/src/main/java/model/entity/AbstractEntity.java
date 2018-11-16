package model.entity;

import java.util.ArrayList;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import model.tile.AbstractTile;
import util.physics.Vector2;

public abstract class AbstractEntity {
	protected Vector2 position ;
	protected Vector2 direction ;
	protected int speed ;
	protected char character ;
	protected TextColor foregroundColor ;
	protected TextColor backgroundColor ;
	protected boolean visible ;
	
	//Getters
	public boolean isVisible(){
		return visible ;
	}
	
	public Vector2 getPosition(){
		return position ;
	}
	
	public Vector2 getDirection(){
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
	
	public void setPosition( Vector2 position ) {
		this.position = position ;
	}
	
	public void setDirection( Vector2 direction ){
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
		Vector2 res = new Vector2( x , y ) ;
		res.multiply( speed ) ;
		position.add( res ) ;
	}
	
	public void move( Vector2 position ) {
		position.multiply( speed ) ;
		this.position.setX( position.getX() ) ;
		this.position.setY( position.getY() ) ;
	}
	
	public void move( Vector2 direction , int speed ) {
		direction.multiply( speed ) ;
		position.add( direction ) ;
	}
	
	public void moveAndCollide( 
			Vector2 direction , 
			ArrayList<ArrayList<AbstractTile>> map ){
		for( int i = 1 ; i < speed + 1 ; i++ ){
			if( 
					map.get( (int)( Vector2.add( position , direction )
							.getY() ) )
					.get( (int)( Vector2.add( position , direction ).getX() ) )
					.isPassable() 
					&& 
					map.get( (int)( Vector2.add( position , direction )
							.getY() ) )
					.get( (int)( Vector2.add( position , direction ).getX() ) )
					.isWalkable() ){
				move( direction , 1 ) ;
			}
		}
	}
	
	public void moveAndCollide( ArrayList<ArrayList<AbstractTile>> map ){
		for( int i = 1 ; i < speed + 1 ; i++ ){
			if( 
					map.get( (int)( Vector2.add( position , direction )
							.getY() ) )
					.get( (int)( Vector2.add( position , direction ).getX() ) )
					.isPassable() 
					&& 
					map.get( (int)( Vector2.add( position , direction )
							.getY() ) )
					.get( (int)( Vector2.add( position , direction ).getX() ) )
					.isWalkable() ){
				move( direction , 1 ) ;
			}
		}
	}
	
	public abstract void update() ;
	
	public void drawSelf( Screen screen ) {
		screen.setCharacter( 
				(int)position.getX() , 
				(int)position.getY() , 
				new TextCharacter( 
				character , 
				foregroundColor , 
				backgroundColor ) 
				) ;
	}
}
