package model.factory;

import model.entity.AbstractEntity;
import model.tile.AbstractTile;

public abstract class AbstractFactory {
	public abstract AbstractEntity getEntity( String entity ) ;
	public abstract AbstractTile getTile( String tile ) ;
}
