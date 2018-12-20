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
	private HashMap<EntityType , Entity> entities ;
	public static final String FOLDER = "entities" ;
	
	//Getters
	public HashMap<EntityType , Entity> getEntities() {
		return entities;
	}

	//Setters
	public void setEntities( HashMap<EntityType , Entity> entities ) {
		this.entities = entities;
	}
	
	public EntityLoader() {
		entities = new HashMap<EntityType , Entity>() ;
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
						Entity entity = load( "/" + name ) ;
						entities.put( entity.getType() , entity ) ;
					}
				}
				jar.close() ;
			}
			else {
				final URL url = getClass().getResource( "/" + FOLDER ) ;
				if( url != null ) {
					final File files = new File( url.toURI() ) ;
					for( File f : files.listFiles() ) {
						Entity entity = load( 
								"/" + FOLDER + "/" + f.getName() ) ;
						entities.put( entity.getType() , entity ) ;
					}
				}
			}
		}
		catch( Exception e ) {
			Debug.logErr( "EntityLoader : constructor" , e );
		}
		
		
		//loadAll() ;
	}
	
//	public void loadAll() {
//		InputStreamReader isr = new InputStreamReader( 
//				folder , StandardCharsets.UTF_8 ) ;
//		BufferedReader br = new BufferedReader( isr ) ;
//		
//		Entity entity = null ;
//		String s = null ;
//		try {
//			while( ( s = br.readLine() ) != null ) {
//				entity = load( s ) ;
//				entities.put( entity.getType() , entity ) ;
//			}
//			br.close() ;
//			isr.close() ;
//			folder.close() ;
//		}
//		catch( Exception e ) {
//			Debug.logErr( "EntityLoader : loadAll" , e ) ;
//		}
//		
//		Debug.logDebug( 
//				"EntityLoader : loaded " + entities.size() + " entities" ) ;
//	}
	
	@Override
	public Entity load( String name ) {
		Entity entity = new Entity() ;
		try {
			InputStream is = getClass().getResourceAsStream( name ) ;
			Debug.logDebug( "is:" + is.toString() ) ;
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
		
		Debug.logDebug( "EntityLoader : loaded : " + entity + " \n"
				+ "\t\t\t from : " + name ) ;
		
		return entity ;
	}
	
}
