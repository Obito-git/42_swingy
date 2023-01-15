package fr.ecole42.swingy.model.enemies;

public class EnemyDirector {
	public static Enemy buildGhost(int heroLvl) {
		return new EnemyBuilder()
				.heroLvl(heroLvl)
				.enemyType(EnemyType.GHOST)
				.build();
	}

	public static Enemy buildGolem(int heroLvl) {
		return new EnemyBuilder()
				.heroLvl(heroLvl)
				.enemyType(EnemyType.GOLEM)
				.build();
	}

	public static Enemy buildKobold(int heroLvl) {
		return new EnemyBuilder()
				.heroLvl(heroLvl)
				.enemyType(EnemyType.KOBOLD)
				.build();
	}
}
