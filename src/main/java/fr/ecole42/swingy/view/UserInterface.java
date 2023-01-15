package fr.ecole42.swingy.view;

import fr.ecole42.swingy.controller.Controller;

abstract public class UserInterface {
	protected Controller controller;
	public abstract void showHeroes();
	public abstract void disableOutput();
	public abstract void enableOutput();

	public abstract void play();

	public abstract void refresh();

	public UserInterface(Controller controller) {
		this.controller = controller;
	}
}
