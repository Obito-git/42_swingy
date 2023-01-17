package fr.ecole42.swingy.view;

import fr.ecole42.swingy.controller.Controller;
import fr.ecole42.swingy.model.enemies.EnemyType;
import fr.ecole42.swingy.model.hero.Hero;

abstract public class UserInterface {
	protected Controller controller;
	public abstract void showHeroes();
	public abstract void disableOutput();
	public abstract void enableOutput();

	public abstract void play();

	public abstract void refresh();

	public abstract void restart();

	public abstract boolean startFight(EnemyType enemyType);


	public UserInterface(Controller controller) {
		this.controller = controller;
	}
}
