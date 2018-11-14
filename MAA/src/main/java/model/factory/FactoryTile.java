package model.factory;

import model.entity.AbstractEntity;
import model.tile.AbstractTile;
import model.tile.Floor;
import model.tile.Wall;

public class FactoryTile extends AbstractFactory {

	@Override
	public AbstractEntity getEntity(String entity) {
		return null;
	}

	@Override
	public AbstractTile getTile(String tile) {
		if( tile.equalsIgnoreCase( "WALL" ) ){
			return new Wall() ;
		}
		if( tile.equalsIgnoreCase( "FLOOR" ) ){
			return new Floor() ;
		}
		return null;
	}

}
