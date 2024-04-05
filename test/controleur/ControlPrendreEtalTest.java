package controleur;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlPrendreEtalTest {

	ControlPrendreEtal control;
	ControlEmmenager controlEmmenager;
	ControlVerifierIdentite controlIdentite;
	ControlLibererEtal controlLibererEtal;
	ControlTrouverEtalVendeur controlTrouvVendeur;

	@BeforeEach
	void setUp() throws Exception {
		Village village = new Village("Village de test", 10, 10);
		Chef chef = new Chef("Chef", 10, village);
		village.setChef(chef);
		controlIdentite = new ControlVerifierIdentite(village);
		controlEmmenager = new ControlEmmenager(village);
		controlTrouvVendeur = new ControlTrouverEtalVendeur(village);
		controlLibererEtal = new ControlLibererEtal(controlTrouvVendeur);
		control = new ControlPrendreEtal(controlIdentite, village);
	}

	@Test
	void testControlPrendreEtal() {
		assertTrue(control != null);
	}

	@Test
	void testResteEtals() {
		assertTrue(control.resteEtals());
		controlEmmenager.ajouterGaulois("E", 1);
		controlEmmenager.ajouterGaulois("F", 2);
		control.prendreEtal("Chef", "truc", 2);
		control.prendreEtal("E", "truc", 2);
		control.prendreEtal("F", "truc", 0);
		assertTrue(control.resteEtals());
		for (int i = 0; i < 7; i++) {
			controlEmmenager.ajouterGaulois("K_" + i, i+1);
			control.prendreEtal("K_" + i, "fleurs", i);
		}
		controlEmmenager.ajouterGaulois("Y", 12);
		assertFalse(control.resteEtals());
		control.prendreEtal("Y", "truc", 12);
		controlLibererEtal.libererEtal("E");
		controlLibererEtal.libererEtal("F");
		assertTrue(control.resteEtals());
		
	}

	@Test
	void testPrendreEtal() {
		assertTrue(control.prendreEtal("X", "X", 0) == -1);
		controlEmmenager.ajouterGaulois("E", 1);
		controlEmmenager.ajouterGaulois("F", 2);
		assertFalse(control.estDejaVendeur("E"));
		assertFalse(control.estDejaVendeur("F"));
		assertTrue(control.prendreEtal("E", "truc", 2) != -1);
		assertTrue(control.prendreEtal("F", "truc", 0) != -1);
		control.prendreEtal("Chef", "truc", 2);
		for (int i = 0; i < 7; i++) {
			controlEmmenager.ajouterGaulois("K_" + i, i+1);
			control.prendreEtal("K_" + i, "fleurs", i);
		}
		controlEmmenager.ajouterGaulois("Y", 12);
		assertTrue(control.prendreEtal("Y", "truc", 12) == -1);
		
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
