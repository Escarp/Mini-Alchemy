package model.DAO.loader;

import java.net.URI;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.googlecode.lanterna.TextColor;

import control.Main;
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

	@Override
	public Entity load( String name ) {
		Entity entity = new Entity() ;
		try {
			//Modify the name and create url from it
			name = Main.class.getClassLoader().getResource( 
					"entities/" + name + ".json" ).toString() ;
			URI uri = new URI( name ) ;
			
			//Create tokener to build the JSONObject with
			JSONTokener tokener = new JSONTokener( uri.toURL().openStream() ) ;
			JSONObject json = new JSONObject( tokener ) ;
			
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
		
		Debug.logDebug( "loaded : " + entity + " \n"
				+ "\t\tfrom : " + name ) ;
		
		return entity ;
	}
	
}
