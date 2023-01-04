package fr.ecole42.swingy.model.hero;

import fr.ecole42.swingy.model.hero.types.HeroType;
import fr.ecole42.swingy.model.hero.types.Hunter;
import fr.ecole42.swingy.model.hero.types.Mage;
import fr.ecole42.swingy.model.hero.types.Warrior;

public class HeroBuilder {
	private int level;
	private int experience;
	private int attack;
	private int defence;
	private int hp;
	private String name;
	private HeroType heroType;

	public HeroBuilder level(int level) {
		this.level = level;
		return this;
	}

	public HeroBuilder experience(int experience) {
		this.experience = experience;
		return this;
	}

	public HeroBuilder attack(int attack) {
		this.attack = attack;
		return this;
	}

	public HeroBuilder defence(int defence) {
		this.defence = defence;
		return this;
	}

	public HeroBuilder hp(int hp) {
		this.hp = hp;
		return this;
	}

	public HeroBuilder name(String name) {
		this.name = name;
		return this;
	}

	public HeroBuilder heroType(HeroType heroType) {
		this.heroType = heroType;
		return this;
	}

	public Hero build() {
		switch (heroType) {
			case WARRIOR -> { return new Warrior(name, level, experience, attack, defence, hp); }
			case HUNTER -> { return new Hunter(name, level, experience, attack, defence, hp); }
			case MAGE -> { return new Mage(name, level, experience, attack, defence, hp); }
			default -> {
				return null;//FIXME
			}
		}
	}

}
