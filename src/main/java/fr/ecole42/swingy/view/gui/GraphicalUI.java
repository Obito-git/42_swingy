package fr.ecole42.swingy.view.gui;

import fr.ecole42.swingy.controller.Controller;
import fr.ecole42.swingy.model.enemies.EnemyType;
import fr.ecole42.swingy.view.UserInterface;


public class GraphicalUI extends UserInterface {
	private MainFrameGUI mainFrameGUI;

	public GraphicalUI(Controller controller) {
		super(controller);
		mainFrameGUI = new MainFrameGUI(controller);
	}

	@Override
	public void showHeroes() {
		mainFrameGUI.showHeroes();
	}

	@Override
	public void disableOutput() {
		mainFrameGUI.setVisible(false);
	}

	@Override
	public void enableOutput() {
		mainFrameGUI.setVisible(true);
	}

	@Override
	public void play() {
		mainFrameGUI.buildMap();
	}

	@Override
	public void refresh() {
		mainFrameGUI.reload();
	}

	@Override
	public void restart() {
		mainFrameGUI.removeAll();
		mainFrameGUI.dispose();
		mainFrameGUI = new MainFrameGUI(controller);
	}

	@Override
	public boolean startFight(EnemyType enemyType) {
		return mainFrameGUI.startFightWindow(enemyType);
	}
}
