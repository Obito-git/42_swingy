package fr.ecole42.swingy.controller;

import fr.ecole42.swingy.config.SpringConfig;
import fr.ecole42.swingy.dao.CharacterDAO;
import fr.ecole42.swingy.model.hero.Hero;
import fr.ecole42.swingy.view.MainVisualizer;
import fr.ecole42.swingy.view.ViewMode;
import fr.ecole42.swingy.view.menu.CharacterMenu;
import fr.ecole42.swingy.view.menu.CharacterMenuConsole;
import fr.ecole42.swingy.view.menu.CharacterMenuGUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class GameController {
	private final CharacterDAO characterDAO;
	private MainVisualizer mainVisualizer;

	@Autowired
	public GameController(CharacterDAO characterDAO, MainVisualizer mainVisualizer) {
		this.characterDAO = characterDAO;
		this.mainVisualizer = mainVisualizer;
	}

	public void changeViewMode(ViewMode viewMode) {
		mainVisualizer.setViewMode(viewMode);
	}

	/*
	public void show_all() {
		try {
			List<Player> l = this.playerDAO.index();
			for (Player p: l)
				System.out.println(p);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void addInit() {
		Player p = new Player("Name123");
		p.addCharacter(CharacterDirector.buildMage());
		p.addCharacter(CharacterDirector.buildHunter());
		p.addCharacter(CharacterDirector.buildWarrior());
		playerDAO.save(p);
	}
	 */

	public void save(Hero c) {
		characterDAO.save(c);
	}
}
