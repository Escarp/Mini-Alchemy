package view;

public class Debug {
	public static void log( String msg ) {
		System.out.print( msg ) ;
	}
	
	public static void logln( String msg ) {
		System.out.println( msg ) ;
	}
	
	public static void logln() {
		System.out.println() ;
	}
	
	public static void logErr( String name , Exception e ) {
		logln( name + ": Error: " + e.getMessage() ) ;
		log( "Stack: " ) ;
		for( int i = 0 ; i < e.getStackTrace().length ; i++ ) {
			logln( "\t" + e.getStackTrace()[i] ) ;
		}
	}
}
