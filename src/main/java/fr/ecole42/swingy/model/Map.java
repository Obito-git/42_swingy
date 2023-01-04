package fr.ecole42.swingy.model;

import fr.ecole42.swingy.model.hero.Hero;

public class Map {
	private int size;
	private Hero hero;

	public Map(Hero hero) {
		this.hero = hero;
		//size = (player.getLevel() - 1) * 5 + 10 - (player.getLevel() % 2);
	}

	public int getSize() {
		return size;
	}
}
