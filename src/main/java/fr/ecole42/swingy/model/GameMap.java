package fr.ecole42.swingy.model;

import fr.ecole42.swingy.model.hero.Hero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameMap {
	public enum directions {
		North, East, South, West
	}

	private final int size;
	private Hero hero;
	private int currentX;
	private int currentY;
	private char[][] map;
	public static final char FIELD_EMPTY = '0';
	public static final char FIELD_PLAYER = 'P';
	public static final char FIELD_ARTIFACT = 'A';
	public static final char FIELD_ENEMY_GHOST = 'H';
	public static final char FIELD_ENEMY_GOLEM = 'G';
	public static final char FIELD_ENEMY_KOBOLD = 'K';

	public GameMap(Hero hero) {
		this.hero = hero;
		size = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);
		map = new char[size][size];
		for (int i = 0; i < size; i++)
			Arrays.fill(map[i], FIELD_EMPTY);
		currentX = currentY = size / 2;
		map[currentY][currentX] = FIELD_PLAYER;
		putArtifacts();
		putEnemies();
	}

	public void putArtifacts() {
		int artifactCount = hero.getLevel() * 5;
		while (artifactCount > 0) {
			int randX = new Random().nextInt(size);
			int randY = new Random().nextInt(size);
			if (map[randX][randY] == FIELD_EMPTY)
				map[randX][randY] = FIELD_ARTIFACT;
			else
				continue;
			artifactCount--;
		}
	}

	public void putEnemies() {
		int enemyCount = hero.getLevel() * 15;
		while (enemyCount > 0) {
			int randX = new Random().nextInt(size);
			int randY = new Random().nextInt(size);
			if (map[randX][randY] == FIELD_EMPTY) {
				int enemy = new Random().nextInt(3);
				switch (enemy) {
					case 0 -> map[randX][randY] = FIELD_ENEMY_GHOST;
					case 1 -> map[randX][randY] = FIELD_ENEMY_GOLEM;
					case 2 -> map[randX][randY] = FIELD_ENEMY_KOBOLD;
				}
			} else
				continue;
			enemyCount--;
		}
	}

	public void movePlayer(directions direction) {
		switch (direction) {
			case East -> {
				if (currentX + 1 < size) {
					map[currentY][currentX] = FIELD_EMPTY;
					map[currentY][++currentX] = FIELD_PLAYER;
				}
			}
			case West -> {
				if (currentX - 1 >= 0) {
					map[currentY][currentX] = FIELD_EMPTY;
					map[currentY][--currentX] = FIELD_PLAYER;
				}
			}
			case North -> {
				if (currentY - 1 >= 0) {
					map[currentY][currentX] = FIELD_EMPTY;
					map[--currentY][currentX] = FIELD_PLAYER;
				}
			}
			default -> {
				if (currentY + 1 < size) {
					map[currentY][currentX] = FIELD_EMPTY;
					map[++currentY][currentX] = FIELD_PLAYER;
				}
			}
		}
	}

	public char[][] getMap() {
		return map;
	}

	public int getSize() {
		return size;
	}

	@Override
	public String toString() {
		String res = "";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				res = res.concat(String.valueOf(map[i][j])).concat(" ");
			}
			res = res.concat("\n");
		}
		return res;
	}
}
