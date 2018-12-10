package model.system;

import model.component.AComponent;
import model.component.Position;
import model.component.Render;
import util.SystemsUtils.SystemType;
import view.GameTerminal;

public class GraphicsSystem extends ASystem {
	GameTerminal gameTerminal ;
	
	public GraphicsSystem( String name , SystemType type ) {
		super( name , type );
	}
	
	public GraphicsSystem( GameTerminal gameterminal ) {
		super( "Graphics" , SystemType.GRAPHICS ) ;
		this.gameTerminal = gameterminal ;
	}

	@Override
	public void update( AComponent... components ) {
		Position position = null ;
		Render render = null ;
		for( AComponent component : components ){
			//Get components from varArgs
			if( component instanceof Position ){
				position = ( Position )component ;
			}
			else if( component instanceof Render ) {
				render = ( Render )component ;
			}
		}
		if( position != null && render != null ){
			//Draw
			gameTerminal.drawRenderAt( position , render ) ;
		}
	}
	
}
