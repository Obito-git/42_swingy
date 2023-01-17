package fr.ecole42.swingy.model;

import fr.ecole42.swingy.model.artifact.Armor;
import fr.ecole42.swingy.model.artifact.Artifact;
import fr.ecole42.swingy.model.artifact.Helm;
import fr.ecole42.swingy.model.artifact.Weapon;
import fr.ecole42.swingy.model.enemies.Enemy;
import fr.ecole42.swingy.model.hero.Hero;
import fr.ecole42.swingy.view.MainUI;
import fr.ecole42.swingy.view.ViewMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class Player {
	private Hero currentHero;
	private Artifact lastDropped;
	private List<Hero> allHeroes;
	private GameMap currentGameMap;
	private final MainUI mainUI;

	@Autowired
	public Player(@Lazy MainUI mainUI) {
		this.mainUI = mainUI;
	}

	public Hero getCurrentHero() {
		return currentHero;
	}

	public void setCurrentHero(Hero currentHero) {
		this.currentHero = currentHero;
		if (currentHero != null)
			mainUI.play();
	}

	public void setView(ViewMode view) {
		mainUI.setViewMode(view);
	}

	public List<Hero> getAllHeroes() {
		return allHeroes;
	}

	public boolean currentPlayerFight(Enemy enemy) {
		return currentHero.fight(enemy);
	}

	public boolean isChosenCurrentHero() {
		return currentHero != null;
	}

	public void setAllHeroes(List<Hero> allHeroes) {
		this.allHeroes = allHeroes;
	}

	public GameMap getNewGameMap() {
		currentGameMap = new GameMap(currentHero, mainUI);
		return currentGameMap;
	}

	public GameMap getCurrentGameMap() {
		return currentGameMap;
	}

	public void restartGame() {
		mainUI.restartGame();
	}

	/*	GAMEPLAY */
	public void increaseExp(int exp) {
		getCurrentHero().increaseExp(exp);
		mainUI.refresh();
	}

	public void movePlayer(GameMap.directions direction) {
		currentGameMap.movePlayer(direction);
		if (currentHero != null)
			mainUI.refresh();
	}

	public Artifact getLastDropped() {
		return lastDropped;
	}

	public void deleteLastDropped() {
		lastDropped = null;
		mainUI.refresh();
	}

	public void generateArtifact(Enemy enemy) {
		if (new Random().nextBoolean()) {
			switch (enemy.getEnemyType()) {
				case GOLEM -> lastDropped = new Armor(new Random().nextInt(enemy.getDefence() + 5) + 5);
				case GHOST -> lastDropped = new Helm(new Random().nextInt(enemy.getMaxHp() + 5) + 5);
				case KOBOLD -> lastDropped = new Weapon(new Random().nextInt(enemy.getAttack() + 5) + 5);
			}
		}
	}
}
