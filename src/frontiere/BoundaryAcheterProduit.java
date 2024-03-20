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
			System.out.println("Je suis désolée " + nomAcheteur + " mais il faut être un habitant de notre village pour acheter ici.");
		} else {
			System.out.println("Quel produit voulez-vous acheter ?");
			String produit = scan.next();
			String[] vendeursProduit = controlAcheterProduit.getVendeursProduit(produit);
			if (vendeursProduit.length == 0) {
				System.out.println("Désolé, personne ne vend ce produit sur le marché.");
			} else {
				StringBuilder question = new StringBuilder("Chez quel commerçant voulez-vous acheter des " + produit + " ?");
				int nbVendeurs;
				for (nbVendeurs = 0; nbVendeurs < vendeursProduit.length; nbVendeurs++) {
					question.append(nbVendeurs+1 + " - " + " " + vendeursProduit[nbVendeurs] + "\n");
				}
				int choixUtilisateur = -1;
				do {
					choixUtilisateur = Clavier.entrerEntier(question.toString());
				} while (choixUtilisateur < 0 || choixUtilisateur > nbVendeurs);
				System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " + vendeursProduit[choixUtilisateur] + ".");
				int nbProduit = Clavier.entrerEntier("Combien de " + produit + " voulez-vous acheter ?");
				int quantite = controlAcheterProduit.getEtalVendeur(vendeursProduit[choixUtilisateur]).acheterProduit(nbProduit);
				if (quantite == 0) {
					System.out.println(nomAcheteur + " veut acheter " + nbProduit + " " + produit + ", malheureusement il n'y en a plus !");
				} else if (quantite < nbProduit) {
					System.out.println(nomAcheteur + " veut ahchetr " + nbProduit + " " + produit + ", malheureusement " + vendeursProduit[choixUtilisateur]
							+ " n'en a plus que " + quantite + ". " + nomAcheteur + " achète tout le stock de " + vendeursProduit[choixUtilisateur] + ".");
				} else {
					System.out.println(nomAcheteur + " achète " + nbProduit + " à " + vendeursProduit[choixUtilisateur] + ".");
				}
			}
		}
	}
}
