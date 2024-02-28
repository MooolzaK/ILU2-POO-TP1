package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	
	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.marche = new Marche(nbEtals);
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	private static class Marche{
		Etal etals[];
		private Marche(int nbEtals) {
			this.etals = new Etal[nbEtals];
		}
		
		void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit,int nbProduit) {
			this.etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		int trouverEtalLibre() {
			for (int i=0; i<this.etals.length;i++) {
				   if (!this.etals[i].isEtalOccupe()) {
					   return i;
				   }
				}
			return -1;
		}
		
		Etal[] trouverEtals(String produit) {
			int taille = 0;
			for (int i=0; i<this.etals.length;i++) {
				   if (this.etals[i].contientProduit(produit)) {
					   taille ++;
				   }
				}
			int indice = 0;
			Etal eProduit[] = new Etal[taille];
			for (int i=0; i<this.etals.length;i++) {
				   if (this.etals[i].contientProduit(produit)) {
					   eProduit[indice] = this.etals[i];
					   indice++;
				   }
				}
			return eProduit;
		}
		
		Etal trouverVendeur(Gaulois gaulois) {
			for (int i=0; i<this.etals.length;i++) {
				   if (this.etals[i].getVendeur() == gaulois) {
					   return this.etals[i];
				   }
				}
			return null;
		}
		
		String afficherMarche() {
			String affichage = "";
			int vide = 0;
			for (int i=0; i<this.etals.length;i++) {
				   if (this.etals[i].isEtalOccupe()) {
					   affichage += this.etals[i].afficherEtal();
				   } else {
					   vide ++;
				   }
				}
			if (vide > 0) {
				affichage += "Il reste " + vide + "étals non utilisés dans le marché.\n";
			}
			return affichage;
		}				
	}	
}