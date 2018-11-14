package model.entity;

import java.util.ArrayList;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import model.tile.AbstractTile;
import util.ButtonUtils;

public class Player extends AbstractEntity {

	public Player( int x , int y ) {
		character = '@' ;
		foregroundColor = TextColor.ANSI.WHITE ;
		backgroundColor = TextColor.ANSI.BLACK ;
		speed = 1 ;
		this.x = x ;
		this.y = y ;
	}
	
	public Player() {
		character = '@' ;
		foregroundColor = TextColor.ANSI.WHITE ;
		backgroundColor = TextColor.ANSI.BLACK ;
		speed = 1 ;
	}
	
	@Override
	public void move() {
	}
	
	@Override
	public void move( int dirx , int diry ) {
		x = x + ( dirx * speed ) ;
		y = y + ( diry * speed ) ;
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

	@Override
	public void move(KeyStroke input, ArrayList<ArrayList<AbstractTile>> map) {
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
		
		int tempX = x + ( dirX * speed ) ;
		int tempY = y + ( dirY * speed ) ;
		
		if( map.get( tempY ).get( tempX ).isPassable() && 
				map.get( tempY ).get( tempX ).isWalkable() ){
			move( dirX , dirY ) ;
		}
		
	}

}
