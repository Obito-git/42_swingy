package fr.ecole42.swingy.model.hero;

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

	public Hero(String name, HeroType heroType, int level, int experience, int attack, int defence, int hp) {
		this.name = name;
		this.level = level;
		this.experience = experience;
		this.attack = attack;
		this.defence = defence;
		this.hp = hp;
		this.heroType = heroType;
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
				'}';
	}
}
