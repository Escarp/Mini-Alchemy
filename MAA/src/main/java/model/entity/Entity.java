package model.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

import model.component.AComponent;
import util.ComponentUtils.ComponentType;
import util.EntityUtils.EntityType;

@SuppressWarnings( "serial" )
public class Entity implements Serializable {
	private String id ;
	private HashMap<ComponentType , AComponent> components ;
	private EntityType type ;

	//Getters
	public String getId() {
		return id;
	}
	public HashMap<ComponentType , AComponent> getComponents(){
		return components ;
	}
	public EntityType getType() {
		return type ;
	}

	//Setters
	public void setId( String id ) {
		this.id = id;
	}
	public void setComponents( 
			HashMap<ComponentType , AComponent> components ) {
		this.components.putAll( components ) ;
	}
	public void setType( EntityType type ){
		this.type = type ;
	}
	
	//Constructors
	public Entity() {
		id = UUID.randomUUID().toString() ;
		components = new HashMap<>() ;
		type = EntityType.DEFAULT ;
	}
	
	public Entity( String id ) {
		this.id = id ;
		components = new HashMap<>() ;
		type = EntityType.DEFAULT ;
	}
	
	public Entity( String id , EntityType type ) {
		this( id ) ;
		this.type = type ;
	}

	//Methods
	public void addComponent( AComponent... components  ) {
		for( AComponent component : components ) {
			if( component != null ){
				this.components.put( component.getType() , component ) ;
			}
		}
	}
	
	public void removeComponent( ComponentType type ) {
		if( components.containsKey( type ) ){
			components.remove( type ) ;
		}
	}
	
	public AComponent getComponent( ComponentType type ) {
		return components.get( type ) ;
	}
	
	public boolean hasComponent( ComponentType type ) {
		return components.containsKey( type ) ;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ( ( components == null ) ? 0 : components.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( type == null ) ? 0 : type.hashCode() );
		return result;
	}
	
	@Override
	public boolean equals( Object obj ) {
		if( this == obj ) return true;
		if( obj == null ) return false;
		if( getClass() != obj.getClass() ) return false;
		Entity other = ( Entity )obj;
		if( components == null ) {
			if( other.components != null ) return false;
		}
		else if( ! components.equals( other.components ) ) return false;
		if( id == null ) {
			if( other.id != null ) return false;
		}
		else if( ! id.equals( other.id ) ) return false;
		if( type != other.type ) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Entity [id=" + id
				+ ", type="
				+ type
				+ ", components="
				+ components
				+ "]";
	}

}
