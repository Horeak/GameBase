package Utils;


import GameFiles.BaseGame;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GLContext;
import org.newdawn.slick.Image;

import java.io.InputStream;

public class ImageLoader {

	Class c;
	BaseGame game;

	public ImageLoader(BaseGame game ){
		c = game.getClass();
		this.game = game;
	}

	public Image getImage(String folder, String id ) {
		try {

			//TODO Find a way to create a OpenGL Context on the current thread or return null when there is no GLContext
			InputStream stream = c.getResourceAsStream(game.getTextureLocation() + "/" + folder + "/" + id + ".png");
			return new Image(stream, id, false);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
