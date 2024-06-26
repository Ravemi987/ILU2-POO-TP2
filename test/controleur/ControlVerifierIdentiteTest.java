package controleur;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlVerifierIdentiteTest {

	ControlVerifierIdentite control;
	ControlEmmenager controlEmmenager;

	@BeforeEach
	void setUp() throws Exception {
		Village village = new Village("Village de test", 10, 10);
		Chef chef = new Chef("Chef", 10, village);
		village.setChef(chef);
		control = new ControlVerifierIdentite(village);
		controlEmmenager = new ControlEmmenager(village);
	}

	@Test
	void testControlVerifierIdentite() {
		assertTrue(control != null);
	}

	@Test
	void testVerifierIdentite() {
		assertTrue(control.verifierIdentite("Chef"));

		assertFalse(control.verifierIdentite("A"));
		controlEmmenager.ajouterGaulois("A", 10);
		assertTrue(control.verifierIdentite("A"));

		for (int i = 0; i < 10; i++) {
			controlEmmenager.ajouterGaulois("H_" + i, 10);
		}
		controlEmmenager.ajouterGaulois("B", 10);

		assertFalse(control.verifierIdentite("B"));
	}

}
