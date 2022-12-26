package fr.ecole42.swingy.controller;

import fr.ecole42.swingy.dao.PlayerDAO;
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
}
