package palace.toolkit.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class WindowFrame {
	
	private Canvas _canvas;

	public WindowFrame(Canvas canvas) {
		_canvas = canvas;
	}

	public void createAndShowGUI(String title, int w, int h) {
		JFrame frame = new JFrame(title);
		frame.add(_canvas);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// Run this on another thread than the AWT event queue to
				// make sure the call to Animator.stop() completes before
				// exiting
				new Thread(new Runnable() {
					public void run() {
						System.exit(0);
					}
				}).start();
			}
		});
		frame.pack();
		frame.setSize(w, h);
		frame.setVisible(true);
	}
}
