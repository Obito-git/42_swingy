package fr.ecole42.swingy.view;

import fr.ecole42.swingy.controller.Controller;
import fr.ecole42.swingy.view.console.ConsoleUI;
import fr.ecole42.swingy.view.gui.GraphicalUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainUI {
    private ViewMode viewMode;
    private Controller controller;
    private final ConsoleUI consoleUI;
    private final GraphicalUI graphicalUI;


    @Autowired
    public MainUI(Controller controller) {
        this.controller = controller;
        consoleUI = new ConsoleUI(controller);
        graphicalUI = new GraphicalUI(controller);

        viewMode = ViewMode.CONSOLE;
    }

    private UserInterface getActiveUI() {
        if (viewMode == ViewMode.CONSOLE)
            return consoleUI;
        return graphicalUI;
    }

    public void startGame() {
        getActiveUI().showHeroes();
    }

    public void play() {
        getActiveUI().play();
    }

    public void setViewMode(ViewMode viewMode) {
        getActiveUI().disableOutput();
        this.viewMode = viewMode;
        getActiveUI().enableOutput();
        if (controller.heroIsChosen())
            play();
        else
            startGame();
    }

    public void refresh() {
        getActiveUI().refresh();
    }
}
