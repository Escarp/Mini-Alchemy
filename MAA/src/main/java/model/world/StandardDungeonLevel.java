package model.world;

public class StandardDungeonLevel extends AbstractLevel {
	private int averageRoomWidth ;
	private int averageRoomHeight ;
	
	//Getters
	public int getAverageRoomWidth() {
		return averageRoomWidth;
	}

	public int getAverageRoomHeight() {
		return averageRoomHeight;
	}

	//Setters
	public void setAverageRoomWidth(int averageRoomWidth) {
		this.averageRoomWidth = averageRoomWidth;
	}

	public void setAverageRoomHeight(int averageRoomHeight) {
		this.averageRoomHeight = averageRoomHeight;
	}

	//Constructors
	public StandardDungeonLevel(){
		super() ;
		averageRoomWidth = 5 ;
		averageRoomHeight = 5 ;
	}
	
	public StandardDungeonLevel( int averageRoomWidth , int averageRoomHeight ){
		this() ;
		this.averageRoomWidth = averageRoomWidth ;
		this.averageRoomHeight = averageRoomHeight ;
	}
	
	//Methods
	@Override
	public void generateMap( int rows , int cols ) {
		
	}
}
