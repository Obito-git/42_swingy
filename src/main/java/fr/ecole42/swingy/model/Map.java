package fr.ecole42.swingy.model;

import fr.ecole42.swingy.model.character.Character;

public class Map {
	private int size;
	private Character character;

	public Map(Character character) {
		this.character = character;
		//size = (player.getLevel() - 1) * 5 + 10 - (player.getLevel() % 2);
	}

	public int getSize() {
		return size;
	}
}
