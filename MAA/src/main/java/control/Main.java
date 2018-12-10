package control;

import control.manager.MainManager;
import view.Debug;

public class Main {
	public static void main( String[] args ){
		boolean running = true ;
		//One manager to rule them all
		MainManager mm = new MainManager() ;
		
		//Main loop
		Debug.logln( "MainLoop : [Start]" );
		while( running ){
			//Update
			mm.update() ;
			
			//Draw
			mm.draw() ;
			
			//Get input ( also stops the loop )
			running = mm.input() ;
		}
		//End of the road
		mm.stop() ;
		Debug.logln( "MainLoop : [End]" );
	}
}
