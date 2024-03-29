/*
 * @(#)Arcs.java	1.5	98/12/03
 *
 * Copyright 1998 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;

import javax.swing.JApplet;

/**
 * The Arcs class demonstrates the three closure types for arcs, 
 * Arc2D Open, Chord & Pie.   Animated Pie Arc.
 */
@SuppressWarnings("serial")
public class Arcs extends JApplet implements Runnable {

    // array containing arc closure types.
    private static String types[] =
        { "Arc2D.CHORD", "Arc2D.OPEN", "Arc2D.PIE" };

    // indicates that animated arc's mouth is closed.
    private static final int CLOSE = 0;

    // indicates that animated arc's mouth is open.
    private static final int OPEN = 1;

    // indicate direction of animated arc. 
    private static final int FORWARD = 0;
    private static final int BACKWARD = 1;
    private static final int DOWN = 2;
    private static final int UP = 3;

    private Thread thread;
    private BufferedImage bimg;
    private int aw, ah; // animated arc width & height
    private int x, y; // position of animated arc 
    private int angleStart = 45; //  the starting angle of the arc
    private int angleExtent = 270; //  the extent of the angle of the arc
    private int mouth = CLOSE;
    private int direction = FORWARD;

    public void init() {
        setBackground(Color.white);
    }

    // Resets the position and size of the animated arc
    public void reset(int w, int h) {
        x = 0;
        y = 0;
        aw = w / 12;
        ah = h / 12;
    }

    public void step(int w, int h) {
        // computes the  direction of the animated arc
        if (x + aw >= w - 5 && direction == FORWARD)
            direction = DOWN;
        if (y + ah >= h - 5 && direction == DOWN)
            direction = BACKWARD;
        if (x - aw <= 5 && direction == BACKWARD)
            direction = UP;
        if (y - ah <= 5 && direction == UP)
            direction = FORWARD;

        // computes the angle start and extent
        if (mouth == CLOSE) {
            angleStart -= 5;
            angleExtent += 10;
        }
        if (mouth == OPEN) {
            angleStart += 5;
            angleExtent -= 10;
        }
        if (direction == FORWARD) {
            x += 5;
            y = 0;
        }
        if (direction == DOWN) {
            x = w;
            y += 5;
        }
        if (direction == BACKWARD) {
            x -= 5;
            y = h;
        }
        if (direction == UP) {
            x = 0;
            y -= 5;
        }
        if (angleStart == 0)
            mouth = OPEN;
        if (angleStart > 45)
            mouth = CLOSE;
    }

    public void drawDemo(int w, int h, Graphics2D g2) {

        g2.setStroke(new BasicStroke(5.0f));

        // draw arcs
        for (int i = 0; i < types.length; i++) {
            Arc2D arc = new Arc2D.Float(i);
            arc.setFrame((i + 1) * w * .2, (i + 1) * h * .2, w * .17, h * .17);
            arc.setAngleStart(45);
            arc.setAngleExtent(270);
            g2.setColor(Color.blue);
            g2.draw(arc);
            g2.setColor(Color.gray);
            g2.fill(arc);
            g2.setColor(Color.black);
            g2.drawString(
                types[i],
                (int) ((i + 1) * w * .2),
                (int) ((i + 1) * h * .2 - 3));
        }

        // draws animated pie arc
        Arc2D pieArc = new Arc2D.Float(Arc2D.PIE);
        pieArc.setFrame(0, 0, aw, ah);
        pieArc.setAngleStart(angleStart);
        pieArc.setAngleExtent(angleExtent);
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        switch (direction) {
            case DOWN :
                at.rotate(Math.toRadians(90));
                break;
            case BACKWARD :
                at.rotate(Math.toRadians(180));
                break;
            case UP :
                at.rotate(Math.toRadians(270));
        }
        g2.setColor(Color.blue);
        g2.fill(at.createTransformedShape(pieArc));
    }

    public Graphics2D createGraphics2D(int w, int h) {
        Graphics2D g2 = null;
        if (bimg == null || bimg.getWidth() != w || bimg.getHeight() != h) {
            bimg = (BufferedImage) createImage(w, h);
            reset(w, h);
        }
        g2 = bimg.createGraphics();
        g2.setBackground(getBackground());
        g2.clearRect(0, 0, w, h);
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        return g2;
    }

    public void paint(Graphics g) {
        Dimension d = getSize();
        step(d.width, d.height);
        Graphics2D g2 = createGraphics2D(d.width, d.height);
        drawDemo(d.width, d.height, g2);
        g2.dispose();
        g.drawImage(bimg, 0, 0, this);
    }

    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    public synchronized void stop() {
        thread = null;
    }

    public void run() {
        Thread me = Thread.currentThread();
        while (thread == me) {
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                break;
            }
        }
        thread = null;
    }

    public static void main(String argv[]) {
        final Arcs demo = new Arcs();
        demo.init();
        Frame f = new Frame("Java 2D(TM) Demo - Arcs");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
            public void windowDeiconified(WindowEvent e) {
                demo.start();
            }
            public void windowIconified(WindowEvent e) {
                demo.stop();
            }
        });
        f.add(demo);
        f.pack();
        f.setSize(new Dimension(400, 300));
        f.setVisible(true);
        demo.start();
    }
}
