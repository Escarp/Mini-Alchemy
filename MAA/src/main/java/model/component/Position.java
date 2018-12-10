package model.component;

import util.ComponentUtils.ComponentType;

@SuppressWarnings( "serial" )
public class Position extends AComponent {
	private int x , y ;
	
	//Getters
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	//Setters
	public void setX( int x ) {
		this.x = x;
	}
	public void setY( int y ) {
		this.y = y;
	}

	//Constructors
	public Position( ComponentType type ) {
		super( type );
		x = 0 ;
		y = 0 ;
	}
	
	public Position(){
		this( ComponentType.POSITION ) ;
	}
	
	//Methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	
	@Override
	public boolean equals( Object obj ) {
		if( this == obj ) return true;
		if( ! super.equals( obj ) ) return false;
		if( getClass() != obj.getClass() ) return false;
		Position other = ( Position )obj;
		if( x != other.x ) return false;
		if( y != other.y ) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + ", type=" + type + "]";
	}
}
