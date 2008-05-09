import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JPanel;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;

@SuppressWarnings("serial")
public class DrawCard extends JPanel {

    private static BufferedImage bi1 = null;

    public DrawCard() {
        loadImage();
    }

    private void loadImage() {
        try {
            File file = new File("Munchcard2.jpg");
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

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
		AffineTransform at = AffineTransform.getScaleInstance(0.5,0.5) ;
        g2.setTransform(at);
        g2.drawImage(bi1, 0, 0, this);

    }

    public static void main(String s[]) {
        DrawCard dc = new DrawCard();
        Frame f = new Frame("  card-jpg");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.add("Center", dc);
        f.pack();
        f.setSize(new Dimension(190, 310));
        f.setVisible(true);
    }
}
