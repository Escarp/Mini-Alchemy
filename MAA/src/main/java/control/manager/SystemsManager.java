package control.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import model.entity.Entity;
import model.map.tile.Tile;
import model.system.ASystem;
import model.system.GraphicsSystem;
import model.system.KeyMovementSystem;
import util.ButtonUtils;
import util.ComponentUtils.ComponentType;
import util.EntityUtils.EntityType;
import util.SystemsUtils.SystemType;
import view.GameTerminal;

public class SystemsManager {
	private HashMap<SystemType , ASystem> systems ;
	private ArrayList<ArrayList<Tile>> map ;
	
	public void setMap( ArrayList<ArrayList<Tile>> map ) {
		this.map = map ;
	}
	
	public SystemsManager() {
		systems = new HashMap<>() ;
	}
	
	public void init( GameTerminal terminal ) {
		//Add all the systems
		systems.put(	SystemType.GRAPHICS , 
						new GraphicsSystem( terminal ) ) ;
		systems.put(	SystemType.KEY_MOVEMENT , 
						new KeyMovementSystem() ) ;
	}
	
	public void draw( Collection<Entity> entities ) {
		//Check if the system got deleted
		if( systems.containsKey( SystemType.GRAPHICS ) ) {
			for( Entity entity : entities ){
				//For each entity pass the position and render to the screen
				systems.get( SystemType.GRAPHICS ).update( 
						entity.getComponent( ComponentType.POSITION ) , 
						entity.getComponent( ComponentType.RENDER ) ) ;
			}
		}
	}
	
	public boolean input( Collection<Entity> entities , KeyStroke input ) {
		boolean running = true ;
		//Exit condition
		if( ButtonUtils.areButtonsPressed( 
				input , KeyType.Escape , KeyType.EOF ) ) {
			running = false ;
			return running ;
		}
		//Update entities that have to move with key inputs like the player
		if( systems.containsKey( SystemType.KEY_MOVEMENT ) ) {
			for( Entity entity : entities ){
				if( entity.getType() == EntityType.PLAYER ) {
					//Move the entities that need moving
					KeyMovementSystem kms = 
							( KeyMovementSystem )systems.get( 
									SystemType.KEY_MOVEMENT ) ;
					kms.setInput( input ) ;
					kms.setMap( map ) ;
					kms.update( 
							entity.getComponent( ComponentType.POSITION ) ,
							entity.getComponent( ComponentType.VELOCITY ) ,
							entity.getComponent( ComponentType.SOLID )
							) ;
				}
			}
		}
		return running ;
	}
}