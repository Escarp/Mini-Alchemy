package model.system;

import model.component.AComponent;
import model.component.Position;
import model.component.Velocity;

public class MovementSystem extends ASystem {

	public MovementSystem( String name ) {
		super( name );
	}
	
	public MovementSystem() {
		this( "movement" ) ;
	}

	@Override
	public void update( AComponent... components ) {
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
		
		if( position != null && velocity != null ) {
			position.setX( position.getX() + velocity.getxVel() ) ;
			position.setY( position.getY() + velocity.getyVel() ) ;
		}
		if( velocity != null ){
			velocity.setxVel( 0 ) ;
			velocity.setyVel( 0 ) ;
		}
	}
	
}
