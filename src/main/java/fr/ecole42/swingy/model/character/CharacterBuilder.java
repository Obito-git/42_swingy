package fr.ecole42.swingy.model.character;

public class CharacterBuilder {
	private int level;
	private PlayerClass heroPlayerClass;
	private int experience;
	private int attack;
	private int defence;
	private int hp;

	public CharacterBuilder level(int level) {
		this.level = level;
		return this;
	}

	public CharacterBuilder playerClass(PlayerClass playerClass) {
		this.heroPlayerClass = playerClass;
		return this;
	}

	public CharacterBuilder experience(int experience) {
		this.experience = experience;
		return this;
	}

	public CharacterBuilder attack(int attack) {
		this.attack = attack;
		return this;
	}

	public CharacterBuilder defence(int defence) {
		this.defence = defence;
		return this;
	}

	public CharacterBuilder hp(int hp) {
		this.hp = hp;
		return this;
	}

	public Character build() {
		return new Character(level, heroPlayerClass, experience, attack, defence, hp);
	}

}
