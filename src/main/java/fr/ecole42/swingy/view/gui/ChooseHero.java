package fr.ecole42.swingy.view.gui;

import fr.ecole42.swingy.controller.Controller;
import fr.ecole42.swingy.model.hero.Hero;
import fr.ecole42.swingy.model.hero.HeroDirector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ChooseHero extends JPanel implements ItemListener {
	private final Controller controller;
	private final JFrame frame;
	private final JButton submit;
	private JComboBox<String> heroesNamesBox;
	private List<Hero> heroes;
	private final List<String> heroesName = new ArrayList<>();

	private JLabel heroLevel = new JLabel();
	private JLabel heroExp = new JLabel();
	private JLabel heroAttack = new JLabel();
	private JLabel heroDefence = new JLabel();
	private JLabel heroHp = new JLabel();
	private JLabel heroType = new JLabel();


	public ChooseHero(Controller controller, JFrame frame, JButton submit) {
		this.frame = frame;
		this.controller = controller;
		this.heroes = this.controller.getAllHeroes();
		this.submit = submit;
		setBackground(Color.GRAY);
		setLayout(new GridLayout(10, 1));

		build();
	}

	private void build() {
		removeAll();
		heroes.clear();
		heroes = this.controller.getAllHeroes();
		heroesName.clear();
		if (!heroes.isEmpty()) {
			for (Hero hero : heroes)
				heroesName.add(hero.getName());
			addHeroesNamesPanel();
			addHeroesDescriptionPanel();
		}
		addCreateButton();
		if (!heroes.isEmpty())
			add(submit);

		updateUI();
	}

	private void addCreateButton() {
		JButton button = new JButton("Create");
		JDialog dialog = createDialog();
		button.addActionListener(e -> dialog.setVisible(true));
		add(button);
	}

	private JDialog createDialog() {
		final JDialog modelDialog = new JDialog(frame, "Swing Tester",
				Dialog.ModalityType.DOCUMENT_MODAL);
		modelDialog.setBounds(132, 132, 300, 200);
		Container dialogContainer = modelDialog.getContentPane();
		dialogContainer.setLayout(new GridLayout(5, 1));
		JTextField jTextField = new JTextField(1);
		JLabel errorLabel = new JLabel();
		JComboBox<String> comboBox = new JComboBox<>(new String[]{"Mage", "Hunter", "Warrior"});
		dialogContainer.add(new JLabel("Character name:"));
		dialogContainer.add(jTextField);
		dialogContainer.add(comboBox);
		dialogContainer.add(errorLabel);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(e -> {
			Hero created;
			if (comboBox.getSelectedIndex() == 0)
				created = HeroDirector.buildMage(jTextField.getText());
			else if (comboBox.getSelectedIndex() == 1)
				created = HeroDirector.buildHunter(jTextField.getText());
			else
				created = HeroDirector.buildWarrior(jTextField.getText());
			if (created == null) {
				errorLabel.setText("Name must be from 2 to 10 characters");
				return;
			}
			try {
				controller.save(created);
				modelDialog.setVisible(false);
				build();
			} catch (Exception exception) {
				errorLabel.setText("Name is already used");
			}
		});

		panel1.add(okButton);
		dialogContainer.add(panel1, BorderLayout.SOUTH);

		return modelDialog;
	}

	private void addHeroesNamesPanel() {
		if (!heroes.isEmpty()) {
			heroesNamesBox = new JComboBox<>(heroesName.toArray(new String[0]));
			JPanel heroesNamesPanel = new JPanel();
			heroesNamesPanel.setSize(400, 100);
			heroesNamesPanel.add(heroesNamesBox);

			add(heroesNamesPanel);
		}
	}

	private void addHeroesDescriptionPanel() {
		if (!heroes.isEmpty()) {
			heroLevel = new JLabel("Level: " + heroes.get(heroesNamesBox.getSelectedIndex()).getLevel());
			heroExp = new JLabel("Experience: " + heroes.get(heroesNamesBox.getSelectedIndex()).getExperience());
			heroAttack = new JLabel("Attack: " + heroes.get(heroesNamesBox.getSelectedIndex()).getAttack());
			heroDefence = new JLabel("Defence: " + heroes.get(heroesNamesBox.getSelectedIndex()).getDefence());
			heroHp = new JLabel("Hp: " + heroes.get(heroesNamesBox.getSelectedIndex()).getHp());
			heroType = new JLabel("Class: " + heroes.get(heroesNamesBox.getSelectedIndex()).getHeroType().toString());

			JPanel heroesDescriptionPanel = new JPanel();
			heroesDescriptionPanel.setSize(new Dimension(400, 400));
			heroesDescriptionPanel.setLayout(new GridLayout(3, 2));
			heroesDescriptionPanel.add(heroLevel);
			heroesDescriptionPanel.add(heroExp);
			heroesDescriptionPanel.add(heroAttack);
			heroesDescriptionPanel.add(heroDefence);
			heroesDescriptionPanel.add(heroHp);
			heroesDescriptionPanel.add(heroType);

			heroesNamesBox.addItemListener(this);
			add(heroesDescriptionPanel);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		heroLevel.setText("Level: " + heroes.get(heroesNamesBox.getSelectedIndex()).getLevel());
		heroExp.setText("Experience: " + heroes.get(heroesNamesBox.getSelectedIndex()).getExperience());
		heroAttack.setText("Attack: " + heroes.get(heroesNamesBox.getSelectedIndex()).getAttack());
		heroDefence.setText("Defence: " + heroes.get(heroesNamesBox.getSelectedIndex()).getDefence());
		heroHp.setText("Hp: " + heroes.get(heroesNamesBox.getSelectedIndex()).getHp());
		heroType.setText("Class: " + heroes.get(heroesNamesBox.getSelectedIndex()).getHeroType().toString());
	}

	public Hero getChosenHero() {
		if (heroesNamesBox == null)
			return null;
		return heroes.get(heroesNamesBox.getSelectedIndex());
	}
}
