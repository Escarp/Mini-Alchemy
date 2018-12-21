package model.DAO.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.json.JSONArray;
import org.json.JSONObject;

import com.googlecode.lanterna.TextColor;

import model.component.Light;
import model.component.Position;
import model.component.Render;
import model.component.Solid;
import model.component.Velocity;
import model.entity.Entity;
import util.ComponentUtils.ComponentType;
import util.EntityUtils.EntityType;
import view.Debug;

public class EntityLoader extends AJSONLoader<Entity> {
	public static final String FOLDER = "entities" ;
	public EntityLoader() {
		
	}
	
	@Override
	public Entity load( String name ) {
		Entity entity = new Entity() ;
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
			
			entity.setType( EntityType.valueOf( json.getString( "type" ) ) ) ;
			
			JSONArray arr = ( JSONArray )json.get( "components" ) ;
			for( int i = 0 ; i < arr.length() ; i++ ) {
				JSONObject cmp = arr.getJSONObject( i ) ;
				switch( ComponentType.valueOf( cmp.getString( "type" ) ) ) {
					case POSITION : {
						Position pos = new Position() ;
						pos.setX( cmp.getInt( "x" ) ) ;
						pos.setY( cmp.getInt( "y" ) ) ;
						entity.addComponent( pos ) ;
						break ;
					}
					case VELOCITY : {
						Velocity vel = new Velocity() ;
						vel.setxVel( cmp.getInt( "velX" ) ) ;
						vel.setyVel( cmp.getInt( "velY" ) ) ;
						entity.addComponent( vel ) ;
						break ;
					}
					case RENDER : {
						Render rend = new Render() ;
						rend.setCharacter( 
								cmp.getString( "character" ).charAt( 0 ) ) ;
						rend.setfColor( new TextColor.RGB(	cmp.getInt( "fr" ) ,
															cmp.getInt( "fg" ) ,
															cmp.getInt( "fb" ) )
								) ;
						rend.setbColor( new TextColor.RGB(	cmp.getInt( "br" ) ,
															cmp.getInt( "bg" ) ,
															cmp.getInt( "bb" ) )
								) ;
						entity.addComponent( rend ) ;
						break ;
					}
					case SOLID : {
						Solid sol = new Solid() ;
						sol.setSolid( cmp.getBoolean( "solid" ) ) ;
						entity.addComponent( sol ) ;
						break ;
					}
					case LIGHT : {
						Light light = new Light() ;
						light.setRadius( cmp.getInt( "radius" ) ) ;
						entity.addComponent( light ) ;
						break ;
					}
					default : {
						break ;
					}
				}
			}
		}
		catch( Exception e ) {
			Debug.logErr( "EntityLoader : load" , e ) ;
		}
		return entity ;
	}
	
}
