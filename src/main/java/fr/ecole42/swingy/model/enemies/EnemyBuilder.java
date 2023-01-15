package fr.ecole42.swingy.model.enemies;

public class EnemyBuilder {

	public static final int GHOST_ATTACK = 10;
	public static final int GHOST_DEFENCE = 0;
	public static final int GHOST_HP = 50;

	public static final int GOLEM_ATTACK = 5;
	public static final int GOLEM_DEFENCE = 50;
	public static final int GOLEM_HP = 50;

	public static final int KOBOLD_ATTACK = 7;
	public static final int KOBOLD_DEFENCE = 7;
	public static final int KOBOLD_HP = 50;
	private int heroLvl;

	private EnemyType enemyType;

	public EnemyBuilder heroLvl(int heroLvl) {
		this.heroLvl = heroLvl;
		return this;
	}

	public EnemyBuilder enemyType(EnemyType enemyType) {
		this.enemyType = enemyType;
		return this;
	}

	public Enemy build() {
		switch (enemyType) {
			case GHOST -> {
				return new Enemy(GHOST_HP, GHOST_ATTACK, GHOST_DEFENCE, EnemyType.GHOST, heroLvl);
			}
			case GOLEM -> {
				return new Enemy(GOLEM_HP, GOLEM_ATTACK, GOLEM_DEFENCE, EnemyType.GOLEM, heroLvl);
			}
			default -> {
				return new Enemy(KOBOLD_HP, KOBOLD_ATTACK, KOBOLD_DEFENCE, EnemyType.KOBOLD, heroLvl);
			}
		}
	}
}
