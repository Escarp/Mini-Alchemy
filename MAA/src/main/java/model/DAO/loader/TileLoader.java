package model.DAO.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.json.JSONObject;

import model.map.tile.Tile;
import view.Debug;

public class TileLoader extends AJSONLoader<Tile> {
	public static final String FOLDER = "tiles" ;
	
	//Constructors
	public TileLoader() {
	}
	
	//Methods
	@Override
	public Tile load( String name ) {
		Tile tile = null ;
		String path = "" ;
		try {
			final File jarFile = new File( 
					getClass()
					.getProtectionDomain()
					.getCodeSource()
					.getLocation()
					.getPath() ) ;
			
			if( jarFile.isFile() ) {
				final JarFile jar = new JarFile( jarFile ) ;
				final Enumeration<JarEntry> entries = jar.entries() ;
				while( entries.hasMoreElements() ) {
					path = entries.nextElement().getName() ;
					if(	path.equals( FOLDER + "/" + name + ".json" ) ) {
						//Load
						path = "/" + path ;
						break ;
					}
				}
				jar.close() ;
			}
			else {
				final URL url = getClass().getResource( "/" + FOLDER ) ;
				if( url != null ) {
					final File files = new File( url.toURI() ) ;
					for( File f : files.listFiles() ) {
						//Load
						if( f.getName().equals( name + ".json" ) ) {
							path = "/" + FOLDER + "/" + f.getName() ;
							break ;
						}
					}
				}
			}
			InputStream is = getClass().getResourceAsStream( path ) ;
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
		return tile ;
	}
}
