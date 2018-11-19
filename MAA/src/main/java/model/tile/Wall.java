package model.tile;

import com.googlecode.lanterna.TextColor;

public class Wall extends AbstractTile {
	public Wall( 
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
	
	public Wall( char character ){
		this( character , 
				false , 
				false , 
				TextColor.ANSI.BLACK , 
				TextColor.ANSI.WHITE ) ;
	}
	
	public Wall(){
		this( '#' , 
				false , 
				false , 
				TextColor.ANSI.BLACK , 
				TextColor.ANSI.WHITE ) ;
	}
}
