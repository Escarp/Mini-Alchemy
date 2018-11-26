package model.tile;

import com.googlecode.lanterna.TextColor;

public class VoidTile extends AbstractTile {
	public VoidTile( 
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
	
	public VoidTile( char character ){
		this( character , 
				true , 
				true ,
				false ,
				TextColor.ANSI.BLACK , 
				TextColor.ANSI.BLACK ) ;
	}
	
	public VoidTile(){
		this( ' ' , 
				true , 
				true ,
				false ,
				TextColor.ANSI.BLACK , 
				TextColor.ANSI.BLACK ) ;
	}
}
