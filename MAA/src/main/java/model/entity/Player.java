package model.entity;

import java.util.ArrayList;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import model.tile.AbstractTile;
import util.ButtonUtils;
import util.physics.Vector2;

public class Player extends AbstractEntity {

	public Player( Vector2 position ) {
		character = '@' ;
		foregroundColor = TextColor.ANSI.WHITE ;
		backgroundColor = TextColor.ANSI.BLACK ;
		speed = 3 ;
		this.position = position ;
	}
	
	public Player() {
		character = '@' ;
		foregroundColor = TextColor.ANSI.WHITE ;
		backgroundColor = TextColor.ANSI.BLACK ;
		speed = 3 ;
	}
	
	@Override
	public void update() {
	}

	@Override
	public void move() {
	}
	
	@Override
	public void move(KeyStroke input) {
	}

	@Override
	public void move(KeyStroke input, ArrayList<ArrayList<AbstractTile>> map) {
		boolean right = 
				ButtonUtils.isButtonPressed( input , KeyType.ArrowRight ) || 
				ButtonUtils.areButtonsPressed( input ,'9' , '6' , '3' ) ;
		
		boolean left = 
				ButtonUtils.isButtonPressed( input , KeyType.ArrowLeft ) || 
				ButtonUtils.areButtonsPressed( input , '7' , '4' , '1' ) ;
		
		boolean up = 
				ButtonUtils.isButtonPressed( input , KeyType.ArrowUp ) || 
				ButtonUtils.areButtonsPressed( input , '7' , '8' , '9' ) ;
		
		boolean down = 
				ButtonUtils.isButtonPressed( input , KeyType.ArrowDown ) || 
				ButtonUtils.areButtonsPressed( input , '1' , '2' , '3' ) ;
		
		direction = new Vector2( 
				( right ? 1 : 0 ) - ( left ? 1 : 0 ) ,
				( down ? 1 : 0 ) - ( up ? 1 : 0 ) ) ;
		
		for( int i = 1 ; i < speed + 1 ; i++ ){
			if( 
					map.get( (int)( Vector2.add( position , direction ).getY() ) )
					.get( (int)( Vector2.add( position , direction ).getX() ) )
					.isPassable() 
					&& 
					map.get( (int)( Vector2.add( position , direction ).getY() ) )
					.get( (int)( Vector2.add( position , direction ).getX() ) )
					.isWalkable() ){
				move( direction , 1 ) ;
			}
		}
	}
}
