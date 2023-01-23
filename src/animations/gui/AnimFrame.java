package animations.gui;

import javax.swing.*;
import java.awt.*;

// Główne okno aplikacji zawierające obszar do rysowania
public class AnimFrame extends JFrame {
	private final int WIDTH = 1024;
	private final int HEIGHT = 768;
	private AnimPanel animPanel;

	public AnimFrame(String title) throws HeadlessException {
		super(title);
		buildFrame();
	}

	private void buildFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenDimension.width - WIDTH)/2, (screenDimension.height - HEIGHT)/2, WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		createAnimPanel();

		setVisible(true);
	}

	private void createAnimPanel(){
		animPanel = new AnimPanel();
		add(animPanel);
		SwingUtilities.invokeLater(()->{
			animPanel.initialize();
		});
		animPanel.animate();
	}
}