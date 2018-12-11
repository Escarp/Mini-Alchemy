package model.component;

import util.ComponentUtils.ComponentType;

@SuppressWarnings( "serial" )
public class Solid extends AComponent {
	private boolean solid ;
	
	//Getters
	public boolean isSolid() {
		return solid;
	}

	//Setters
	public void setSolid( boolean solid ) {
		this.solid = solid;
		solid = true ;
	}
	
	//Constructors
	public Solid( ComponentType type ) {
		super( type );
	}
	
	public Solid() {
		this( ComponentType.SOLID ) ;
	}

	//Methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( solid ? 1231 : 1237 );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if( this == obj ) return true;
		if( ! super.equals( obj ) ) return false;
		if( getClass() != obj.getClass() ) return false;
		Solid other = ( Solid )obj;
		if( solid != other.solid ) return false;
		return true;
	}

	@Override
	public String toString() {
		return "Solid [solid=" + solid + "]";
	}
}
