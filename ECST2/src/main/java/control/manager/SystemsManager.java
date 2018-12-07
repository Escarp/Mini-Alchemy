package control.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import model.entity.Entity;
import model.system.ASystem;
import model.system.GraphicsSystem;
import util.ButtonUtils;
import util.ComponentUtils;
import util.SystemsUtils.SystemType;
import view.Debug;
import view.GameTerminal;

public class SystemsManager {
	private HashMap<SystemType , ASystem> systems ;
	GameTerminal terminal ;
	
	public SystemsManager() {
		systems = new HashMap<>() ;
	}
	
	public void init() {
		try {
			terminal = new GameTerminal( "Test" , 50 , 30 ) ;
			systems.put(	SystemType.GRAPHICS , 
							new GraphicsSystem( terminal ) ) ;
		}
		catch( IOException e ) {
			Debug.logErr( "SystemManager : constructor" , e ) ;
		}
	}
	
	public void draw( ArrayList<Entity> entities ) {
		try{
			terminal.wipeScreen() ;
			if( systems.containsKey( SystemType.GRAPHICS ) ) {
				for( Entity entity : entities ){
					systems.get( SystemType.GRAPHICS ).update( 
							entity.getComponent( ComponentUtils.POSITION ) , 
							entity.getComponent( ComponentUtils.RENDER ) ) ;
				}
			}
			terminal.refreshScreen() ;
		}
		catch( IOException e ) {
			Debug.logErr( "SystemsManager : draw" , e ) ;
		}
	}
	
	public void stop() {
		try{
			terminal.stop() ;
		}
		catch( Exception e ) {
			Debug.logErr( "SystemsManager : stop" , e ) ;
		}
	}
	
	public boolean input() {
		boolean running = true ;
		
		try {
			KeyStroke input = terminal.getInput() ;
			
			if( ButtonUtils.areButtonsPressed( 
					input , KeyType.Escape , KeyType.EOF ) ) {
				running = false ;
			}
		}
		catch( IOException e ) {
			Debug.logErr( "SystemsManager : input" , e ) ;
		}
		
		return running ;
	}
}
