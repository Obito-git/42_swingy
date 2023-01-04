package fr.ecole42.swingy.model.hero;

import fr.ecole42.swingy.model.hero.types.HeroType;

public class HeroDirector {
	public static Hero buildWarrior(String name) {
		return new HeroBuilder().level(1)
				.name(name)
				.heroType(HeroType.WARRIOR)
				.experience(0)
				.attack(25)
				.defence(20)
				.hp(120)
				.build();
	}

	public static Hero buildMage(String name) {
		return new HeroBuilder().level(1)
				.name(name)
				.heroType(HeroType.MAGE)
				.experience(0)
				.attack(50)
				.defence(10)
				.hp(80)
				.build();
	}

	public static Hero buildHunter(String name) {
		return new HeroBuilder().level(1)
				.name(name)
				.heroType(HeroType.HUNTER)
				.experience(0)
				.attack(40)
				.defence(15)
				.hp(100)
				.build();
	}
}
