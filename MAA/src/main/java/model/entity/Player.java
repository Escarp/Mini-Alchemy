package model.entity;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import util.ButtonUtils;

public class Player extends AbstractEntity {

	public Player( int x , int y ) {
		this.setCharacter( '@' ) ;
		this.setForegroundColor( TextColor.ANSI.WHITE ) ;
		this.setBackgroundColor( TextColor.ANSI.BLACK ) ;
		this.setSpeed( 1 ) ;
		this.setX( x ) ;
		this.setY( y ) ;
	}
	
	public Player() {
		this.setCharacter( '@' ) ;
		this.setForegroundColor( new TextColor.RGB( 255 , 255 , 255 ) ) ;
		this.setBackgroundColor( new TextColor.RGB( 0 , 0 , 0 ) ) ;
		this.setSpeed( 1 ) ;
	}
	
	@Override
	public void move() {
	}
	
	@Override
	public void move( int dirx , int diry ) {
		this.setX( this.getX() + ( dirx * this.getSpeed() ) ) ;
		this.setY( this.getY() + ( diry * this.getSpeed() ) ) ;
	}
	
	@Override
	public void move( KeyStroke input ){
		boolean right = 
				ButtonUtils.isButtonPressed( 
						input , 
						KeyType.ArrowRight ) || 
				ButtonUtils.isButtonPressed( 
						input , 
						'9' ) ||
				ButtonUtils.isButtonPressed( 
						input , 
						'3' ) ||
				ButtonUtils.isButtonPressed( 
						input , 
						'6' ) ;
		
		boolean left = 
				ButtonUtils.isButtonPressed( 
						input , 
						KeyType.ArrowLeft ) || 
				ButtonUtils.isButtonPressed( 
						input , 
						'7' ) ||
				ButtonUtils.isButtonPressed( 
						input , 
						'1' ) ||
				ButtonUtils.isButtonPressed( 
						input , 
						'4' ) ;
		
		boolean up = 
				ButtonUtils.isButtonPressed( 
						input , 
						KeyType.ArrowUp ) || 
				ButtonUtils.isButtonPressed( 
						input , 
						'7' ) ||
				ButtonUtils.isButtonPressed( 
						input , 
						'9' ) ||
				ButtonUtils.isButtonPressed( 
						input , 
						'8' ) ;
		
		boolean down = 
				ButtonUtils.isButtonPressed( 
						input , 
						KeyType.ArrowDown ) || 
				ButtonUtils.isButtonPressed( 
						input , 
						'1' ) ||
				ButtonUtils.isButtonPressed( 
						input , 
						'3' ) ||
				ButtonUtils.isButtonPressed( 
						input , 
						'2' ) ;
		
		int dirX = ( right ? 1 : 0 ) - ( left ? 1 : 0 ) ;
		
		int dirY = ( down ? 1 : 0 ) - ( up ? 1 : 0 ) ;
		
		move( dirX , dirY ) ;
	}

	@Override
	public void update() {
	}

}
