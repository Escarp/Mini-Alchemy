package util.physics;

public class Direction {
	public static final Vector2i TOP	= new Vector2i( 0	, -1 ) ;
	public static final Vector2i BOTTOM	= new Vector2i( 0	, 1 ) ;
	public static final Vector2i RIGHT	= new Vector2i( 1	, 0 ) ;
	public static final Vector2i LEFT	= new Vector2i( -1 	, 0 ) ;
	
	public static final Vector2i TOP_RIGHT		= new Vector2i( 1	, -1 ) ;
	public static final Vector2i TOP_LEFT		= new Vector2i( -1	, -1 ) ;
	public static final Vector2i BOTTOM_RIGHT	= new Vector2i( 1	, 1 ) ;
	public static final Vector2i BOTTOM_LEFT	= new Vector2i( -1	, 1 ) ;
}
