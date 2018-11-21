package model.factory;

import model.entity.AbstractEntity;
import model.entity.Player;
import model.tile.AbstractTile;
import model.world.AbstractLevel;

public class FactoryEntity extends AbstractFactory {

	@Override
	public AbstractEntity getEntity( String entity ) {
		if( entity.equalsIgnoreCase( "PLAYER" ) ) {
			return new Player() ;
		}
		
		return null;
	}

	@Override
	public AbstractTile getTile(String tile) {
		return null;
	}

	@Override
	public AbstractLevel getLevel(String level) {
		return null;
	}

}
