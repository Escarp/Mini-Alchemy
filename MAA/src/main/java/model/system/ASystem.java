package model.system;

import model.component.AComponent;
import util.SystemsUtils.SystemType;

public abstract class ASystem {
	protected String name ;
	protected SystemType type ;
	
	//Getters
	public String getName() {
		return name;
	}
	public SystemType getType() {
		return type ;
	}
	

	//Setters
	public void setName( String name ) {
		this.name = name;
	}
	public void setType( SystemType type ) {
		this.type = type ;
	}

	//Constructors
	public ASystem( String name , SystemType type ) {
		this.name = name ;
		this.type = type ;
	}
	
	//Methods
	public abstract void update( AComponent... components ) ;
}
