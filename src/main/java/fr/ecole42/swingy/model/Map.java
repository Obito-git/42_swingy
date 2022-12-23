package fr.ecole42.swingy.model;

import fr.ecole42.swingy.model.character.Player;

public class Map {
	private int size;
	private Player player;

	public Map(Player player) {
		this.player = player;
		//size = (player.getLevel() - 1) * 5 + 10 - (player.getLevel() % 2);
	}

	public int getSize() {
		return size;
	}
}
