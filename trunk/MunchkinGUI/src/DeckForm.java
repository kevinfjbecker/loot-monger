import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class DeckForm {

    int card_z = 0; // used to figure out which card is on top of a pile

    double card_x = 10,
        card_y = 10,
        card_w = 50,
        card_h = 80,
        card_arc_w = 15,
        card_arc_h = 15,
        new_x,
        new_y;

    RoundRectangle2D cardForm =
        new RoundRectangle2D.Double(
            card_x,
            card_y,
            card_w,
            card_h,
            card_arc_w,
            card_arc_h);

    void draw(Graphics2D g2d) {

        g2d.setColor(Color.DARK_GRAY);
        for (int i = 6; i > 0; i -= 2) {
            cardForm.setFrame(card_x + i, card_y + i, card_w, card_h);
			g2d.draw(cardForm);
        }
        
        cardForm.setFrame(card_x, card_y, card_w, card_h);

        g2d.setColor(Color.WHITE);
        g2d.fill(cardForm);

        g2d.setColor(Color.DARK_GRAY);
        g2d.draw(cardForm);

        Font font = new Font("Times New Roman", Font.ITALIC, 12);
        FontRenderContext frc = g2d.getFontRenderContext();
        TextLayout layout = new TextLayout("deck", font, frc);

        Rectangle2D rect = layout.getBounds();

        double text_x = (card_w - rect.getWidth()) / 2 + card_x;
        double text_y = (card_h - rect.getHeight()) / 2 + card_y;

        layout.draw(g2d, (float) text_x, (float) text_y);

    }
}
