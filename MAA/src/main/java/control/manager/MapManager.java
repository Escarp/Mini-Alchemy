package control.manager;

import java.util.ArrayList;

import model.map.AMap;
import model.map.EmptyMap;
import view.GameTerminal;

public class MapManager {
	private ArrayList<AMap> levels ;

	public ArrayList<AMap> getLevels() {
		return levels;
	}

	public void setLevels( ArrayList<AMap> levels ) {
		this.levels = levels;
	}
	
	public MapManager() {
		//Generate empty map and add player : Warning : to be changed
		levels = new ArrayList<>() ;
		levels.add( new EmptyMap() ) ;
		levels.get( 0 ).generateMap( 100 , 40 ) ;
	}
	
	public void draw( GameTerminal terminal , int level ) {
		for( int y = 0 ; y < levels.get( level ).getMap().size() ; y++ ) {
			for(	int x = 0 ; 
					x < levels.get( level ).getMap().get( 0 ).size() ; 
					x++ ) {
				//Pass char and position to the screen
				terminal.drawCharAt( 
						levels.get( level )
								.getMap()
								.get( y )
								.get( x )
								.getCharacter() , 
						x , 
						y ) ;
			}
		}
	}
}
