package model.tile;

public class Floor extends AbstractTile {
	public Floor( char character , boolean passable , boolean walkable ){
		this.character = character ;
		this.passable = passable ;
		this.walkable = walkable ;
	}
	
	public Floor( char character ){
		this( character , true , true ) ;
	}
	
	public Floor(){
		this( '-' , true , true ) ;
	}
}
