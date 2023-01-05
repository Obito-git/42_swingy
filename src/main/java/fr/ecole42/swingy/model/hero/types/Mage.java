package fr.ecole42.swingy.model.hero.types;

import fr.ecole42.swingy.model.hero.Hero;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("mag")
public class Mage extends Hero {
    public Mage(String name, int level, int experience, int attack, int defence, int hp) {
        super(name, HeroType.MAGE, level, experience, attack, defence, hp);
    }

    public Mage() {

    }

    @Override
    public void attack() {

    }

    @Override
    public void getDamage() {

    }
}
