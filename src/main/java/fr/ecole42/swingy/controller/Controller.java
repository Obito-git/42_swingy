package fr.ecole42.swingy.controller;

import fr.ecole42.swingy.dao.HeroDAO;
import fr.ecole42.swingy.model.GameMap;
import fr.ecole42.swingy.model.Player;
import fr.ecole42.swingy.model.artifact.Armor;
import fr.ecole42.swingy.model.artifact.Artifact;
import fr.ecole42.swingy.model.artifact.Helm;
import fr.ecole42.swingy.model.artifact.Weapon;
import fr.ecole42.swingy.model.enemies.Enemy;
import fr.ecole42.swingy.model.enemies.EnemyBuilder;
import fr.ecole42.swingy.model.enemies.EnemyType;
import fr.ecole42.swingy.model.hero.Hero;
import fr.ecole42.swingy.view.ViewMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@DependsOn("dbInitBean")
public class Controller {
    private final HeroDAO heroDAO;
    private final Player player;

    @Autowired
    public Controller(HeroDAO heroDAO, Player player) {
        this.heroDAO = heroDAO;
        this.player = player;

        this.player.setAllHeroes(this.heroDAO.index());
    }

    public boolean fightSimulation(EnemyType enemyType) {
        Enemy enemy = new EnemyBuilder().enemyType(enemyType).heroLvl(player.getCurrentHero().getLevel()).build();
        boolean res = player.currentPlayerFight(enemy);
        if (res) {
            switch (enemyType) {
                case KOBOLD -> player.increaseExp(300 + 10);
                case GHOST -> player.increaseExp(300 + 15);
                case GOLEM -> player.increaseExp(300 + 20);
            }
            player.generateArtifact(enemy);
        }
        return res;
    }

    public void setView(ViewMode view) {
        player.setView(view);
    }

    public void setCurrentHero(Hero hero) {
        player.setCurrentHero(hero);
    }

    public List<Hero> getAllHeroes() {
        return heroDAO.index();
    }

    public void saveNewHero(Hero c) {
        heroDAO.save(c);
        player.setAllHeroes(this.heroDAO.index());
    }

    public void saveProgress() {
        if (player.isChosenCurrentHero())
            heroDAO.update(player.getCurrentHero());
    }

    public void moveDirection(GameMap.directions direction) {
        player.movePlayer(direction);
    }

    public void restartGame() {
        player.restartGame();
    }

    public void resetChosenHero() {
        player.setCurrentHero(null);
    }

    public boolean heroIsChosen() {
        return player.isChosenCurrentHero();
    }

    /* UI demands */
    public Hero getHeroCopy() {
        return new Hero(player.getCurrentHero());
    }

    public GameMap getNewGameMap() {
        return player.getNewGameMap();
    }

    public GameMap getCurrentGameMap() {
        return player.getCurrentGameMap();
    }

    public Artifact getLastDroppedArtifact() {
        return player.getLastDropped();
    }

    /* UI modifies model */
    public void acceptNewArtifact() {
        Artifact lastDropped = player.getLastDropped();
        if (lastDropped.getClass() == Armor.class) {
            player.getCurrentHero().setArmor((Armor) lastDropped);
        } else if (lastDropped.getClass() == Helm.class) {
            player.getCurrentHero().setHelm((Helm) lastDropped);
        } else
            player.getCurrentHero().setWeapon((Weapon) lastDropped);
        player.deleteLastDropped();
        saveProgress();
    }

    public void refuseNewArtifact() {
        player.deleteLastDropped();
    }
}
