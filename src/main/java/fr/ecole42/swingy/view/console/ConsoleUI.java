package fr.ecole42.swingy.view.console;

import fr.ecole42.swingy.controller.Controller;
import fr.ecole42.swingy.model.hero.Hero;
import fr.ecole42.swingy.view.UserInterface;

import java.util.List;

public class ConsoleUI extends UserInterface {
	public ConsoleUI(Controller controller) {
		super(controller);
	}

	@Override
	public void showHeroes() {
		List<Hero> heroes = controller.getAllHeroes();
		while (!controller.heroIsChosen()) {
			if (heroes.isEmpty()) {
				Logger.print("No heroes created");
				try {
					controller.save(createHero());
				} catch (Exception e) {
					System.out.println("Nickname is already used");
				}

			} else {
				Hero chosen = Reader.chooseHero(heroes);
				if (chosen == null) {
					try {
						controller.save(createHero());
					} catch (Exception e) {
						System.out.println("Nickname is already used");
						Logger.printDelimiter();
					}
				} else
					controller.setCurrentHero(chosen);
			}
		}
	}

	private Hero createHero() {
		Hero hero;

		while ((hero = Reader.createHero()) == null)
			;
		return hero;
	}

	@Override
	public void disableOutput() {
		Logger.setActive(false);
	}

	@Override
	public void enableOutput() {
		Logger.setActive(true);
		Logger.print("You can always switch to GUI typing \"GUI\" anywhere");
	}

	@Override
	public void refresh() {

	}
}
