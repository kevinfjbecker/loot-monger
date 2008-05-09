package palace.toolkit.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.util.Observable;

import palace.toolkit.visual.AbstractInteractive;


public class VisualTextLabel extends AbstractInteractive {

	private String _text = "";

	public VisualTextLabel(String text) {
		_text = text;
		_form = new Rectangle2D.Double();
		setHeight(14);
	}

	public void draw(Graphics2D g2d) {
		
		Font font = Font.decode("times-12");
		FontRenderContext frc = g2d.getFontRenderContext();
		TextLayout textLayout = new TextLayout(". " + _text + " .", font, frc);

		setWidth((int) textLayout.getBounds().getWidth() - 1);

		Color previousColor = g2d.getColor();
		g2d.setColor(Color.WHITE);
		g2d.fill(_form);
		g2d.setColor(previousColor);

		textLayout.draw(g2d, getX() - 1, getY() + getHeight() - 2);

		g2d.draw(_form);
	}

	public void update(Observable logicalScore, Object text) {
		_text = (String) text;
		setChanged();
		notifyObservers();
	}

}
