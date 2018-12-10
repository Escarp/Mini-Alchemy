package control;

import control.manager.MainManager;
import view.Debug;

public class Main {
	public static void main( String[] args ){
		boolean running = true ;
		MainManager mm = new MainManager() ;
		
		Debug.logln( "MainLoop : [Start]" );
		while( running ){
			mm.update() ;
			
			mm.draw() ;
			
			running = mm.input() ;
		}
		mm.stop() ;
		Debug.logln( "MainLoop : [End]" );
	}
}
