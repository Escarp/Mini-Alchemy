package control;

import java.util.ArrayList;

import control.manager.SystemsManager;
import model.DAO.builder.EntityBuilder;
import model.entity.Entity;
import util.EntityUtils.EntityType;

public class Main {
	public static void main( String[] args ){
		boolean running = true ;
		SystemsManager sm = new SystemsManager() ;
		sm.init() ;
		
		EntityBuilder eb = new EntityBuilder() ;
		
		ArrayList<Entity> entities = new ArrayList<>() ;
		entities.add( eb.createEntity( EntityType.PLAYER ) ) ;
		
		while( running ){
			//TODO : UPDATE
			
			//DRAW : TODO : change to one method : sm.draw()
				//DRAW MAP
			
				//DRAW ITEMS
			
				//DRAW ENTITIES
			sm.draw( entities ) ;
			
			//INPUT
			running = sm.input() ;
		}
		
		sm.stop();
	}
}
