package model.map.tile;

public class Tile {
	private String type ;
	private boolean passable ;
	private boolean walkable ;
	private char character ;
	
	//Getters
	public String getType() {
		return type;
	}
	public boolean isPassable() {
		return passable;
	}
	public boolean isWalkable() {
		return walkable;
	}
	public char getCharacter() {
		return character ;
	}
	
	//Setters
	public void setType( String type ) {
		this.type = type;
	}
	public void setPassable( boolean passable ) {
		this.passable = passable;
	}
	public void setWalkable( boolean walkable ) {
		this.walkable = walkable;
	}
	public void setCharacter( char character ) {
		this.character = character ;
	}
	
	//Constructors
	public Tile() {
		type = "VOID" ;
		passable = true ;
		walkable = true ;
	}
	
	public Tile( 
			String type , boolean passable , boolean walkable , char character ) {
		this.type = type ;
		this.passable = passable ;
		this.walkable = walkable ;
		this.character = character ;
	}
}
