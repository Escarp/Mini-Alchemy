package model.DAO.builder;

import model.DAO.factory.ComponentFactory;
import model.component.Render;
import model.entity.Entity;
import util.ComponentUtils;
import util.EntityUtils.EntityType;

public class EntityBuilder {
	ComponentFactory factory ;
	
	public EntityBuilder() {
		factory = new ComponentFactory() ;
	}
	
	public Entity buildEntity( String... types ) {
		Entity entity = new Entity( ) ;
		
		for( String type : types ) {
			entity.addComponent( factory.getComponent( type ) ) ;
		}
		
		return entity ;
	}
	
	public Entity createEntity( EntityType type ) {
		switch( type ) {
			case PLAYER : {
				Entity entity =  buildEntity(	ComponentUtils.POSITION , 
												ComponentUtils.RENDER , 
												ComponentUtils.VELOCITY ) ;
				entity.setType( type ) ;
				Render render = ( Render )entity.getComponent( 
						ComponentUtils.RENDER ) ;
				render.setCharacter( '@' );
				return entity ;
			}
			case DEFAULT : {
			}
			default : {
				return buildEntity( ComponentUtils.POSITION , 
									ComponentUtils.RENDER ) ;
			}
		}
	}
}
