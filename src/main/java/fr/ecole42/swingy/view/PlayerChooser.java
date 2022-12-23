package fr.ecole42.swingy.view;

public abstract class PlayerChooser {

	public PlayerChooser() {
	}

	protected abstract void showSavedPlayers();
	protected abstract void createNewPlayer();

	void saveNewPlayer() {

	}
}
