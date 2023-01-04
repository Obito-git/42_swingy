package fr.ecole42.swingy.model.character;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Character")
public class Character {
	@Column (name = "name", unique = true)
	@Size(min = 2, max = 30)
	private String name;
	@Column (name = "level")
	@Min(1)
	@Max(20)
	private int level;
	@Enumerated(EnumType.STRING)
	@Column (name = "hero_class")
	private HeroClass heroClass;
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

	public Character(String name, int level, HeroClass heroClass, int experience, int attack, int defence, int hp) {
		this.name = name;
		this.level = level;
		this.heroClass = heroClass;
		this.experience = experience;
		this.attack = attack;
		this.defence = defence;
		this.hp = hp;
	}

	public Character() {

	}

	@Override
	public String toString() {
		return "Character{" +
				"name=" + name +
				"level=" + level +
				", heroClass=" + heroClass +
				", experience=" + experience +
				", attack=" + attack +
				", defence=" + defence +
				", hp=" + hp +
				'}';
	}
}
