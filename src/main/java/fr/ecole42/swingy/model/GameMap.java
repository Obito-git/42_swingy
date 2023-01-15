package fr.ecole42.swingy.model;

import fr.ecole42.swingy.model.hero.Hero;

import java.util.Arrays;

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

	public GameMap(Hero hero) {
		this.hero = hero;
		size = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);
		map = new char[size][size];
		for (int i = 0; i < size; i++)
			Arrays.fill(map[i], FIELD_EMPTY);
		currentX = currentY = size / 2;
		map[currentY][currentX] = FIELD_PLAYER;
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
}
