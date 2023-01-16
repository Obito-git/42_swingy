package fr.ecole42.swingy.view.console;

import fr.ecole42.swingy.controller.Controller;
import fr.ecole42.swingy.model.enemies.EnemyType;
import fr.ecole42.swingy.model.hero.Hero;
import fr.ecole42.swingy.view.UserInterface;

import java.util.List;

public class ConsoleUI extends UserInterface {
	Reader reader = new Reader(controller);
	public ConsoleUI(Controller controller) {
		super(controller);
	}

	@Override
	public void showHeroes() {
		List<Hero> heroes = controller.getAllHeroes();
		while (!controller.heroIsChosen() && Logger.isActive()) {
			if (heroes.isEmpty()) {
				Logger.print("No heroes created");
				try {
					controller.save(createHero());
				} catch (Exception e) {
					System.out.println("Nickname is already used");
				}

			} else {
				Hero chosen = reader.chooseHero(heroes);
				if (chosen == null && Logger.isActive()) {
					try {
						controller.save(createHero());
					} catch (Exception e) {
						System.out.println("Nickname is already used");
						Logger.printDelimiter();
					}
				} else if (chosen != null)
					controller.setCurrentHero(chosen);
			}
		}
	}

	private Hero createHero() {
		Hero hero;

		while ((hero = reader.createHero()) == null)
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
	public void play() {
		controller.getPlayer().getNewGameMap(); //FIXME
		reader.moveDirection();
	}

	@Override
	public void refresh() {
		//FIXME
	}

	@Override
	public boolean startFight(EnemyType enemyType) {
		//FIXME
		return false;
	}
}
