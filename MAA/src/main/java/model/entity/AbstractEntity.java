package model.entity;

import java.io.IOException;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;

public abstract class AbstractEntity {
	private int x ;
	private int y ;
	private int speed ;
	private char character ;
	private TextColor foregroundColor ;
	private TextColor backgroundColor ;
	
	//Getters
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
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
	
	public abstract void move( int dirX , int dirY ) ;
	
	public abstract void update() ;
	
	public void drawSelf( Screen screen ) 
			throws IOException {
		
		screen.setCharacter( x , y , new TextCharacter( 
				character , 
				foregroundColor , 
				backgroundColor ) 
				) ;
	}
	
}
