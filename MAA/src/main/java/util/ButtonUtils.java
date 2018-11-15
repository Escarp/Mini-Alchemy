package util;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public final class ButtonUtils {
	public static boolean isButtonPressed( KeyStroke input , KeyType type ) {
		return input.equals( new KeyStroke( type ) ) ;
	}
	
	public static boolean isButtonPressed( 
			KeyStroke input , 
			Character character ) {
		return input.equals( 
				new KeyStroke( 
						character , 
						input.isCtrlDown() , 
						input.isAltDown() ,
						input.isShiftDown() ) ) ;
	}
	
	public static boolean isButtonPressed( 
			KeyStroke input , 
			Character character ,
			boolean isCtrlDown ,
			boolean isShiftDown ,
			boolean isAltDown ) {
		return input.equals( 
				new KeyStroke( 
						character , 
						isCtrlDown , 
						isAltDown ,
						isShiftDown ) ) ;
	}
	
	public static boolean areButtonsPressed( 
			KeyStroke input , 
			KeyType... types ){
		boolean pressed = false ;
		
		for( KeyType type : types ){
			pressed = isButtonPressed( input , type ) ;
			if( pressed ){
				break ;
			}
		}
		
		return pressed ;
	}
	
	public static boolean areButtonsPressed( 
			KeyStroke input , 
			Character... characters ){
		boolean pressed = false ;
		
		for( Character character : characters ){
			pressed = isButtonPressed( input , character ) ;
			if( pressed ){
				break ;
			}
		}
		
		return pressed ;
	}
	
	public static boolean areButtonsPressed( 
			KeyStroke input , 
			boolean isCtrlDown ,
			boolean isShiftDown ,
			boolean isAltDown ,
			Character... characters ) {
		boolean pressed = false ;
		
		for( Character character : characters ){
			pressed = isButtonPressed( 
					input , 
					character , 
					isCtrlDown , 
					isShiftDown , 
					isAltDown ) ;
			if( pressed ){
				break ;
			}
		}
		
		return pressed ;
	}
	
}
