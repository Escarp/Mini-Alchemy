package model.component;

import util.ComponentUtils.ComponentType;

@SuppressWarnings( "serial" )
public class Light extends AComponent {
	private int radius ;
	
	//Getters
	public int getRadius() {
		return radius;
	}

	//Setters
	public void setRadius( int radius ) {
		this.radius = radius;
	}
	
	//Constructors
	public Light( ComponentType type ) {
		super( type );
	}
	
	public Light() {
		this( ComponentType.LIGHT ) ;
	}

	//Methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + radius;
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if( this == obj ) return true;
		if( ! super.equals( obj ) ) return false;
		if( getClass() != obj.getClass() ) return false;
		Light other = ( Light )obj;
		if( radius != other.radius ) return false;
		return true;
	}

	@Override
	public String toString() {
		return "Light [radius=" + radius + "]";
	}

}
