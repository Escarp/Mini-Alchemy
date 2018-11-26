package model.factory;

import model.entity.AbstractEntity;
import model.tile.AbstractTile;
import model.world.AbstractLevel;
import model.world.DrunkenDungeonLevel;
import model.world.StandardLevel;

public class FactoryLevel extends AbstractFactory {

	@Override
	public AbstractEntity getEntity(String entity) {
		return null;
	}

	@Override
	public AbstractTile getTile(String tile) {
		return null;
	}

	@Override
	public AbstractLevel getLevel(String level) {
		if( level.equalsIgnoreCase( "STANDARD" ) ){
			return new StandardLevel() ;
		}
		if( level.equalsIgnoreCase( "DRUNK" ) ){
			return new DrunkenDungeonLevel() ;
		}
		return null;
	}

}
