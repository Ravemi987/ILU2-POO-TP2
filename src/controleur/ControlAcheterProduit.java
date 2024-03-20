package controleur;

import villagegaulois.Etal;
import villagegaulois.Village;
import personnages.Gaulois;

public class ControlAcheterProduit {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
			ControlTrouverEtalVendeur controlTrouverEtalVendeur,
			Village village) {
		this.village = village;
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}

	public String[] getVendeursProduit(String produit) {
		Gaulois[] vendeurs = village.rechercherVendeursProduit(produit);
		String[] nomsVendeurs = new String[vendeurs.length];
		for (int i = 0; i < nomsVendeurs.length; i++) {
			nomsVendeurs[i] = vendeurs[i].getNom();
		}
		return nomsVendeurs;
	}
	
	public boolean isAcheteur(String nomAcheteur) {
		return controlVerifierIdentite.verifierIdentite(nomAcheteur);
	}
	
	public Etal getEtalVendeur(String nomVendeur) {
		return controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur);
	}
}
