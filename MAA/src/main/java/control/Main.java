package control;

import control.manager.MainManager;
import view.Debug; 

public class Main {
	
	/*
	 * To-do list :
	 * 
	 * DONE : Player
	 * DONE : Maps
	 * TODO : Camera		( to	re-adapt from master )
	 * TODO : FOV			( to	copy from master )
	 * TODO : Path finding	( 1%	done )
	 * TODO : Monsters		( 80%	done )
	 * TODO : Combat		( 10%	done )
	 * TODO : Items			( 80%	done )
	 * TODO : GUI			( 20%	done )
	 * TODO : Saving		( 50%	done )
	 * 
	 * also remove the tile class and use entities?
	 */
	
	public static void main( String[] args ){
		//One manager to rule them all
		MainManager mm = new MainManager() ;
		boolean running = true ;
		
		//Main loop
		Debug.logln( "MainLoop : [Start]" );
		do {
			//Update
			mm.update() ;
			
			//Draw
			mm.draw() ;
			
			//Get input ( also stops the loop )
			running = mm.input() ;
		}
		while( running ) ;
		//End of the road
		mm.stop() ;
		Debug.logln( "MainLoop : [End]" );
	}
}
