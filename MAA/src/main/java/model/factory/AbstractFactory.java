package model.factory;

import model.entity.AbstractEntity;

public abstract class AbstractFactory {
	public abstract AbstractEntity getEntity( String entity ) ;
}
