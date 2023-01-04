package fr.ecole42.swingy.controller;

import fr.ecole42.swingy.dao.CharacterDAO;
import fr.ecole42.swingy.model.character.Character;
import fr.ecole42.swingy.view.ViewModes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameController {
	private final CharacterDAO characterDAO;
	private ViewModes viewModes;

	@Autowired
	public GameController(CharacterDAO characterDAO) {
		this.characterDAO = characterDAO;
		viewModes = ViewModes.CONSOLE;
	}

	public void setViewMode(ViewModes viewModes) {
		this.viewModes = viewModes;
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

	public void save(Character c) {
		characterDAO.save(c);
	}
}
