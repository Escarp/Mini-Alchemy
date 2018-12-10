package model.DAO.builder;

import model.DAO.factory.ComponentFactory;
import model.DAO.loader.EntityLoader;
import model.component.Render;
import model.entity.Entity;
import util.ComponentUtils.ComponentType;
import util.EntityUtils.EntityType;

public class EntityBuilder {
	ComponentFactory factory ;
	
	//Constructors
	public EntityBuilder() {
		factory = new ComponentFactory() ;
	}
	
	//Methods
	public Entity buildEntity( ComponentType... types ) {
		Entity entity = new Entity( ) ;
		for( ComponentType type : types ) {
			//For each type, create component and add it to the entity
			entity.addComponent( factory.getComponent( type ) ) ;
		}
		return entity ;
	}
	
	public Entity createEntity( EntityType type ) {
		switch( type ) {
			//Switch which entity to create and add the appropriate components
			case PLAYER : {
				Entity entity =  buildEntity(	ComponentType.POSITION , 
												ComponentType.RENDER , 
												ComponentType.VELOCITY ) ;
				entity.setType( type ) ;
				Render render = ( Render )entity.getComponent( 
						ComponentType.RENDER ) ;
				render.setCharacter( '@' );
				return entity ;
			}
			case DEFAULT : {
			}
			default : {
				return buildEntity( ComponentType.POSITION , 
									ComponentType.RENDER ) ;
			}
		}
	}
	
	public Entity loadEntity( EntityType type ) {
		switch( type ) {
			case PLAYER : {
				EntityLoader el = new EntityLoader() ;
				return el.load( type.toString() ) ;
			}
			case DEFAULT : {
				
			}
			default : {
				return buildEntity( ComponentType.POSITION , 
						ComponentType.RENDER ) ;
			}
		}
	}
}
