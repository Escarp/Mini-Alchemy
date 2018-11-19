package util.physics;

public class AbstractVector2<T> {
	protected T x ;
	protected T y ;
	protected T value ;	
	protected T magnitude ;
	
	//Getters
	public T getX() {
		return x;
	}

	public T getY() {
		return y;
	}

	public T getValue() {
		return value;
	}

	public T getMagnitude() {
		return magnitude;
	}
	
	//Setters
	public void setX(T x) {
		this.x = x;
	}

	public void setY(T y) {
		this.y = y;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public void setMagnitude(T magnitude) {
		this.magnitude = magnitude;
	}

	//Constructors
	public AbstractVector2( T x , T y ){
		this.x = x ;
		this.y = y ;
	}
}
