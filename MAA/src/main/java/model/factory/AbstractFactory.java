package model.factory;

import model.entity.AbstractEntity;
import model.tile.AbstractTile;
import model.world.AbstractLevel;

public abstract class AbstractFactory {
	public abstract AbstractEntity getEntity( String entity ) ;
	public abstract AbstractTile getTile( String tile ) ;
	public abstract AbstractLevel getLevel( String level ) ;
}
