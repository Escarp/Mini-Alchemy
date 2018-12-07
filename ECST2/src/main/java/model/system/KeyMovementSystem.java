package model.system;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import model.component.AComponent;
import model.component.Position;
import model.component.Velocity;
import util.ButtonUtils;
import util.SystemsUtils.SystemType;

public class KeyMovementSystem extends ASystem {
	private KeyStroke input ;
	
	public KeyStroke getInput() {
		return input;
	}

	public void setInput( KeyStroke input ) {
		this.input = input;
	}

	public KeyMovementSystem() {
		super( "keyMovement" , SystemType.KEY_MOVEMENT ) ;
	}

	@Override
	public void update( AComponent... components ) {
		if( input != null ){
			Position position = null ;
			Velocity velocity = null ;
			
			for( AComponent component : components ) {
				if( component instanceof Position ) {
					position = ( Position )component ;
				}
				else if( component instanceof Velocity ) {
					velocity = ( Velocity )component ;
				}
			}
			
			if( position != null && velocity != null ){
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
				
				velocity.setxVel( 
						velocity.getxVel() + 
						( ( right ? 1 : 0 ) - ( left ? 1 : 0 )  ) ) ;
				velocity.setyVel( 
						velocity.getyVel() + 
						( ( down ? 1 : 0 ) - ( up ? 1 : 0 )  ) ) ;
				
				position.setX( position.getX() + velocity.getxVel() ) ;
				position.setY( position.getY() + velocity.getyVel() ) ;
				
				velocity.setxVel( 0 ) ;
				velocity.setyVel( 0 ) ;
			}
		}
	}
	
}
