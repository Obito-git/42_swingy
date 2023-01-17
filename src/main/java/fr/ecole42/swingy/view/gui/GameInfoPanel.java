package fr.ecole42.swingy.view.gui;

import fr.ecole42.swingy.controller.Controller;
import fr.ecole42.swingy.model.hero.Hero;
import fr.ecole42.swingy.model.hero.HeroDirector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GameInfoPanel extends JPanel implements ItemListener {
	private final Controller controller;
	private final JFrame frame;
	private final JButton submit;
	private final JButton changeUI;
	private final JButton save;
	private HeroInfoPanel heroInfoPanel;
	private JComboBox<String> heroesNamesBox;
	private List<Hero> heroes;
	private final List<String> heroesName = new ArrayList<>();




	public GameInfoPanel(Controller controller, JFrame frame, JButton submit, JButton changeUI, JButton save) {
		this.frame = frame;
		this.controller = controller;
		this.heroes = this.controller.getAllHeroes();
		this.submit = submit;
		this.changeUI = changeUI;
		this.heroInfoPanel = new HeroInfoPanel();
		this.save = save;
		setBackground(Color.GRAY);
		setLayout(new GridLayout(10, 1));

		buildChooseHero();
	}

	private void buildChooseHero() {
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
		add(changeUI);

		updateUI();
	}

	public void inGamePlayerInfo(Hero hero) {
		removeAll();
		heroInfoPanel = new HeroInfoPanel();
		heroInfoPanel.updateInfo(hero);
		add(heroInfoPanel);
		add(changeUI);
		add(save);
		setFocusable(false);
		updateUI();
	}

	private void addCreateButton() {
		JButton button = new JButton("Create");
		JDialog dialog = createDialog();
		button.addActionListener(e -> dialog.setVisible(true));
		add(button);
	}

	private JDialog createDialog() {
		final JDialog modelDialog = new JDialog(frame, "Create Hero",
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
				controller.saveNewHero(created);
				modelDialog.setVisible(false);
				buildChooseHero();
			} catch (Exception exception) {
				errorLabel.setText("Name is already used");
				exception.printStackTrace();
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
			heroInfoPanel.updateInfo(heroes.get(heroesNamesBox.getSelectedIndex()));

			heroesNamesBox.addItemListener(this);
			add(heroInfoPanel);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		heroInfoPanel.updateInfo(heroes.get(heroesNamesBox.getSelectedIndex()));
	}

	public Hero getChosenHero() {
		if (heroesNamesBox == null)
			return null;
		return heroes.get(heroesNamesBox.getSelectedIndex());
	}
}
