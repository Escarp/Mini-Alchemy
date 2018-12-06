package model.system;

import model.component.AComponent;

public abstract class ASystem {
	protected String name ;
	
	//Getters
	public String getName() {
		return name;
	}

	//Setters
	public void setName( String name ) {
		this.name = name;
	}

	//Constructors
	public ASystem( String name ) {
		this.name = name ;
	}
	
	//Methods
	public abstract void update( AComponent... components ) ;
}
