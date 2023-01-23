package animations.gui;

import animations.figures.Rect;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;
import javax.swing.Timer;

// Obszar do rysowania
public class AnimPanel extends JPanel implements MouseListener, ActionListener {
	// Off-Screen image
	private Image offScreen;
	// Off-Screen buffer
	private Graphics2D offBuffer;
	// On-Screen buffer
	private Graphics2D device;

	private final int DELAY = 30;
	private final Timer timer;

	private final Point[] mouseClickPoints;

	public AnimPanel() {
		setBackground(Color.WHITE);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

		timer = new Timer(DELAY, this);
		mouseClickPoints = new Point[2];

		addMouseListener(this);
	}

	public void initialize() {
		int width = getWidth();
		int height = getHeight();

		offScreen = createImage(width, height);
		offBuffer = (Graphics2D) offScreen.getGraphics();
		offBuffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		device = (Graphics2D) getGraphics();
		device.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	void animate() {
		timer.start();
	}

	private void addRectangle(Point[] mouseClickPoints){
		Rect figure = new Rect(offBuffer, DELAY, getWidth(), getHeight(), mouseClickPoints);
		timer.addActionListener(figure);
		// Każdy prostokąt sterowany nowym wątkiem
		new Thread(figure).start();
	}

	// for ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		device.drawImage(offScreen, 0, 0, null);
		offBuffer.clearRect(0, 0, getWidth(), getHeight());
	}

	// for MouseListener
	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
	}

	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		// Naciśnięcie przycisku - jeden róg
		mouseClickPoints[0] = new Point(mouseEvent.getX(), mouseEvent.getY());
	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
		// Puszczenie przycisku - drugi róg
		mouseClickPoints[1] = new Point(mouseEvent.getX(), mouseEvent.getY());
		if (!mouseClickPoints[0].equals(mouseClickPoints[1])){
			addRectangle(mouseClickPoints);
		}
	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {

	}
}
