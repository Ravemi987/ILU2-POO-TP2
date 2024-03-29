package controleur;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlEmmenagerTest {

	ControlEmmenager control;

	@BeforeEach
	void setUp() throws Exception {
		Village village = new Village("Village de test", 10, 10);
		Chef chef = new Chef("Chef", 10, village);
		village.setChef(chef);
		control = new ControlEmmenager(village);
	}

	@Test
	void testControlEmmenager() {
		assertTrue(control != null);
	}

	@Test
	void testAjouterDruide() {
		assertFalse(control.isHabitant("C"));
		control.ajouterDruide("C", 1, 1, 1);
		assertTrue(control.isHabitant("C"));

		for (int i = 0; i < 10; i++) {
			control.ajouterDruide("D_" + i, 1, 1, 1);
		}
		control.ajouterDruide("E", 1, 1, 1);
		assertFalse(control.isHabitant("E"));
	}

	@Test
	void testAjouterGaulois() {
		assertFalse(control.isHabitant("A"));

		control.ajouterGaulois("A", 10);
		assertTrue(control.isHabitant("A"));

		assertTrue(control.isHabitant("Chef"));

		for (int i = 0; i < 10; i++) {
			control.ajouterGaulois("H_" + i, 10);
		}
		control.ajouterGaulois("B", 10);
		assertFalse(control.isHabitant("B"));
	}

}
