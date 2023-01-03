package fr.ecole42.swingy.controller;

import fr.ecole42.swingy.dao.PlayerDAO;
import fr.ecole42.swingy.model.character.CharacterDirector;
import fr.ecole42.swingy.model.character.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameController {
	private final PlayerDAO playerDAO;

	@Autowired
	public GameController(PlayerDAO playerDAO) {
		this.playerDAO = playerDAO;

	}

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
}
