package util.physics;

public class Vector2 {
	/*FIXME : make this into an interface to make an int variant to avoid 
	 * rounding problems
	 */
	
	private double x ;
	private double y ;
	private double value ;	
	private double magnitude ;
	
	//Getters
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getValue() {
		return value;
	}

	public double getMagnitude() {
		return magnitude;
	}
	
	//Setters
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
	}

	//Constructors
	public Vector2(){
		x = 0.0d ;
		y = 0.0d ;
		value = 0.0d ;
		magnitude = 0.0d ;
	}
	
	public Vector2( double x , double y ){
		this.x = x ;
		this.y = y ;
		value = Math.sqrt( ( x * x ) + ( y * y ) ) ;
		magnitude = Math.tanh( y/x ) ;
	}
	
	public Vector2( double x , double y , double value , double magnitude ) 
			throws Exception {
		if ( magnitude != 0 && value != 0 ) {
			this.magnitude = magnitude ;
			if (x == 0) {
				this.x = value * Math.cos(magnitude);
			} else {
				this.x = x;
			}
			if (y == 0) {
				this.y = value * Math.sin(magnitude);
			} else {
				this.y = y;
			}
			this.value = value ;
		}
		else if( x != 0 && y != 0 ){
			this.value = Math.sqrt( ( x * x ) + ( y * y ) ) ;
			this.magnitude = Math.tanh( y/x ) ;
		}
		else{
			throw new Exception() ;
		}
	}
	
	//Methods
	public void add( Vector2 other ) {
		this.x += other.x ;
		this.y += other.y ;
		this.value = Math.sqrt( ( this.x * this.x ) + ( this.y * this.y ) ) ;
		this.magnitude = Math.tanh( this.y / this.x ) ;
	}
	
	public static Vector2 add( Vector2 vec1 , Vector2 vec2 ) {
		Vector2 result = new Vector2() ;
		
		result.x = vec1.x + vec2.x ;
		result.y = vec1.y + vec2.y ;
		
		result.value = Math.sqrt( 
				( result.x * result.x ) + 
				( result.y * result.y ) ) ;
		result.magnitude = Math.tanh( result.y / result.x ) ;
		
		return result ;
	}
	
	public void multiply( Vector2 other ){
		this.x *= other.x ;
		this.y *= other.y ;
		this.value = Math.sqrt( ( this.x * this.x ) + ( this.y * this.y ) ) ;
		this.magnitude = Math.tanh( this.y / this.x ) ;
	}
	
	public void multiply( int mul ) {
		this.x *= mul ;
		this.y *= mul ;
		this.value = Math.sqrt( ( this.x * this.x ) + ( this.y * this.y ) ) ;
		this.magnitude = Math.tanh( this.y / this.x ) ;
	}
	
	public static Vector2 multiply( Vector2 result , int mul ) {
		result.x *= mul ;
		result.y *= mul ;
		result.value = Math.sqrt( 
				( result.x * result.x ) + 
				( result.y * result.y ) ) ;
		result.magnitude = Math.tanh( result.y / result.x ) ;
		
		return result ;
	}
	
	public static Vector2 multiply( Vector2 vec1 , Vector2 vec2 ) {
		Vector2 result = new Vector2() ;
		
		result.x = vec1.x + vec2.x ;
		result.y = vec1.y + vec2.y ;
		
		result.value = Math.sqrt( 
				( result.x * result.x ) + 
				( result.y * result.y ) ) ;
		result.magnitude = Math.tanh( result.y / result.x ) ;
		
		return result ;
	}

	@Override
	public String toString() {
		return "Vector2 "
				+ "[x=" + x + 
				", y=" + y + 
				", value=" + value + 
				", magnitude=" + magnitude + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(magnitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector2 other = (Vector2) obj;
		if (Double.doubleToLongBits(magnitude) != Double.doubleToLongBits(
				other.magnitude))
			return false;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(
				other.value))
			return false;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
}
