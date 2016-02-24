package Utils;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.font.effects.ColorEffect;

import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;

public class FontHandler {

	public static HashMap<Graphics, Font> tempFontStore = new HashMap<>();
	public static HashMap<Font, UnicodeFont> store = new HashMap<>();

	//This is made because of IDE specific features related to java.awt.Color which is not compatible with org.newdawn.slick.Color
	public static org.newdawn.slick.Color getColorToSlick( Color c ) {
		return new org.newdawn.slick.Color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
	}


	//Overcomplicated font system because of Slick2D having to remake everything....
	public static UnicodeFont getFont( Font font ) {
		if (font != null) {

			if (!store.containsKey(font)) {
				UnicodeFont fontt = new UnicodeFont(font);

				try {
					//TODO This is take alot of time!
					fontt.getEffects().add(new ColorEffect(Color.WHITE));
				} catch (Exception e) {
					e.printStackTrace();
				}
				store.put(font, fontt);
				return fontt;
			} else {
				//TODO Might have to add Mac specific loading time here! Mac has up towards of 1800ms loading while windows has around 800ms
				try {
					store.get(font).loadGlyphs(20);
				} catch (SlickException e) {
					e.printStackTrace();
				}

				return store.get(font);
			}
		}

		return null;
	}

	public static void setFont( Graphics g2, org.newdawn.slick.Font font ) {
		g2.setFont(font);
	}

	public static Font getFontFromSlick( org.newdawn.slick.Font font ) {
		if (font instanceof UnicodeFont) {
			UnicodeFont font1 = (UnicodeFont) font;

			try {
				Field field = font.getClass().getDeclaredField("font");
				field.setAccessible(true);

				Font ft = (Font) field.get(font1);

				return ft;

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return null;
	}

	public static void resizeFont( Graphics g2, int size ) {
		Font temp = getFontFromSlick(g2.getFont());

		if (temp != null) {
			if (tempFontStore.get(g2) == null) tempFontStore.put(g2, temp);

			Font fnt = new Font(temp.getName(), temp.getStyle(), size);
			setFont(g2, getFont(fnt));
		}
	}

	public static void changeFontStyle( Graphics g2, int style ) {
		Font temp = getFontFromSlick(g2.getFont());

		if (temp != null) {
			if (tempFontStore.get(g2) == null) tempFontStore.put(g2, temp);

			Font fnt = new Font(temp.getName(), style, temp.getSize());
			setFont(g2, getFont(fnt));
		}
	}

	public static void changeFontName( Graphics g2, String name ) {
		Font temp = getFontFromSlick(g2.getFont());

		if (temp != null) {
			if (tempFontStore.get(g2) == null) tempFontStore.put(g2, temp);

			Font fnt = new Font(name, temp.getStyle(), temp.getSize());
			setFont(g2, getFont(fnt));
		}
	}


	public static void resetFont( Graphics g2 ) {
		Font temp = tempFontStore.get(g2);

		if (temp != null) setFont(g2, getFont(temp));
	}

}
