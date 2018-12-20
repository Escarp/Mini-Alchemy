package model.DAO.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.json.JSONObject;

import model.map.tile.Tile;
import view.Debug;

public class TileLoader extends AJSONLoader<Tile> {
	public static final String FOLDER = "tiles" ;
	private HashMap<String , Tile> tiles ;
	
	//Getters
	public HashMap<String , Tile> getTiles() {
		return tiles;
	}

	//Setters
	public void setTiles( HashMap<String , Tile> tiles ) {
		this.tiles = tiles;
	}

	//Constructors
	public TileLoader() {
		tiles = new HashMap<>() ;
		
		final File jarFile = new File( 
				getClass()
				.getProtectionDomain()
				.getCodeSource()
				.getLocation()
				.getPath() ) ;
		
		try {
			if( jarFile.isFile() ) {
				final JarFile jar = new JarFile( jarFile ) ;
				final Enumeration<JarEntry> entries = jar.entries() ;
				while( entries.hasMoreElements() ) {
					final String name = entries.nextElement().getName() ;
					if(		name.startsWith( FOLDER + "/" ) && 
							name.endsWith( ".json" ) ) {
						Tile tile = load( "/" + name ) ;
						tiles.put( tile.getType() , tile ) ;
					}
				}
				jar.close() ;
			}
			else {
				final URL url = getClass().getResource( "/" + FOLDER ) ;
				if( url != null ) {
					final File files = new File( url.toURI() ) ;
					for( File f : files.listFiles() ) {
						Tile tile = load( "/" + FOLDER + "/" + f.getName() ) ;
						tiles.put( tile.getType() , tile ) ;
					}
				}
			}
		}
		catch( Exception e ) {
			Debug.logErr( "EntityLoader : constructor" , e );
		}
			
		//loadAll() ;
	}
	
	//Methods
	@Override
	public Tile load( String name ) {
		Tile tile = null ;
		try {
			InputStream is = getClass().getResourceAsStream( name ) ;
			InputStreamReader isr = new InputStreamReader( is ) ;
			BufferedReader br = new BufferedReader( isr ) ;
			
			StringBuilder sb = new StringBuilder() ;
			br.lines().forEach( l -> sb.append( l ) ) ;
			
			JSONObject json = new JSONObject( sb.toString() ) ;
			
			//Return the tile
			tile =  new Tile( 
					json.getString(		"type"		) , 
					json.getBoolean(	"passable"	) , 
					json.getBoolean(	"walkable"	) ,
					json.getString(		"character"	).charAt( 0 ) ) ;
		}
		catch( Exception e ) {
			Debug.logErr( "TileLoader : load" , e ) ;
		}
		
		Debug.logDebug( "TileLoader : loaded : " + tile + "\n"
				+ "\t\t\t from : " + name ) ;
		
		return tile ;
	}
	
//	public void loadAll() {
//		InputStreamReader isr = new InputStreamReader( 
//				folder , StandardCharsets.UTF_8 ) ;
//		BufferedReader br = new BufferedReader( isr ) ;
//		
//		br.lines().forEach( l -> tiles.put( l , load( l ) ) ) ;
//		
//		try {
//			br.close() ;
//			isr.close() ;
//			folder.close() ;
//		}
//		catch( Exception e ) {
//			Debug.logErr( "TileLoader : loadAll" , e ) ;
//		}
//		
//		Debug.logDebug( "TileLoader : loaded " + tiles.size() + " tiles" ) ;
//	}
}
