package fr.ecole42.swingy.model.hero;

import fr.ecole42.swingy.model.artifact.Armor;
import fr.ecole42.swingy.model.artifact.Helm;
import fr.ecole42.swingy.model.artifact.Weapon;
import fr.ecole42.swingy.model.enemies.Enemy;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "character")
public class Hero {
	@Column (name = "name", unique = true)
	@Size(min = 2, max = 10, message = "Heroes name length must be between 2 and 10 characters")
	private String name;
	@Column (name = "level")
	@Min(1)
	@Max(20)
	private int level;
	@Enumerated(EnumType.STRING)
	@Column (name = "hero_type")
	private HeroType heroType;
	@Column (name = "experience")
	@Min(0)
	private int experience;
	@Column (name = "attack")
	@Min(0)
	private int attack;
	@Column (name = "defence")
	@Min(0)
	private int defence;
	@Column (name = "maxHp")
	@Min(0)
	private int maxHp;

	@Column (name = "max")
	@Min(0)
	private int hp;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "weapon_id")
	private Weapon weapon;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "armor_id")
	private Armor armor;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "helm_id")
	private Helm helm;

	public Hero(String name, HeroType heroType, int level, int experience, int attack, int defence, int maxHp) {
		this.name = name;
		this.level = level;
		this.experience = experience;
		this.attack = attack;
		this.defence = defence;
		this.maxHp = maxHp;
		this.heroType = heroType;
		this.hp = maxHp;

		armor = new Armor(5);
		weapon = new Weapon(5);
		helm = new Helm(5);
	}

	public Hero() {

	}

	public Hero(Hero other) {
		this.name = other.name;
		this.level = other.level;
		this.experience = other.experience;
		this.attack = other.attack;
		this.defence = other.defence;
		this.maxHp = other.maxHp;
		this.heroType = other.heroType;
		this.hp = other.hp;

		this.armor = new Armor(other.getArmor().getStat());
		this.weapon = new Weapon(other.getWeapon().getStat());
		this.helm = new Helm(other.getHelm().getStat());
	}

	public boolean fight(Enemy enemy) {
		int sumAttack = attack + weapon.getStat();
		int sumDefence = defence + armor.getStat();
		int sumHp = hp + helm.getStat();
		while (sumHp > 0 && enemy.getHp() > 0) {
			int heroDmg = enemy.getDefence() >= sumAttack ? 1 : sumAttack - enemy.getDefence();
			int enemyDmg = sumDefence >= enemy.getAttack() ? 1 : enemy.getAttack() - sumDefence;
			enemy.setHp(enemy.getHp() - heroDmg);
			sumHp -= enemyDmg;
			if (sumHp < 0)
				hp = 0;
		}
		return hp > 0;
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public HeroType getHeroType() {
		return heroType;
	}

	public int getExperience() {
		return experience;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefence() {
		return defence;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public Armor getArmor() {
		return armor;
	}

	public void setArmor(Armor armor) {
		this.armor = armor;
	}

	public Helm getHelm() {
		return helm;
	}

	public void setHelm(Helm helm) {
		this.helm = helm;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void increaseExp(int exp) {
		experience += exp;
		if (experience > (int)(level*1000 + (Math.pow(level - 1, 2) *450))) {
			level++;
			hp = maxHp;
		}
	}

	@Override
	public String toString() {
		return "Name='" + name + '\'' +
				", level=" + level +
				", heroType=" + heroType +
				", experience=" + experience +
				", attack=" + attack +
				", defence=" + defence +
				", max hp=" + maxHp +
				", hp=" + hp +
				",\n\t" + helm + ", " + armor + ", " + weapon ;
	}
}
