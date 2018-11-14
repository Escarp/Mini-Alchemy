package model.tile;

public class Wall extends AbstractTile {
	public Wall( char character , boolean passable , boolean walkable ){
		this.character = character ;
		this.passable = passable ;
		this.walkable = walkable ;
	}
	
	public Wall( char character ){
		this( character , false , false ) ;
	}
	
	public Wall(){
		this( '#' , false , false ) ;
	}
}
