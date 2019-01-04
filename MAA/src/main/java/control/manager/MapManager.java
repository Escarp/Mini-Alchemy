package control.manager;

import java.util.ArrayList;

import model.map.AMap;
import model.map.DrunkMap;
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
		levels.add( new DrunkMap() ) ;
		levels.get( 0 ).generateMap( 80 , 80 ) ;
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

	public void reload( int currentLevel ) {
		levels.get( currentLevel ).generateMap( 100 , 100 ) ;
	}
}
