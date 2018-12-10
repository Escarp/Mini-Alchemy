package model.DAO.builder;

import model.DAO.factory.ComponentFactory;
import model.component.Render;
import model.entity.Entity;
import util.ComponentUtils;
import util.EntityUtils.EntityType;

public class EntityBuilder {
	ComponentFactory factory ;
	
	//Constructors
	public EntityBuilder() {
		factory = new ComponentFactory() ;
	}
	
	//Methods
	public Entity buildEntity( String... types ) {
		Entity entity = new Entity( ) ;
		for( String type : types ) {
			//For each type, create component and add it to the entity
			entity.addComponent( factory.getComponent( type ) ) ;
		}
		return entity ;
	}
	
	public Entity createEntity( EntityType type ) {
		switch( type ) {
			//Switch which entity to create and add the appropriate components
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
	
	public Entity loadEntity( EntityType type ) {
		//TODO : loadEntity
		return null ;
	}
}
