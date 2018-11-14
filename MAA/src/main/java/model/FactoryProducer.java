package model;

import model.factory.AbstractFactory;
import model.factory.FactoryEntity;
import model.factory.FactoryTile;

public class FactoryProducer {
	public AbstractFactory getFactory( String factory ) {
		if( factory.equalsIgnoreCase( "ENTITY" ) ) {
			return new FactoryEntity() ;
		}
		if( factory.equalsIgnoreCase( "TILE" ) ){
			return new FactoryTile() ;
		}
		return null ;
	}
}
