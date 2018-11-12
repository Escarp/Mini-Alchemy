package model.factory;

public class FactoryProducer {
	public AbstractFactory getFactory( String factory ) {
		if( factory.equalsIgnoreCase( "ENTITY" ) ) {
			return new FactoryEntity() ;
		}
		return null ;
	}
}
