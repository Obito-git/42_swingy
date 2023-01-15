package fr.ecole42.swingy;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TexturesTest {
	private static final String MAGE_ICON = "/icons/mage-icon.png";
	private static final String HUNTER_ICON = "/icons/hunter-icon.png";
	private static final String WARRIOR_ICON = "/icons/warrior-icon.png";

	@Test
	public void texturesTest() {
		assertNotNull("Mage icon is not found", getClass().getResource(MAGE_ICON));
		assertNotNull("Hunter icon is not found", getClass().getResource(HUNTER_ICON));
		assertNotNull("Warrior icon is not found", getClass().getResource(WARRIOR_ICON));
	}
}
