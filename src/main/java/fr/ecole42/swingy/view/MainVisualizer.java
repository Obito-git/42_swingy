package fr.ecole42.swingy.view;

import fr.ecole42.swingy.controller.GameController;
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
    Visible currentView;

    @Autowired
    public MainVisualizer(@Lazy GameController gameController) {
        this.gameController = gameController;
        this.currentStage = Stage.MENU;
        this.viewMode = ViewMode.CONSOLE;
    }

    public void setViewMode(ViewMode viewMode) {
        if (currentView != null)
            this.currentView.hide();
        this.viewMode = viewMode;
        updateCurrentView();
        currentView.show();
    }

    public void start() {
        updateCurrentView();
        this.currentView.show();
    }

    private void updateCurrentView() {
        if (currentStage == Stage.MENU) {
            if (viewMode == ViewMode.CONSOLE)
                currentView = new CharacterMenuConsole(gameController);
            else
                currentView = new CharacterMenuGUI(gameController);
        }
    }
}
