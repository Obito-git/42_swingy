package fr.ecole42.swingy.model.hero.types;

import fr.ecole42.swingy.model.hero.Hero;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("hunt")
public class Hunter extends Hero {
    public Hunter(String name, int level, int experience, int attack, int defence, int hp) {
        super(name, HeroType.HUNTER, level, experience, attack, defence, hp);
    }

    public Hunter() {

    }

    @Override
    public void attack() {

    }

    @Override
    public void getDamage() {

    }
}
