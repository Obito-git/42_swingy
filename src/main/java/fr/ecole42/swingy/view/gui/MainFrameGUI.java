package fr.ecole42.swingy.view.gui;

import fr.ecole42.swingy.controller.Controller;
import fr.ecole42.swingy.view.ViewMode;

import javax.swing.*;
import java.awt.*;

public class MainFrameGUI extends JFrame {
	private final Controller controller;
	private static final int WINDOW_WIDTH = 1600;
	private static final int WINDOW_HEIGHT = 1200;
	private static final int INFO_PANEL_WIDTH = 400;
	public static final int GAME_PANEL_WIDTH = 1200;
	private GameInfoPanel gameInfoPanel;
	private GamePanel gamePanel;

	public MainFrameGUI(Controller controller) {
		this.controller = controller;

		setTitle("Swingy");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());

		JButton submit = new JButton("Choose Hero");
		submit.addActionListener(e -> {
			controller.setCurrentHero(gameInfoPanel.getChosenHero());
		});

		JButton changeUI = new JButton("Change UI");
		changeUI.addActionListener(e -> {
			controller.setView(ViewMode.CONSOLE);
		});

		gameInfoPanel = new GameInfoPanel(controller, this, submit, changeUI);
		gamePanel = new GamePanel(controller);
		gameInfoPanel.setPreferredSize(new Dimension(INFO_PANEL_WIDTH, WINDOW_HEIGHT));
		gamePanel.setPreferredSize(new Dimension(GAME_PANEL_WIDTH, WINDOW_HEIGHT));

		add(gameInfoPanel, BorderLayout.WEST);
		add(gamePanel, BorderLayout.EAST);
		SwingUtilities.updateComponentTreeUI(this);
	}

	public void reload() {
		gameInfoPanel.inGamePlayerInfo(controller.getPlayer().getCurrentHero());
		gamePanel.build(controller.getPlayer().getCurrentGameMap());
	}

	public void showHeroes() {
		setVisible(true);
	}

	public void buildMap() {
		gameInfoPanel.inGamePlayerInfo(controller.getPlayer().getCurrentHero());
		gamePanel.requestFocusInWindow();
		SwingUtilities.updateComponentTreeUI(this);
		gamePanel.build(controller.getPlayer().getNewGameMap());

	}
}
