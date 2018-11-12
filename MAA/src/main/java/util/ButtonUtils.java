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
	
}
