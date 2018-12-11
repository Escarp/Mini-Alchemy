package model.system;

import java.util.ArrayList;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import model.component.AComponent;
import model.component.Position;
import model.component.Velocity;
import model.map.tile.Tile;
import util.ButtonUtils;
import util.SystemsUtils.SystemType;

public class KeyMovementSystem extends ASystem {
	private KeyStroke input ;
	private ArrayList<ArrayList<Tile>> map ;
	
	//Getters
	public KeyStroke getInput() {
		return input;
	}

	//Setters
	public void setInput( KeyStroke input ) {
		this.input = input;
	}
	public void setMap( ArrayList<ArrayList<Tile>> map ) {
		this.map = map ;
	}

	//Constructors
	public KeyMovementSystem() {
		super( "keyMovement" , SystemType.KEY_MOVEMENT ) ;
	}

	//Methods
	@Override
	public void update( AComponent... components ) {
		if( input != null ){
			Position position = null ;
			Velocity velocity = null ;
			
			for( AComponent component : components ) {
				//Get components from varArgs
				if( component instanceof Position ) {
					position = ( Position )component ;
				}
				else if( component instanceof Velocity ) {
					velocity = ( Velocity )component ;
				}
			}
			
			if( position != null && velocity != null ) {
				//Decide the direction
				boolean right = 
						ButtonUtils.isButtonPressed( 
								input , KeyType.ArrowRight ) || 
						ButtonUtils.areButtonsPressed( 
								input ,'9' , '6' , '3' ) ;
				
				boolean left = 
						ButtonUtils.isButtonPressed( 
								input , KeyType.ArrowLeft ) || 
						ButtonUtils.areButtonsPressed( 
								input , '7' , '4' , '1' ) ;
				
				boolean up = 
						ButtonUtils.isButtonPressed( 
								input , KeyType.ArrowUp ) || 
						ButtonUtils.areButtonsPressed( 
								input , '7' , '8' , '9' ) ;
				
				boolean down = 
						ButtonUtils.isButtonPressed( 
								input , KeyType.ArrowDown ) || 
						ButtonUtils.areButtonsPressed( 
								input , '1' , '2' , '3' ) ;
				
				//Set the velocity
				velocity.setxVel( 
						velocity.getxVel() + 
						( ( right ? 1 : 0 ) - ( left ? 1 : 0 )  ) ) ;
				velocity.setyVel( 
						velocity.getyVel() + 
						( ( down ? 1 : 0 ) - ( up ? 1 : 0 )  ) ) ;
				
				//Set the direction
				int dirX = ( right	? 1 : 0 ) - ( left	? 1 : 0 ) ;
				int dirY = ( down	? 1 : 0 ) - ( up	? 1 : 0 ) ;
				
				//Check for collisions
				int targetX = position.getX() + velocity.getxVel() ;
				int targetY = position.getY() + velocity.getyVel() ;
				
				//Set 
				int distance = (int)Math.sqrt( 
						Math.pow( ( targetX - position.getX() ) , 2 ) + 
						Math.pow( ( targetY - position.getY() ) , 2 ) ) ;
				
				for( int i = 0 ; i < distance ; i++ ) {
					if( 	map.get( position.getY() + dirY )
									.get( position.getX() + dirX )
									.isPassable() 
							&& map.get( position.getY() + dirY )
									.get( position.getX() + dirX )
									.isWalkable() ){
						position.setX( position.getX() + dirX ) ;
						position.setY( position.getY() + dirY ) ;
					}
				}
				
				//Set velocity back to 0
				velocity.setxVel( 0 ) ;
				velocity.setyVel( 0 ) ;
			}
		}
	}
}
