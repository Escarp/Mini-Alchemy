package view;

public class Debug {
	public static void log( String msg ) {
		System.out.print( msg ) ;
	}
	
	public static void logln( String msg , boolean debug ) {
		if( debug ){
			msg = "[Debug] : " + msg ;
			System.out.println( msg ) ;
		}
	}
	
	public static void logln( String msg ) {	
		System.out.println( msg ) ;
	}
	
	public static void logDebug( String msg ) {	
		logln( "[DEBUG] : " + msg ) ;
	}
	
	public static void logErr( String name , Exception e ) {
		logln( "[Error] : " + name + " : " + e.getMessage() ) ;
		log( "[Stack] : \n" ) ;
		for( int i = 0 ; i < e.getStackTrace().length ; i++ ) {
			logln( "\t" + e.getStackTrace()[i] ) ;
		}
		logln( "[Stack] : [End]" ) ;
	}
}
