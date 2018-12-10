package model.component;

import java.io.Serializable;

import util.ComponentUtils.ComponentType;

@SuppressWarnings( "serial" )
public abstract class AComponent implements Serializable {
	protected ComponentType type ;

	//Getters
	public ComponentType getType() {
		return type;
	}

	//Setters
	public void setType( ComponentType type ) {
		this.type = type;
	}
	
	//Constructors
	public AComponent( ComponentType type ) {
		this.type = type ;
	}

	//Methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( type == null ) ? 0 : type.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if( this == obj ) return true;
		if( obj == null ) return false;
		if( getClass() != obj.getClass() ) return false;
		AComponent other = ( AComponent )obj;
		if( type == null ) {
			if( other.type != null ) return false;
		}
		else if( ! type.equals( other.type ) ) return false;
		return true;
	}

	@Override
	public String toString() {
		return "AComponent [type=" + type + "]";
	}
}
