package model.tile;

import com.googlecode.lanterna.TextColor;

public class AbstractTile {
	protected boolean passable ;
	protected boolean walkable ;
	protected char character ;
	protected TextColor foregroundColor ;
	protected TextColor backgroundColor ;
	protected boolean discovered ;
	
	//Getters
	public boolean isDiscovered() {
		return discovered ;
	}
	
	public boolean isPassable() {
		return passable;
	}
	public boolean isWalkable() {
		return walkable;
	}
	public char getCharacter() {
		return character;
	}
	public TextColor getForegroundColor() {
		return foregroundColor;
	}
	public TextColor getBackgroundColor() {
		return backgroundColor;
	}
	
	//Setters
	public void setDiscovered( boolean discovered ) {
		this.discovered = discovered ;
	}
	public void setPassable(boolean passable) {
		this.passable = passable;
	}
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
	public void setCharacter(char character) {
		this.character = character;
	}
	public void setForegroundColor(TextColor foregroundColor) {
		this.foregroundColor = foregroundColor;
	}

	public void setBackgroundColor(TextColor backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
}
