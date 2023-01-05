package fr.ecole42.swingy.view.menu;

import fr.ecole42.swingy.controller.GameController;
import fr.ecole42.swingy.view.ViewMode;
import fr.ecole42.swingy.view.Visible;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterMenuGUI implements CharacterMenu, Visible {
    JFrame frame = new JFrame("Test Frame");
    private GameController gameController;

    public CharacterMenuGUI(GameController gameController) {
        this.gameController = gameController;
        buildGui();
    }

    @Override
    public Character createCharacter() {
        return null;
    }

    @Override
    public void show() {
        frame.setVisible(true);
    }

    @Override
    public void hide() {
        frame.setVisible(false);
    }

    private void buildGui(){
        buildContent(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }

    private void buildContent(JFrame aFrame){
        JPanel panel = new JPanel();
        panel.add(new JLabel("Hello"));
        JButton ok = new JButton("OK");
        ok.addActionListener(new ShowDialog(aFrame, gameController));
        panel.add(ok);
        aFrame.getContentPane().add(panel);
    }

    private static final class ShowDialog implements ActionListener {
        /** Defining the dialog's owner JFrame is highly recommended. */
        ShowDialog(JFrame aFrame, GameController gameController){
            fFrame = aFrame;
            this.gameController = gameController;
        }
        @Override public void actionPerformed(ActionEvent aEvent) {
            this.gameController.changeViewMode(ViewMode.CONSOLE);
        }
        private JFrame fFrame;
        private GameController  gameController;
    }
}
