package fr.ecole42.swingy.model;

import fr.ecole42.swingy.model.hero.Hero;
import fr.ecole42.swingy.view.MainUI;
import fr.ecole42.swingy.view.ViewMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Player {
	private Hero currentHero;
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
		mainUI.play();
	}

	public void setView(ViewMode view) {
		mainUI.setViewMode(view);
	}

	public List<Hero> getAllHeroes() {
		return allHeroes;
	}

	public void setAllHeroes(List<Hero> allHeroes) {
		this.allHeroes = allHeroes;
	}

	public GameMap getNewGameMap() {
		currentGameMap = new GameMap(currentHero);
		return currentGameMap;
	}

	public GameMap getCurrentGameMap() {
		return currentGameMap;
	}

	public void movePlayer(GameMap.directions direction) {
		currentGameMap.movePlayer(direction);
		mainUI.refresh();
	}
}
