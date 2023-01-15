package fr.ecole42.swingy.model.hero;

import fr.ecole42.swingy.model.artifact.Armor;
import fr.ecole42.swingy.model.artifact.Helm;
import fr.ecole42.swingy.model.artifact.Weapon;
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
	@Column (name = "hp")
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

	public Hero(String name, HeroType heroType, int level, int experience, int attack, int defence, int hp) {
		this.name = name;
		this.level = level;
		this.experience = experience;
		this.attack = attack;
		this.defence = defence;
		this.hp = hp;
		this.heroType = heroType;

		armor = new Armor(5);
		weapon = new Weapon(5);
		helm = new Helm(5);
	}

	public Hero() {

	}

	public void attack() {

	}

	public void getDamage() {

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

	public int getHp() {
		return hp;
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

	@Override
	public String toString() {
		return "Hero{" +
				"name='" + name + '\'' +
				", level=" + level +
				", heroType=" + heroType +
				", experience=" + experience +
				", attack=" + attack +
				", defence=" + defence +
				", hp=" + hp +
				",\n" +
				helm + "\n" +
				armor + "\n" +
				weapon + "\n";
	}
}
