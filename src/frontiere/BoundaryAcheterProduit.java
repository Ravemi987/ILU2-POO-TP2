package frontiere;

import java.util.Scanner;

import controleur.ControlAcheterProduit;

public class BoundaryAcheterProduit {
	private Scanner scan = new Scanner(System.in);
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		if (!controlAcheterProduit.isAcheteur(nomAcheteur)) {
			System.out.println("Je suis désolée " + nomAcheteur
					+ " mais il faut être un habitant de notre village pour acheter ici.");
		} else {
			System.out.println("Quel produit voulez-vous acheter ?");
			String produit = scan.next();
			String[] vendeursProduit = controlAcheterProduit.getVendeursProduit(produit);
			if (vendeursProduit == null) {
				System.out.println("Désolé, personne ne vend ce produit sur le marché.");
			} else {
				StringBuilder question = new StringBuilder(
						"Chez quel commerçant voulez-vous acheter des " + produit + " ?\n");
				int nbVendeurs;
				for (nbVendeurs = 0; nbVendeurs < vendeursProduit.length; nbVendeurs++) {
					question.append(nbVendeurs + 1 + " - " + " " + vendeursProduit[nbVendeurs] + "\n");
				}

				int choixUtilisateur;
				do {
					choixUtilisateur = Clavier.entrerEntier(question.toString());
				} while (choixUtilisateur < 1 || choixUtilisateur > nbVendeurs + 1);
				String vendeur = vendeursProduit[choixUtilisateur - 1];

				System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " + vendeur + ".\n");
				int nbProduit = Clavier
						.entrerEntier("Bonjour " + nomAcheteur + ". Combien de " + produit + " voulez-vous acheter ?");
				int quantite = controlAcheterProduit.getEtalVendeur(vendeur).acheterProduit(nbProduit);
				if (quantite == 0) {
					System.out.println(nomAcheteur + " veut acheter " + nbProduit + " " + produit
							+ ", malheureusement il n'y en a plus !");
				} else if (quantite < nbProduit) {
					System.out.println(nomAcheteur + " veut acheter " + nbProduit + " " + produit + ", malheureusement "
							+ vendeur + " n'en a plus que " + quantite + ". " + nomAcheteur
							+ " achète tout le stock de " + vendeur + ".");
				} else {
					System.out.println(nomAcheteur + " achète " + nbProduit + " " + produit + " à " + vendeur + ".");
				}
			}
		}
	}
}
