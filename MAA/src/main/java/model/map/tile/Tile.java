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
		passable = false ;
		walkable = false ;
		character = ' ' ;
	}
	
	public Tile( 
			String type , boolean passable , boolean walkable , char character ) {
		this.type = type ;
		this.passable = passable ;
		this.walkable = walkable ;
		this.character = character ;
	}
	
	//Methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + character;
		result = prime * result + ( passable ? 1231 : 1237 );
		result = prime * result + ( ( type == null ) ? 0 : type.hashCode() );
		result = prime * result + ( walkable ? 1231 : 1237 );
		return result;
	}
	
	@Override
	public boolean equals( Object obj ) {
		if( this == obj ) return true;
		if( obj == null ) return false;
		if( getClass() != obj.getClass() ) return false;
		Tile other = ( Tile )obj;
		if( character != other.character ) return false;
		if( passable != other.passable ) return false;
		if( type == null ) {
			if( other.type != null ) return false;
		}
		else if( ! type.equals( other.type ) ) return false;
		if( walkable != other.walkable ) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Tile [type=" + type
				+ ", passable="
				+ passable
				+ ", walkable="
				+ walkable
				+ ", character="
				+ character
				+ "]";
	}
}
