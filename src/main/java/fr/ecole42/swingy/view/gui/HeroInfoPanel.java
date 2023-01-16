package fr.ecole42.swingy.view.gui;

import fr.ecole42.swingy.model.hero.Hero;

import javax.swing.*;
import java.awt.*;

public class HeroInfoPanel extends JPanel {
	private final JLabel heroLevel = new JLabel();
	private final JLabel heroExp = new JLabel();
	private final JLabel heroAttack = new JLabel();
	private final JLabel heroDefence = new JLabel();
	private final JLabel heroMaxHp = new JLabel();
	private final JLabel heroHp = new JLabel();
	private final JLabel heroType = new JLabel();
	private final JLabel armor = new JLabel();
	private final JLabel weapon = new JLabel();
	private final JLabel helm = new JLabel();

	public HeroInfoPanel() {
		setSize(new Dimension(400, 400));
		setLayout(new GridLayout(4, 2));

		add(heroLevel);
		add(heroExp);
		add(heroAttack);
		add(heroDefence);
		add(heroMaxHp);
		add(heroHp);
		add(heroType);
		add(weapon);
		add(armor);
		add(helm);
	}
	
	public void updateInfo(Hero hero) {
		heroLevel.setText("Level: " + hero.getLevel());
		heroExp.setText("Experience: " + hero.getExperience());
		heroAttack.setText("Attack: " + hero.getAttack());
		heroDefence.setText("Defence: " + hero.getDefence());
		heroMaxHp.setText("Max Hp: " + hero.getMaxHp());
		heroHp.setText("Hp: " + hero.getHp());
		heroType.setText("Class: " + hero.getHeroType().toString());
		weapon.setText(hero.getWeapon().toString());
		armor.setText(hero.getArmor().toString());
		helm.setText(hero.getHelm().toString());
	}
}
