package fr.ecole42.swingy.view.gui;

import fr.ecole42.swingy.controller.Controller;
import fr.ecole42.swingy.view.UserInterface;


public class GraphicalUI extends UserInterface {
	private final MainFrameGUI mainFrameGUI;

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
	public void refresh() {
		mainFrameGUI.reload();
	}
}
