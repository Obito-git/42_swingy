package fr.ecole42.swingy.view.console;

import fr.ecole42.swingy.controller.Controller;
import fr.ecole42.swingy.model.GameMap;
import fr.ecole42.swingy.model.hero.Hero;
import fr.ecole42.swingy.model.hero.HeroDirector;
import fr.ecole42.swingy.view.ViewMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Reader {
	private Controller controller;

	public Reader(Controller controller) {
		this.controller = controller;
	}

	private static String readLine() {
		BufferedReader br = null;
		String res = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			res = br.readLine();
		} catch (Exception e) {
			System.out.println("ERROR: Invalid input for hero class.");
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return res;
	}

	public Hero chooseHero(List<Hero> heroes) {
		Logger.print("Found " + heroes.size() + " heroes");
		Logger.print("Choose existing hero or type \"create\" to create a new hero");
		while (true) {
			for (int i = 0; i < heroes.size(); i++)
				Logger.print((i + 1) + ". " + heroes.get(i));
			try {
				String res = readLine();
				if (res == null || res.equalsIgnoreCase("create"))
					return null;
				if (res.equalsIgnoreCase("gui")) {
					controller.setView(ViewMode.GUI);
					return null;
				}
				return heroes.get(Integer.parseInt(res) - 1);
			} catch (Exception e) {
				Logger.print("Choose existing hero or type \"create\" to create a new hero");
			}
		}
	}

	private static String heroDescription(int hp, int def, int attack) {
		return "Attack " + attack + ", defence " + def + ", hp " + hp;
	}

	public Hero createHero() {
		Logger.printDelimiter();
		Logger.print("Hero creation:");
		Logger.print("Let's name your character (min 2 character, max 30)");
		String name = readLine();
		Logger.lnprint("Let's choose a hero class: ");
		while (true) {
			Logger.print("Warrior " + heroDescription(HeroDirector.WARRIOR_HP, HeroDirector.WARRIOR_DEFENCE, HeroDirector.WARRIOR_ATTACK));
			Logger.print("Hunter " + heroDescription(HeroDirector.HUNTER_HP, HeroDirector.HUNTER_DEFENCE, HeroDirector.HUNTER_ATTACK));
			Logger.print("Mage " + heroDescription(HeroDirector.MAGE_HP, HeroDirector.MAGE_DEFENCE, HeroDirector.MAGE_ATTACK));
			String res = readLine();
			if (res == null)
				continue;
			switch (res.toLowerCase()) {
				case "warrior" -> {
					return HeroDirector.buildWarrior(name);
				}
				case "hunter" -> {
					return HeroDirector.buildHunter(name);
				}
				case "mage" -> {
					return HeroDirector.buildMage(name);
				}
			}
		}
	}

	public void moveDirection() {
		while (true) {
			System.out.println(controller.getPlayer().getCurrentGameMap());
			Logger.printDelimiter();
			Logger.print("Make your move:");
			Logger.print("(N, E, S, W)");
			String res = readLine();
			if (res == null)
				continue;
			switch (res.toLowerCase()) {
				case "n" -> controller.moveDirection(GameMap.directions.North);
				case "w" -> controller.moveDirection(GameMap.directions.West);
				case "e" -> controller.moveDirection(GameMap.directions.East);
				case "s" -> controller.moveDirection(GameMap.directions.South);
			}
		}
	}
}
