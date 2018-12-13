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
	
	public void draw( 
			GameTerminal terminal , 
			int level , 
			int x , 
			int y , 
			int indX , 
			int indY ) {
		terminal.drawCharAt( 
				levels.get( level )
						.getMap()
						.get( indY )
						.get( indX )
						.getCharacter() , x , y ) ;
	}
}
