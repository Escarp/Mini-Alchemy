package util.physics;

public class Vector2i extends AbstractVector2<Integer> {

	public Vector2i(){
		super( 0 , 0 ) ;
		value = 0 ;
		magnitude = 0 ;
	}
	
	public Vector2i(Integer x, Integer y) {
		super(x, y);
		value = (int)Math.sqrt( ( x * x ) + ( y * y ) ) ;
		if( x != 0 ){
			magnitude = (int)Math.tanh( y/x ) ;
		}
	}

	public Vector2i( int x , int y , int value , int magnitude ) 
			throws Exception {
		this() ;
		if ( magnitude != 0 && value != 0 ) {
			this.magnitude = magnitude ;
			if (x == 0) {
				this.x = (int)( value * Math.cos( magnitude ) ) ;
			} else {
				this.x = x;
			}
			if (y == 0) {
				this.y = (int)( value * Math.sin(magnitude) ) ;
			} else {
				this.y = y;
			}
			this.value = value ;
		}
		else if( x != 0 && y != 0 ){
			this.value = (int)Math.sqrt( ( x * x ) + ( y * y ) ) ;
			this.magnitude = (int)Math.tanh( y/x ) ;
		}
		else{
			throw new Exception() ;
		}
	}
	
	public void add( Vector2i other ) {
		this.x += other.x ;
		this.y += other.y ;
		this.value = (int)Math.sqrt( ( this.x * this.x ) + ( this.y * this.y ) ) ;
		if( this.x != 0 ){
			this.magnitude = (int)Math.tanh( this.y / this.x ) ;
		}
	}
	
	public static Vector2i add( Vector2i vec1 , Vector2i vec2 ) {
		Vector2i result = new Vector2i() ;
		
		result.x = (int) (vec1.x + vec2.x) ;
		result.y = (int) (vec1.y + vec2.y) ;
		
		result.value = (int)Math.sqrt( 
				( result.x * result.x ) + 
				( result.y * result.y ) ) ;
		if( result.x != 0 ) {
			result.magnitude = (int)Math.tanh( result.y / result.x ) ;
		}
		
		return result ;
	}
	
	public static Vector2i add( Vector2d vec1 , Vector2i vec2 ) {
		Vector2i result = new Vector2i() ;
		
		result.x = (int) (vec1.x + vec2.x) ;
		result.y = (int) (vec1.y + vec2.y) ;
		
		result.value = (int)Math.sqrt( 
				( result.x * result.x ) + 
				( result.y * result.y ) ) ;
		if( result.x != 0 ) {
			result.magnitude = (int)Math.tanh( result.y / result.x ) ;
		}
		
		return result ;
	}
	
	public void multiply( Vector2i other ){
		this.x *= other.x ;
		this.y *= other.y ;
		this.value = (int)Math.sqrt( ( this.x * this.x ) + ( this.y * this.y ) ) ;
		this.magnitude = (int)Math.tanh( this.y / this.x ) ;
	}
	
	public void multiply( int mul ) {
		this.x *= mul ;
		this.y *= mul ;
		this.value = (int)Math.sqrt( ( this.x * this.x ) + ( this.y * this.y ) ) ;
		if( this.x != 0 ){
			this.magnitude = (int)Math.tanh( this.y / this.x ) ;
		}
	}
	
	public static Vector2i multiply( Vector2i result , int mul ) {
		result.x *= mul ;
		result.y *= mul ;
		result.value = (int)Math.sqrt( 
				( result.x * result.x ) + 
				( result.y * result.y ) ) ;
		result.magnitude = (int)Math.tanh( result.y / result.x ) ;
		
		return result ;
	}
	
	public static Vector2i multiply( Vector2i vec1 , Vector2i vec2 ) {
		Vector2i result = new Vector2i() ;
		
		result.x = (int)( vec1.x + vec2.x ) ;
		result.y = (int)( vec1.y + vec2.y ) ;
		
		result.value = (int)Math.sqrt( 
				( result.x * result.x ) + 
				( result.y * result.y ) ) ;
		result.magnitude = (int)Math.tanh( result.y / result.x ) ;
		
		return result ;
	}
}
