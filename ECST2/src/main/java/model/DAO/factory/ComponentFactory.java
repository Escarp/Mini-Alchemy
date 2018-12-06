package model.DAO.factory;

import model.component.AComponent;
import model.component.Position;
import model.component.Render;
import model.component.Velocity;
import util.ComponentUtils;

public class ComponentFactory {
	public AComponent getComponent( String name ) {
		switch( name ) {
			case( ComponentUtils.POSITION ) : {
				return new Position() ;
			}
			case( ComponentUtils.RENDER ) : {
				return new Render() ;
			}
			case( ComponentUtils.VELOCITY ) : {
				return new Velocity() ;
			}
			default : {
				return null ;
			}
		}
	}
}
