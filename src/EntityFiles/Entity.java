package EntityFiles;

import World.WorldBase;
import org.newdawn.slick.Graphics;

public abstract class Entity {
	public float x, y;
	public WorldBase world;
	public int health = getEntityMaxHealth();
	
	public Entity( WorldBase world, float x, float y ) {
		this.x = x;
		this.y = y;
		
		this.world = world;
	}
	
	public int getEntityHealth() {return health;}
	
	public void setEntityHealth( int i ) {
		health = i;
	}
	
	
	public abstract int getEntityMaxHealth();
	
	public abstract String getEntityName();
	
	public abstract void renderEntity( Graphics g2, int renderX, int renderY );
	
}
