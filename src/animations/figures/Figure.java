package animations.figures;

import animations.CollisionDetector;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.Random;

public abstract class Figure implements Runnable, ActionListener {
	protected Area area;
	protected Shape shape;
	protected AffineTransform aft;

	protected Graphics2D buffer;
	private final int DELAY;
	protected int panelWidth;
	protected int panelHeight;

	protected int dx, dy;
	protected final Color color;

	protected static final Random rand = new Random();

	public Figure(Graphics2D buffer, int DELAY, int panelWidth, int panelHeight) {
		this.buffer = buffer;
		this.DELAY = DELAY;
		this.panelWidth = panelWidth;
		this.panelHeight = panelHeight;

		CollisionDetector.setPanelWidth(panelWidth);
		CollisionDetector.setPanelHeight(panelHeight);

		color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), 255);
	}

	protected abstract AffineTransform move();

	protected Shape nextFrame() {
		aft = move();
		area.transform(aft);
		return area;
	}

	// for Runnable interface
	@Override
	public void run() {
		area.transform(aft);
		shape = area;

		while (true) {
			// przygotowanie nastepnego kadru
			shape = nextFrame();
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// for ActionListener
	@Override
	public void actionPerformed(ActionEvent evt) {
		buffer.setColor(color.brighter());
		buffer.fill(shape);
		buffer.setColor(color.darker());
		buffer.draw(shape);
	}

	// Getters
	public Shape getShape() {
		return shape;
	}
}
