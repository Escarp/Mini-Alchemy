package model.tile;

import com.googlecode.lanterna.TextColor;

public class Floor extends AbstractTile {
	public Floor( 
			char character , 
			boolean passable , 
			boolean walkable ,
			boolean discovered ,
			TextColor foregroundColor , 
			TextColor backgroundColor ){
		this.character = character ;
		this.passable = passable ;
		this.walkable = walkable ;
		this.discovered = discovered ;
		this.foregroundColor = foregroundColor ;
		this.backgroundColor = backgroundColor ;
	}
	
	public Floor( char character ){
		this( character , 
				true , 
				true ,
				false ,
				TextColor.ANSI.BLACK , 
				TextColor.ANSI.WHITE ) ;
	}
	
	public Floor(){
		this( '-' , 
				true , 
				true ,
				false ,
				TextColor.ANSI.YELLOW , 
				TextColor.ANSI.YELLOW ) ;
	}
}
