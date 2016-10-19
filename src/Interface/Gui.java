package Interface;

import Utilities.FontHandler;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Gui extends UIMenu {
	public HashMap<org.newdawn.slick.geom.Rectangle, String[]> toolTipRendering = new HashMap<>();
	public GameContainer container;
	
	public Gui( GameContainer container, boolean b ) {
		this.container = container;
		
		if (b) {
			this.container.setPaused(true);
		}
	}
	
	public void renderTooltip( int x, int y, int width, int height, String[] text ) {
		toolTipRendering.put(new Rectangle(x, y, width, height), text);
	}
	
	public void renderPost( Graphics g2 ) {
		
		int mouseX = container.getInput().getMouseX();
		int mouseY = container.getInput().getMouseY();
		
		
		g2.pushTransform();
		
		g2.scale(0.5F, 0.5F);
		g2.translate(mouseX - 16, mouseY - 16);
		
		g2.scale(2, 2);
		g2.popTransform();
		
		for (Map.Entry<org.newdawn.slick.geom.Rectangle, String[]> ent : toolTipRendering.entrySet()) {
			int x = (int) ent.getKey().getX(), y = (int) ent.getKey().getY();
			
			AffineTransform affinetransform = new AffineTransform();
			FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
			
			Font fontB = new Font("ARIAL", Font.BOLD, 10);
			Font fontP = new Font("ARIAL", Font.PLAIN, 10);
			
			float width = 0;
			float sHeight = (float) fontP.getStringBounds("t", frc).getHeight();
			
			ArrayList<String> ar = new ArrayList<>();
			
			int j = 0;
			for (String t : ent.getValue()) {
				float tt = 0;
				
				if (t != null) {
					if (j == 0) {
						tt = (float) fontB.getStringBounds(t, frc).getWidth();
					} else {
						tt = (float) fontP.getStringBounds(t, frc).getWidth();
					}
					
					if (tt > width) {
						width = tt;
					}
					
					j += 1;
					ar.add(t);
				}
			}
			
			width += 10;
			
			org.newdawn.slick.geom.Rectangle rect = new org.newdawn.slick.geom.Rectangle(ent.getKey().getX(), ent.getKey().getY(), width > ent.getKey().getWidth() ? width : ent.getKey().getWidth(), (sHeight * (ar.size())) + 10);
			
			g2.setColor(org.newdawn.slick.Color.lightGray);
			g2.fill(rect);
			
			g2.setColor(org.newdawn.slick.Color.black);
			g2.draw(rect);
			
			
			for (int g = 0; g < ar.size(); g++) {
				FontHandler.resizeFont(g2, 10);
				
				if (g == 0) {
					g2.setColor(org.newdawn.slick.Color.black);
					FontHandler.changeFontStyle(g2, Font.BOLD);
				} else {
					g2.setColor(org.newdawn.slick.Color.darkGray);
				}
				
				g2.drawString(ar.get(g), rect.getX() + 5, (rect.getY() + 5) + (g * sHeight));
				
				FontHandler.resetFont(g2);
			}
			
		}
		toolTipRendering.clear();
	}
}
