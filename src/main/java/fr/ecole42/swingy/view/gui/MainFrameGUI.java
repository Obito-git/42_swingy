package fr.ecole42.swingy.view.gui;

import fr.ecole42.swingy.controller.Controller;
import fr.ecole42.swingy.model.artifact.Artifact;
import fr.ecole42.swingy.model.enemies.EnemyType;
import fr.ecole42.swingy.view.ViewMode;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainFrameGUI extends JFrame {
    private final Controller controller;
    private static final int WINDOW_WIDTH = 1600;
    private static final int WINDOW_HEIGHT = 1200;
    private static final int INFO_PANEL_WIDTH = 400;
    public static final int GAME_PANEL_WIDTH = 1200;
    private GameInfoPanel gameInfoPanel;
    private GamePanel gamePanel;

    public MainFrameGUI(Controller controller) {
        this.controller = controller;

        setTitle("Swingy");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        JButton submit = new JButton("Choose Hero");
        submit.addActionListener(e -> {
            controller.setCurrentHero(gameInfoPanel.getChosenHero());
        });

        JButton changeUI = new JButton("Change UI");
        changeUI.addActionListener(e -> {
            controller.setView(ViewMode.CONSOLE);
        });

        gameInfoPanel = new GameInfoPanel(controller, this, submit, changeUI);
        gamePanel = new GamePanel(controller);
        gameInfoPanel.setPreferredSize(new Dimension(INFO_PANEL_WIDTH, WINDOW_HEIGHT));
        gamePanel.setPreferredSize(new Dimension(GAME_PANEL_WIDTH, WINDOW_HEIGHT));

        add(gameInfoPanel, BorderLayout.WEST);
        add(gamePanel, BorderLayout.EAST);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void reload() {
        gameInfoPanel.inGamePlayerInfo(controller.getHeroCopy());
        gamePanel.build(controller.getCurrentGameMap());
    }

    public void showHeroes() {
        setVisible(true);
    }

    public void buildMap() {
        gameInfoPanel.inGamePlayerInfo(controller.getHeroCopy());
        gamePanel.requestFocusInWindow();
        SwingUtilities.updateComponentTreeUI(this);
        gamePanel.build(controller.getNewGameMap());
    }

    public boolean startFightWindow(EnemyType enemyType) {
        AtomicBoolean fightRes = new AtomicBoolean(false);
        JDialog acceptOrRun = new JDialog(this, "Wanna fight?", true);
        acceptOrRun.setLocationRelativeTo(this);
        acceptOrRun.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JDialog fightResultDialog = new JDialog(this, "Result", true);

        JButton accept = new JButton("Accept");
        accept.addActionListener(e -> {
            fightRes.set(controller.fightSimulation(enemyType));
            acceptOrRun.setVisible(false);
            setUpFightResult(fightResultDialog, fightRes.get());
        });
        JButton run = new JButton("Run");
        run.addActionListener(e -> {
            if (new Random().nextBoolean()) {
                fightRes.set(controller.fightSimulation(enemyType));
                acceptOrRun.setVisible(false);
                setUpFightResult(fightResultDialog, fightRes.get());
            } else
                fightResultDialog.add(new JLabel("You ran away"));
            acceptOrRun.setVisible(false);
        });
        acceptOrRun.setSize(300, 300);
        acceptOrRun.setLayout(new GridLayout(1, 2));
        acceptOrRun.add(accept);
        acceptOrRun.add(run);
        acceptOrRun.setVisible(true);
        return fightRes.get();
    }

    private void setUpFightResult(JDialog fightResult, boolean result) {
        fightResult.setLocationRelativeTo(this);
        fightResult.setSize(500, 500);
        fightResult.setLayout(new GridLayout(2, 2));
        if (result) {
            fightResult.add(new JLabel("You won"));
            Artifact lastDropped = controller.getLastDroppedArtifact();
            if (lastDropped != null) {
                fightResult.add(new JLabel(lastDropped.toString()));
                JButton accept = new JButton("Accept");
                JButton decline = new JButton("Decline");
                accept.addActionListener(e -> {
                    fightResult.setVisible(false);
                    controller.acceptNewArtifact();
                });

                decline.addActionListener(e -> {
                    fightResult.setVisible(false);
                    controller.refuseNewArtifact();
                });
                fightResult.add(accept);
                fightResult.add(decline);
                fightResult.setVisible(true);
            }
        } else {
            fightResult.setVisible(false);
            controller.restartGame();
        }

    }
}
