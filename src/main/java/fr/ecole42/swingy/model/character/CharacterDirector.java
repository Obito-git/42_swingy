package fr.ecole42.swingy.model.character;

public class CharacterDirector {
	public static Character buildWarrior(String name) {
		return new CharacterBuilder().level(1)
				.name(name)
				.playerClass(HeroClass.WARRIOR)
				.experience(0)
				.attack(25)
				.defence(20)
				.hp(120)
				.build();
	}

	public static Character buildMage(String name) {
		return new CharacterBuilder().level(1)
				.name(name)
				.playerClass(HeroClass.MAGE)
				.experience(0)
				.attack(50)
				.defence(10)
				.hp(80)
				.build();
	}

	public static Character buildHunter(String name) {
		return new CharacterBuilder().level(1)
				.name(name)
				.playerClass(HeroClass.HUNTER)
				.experience(0)
				.attack(40)
				.defence(15)
				.hp(100)
				.build();
	}
}
