package frontiere;

import controleur.ControlAfficherMarche;

public class BoundaryAfficherMarche {
	private ControlAfficherMarche controlAfficherMarche;

	public BoundaryAfficherMarche(ControlAfficherMarche controlAfficherMarche) {
		this.controlAfficherMarche = controlAfficherMarche;
	}

	public void afficherMarche(String nomAcheteur) {
		String[] infosMarche = controlAfficherMarche.donnerInfosMarche();
		if (infosMarche.length == 0) {
			System.out.println("Le marché est vide, revenez plus tard.");
		} else {
			System.out.println(nomAcheteur + ", vous trouverez au marché :");
			int i = 0;
			while (i < infosMarche.length) {
				String vendeur = infosMarche[i];
				i++;
				String quantite = infosMarche[i];
				i++;
				String produit = infosMarche[i];
				System.out.println("- " + vendeur + " qui vend " + quantite + " " + produit + ".");
				i++;
			}
		}
	}
}
