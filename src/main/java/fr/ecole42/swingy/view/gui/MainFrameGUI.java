package fr.ecole42.swingy.view.gui;

import fr.ecole42.swingy.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class MainFrameGUI extends JFrame {
	private final Controller controller;
	private static final int WINDOW_WIDTH = 1600;
	private static final int WINDOW_HEIGHT = 1200;
	private static final int INFO_PANEL_WIDTH = 400;
	public static final int GAME_PANEL_WIDTH = 1200;
	private ChooseHero chooseHero;
	private GamePanel gamePanel;

	public MainFrameGUI(Controller controller) {
		this.controller = controller;

		setTitle("Swingy");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setLayout(new BorderLayout());

		startWindow();
	}

	private void startWindow() {
		JButton submit = new JButton("Choose Hero");
		submit.addActionListener(e -> {
			controller.setCurrentHero(chooseHero.getChosenHero());
			remove(chooseHero);
			SwingUtilities.updateComponentTreeUI(this);
			gamePanel.build(controller.getPlayer().getNewGameMap());
		});

		chooseHero = new ChooseHero(controller, this, submit);
		gamePanel = new GamePanel(controller);
		chooseHero.setPreferredSize(new Dimension(INFO_PANEL_WIDTH, WINDOW_HEIGHT));
		gamePanel.setPreferredSize(new Dimension(GAME_PANEL_WIDTH, WINDOW_HEIGHT));

		add(chooseHero, BorderLayout.WEST);
		add(gamePanel, BorderLayout.EAST);
	}

	public void reload() {
		gamePanel.build(controller.getPlayer().getCurrentGameMap());
	}

	public void showHeroes() {
		//FIXME
	}
}
