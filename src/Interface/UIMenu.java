package Interface;

import org.newdawn.slick.Graphics;

import java.util.concurrent.CopyOnWriteArrayList;

public abstract class UIMenu {
	public CopyOnWriteArrayList<GuiObject> guiObjects = new CopyOnWriteArrayList<>();
	//Used to delay button actions when UI is first open to prevent clicking an button below the one that was used to open the UI
	public int buttonTimeLimiter = -1, buttonTimeLimit = 4;
	
	public UIMenu() {
		buttonTimeLimiter = 0;
	}
	
	public abstract void render( Graphics g2 );
	
	public abstract boolean canRender();
	
	public boolean renderOtherWindowsRenders() {
		return true;
	}
	
	public boolean overrideKeybindigs() {return true;}
	
	public void keyPressed( int key, char c ) {
	}
	
	public void keyReleased( int key, char c ) {
	}
	
	public void onMouseWheelMoved( int change ) {
		for (GuiObject ob : guiObjects) {
			if (ob.isMouseOver()) {
				ob.onMouseWheelMoved(change);
			}
		}
	}
	
	//TODO Improve this! Make it not require manual implemantion on renderObject override!
	public void update() {
		if (buttonTimeLimiter != -1 && buttonTimeLimiter < buttonTimeLimit) {
			buttonTimeLimiter += 1;
		}
	}
	
	public void renderObject( Graphics g2 ) {
		update();
		
		for (GuiObject object : guiObjects) {
			object.renderObject(g2, this);
		}
	}
	
	public void mouseClick( int button, int x, int y ) {
		if (buttonTimeLimiter == -1 || buttonTimeLimiter >= buttonTimeLimit) {
			for (GuiObject ob : guiObjects) {
				if (ob.isMouseOver()) {
					ob.onClicked(button, x, y, this);
				}
			}
		}
	}
	
	public void buttonPressed( GuiObject button ) {
	}
}
