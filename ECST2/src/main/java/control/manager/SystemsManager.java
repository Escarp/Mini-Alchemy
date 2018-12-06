package control.manager;

import java.io.IOException;
import java.util.HashMap;

import model.system.ASystem;
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
			terminal = new GameTerminal( "Test" , 50 , 50 ) ;
		}
		catch( IOException e ) {
			Debug.logErr( "SystemManager : constructor" , e ) ;
		}
	}
}
