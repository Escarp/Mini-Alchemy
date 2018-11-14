package model.tile;

public class AbstractTile {
	protected boolean passable ;
	protected boolean walkable ;
	protected char character ;
	
	//Getters
	public boolean isPassable() {
		return passable;
	}
	public boolean isWalkable() {
		return walkable;
	}
	public char getCharacter() {
		return character;
	}
	
	//Setters
	public void setPassable(boolean passable) {
		this.passable = passable;
	}
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
	public void setCharacter(char character) {
		this.character = character;
	}
	
}
