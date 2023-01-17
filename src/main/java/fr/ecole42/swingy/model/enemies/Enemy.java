package fr.ecole42.swingy.model.enemies;

public class Enemy {
	private int hp;
	private int attack;
	private int defence;
	private int heroLvl;
	private int maxHp;

	private EnemyType enemyType;

	public Enemy(int hp, int attack, int defence, EnemyType enemyType, int heroLvl) {
		this.hp = hp * heroLvl;
		this.maxHp = hp;
		this.attack = attack * heroLvl;
		this.defence = defence * heroLvl;
		this.heroLvl = heroLvl;
		this.enemyType = enemyType;
	}

	public int getHp() {
		return hp;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefence() {
		return defence;
	}

	public int getHeroLvl() {
		return heroLvl;
	}

	public EnemyType getEnemyType() {
		return enemyType;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
}
