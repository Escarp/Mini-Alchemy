package model.DAO.factory;

import model.component.AComponent;
import model.component.Position;
import model.component.Render;
import model.component.Velocity;
import util.ComponentUtils.ComponentType;

public class ComponentFactory {
	public AComponent getComponent( ComponentType type ) {
		switch( type ) {
			//Switch names to create appropriate component
			case POSITION : {
				return new Position() ;
			}
			case RENDER : {
				return new Render() ;
			}
			case VELOCITY : {
				return new Velocity() ;
			}
			default : {
				return null ;
			}
		}
	}
}
