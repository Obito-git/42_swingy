package fr.ecole42.swingy.model.hero.types;

import fr.ecole42.swingy.model.hero.Hero;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("war")
public class Warrior extends Hero {
    public Warrior(String name, int level, int experience, int attack, int defence, int hp) {
        super(name, HeroType.WARRIOR, level, experience, attack, defence, hp);
    }

    public Warrior() {

    }

    @Override
    public void attack() {

    }

    @Override
    public void getDamage() {

    }
}
