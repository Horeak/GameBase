package GameFiles;

import Interface.Gui;
import Interface.UIMenu;
import Rendering.AbstractWindowRender;
import Settings.Config;
import Settings.Values.Keybinding;
import Utilities.FontHandler;
import Utilities.ImageLoader;
import Utilities.SaveUtils;
import org.newdawn.slick.*;

public abstract class BaseGame extends BasicGame implements InputListener {
	public ImageLoader imageLoader = new ImageLoader(this);
	public SaveUtils saveUtil = new SaveUtils(this);
	public AppGameContainer gameContainer;
	public String title;
	boolean firstRun = true;
	private UIMenu currentMenu = null;
	
	public BaseGame( String title, int xSize, int ySize, boolean fullscreen ) {
		super(title);
		
		this.title = title;
		
		try {
			gameContainer = new AppGameContainer(this);
			gameContainer.setDisplayMode(xSize, ySize, fullscreen);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void init( GameContainer container ) throws SlickException {
	//	LoggerUtil.activate(title);
		
		if (getConfig() != null) {
			getConfig().loadConfig(this, "config/");
		}
		
		loadGame();
		
		container.getInput().addMouseListener(new MouseListener() {
			public void mouseWheelMoved( int change ) {
				if (currentMenu != null) {
					currentMenu.onMouseWheelMoved(change);
				}
				
			}
			
			public void mouseClicked( int button, int x, int y, int clickCount ) {
			}
			
			public void mousePressed( int button, int x, int y ) {
				for (AbstractWindowRender render : getAbstractWindowRenderers()) {
					if (currentMenu == null || render.canRenderWithWindow()) {
						render.mouseClick(button, x, y);
					}
				}
				
				if (currentMenu != null) {
					currentMenu.mouseClick(button, x, y);
				}
			}
			
			public void mouseReleased( int button, int x, int y ) {
			}
			
			public void mouseMoved( int oldx, int oldy, int newx, int newy ) {
			}
			
			public void mouseDragged( int oldx, int oldy, int newx, int newy ) {
			}
			
			public void setInput( Input input ) {
			}
			
			public boolean isAcceptingInput() {
				return true;
			}
			
			public void inputEnded() {
			}
			
			public void inputStarted() {
			}
		});
		
		initGame(container);
	}
	
	@Override
	public void update( GameContainer container, int delta ) throws SlickException {
		updateGame(container, delta);
	}
	
	@Override
	public void render( GameContainer container, Graphics g2 ) throws SlickException {
		//TODO This might be what is causing the delay on mac!
		if ((g2.getFont() instanceof AngelCodeFont)) {
			g2.setFont(FontHandler.getFont(new java.awt.Font("Arial", 0, 0)));
		}
		
		for (AbstractWindowRender render : getAbstractWindowRenderers()) {
			if ((currentMenu instanceof Gui) || currentMenu == null || render.canRenderWithWindow()) {
				if (render.canRender()) {
					render.render(g2);
				}
			}
		}
		
		
		if (currentMenu != null) {
			if (currentMenu.canRender()) {
				currentMenu.render(g2);
				currentMenu.renderObject(g2);
				
				if (currentMenu instanceof Gui) {
					((Gui) currentMenu).renderPost(g2);
				}
			}
		}
		
		
		renderGame(container, g2);
	}
	
	@Override
	public boolean closeRequested() {
		if (getConfig() != null) {
			getConfig().saveConfig(this, "config/");
		}
		
		closeGame();
		return true;
	}
	
	@Override
	public void keyPressed( int key, char c ) {
		if (currentMenu != null && currentMenu.overrideKeybindigs()) {
			currentMenu.keyPressed(key, c);
			
			//TODO Fix! Needed for TD_Game but makes closing guis in TestGame break
			//if(currentMenu != null && currentMenu.overrideKeybindigs())
			return;
		}
		
		for (Keybinding keybind : getConfig().getKeybindings()) {
			if (keybind != null && keybind.getAction() != null) {
				if (keybind.getKey() == key) {
					keybind.getAction().performAction();
					return;
				}
			}
		}
	}
	
	@Override
	public void keyReleased( int key, char c ) {
		if (currentMenu != null) {
			currentMenu.keyReleased(key, c);
		}
	}
	
	public abstract Config getConfig();
	
	/**
	 * The resource location from the main game class
	 * Textures are loaded as .png
	 *
	 * @return the path as a string
	 */
	public abstract String getTextureLocation();
	
	public abstract String getFilesSaveLocation();
	
	public abstract AbstractWindowRender[] getAbstractWindowRenderers();
	
	public abstract void initGame( GameContainer container ) throws SlickException;
	
	public abstract void updateGame( GameContainer container, int delta ) throws SlickException;
	
	public abstract void renderGame( GameContainer container, Graphics g2 ) throws SlickException;
	
	public abstract void loadGame();
	
	public abstract void closeGame();
	
	public UIMenu getCurrentMenu() {
		return currentMenu;
	}
	
	public void setCurrentMenu( UIMenu currentMenu ) {
		this.currentMenu = currentMenu;
		//		LoggerUtil.out.log(Level.FINE, "currentMenu was changed to: " + currentMenu);
	}
}
