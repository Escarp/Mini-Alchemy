package model.DAO.loader;

import java.net.URI;

import org.json.JSONObject;
import org.json.JSONTokener;

import control.Main;
import model.map.tile.Tile;
import view.Debug;

public class TileLoader extends AJSONLoader<Tile> {

	@Override
	public Tile load( String name ) {
		try {
			name = Main.class.getClassLoader().getResource( 
					"tiles/" + name + ".json" ).toString() ;
			
			URI uri = new URI( name ) ;
			
			JSONTokener tokener = new JSONTokener( uri.toURL().openStream() ) ;
			JSONObject json = new JSONObject( tokener ) ;
			
			return new Tile( 
					json.getString( "name" ) , 
					json.getBoolean( "passable" ) , 
					json.getBoolean( "walkable" ) ,
					json.getString( "character" ).charAt( 0 ) ) ;
		}
		catch( Exception e ) {
			Debug.logErr( "Tile : load" , e ) ;
		}
		return null ;
	}
}
