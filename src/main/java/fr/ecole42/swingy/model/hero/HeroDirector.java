package fr.ecole42.swingy.model.hero;

public class HeroDirector {
	public static final int WARRIOR_ATTACK = 25;
	public static final int WARRIOR_DEFENCE = 25;
	public static final int WARRIOR_HP = 125;

	public static final int MAGE_ATTACK = 50;
	public static final int MAGE_DEFENCE = 10;
	public static final int MAGE_HP = 80;

	public static final int HUNTER_ATTACK = 40;
	public static final int HUNTER_DEFENCE = 15;
	public static final int HUNTER_HP = 100;

	public static Hero buildWarrior(String name) {
		return new HeroBuilder().level(1)
				.name(name)
				.heroType(HeroType.WARRIOR)
				.experience(0)
				.attack(WARRIOR_ATTACK)
				.defence(WARRIOR_DEFENCE)
				.hp(WARRIOR_HP)
				.build();
	}

	public static Hero buildMage(String name) {
		return new HeroBuilder().level(1)
				.name(name)
				.heroType(HeroType.MAGE)
				.experience(0)
				.attack(MAGE_ATTACK)
				.defence(MAGE_DEFENCE)
				.hp(MAGE_HP)
				.build();
	}

	public static Hero buildHunter(String name) {
		return new HeroBuilder().level(1)
				.name(name)
				.heroType(HeroType.HUNTER)
				.experience(0)
				.attack(HUNTER_ATTACK)
				.defence(HUNTER_DEFENCE)
				.hp(HUNTER_HP)
				.build();
	}
}
