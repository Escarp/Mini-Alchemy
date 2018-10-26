package control;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class Main {

	public static void main(String[] args) {
		DefaultTerminalFactory defaultTerminalFactory = 
				new DefaultTerminalFactory() ;
		Terminal terminal = null ;
		try{
			terminal = defaultTerminalFactory.createTerminal() ;
					
			terminal.setBackgroundColor(TextColor.ANSI.BLUE);
		    terminal.setForegroundColor(TextColor.ANSI.YELLOW);
		    
		    terminal.putCharacter( 'H' ) ;
		    terminal.putCharacter( 'e' ) ;
		    terminal.putCharacter( 'l' ) ;
		    terminal.putCharacter( 'l' ) ;
		    terminal.putCharacter( 'o' ) ;
		    terminal.putCharacter( ' ' ) ;
		    terminal.putCharacter( 'W' ) ;
		    terminal.putCharacter( 'o' ) ;
		    terminal.putCharacter( 'r' ) ;
		    terminal.putCharacter( 'l' ) ;
		    terminal.putCharacter( 'd' ) ;
		    terminal.putCharacter( '!' ) ;
		    terminal.putCharacter( '\n' ) ;
		    
		    terminal.flush() ;
		    
		    Thread.sleep( 2000 ) ;
		    
		}
		catch( Exception e ){
			e.printStackTrace() ;
		}
		finally {
		    if( terminal != null ) {
		        try {
		        	terminal.close() ;
		        }
		        catch( Exception e ){
		        	e.printStackTrace() ;
		        }
		    }
		}
	}

}
