package model.component;

import java.io.Serializable;

@SuppressWarnings( "serial" )
public abstract class AComponent implements Serializable {
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
	public AComponent( String name ) {
		this.name = name ;
	}

	//Methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if( this == obj ) return true;
		if( obj == null ) return false;
		if( getClass() != obj.getClass() ) return false;
		AComponent other = ( AComponent )obj;
		if( name == null ) {
			if( other.name != null ) return false;
		}
		else if( ! name.equals( other.name ) ) return false;
		return true;
	}

	@Override
	public String toString() {
		return "AComponent [name=" + name + "]";
	}
}
