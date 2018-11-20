package util;

public class KeySet {
	private int key1 ;
	private int key2 ;
	
	//Getters
	public int getKey1() {
		return key1;
	}
	public int getKey2() {
		return key2;
	}
	
	//Setters
	public void setKey1(int key1) {
		this.key1 = key1;
	}
	public void setKey2(int key2) {
		this.key2 = key2;
	}
	
	//Constructors
	public KeySet(){
	}
	
	public KeySet( int key1 , int key2 ){
		this.key1 = key1 ;
		this.key2 = key2 ;
	}
	
	//Methods
	@Override
	public boolean equals(Object obj) {
		KeySet other = (KeySet) obj;
		return key1 == other.key1 && key2 == other.key2 ;
	}
	
	@Override
	public int hashCode() {
		int result = key1 ;
		result = 31 * result + key2 ;
		return result ;
	}
	
	@Override
	public String toString() {
		return "KeySet [key1=" + key1 + ", key2=" + key2 + "]";
	}
	
	
}
