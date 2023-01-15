package fr.ecole42.swingy.model.hero;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class HeroBuilder {
	private int level;
	private int experience;
	private int attack;
	private int defence;
	private int hp;
	private String name;
	private HeroType heroType;

	public HeroBuilder level(int level) {
		this.level = level;
		return this;
	}

	public HeroBuilder experience(int experience) {
		this.experience = experience;
		return this;
	}

	public HeroBuilder attack(int attack) {
		this.attack = attack;
		return this;
	}

	public HeroBuilder defence(int defence) {
		this.defence = defence;
		return this;
	}

	public HeroBuilder hp(int hp) {
		this.hp = hp;
		return this;
	}

	public HeroBuilder name(String name) {
		this.name = name;
		return this;
	}

	public HeroBuilder heroType(HeroType heroType) {
		this.heroType = heroType;
		return this;
	}

	public Hero build() {
		Hero hero = new Hero(name, heroType, level, experience, attack, defence, hp);

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Hero>> constraintViolations = validator.validate(hero);

		factory.close();
		//Show errors
		if (constraintViolations.size() > 0) {
			for (ConstraintViolation<Hero> violation : constraintViolations) {
				System.out.println(violation.getMessage());
			}
			return null;
		} else {
			return hero;
		}
	}

}
