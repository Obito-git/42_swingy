package fr.ecole42.swingy.model.character;

public class CharacterDirector {
	public static Character buildWarrior() {
		return new CharacterBuilder().level(1)
				.playerClass(PlayerClass.WARRIOR)
				.experience(0)
				.attack(25)
				.defence(20)
				.hp(120)
				.build();
	}

	public static Character buildMage() {
		return new CharacterBuilder().level(1)
				.playerClass(PlayerClass.MAGE)
				.experience(0)
				.attack(50)
				.defence(10)
				.hp(80)
				.build();
	}

	public static Character buildHunter() {
		return new CharacterBuilder().level(1)
				.playerClass(PlayerClass.HUNTER)
				.experience(0)
				.attack(40)
				.defence(15)
				.hp(100)
				.build();
	}
}
