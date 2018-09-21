package jeu;

import exception.DebitImpossibleException;
import exception.JeuFermeException;

public class Jeu {

	private Banque labanque;

	public Jeu(Banque labanque) {
		this.labanque = labanque;
	}

	public void jouer(Joueur joueur, De de1, De de2) throws JeuFermeException {
		int mise = joueur.mise();
		// on verifie que la banque est ouverte
		if (estOuvert()) {
			try {
				joueur.debiter(mise);
				labanque.crediter(mise);
			} catch (DebitImpossibleException e) {
				e.printStackTrace();
			}
			// si la somme des des vaut 7, il gagne
			if (de1.lancer() + de2.lancer() == 7) {
				labanque.debiter(mise * 2);
				joueur.crediter(mise * 2);
			}
		} else {
			fermer();
		}
	}

	public void fermer() throws JeuFermeException {
		throw new JeuFermeException();
	}

	public boolean estOuvert(){
		if(labanque.est_solvable()){
			return true;
		}
		return false;
	}
}
