import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

@SuppressWarnings("serial")
class CardGUI extends JPanel implements MouseListener, MouseMotionListener {

    public CardGUI() {
        super();
        cards = new ArrayList();        
        deck = new DeckForm();
    }

    private double mouse_x = Double.MIN_VALUE;
    private double mouse_y = Double.MIN_VALUE;

    ArrayList<CardForm> cards;
    DeckForm deck;
    
    public void paintComponent(Graphics g) {
        clear(g);
        Graphics2D g2d = (Graphics2D) g;

        deck.draw(g2d);

        for (int i = 0; i < cards.size(); i++) {
            ((CardForm) cards.get(i)).draw(g2d);
        }

    }

    protected void clear(Graphics g) {
        // super.paintComponent clears offscreen pixmap,
        // since we're using double buffering by default.
        super.paintComponent(g);
    }

    public void hookListeners() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public static void main(String s[]) {
        CardGUI se = new CardGUI();
        Frame f = new Frame("  card-move");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        se.setBackground(Color.white);
        se.hookListeners();
        f.add("Center", se);
        f.pack();
        f.setSize(new Dimension(640, 480));
        f.setVisible(true);
    }

    /////////////////////////////////////////////////////////////////////

    public void mouseClicked(MouseEvent arg0) {
		if (deck.cardForm.contains(mouse_x, mouse_y)) {
			cards.add(new CardForm());
		}
		repaint();
    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseExited(MouseEvent arg0) {
    }

    public void mousePressed(MouseEvent me) {
        mouse_x = me.getX();
        mouse_y = me.getY();

        for (int i = cards.size() - 1; i >= 0; i--) {
            CardForm card = (CardForm) cards.get(i);
            if (card.cardForm.contains(mouse_x, mouse_y)) {
                card.isCardGrabbed = true;
                card.card_z = 1000;
                break;
            }
        }
        Collections.sort(cards);
        repaint();
    }

    public void mouseReleased(MouseEvent me) {
        boolean placedOnTopOfAnotherCard = false;
        int x = me.getX();
        int y = me.getY();
        for (int i = cards.size() - 1; i >= 0; i--) {
            CardForm card = (CardForm) cards.get(i);
            if (card.isCardGrabbed) {
                card.isCardGrabbed = false;
                for (int k = cards.size() - 1; k >= 0; k--) {
                    CardForm c = (CardForm) cards.get(k);
                    if (card.intersects(c) && c != card) {
                        card.card_z = c.card_z + 1;
                        placedOnTopOfAnotherCard = true;
                        break;
                    }
                }
                if (!placedOnTopOfAnotherCard) {
                    card.card_z = 0;
                }
            }
        }
        mouse_x = x;
        mouse_y = y;
        Collections.sort(cards);
        repaint();
    }

    public void mouseDragged(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        for (int i = cards.size() - 1; i >= 0; i--) {
            CardForm card = (CardForm) cards.get(i);
            if (card.isCardGrabbed) {
                card.move(x - mouse_x, y - mouse_y);
                card.cardForm.setFrame(
                    card.card_x,
                    card.card_y,
                    card.card_w,
                    card.card_h);
            }
        }
        mouse_x = x;
        mouse_y = y;
        repaint();
    }

    public void mouseMoved(MouseEvent arg0) {
    }
}