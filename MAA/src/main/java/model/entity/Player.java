package model.entity;

import com.googlecode.lanterna.TextColor;

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
		// TODO Auto-generated method stub

	}
	
	@Override
	public void move( int dirx , int diry ) {
		this.setX( this.getX() + ( dirx * this.getSpeed() ) ) ;
		this.setY( this.getY() + ( diry * this.getSpeed() ) ) ;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
