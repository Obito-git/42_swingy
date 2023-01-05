package fr.ecole42.swingy.view;

import fr.ecole42.swingy.controller.GameController;
import fr.ecole42.swingy.view.menu.CharacterMenu;
import fr.ecole42.swingy.view.menu.CharacterMenuConsole;
import fr.ecole42.swingy.view.menu.CharacterMenuGUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class MainVisualizer {
    public enum Stage {
        MENU,
        EXPLORING,
        FIGHT
    }

    private Stage currentStage;
    private ViewMode viewMode;
    private GameController gameController;
    CharacterMenu characterMenu;

    @Autowired
    public MainVisualizer(@Lazy GameController gameController) {
        this.gameController = gameController;
        this.currentStage = Stage.MENU;
        this.viewMode = ViewMode.CONSOLE;
    }

    public void setViewMode(ViewMode viewMode) {
        if (characterMenu != null)
            this.characterMenu.hide();
        this.viewMode = viewMode;
        this.characterMenu = viewMode == ViewMode.CONSOLE ? new CharacterMenuConsole(gameController) : new CharacterMenuGUI(gameController);
        characterMenu.show();
    }

    public void start() {
        this.characterMenu.show();
    }
}
