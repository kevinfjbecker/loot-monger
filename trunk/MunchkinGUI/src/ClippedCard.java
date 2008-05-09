import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JPanel;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;

@SuppressWarnings("serial")
public class ClippedCard
    extends JPanel
    implements MouseListener, MouseMotionListener {

    double mouse_x = Double.MIN_VALUE;
    double mouse_y = Double.MIN_VALUE;

    JpegCard cardOne;
    JpegCard cardTwo;

    public ClippedCard() {
        cardOne = new JpegCard("Munchcard2.jpg");
        cardTwo = new JpegCard("Munchcard3.jpg");
    }

    class JpegCard {

        private boolean isCardGrabbed = false;

        private BufferedImage bi1 = null;
        private RoundRectangle2D cardForm;
        private double card_x,
            card_y,
            card_w,
            card_h,
            card_arc_w,
            card_arc_h;

        public JpegCard(String imageName) {
            loadImage(imageName);

            card_x = 10;
            card_y = 10;
            card_w = bi1.getWidth() / 2;
            card_h = bi1.getHeight() / 2;
            card_arc_w = 20;
            card_arc_h = 20;

            cardForm =
                new RoundRectangle2D.Double(
                    card_x,
                    card_y,
                    card_w,
                    card_h,
                    card_arc_w,
                    card_arc_h);

        }

        private void loadImage(String imageName) {
            try {
                File file = new File(imageName);
                FileInputStream in = new FileInputStream(file);
                JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(in);
                bi1 = decoder.decodeAsBufferedImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (bi1 == null) {
                System.err.println("decodeAsBufferedImage=null");
                return;
            }
        }

        public void draw(Graphics2D g2, ImageObserver imageObserver) {
            g2.clip(cardForm);
            g2.drawImage(
                bi1,
                (int) card_x - 15,
                (int) card_y - 15,
                (int) card_w + 30,
                (int) card_h + 30,
                imageObserver);
            g2.setClip(new Rectangle(0, 0, getWidth(), getHeight()));
            g2.setColor(Color.green);
            g2.draw(cardForm);
        }

    }

    protected void clear(Graphics g) {
        super.paintComponent(g);
    }

    public void paintComponent(Graphics g) {
        clear(g);
        Graphics2D g2 = (Graphics2D) g;

        cardTwo.draw(g2, this);
        cardOne.draw(g2, this);
    }

    public void hookListeners() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public static void main(String[] args) {
        ClippedCard cc = new ClippedCard();
        Frame f = new Frame("  blah");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        cc.setBackground(Color.white);
        cc.hookListeners();
        f.add("Center", cc);
        f.pack();
        f.setSize(new Dimension(300, 500));
        f.setVisible(true);
    }

    //	///////////////////////////////////////////////////////////////////

    public void mouseClicked(MouseEvent arg0) {
    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseExited(MouseEvent arg0) {
    }

    public void mousePressed(MouseEvent me) {
        mouse_x = me.getX();
        mouse_y = me.getY();
        if (cardOne.cardForm.contains(mouse_x, mouse_y))
            cardOne.isCardGrabbed = true;
    }

    public void mouseReleased(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        if (cardOne.isCardGrabbed) {
            cardOne.card_x += x - mouse_x;
            cardOne.card_y += y - mouse_y;
            cardOne.cardForm.setFrame(
                cardOne.card_x,
                cardOne.card_y,
                cardOne.card_w,
                cardOne.card_h);
            repaint();
        }
        mouse_x = x;
        mouse_y = y;
        cardOne.isCardGrabbed = false;
    }

    public void mouseDragged(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        if (cardOne.isCardGrabbed) {
            cardOne.card_x += x - mouse_x;
            cardOne.card_y += y - mouse_y;
            cardOne.cardForm.setFrame(
                cardOne.card_x,
                cardOne.card_y,
                cardOne.card_w,
                cardOne.card_h);
            repaint();
        }
        mouse_x = x;
        mouse_y = y;
    }

    public void mouseMoved(MouseEvent arg0) {
    }
}
