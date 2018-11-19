package util.physics;

public class Vector2d extends AbstractVector2<Double> {
	//Constructors
	public Vector2d(){
		super( 0.0d , 0.0d ) ;
		value = 0.0d ;
		magnitude = 0.0d ;
	}
	
	public Vector2d( Double x , Double y ){
		super( x , y ) ;
		value = Math.sqrt( ( x * x ) + ( y * y ) ) ;
		magnitude = Math.tanh( y/x ) ;
	}
	
	public Vector2d( double x , double y , double value , double magnitude ) 
			throws Exception {
		this() ;
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
	public void add( Vector2d other ) {
		this.x += other.x ;
		this.y += other.y ;
		this.value = Math.sqrt( ( this.x * this.x ) + ( this.y * this.y ) ) ;
		this.magnitude = Math.tanh( this.y / this.x ) ;
	}
	
	public static Vector2d add( Vector2d vec1 , Vector2d vec2 ) {
		Vector2d result = new Vector2d() ;
		
		result.x = vec1.x + vec2.x ;
		result.y = vec1.y + vec2.y ;
		
		result.value = Math.sqrt( 
				( result.x * result.x ) + 
				( result.y * result.y ) ) ;
		result.magnitude = Math.tanh( result.y / result.x ) ;
		
		return result ;
	}
	
	public void multiply( Vector2d other ){
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
	
	public static Vector2d multiply( Vector2d result , int mul ) {
		result.x *= mul ;
		result.y *= mul ;
		result.value = Math.sqrt( 
				( result.x * result.x ) + 
				( result.y * result.y ) ) ;
		result.magnitude = Math.tanh( result.y / result.x ) ;
		
		return result ;
	}
	
	public static Vector2d multiply( Vector2d vec1 , Vector2d vec2 ) {
		Vector2d result = new Vector2d() ;
		
		result.x = vec1.x + vec2.x ;
		result.y = vec1.y + vec2.y ;
		
		result.value = Math.sqrt( 
				( result.x * result.x ) + 
				( result.y * result.y ) ) ;
		result.magnitude = Math.tanh( result.y / result.x ) ;
		
		return result ;
	}

	public void add(Vector2i other) {
		this.x += other.x ;
		this.y += other.y ;
		this.value = Math.sqrt( ( this.x * this.x ) + ( this.y * this.y ) ) ;
		this.magnitude = Math.tanh( this.y / this.x ) ;
	}

	
}
