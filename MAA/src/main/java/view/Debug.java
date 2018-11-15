package view;

public class Debug {
	public static void log( String msg ) {
		System.out.print( msg ) ;
	}
	
	public static void logln( String msg , boolean debug ) {
		if( debug ){
			msg = "[Debug] : " + msg ;
		}
		System.out.println( msg ) ;
	}
	
	public static void logErr( String name , Exception e ) {
		logln( "[Error] : " + name + ": " + e.getMessage() , false ) ;
		log( "[Stack] : \n" ) ;
		for( int i = 0 ; i < e.getStackTrace().length ; i++ ) {
			logln( "\t" + e.getStackTrace()[i] , false ) ;
		}
		logln( "[Stack] : [End]" , false ) ;
	}
}
