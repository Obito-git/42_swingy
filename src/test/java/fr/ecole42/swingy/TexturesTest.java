package fr.ecole42.swingy;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TexturesTest {
	private static final String MAGE_ICON = "/icons/mage-icon.png";
	private static final String HUNTER_ICON = "/icons/hunter-icon.png";
	private static final String WARRIOR_ICON = "/icons/warrior-icon.png";


	private static final String GHOST_ICON = "/icons/ghost-icon.png";
	private static final String GOLEM_ICON = "/icons/golem-icon.png";
	private static final String KOBOLD_ICON = "/icons/kobold-icon.png";


	private static final String ARTIFACT_ICON = "/icons/artifact-icon.png";

	@Test
	public void texturesHeroesTest() {
		assertNotNull("Mage icon is not found", getClass().getResource(MAGE_ICON));
		assertNotNull("Hunter icon is not found", getClass().getResource(HUNTER_ICON));
		assertNotNull("Warrior icon is not found", getClass().getResource(WARRIOR_ICON));
	}

	@Test
	public void texturesItemsTest() {
		assertNotNull("Helm icon is not found", getClass().getResource(ARTIFACT_ICON));
	}

	@Test
	public void texturesEnemies() {
		assertNotNull("Ghost icon is not found", getClass().getResource(GHOST_ICON));
		assertNotNull("Golem icon is not found", getClass().getResource(GOLEM_ICON));
		assertNotNull("Kobold icon is not found", getClass().getResource(KOBOLD_ICON));

	}
}
