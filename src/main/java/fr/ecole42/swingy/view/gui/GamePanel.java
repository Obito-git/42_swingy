package fr.ecole42.swingy.view.gui;

import fr.ecole42.swingy.controller.Controller;
import fr.ecole42.swingy.model.GameMap;
import fr.ecole42.swingy.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel {
	private final Player player;
	private final Controller controller;
	private ImageIcon heroIcon;
	private ImageIcon ghostIcon;
	private ImageIcon golemIcon;
	private ImageIcon koboldIcon;

	private static final String MAGE_ICON = "/icons/mage-icon.png";
	private static final String HUNTER_ICON = "/icons/hunter-icon.png";
	private static final String WARRIOR_ICON = "/icons/warrior-icon.png";
	private static final String GHOST_ICON = "/icons/ghost-icon.png";
	private static final String GOLEM_ICON = "/icons/golem-icon.png";
	private static final String KOBOLD_ICON = "/icons/kobold-icon.png";


	public GamePanel(Controller controller) {
		this.controller = controller;
		this.player = this.controller.getPlayer();
		addKeyListener(new CustomKeyListener());
		setFocusable(true);

		setBackground(Color.pink);
	}

	private void setIcon(int mapSize) {
		String iconPath;
		int iconSize = (int) (MainFrameGUI.GAME_PANEL_WIDTH / mapSize * 0.9);
		switch (player.getCurrentHero().getHeroType()) {
			case MAGE -> iconPath = MAGE_ICON;
			case HUNTER -> iconPath = HUNTER_ICON;
			default -> iconPath = WARRIOR_ICON;
		}
		try {
			heroIcon = new ImageIcon(new javax.swing.ImageIcon(getClass().getResource(iconPath))
					.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
			koboldIcon = new ImageIcon(new javax.swing.ImageIcon(getClass().getResource(KOBOLD_ICON))
					.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
			ghostIcon = new ImageIcon(new javax.swing.ImageIcon(getClass().getResource(GHOST_ICON))
					.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
			golemIcon = new ImageIcon(new javax.swing.ImageIcon(getClass().getResource(GOLEM_ICON))
					.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
		} catch (Exception ig) {
			System.out.println("Textures problem");
			System.exit(666);
		}
	}

	public void build(GameMap gameMap) {
		removeAll();
		setIcon(gameMap.getSize());

		int size = gameMap.getSize();
		char[][] map = gameMap.getMap();
		setLayout(new GridLayout(size, size));
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				JPanel jPanel = new JPanel();
				JLabel jLabel = new JLabel();
				if (map[i][j] == GameMap.FIELD_EMPTY) {
					jPanel.setBackground(Color.GRAY);
					jPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				} else if (map[i][j] == GameMap.FIELD_ENEMY_KOBOLD){
					jLabel.setIcon(koboldIcon);
				} else if (map[i][j] == GameMap.FIELD_ENEMY_GOLEM){
					jLabel.setIcon(golemIcon);
				} else if (map[i][j] == GameMap.FIELD_ENEMY_GHOST){
					jLabel.setIcon(ghostIcon);
				} else
					jLabel.setIcon(heroIcon);
				add(jPanel);
				jPanel.add(jLabel);
			}
		}
		updateUI();
	}

	class CustomKeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				controller.moveDirection(GameMap.directions.East);
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				controller.moveDirection(GameMap.directions.West);
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				controller.moveDirection(GameMap.directions.North);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				controller.moveDirection(GameMap.directions.South);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
}
