package model.DAO.loader;

import java.io.File;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;

import control.Main;
import model.map.tile.Tile;
import view.Debug;

public class TileLoader extends AJSONLoader<Tile> {
	private File folder ;
	private HashMap<String , Tile> tiles ;
	
	//Getters
	public File getFolder() {
		return folder;
	}

	public HashMap<String , Tile> getTiles() {
		return tiles;
	}

	//Setters
	public void setFolder( File folder ) {
		this.folder = folder;
	}

	public void setTiles( HashMap<String , Tile> tiles ) {
		this.tiles = tiles;
	}

	//Constructors
	public TileLoader() {
		try {
			tiles = new HashMap<>() ;
			folder = new File( 
					Main.class.getClassLoader().getResource("tiles").toURI() ) ;
			
			loadAll() ;
		}
		catch( Exception e ) {
			Debug.logErr( "TileLoader : constructor" , e ) ;
		}
	}
	
	//Methods
	@Override
	public Tile load( String name ) {
		Tile tile = null ;
		try {
			//Modify the name and create url from it
			name = Main.class.getClassLoader().getResource( 
					"tiles/" + name ).toString() ;
			URI uri = new URI( name ) ;
			
			//Create tokener to build the JSONObject with
			JSONTokener tokener = new JSONTokener( uri.toURL().openStream() ) ;
			JSONObject json = new JSONObject( tokener ) ;
			
			//Return the tile
			tile =  new Tile( 
					json.getString( "name" ) , 
					json.getBoolean( "passable" ) , 
					json.getBoolean( "walkable" ) ,
					json.getString( "character" ).charAt( 0 ) ) ;
		}
		catch( Exception e ) {
			Debug.logErr( "TileLoader : load" , e ) ;
		}
		
		Debug.logDebug( "loaded : " + tile + "\n"
				+ "\t\tfrom : " + name ) ;
		
		return tile ;
	}
	
	public void loadAll() {
		File[] list = folder.listFiles() ;
		
		Debug.logDebug( "TileLoader : loadAll : " + 
					Arrays.toString( list ) ) ;
		
		for( File file : list ) {
			if( file.isFile() ){
				tiles.put( file.getName() , load( file.getName() ) ) ;
			}
		}
	}
}
