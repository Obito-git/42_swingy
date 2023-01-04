package fr.ecole42.swingy.model.character;

public class CharacterBuilder {
	private int level;
	private HeroClass heroHeroClass;
	private int experience;
	private int attack;
	private int defence;
	private int hp;
	private String name;

	public CharacterBuilder level(int level) {
		this.level = level;
		return this;
	}

	public CharacterBuilder playerClass(HeroClass heroClass) {
		this.heroHeroClass = heroClass;
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

	public CharacterBuilder name(String name) {
		this.name = name;
		return this;
	}

	public Character build() {
		return new Character(name, level, heroHeroClass, experience, attack, defence, hp);
	}

}
