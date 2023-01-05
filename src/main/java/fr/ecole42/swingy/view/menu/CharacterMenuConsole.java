package fr.ecole42.swingy.view.menu;

import fr.ecole42.swingy.controller.GameController;
import fr.ecole42.swingy.view.Logger;
import fr.ecole42.swingy.view.ViewMode;
import fr.ecole42.swingy.view.Visible;

import java.util.Objects;
import java.util.Scanner;

public class CharacterMenuConsole implements CharacterMenu, Visible {
    private GameController gameController;

    public CharacterMenuConsole(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public Character createCharacter() {
        return null;
    }

    @Override
    public void show() {
        Logger.setActive(true);
        Logger.print("console show");
        System.out.print("Enter a string : ");
        Scanner scanner = new Scanner(System. in);
        String inputString = scanner. nextLine();
        if (Objects.equals(inputString, "gui"))
            gameController.changeViewMode(ViewMode.GUI);
    }

    @Override
    public void hide() {
        Logger.print("console hide");
        Logger.setActive(false);
    }
}
