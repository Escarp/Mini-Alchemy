package model.tile;

import com.googlecode.lanterna.TextColor;

public class Floor extends AbstractTile {
	public Floor( 
			char character , 
			boolean passable , 
			boolean walkable ,
			TextColor foregroundColor , 
			TextColor backgroundColor ){
		this.character = character ;
		this.passable = passable ;
		this.walkable = walkable ;
		this.foregroundColor = foregroundColor ;
		this.backgroundColor = backgroundColor ;
	}
	
	public Floor( char character ){
		this( character , 
				true , 
				true , 
				TextColor.ANSI.BLACK , 
				TextColor.ANSI.WHITE ) ;
	}
	
	public Floor(){
		this( '-' , 
				true , 
				true , 
				TextColor.ANSI.YELLOW , 
				TextColor.ANSI.BLACK ) ;
	}
}
