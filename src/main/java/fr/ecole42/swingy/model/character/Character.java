package fr.ecole42.swingy.model.character;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class Character {
	@Column (name = "level")
	private int level;
	@Enumerated(EnumType.STRING)
	@Column (name = "player_class")
	private PlayerClass heroPlayerClass;
	@Column (name = "experience")
	private int experience;
	@Column (name = "attack")
	private int attack;
	@Column (name = "defence")
	private int defence;
	@Column (name = "hp")
	private int hp;

	public Character(int level, PlayerClass heroPlayerClass, int experience, int attack, int defence, int hp) {
		this.level = level;
		this.heroPlayerClass = heroPlayerClass;
		this.experience = experience;
		this.attack = attack;
		this.defence = defence;
		this.hp = hp;
	}

	public Character() {

	}

	@Override
	public String toString() {
		return "Character{" +
				"level=" + level +
				", heroPlayerClass=" + heroPlayerClass +
				", experience=" + experience +
				", attack=" + attack +
				", defence=" + defence +
				", hp=" + hp +
				'}';
	}
}
