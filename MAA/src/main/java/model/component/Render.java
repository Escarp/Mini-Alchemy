package model.component;

import com.googlecode.lanterna.TextColor;

import util.ComponentUtils;

@SuppressWarnings( "serial" )
public class Render extends AComponent {
	TextColor fColor ;
	TextColor bColor ;
	char character ;
	
	//Getters
	public TextColor getfColor() {
		return fColor;
	}
	public TextColor getbColor() {
		return bColor;
	}
	public char getCharacter() {
		return character;
	}

	//Setters
	public void setfColor( TextColor fColor ) {
		this.fColor = fColor;
	}
	public void setbColor( TextColor bColor ) {
		this.bColor = bColor;
	}
	public void setCharacter( char character ) {
		this.character = character;
	}

	//Constructors
	public Render( String name ) {
		super( name );
		fColor = TextColor.ANSI.WHITE ;
		bColor = TextColor.ANSI.BLACK ;
		character = '?' ;
	}
	
	public Render() {
		this( ComponentUtils.RENDER ) ;
	}
	
	//Methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ( ( bColor == null ) ? 0 : bColor.hashCode() );
		result = prime * result + character;
		result = prime * result
				+ ( ( fColor == null ) ? 0 : fColor.hashCode() );
		return result;
	}
	
	@Override
	public boolean equals( Object obj ) {
		if( this == obj ) return true;
		if( ! super.equals( obj ) ) return false;
		if( getClass() != obj.getClass() ) return false;
		Render other = ( Render )obj;
		if( bColor == null ) {
			if( other.bColor != null ) return false;
		}
		else if( ! bColor.equals( other.bColor ) ) return false;
		if( character != other.character ) return false;
		if( fColor == null ) {
			if( other.fColor != null ) return false;
		}
		else if( ! fColor.equals( other.fColor ) ) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Render [fColor=" + fColor
				+ ", bColor="
				+ bColor
				+ ", character="
				+ character
				+ ", name="
				+ name
				+ "]";
	}
	
}
