package model.factory;

import model.entity.AbstractEntity;
import model.entity.Player;
import model.tile.AbstractTile;

public class FactoryEntity extends AbstractFactory {

	@Override
	public AbstractEntity getEntity( String entity ) {
		if( entity.equalsIgnoreCase( "PLAYER" ) ) {
			return new Player() ;
		}
		return null;
	}
	
	public AbstractEntity getEntity( String entity , int x , int y ) {
		if( entity.equalsIgnoreCase( "PLAYER" ) ) {
			return new Player( x , y ) ;
		}
		return null;
	}

	@Override
	public AbstractTile getTile(String tile) {
		return null;
	}

}
