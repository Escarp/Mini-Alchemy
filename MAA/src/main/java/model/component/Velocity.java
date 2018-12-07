package model.component;

import util.ComponentUtils;

@SuppressWarnings( "serial" )
public class Velocity extends AComponent {
	private int xVel , yVel ;

	//Getters
	public int getxVel() {
		return xVel;
	}
	public int getyVel() {
		return yVel;
	}

	//Setters
	public void setxVel( int xVel ) {
		this.xVel = xVel;
	}
	public void setyVel( int yVel ) {
		this.yVel = yVel;
	}
	
	//Constructors
	public Velocity( String name ) {
		super( name );
		xVel = 0 ;
		yVel = 0 ;
	}
	
	public Velocity() {
		this( ComponentUtils.VELOCITY ) ;
	}
	
	//Methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + xVel;
		result = prime * result + yVel;
		return result;
	}
	
	@Override
	public boolean equals( Object obj ) {
		if( this == obj ) return true;
		if( ! super.equals( obj ) ) return false;
		if( getClass() != obj.getClass() ) return false;
		Velocity other = ( Velocity )obj;
		if( xVel != other.xVel ) return false;
		if( yVel != other.yVel ) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Velocity [xVel=" + xVel
				+ ", yVel="
				+ yVel
				+ ", name="
				+ name
				+ "]";
	}
}
