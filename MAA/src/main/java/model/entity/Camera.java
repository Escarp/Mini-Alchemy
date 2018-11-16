package model.entity;

import java.util.ArrayList;

//import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import model.tile.AbstractTile;
import util.physics.Vector2;

public class Camera extends AbstractEntity {
	private AbstractEntity target ;
	private Vector2 size ;
	
	//Getters
	public AbstractEntity getTarget(){
		return target ;
	}
	
	public Vector2 getSize(){
		return size ;
	}
	
	//Setters
	public void setTarget( AbstractEntity target ) {
		this.target = target ;
	}
	
	public void setSize( Vector2 size ) {
		this.size = size ;
	}
	
	//Methods
	@Override
	public void move() {
	}

	@Override
	public void move(KeyStroke input) {
	}

	@Override
	public void move(KeyStroke input, ArrayList<ArrayList<AbstractTile>> map) {
	}

	@Override
	public void update() {
		this.position = target.position ;
	}

	@Override
	public void drawSelf( Screen screen ){
//		screen.setCharacter( 
//				(int)position.getX() , 
//				(int)position.getY() - 1 , 
//				new TextCharacter( 
//				'0' , 
//				foregroundColor , 
//				backgroundColor ) 
//				) ;
	}
}
