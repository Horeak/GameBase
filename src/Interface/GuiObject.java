package Interface;

import GameFiles.BaseGame;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.MouseOverArea;

public abstract class GuiObject extends MouseOverArea {

	public int x, y;
	public int width, height;
	public boolean enabled = true;
	public UIMenu menu;
	public BaseGame game;

	public GuiObject(BaseGame game, int x, int y, int width, int height, UIMenu menu ) {
		super(game.gameContainer, null, x, y, width, height);

		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;

		this.menu = menu;
		this.game = game;
	}

	public abstract void onClicked( int button, int x, int y, UIMenu menu );

	public void onMouseWheelMoved( int change ) {
	}

	public abstract void renderObject( Graphics g2, UIMenu menu );

	public boolean isMouseOver() {
		if (!(game.getCurrentMenu() instanceof Gui) && menu != null && (menu instanceof Gui)) {
			return false;
		}

		return super.isMouseOver();
	}
}
