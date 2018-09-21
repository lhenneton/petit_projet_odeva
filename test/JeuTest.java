package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import exception.DebitImpossibleException;
import exception.JeuFermeException;
import jeu.Banque;
import jeu.De;
import jeu.Jeu;
import jeu.Joueur;

public class JeuTest {
	
	//test joueur qui gagne
	@Test
	public void testJouerLeJoueurFait7() throws JeuFermeException, DebitImpossibleException {
		Banque b = mock(Banque.class);
		Joueur j = mock(Joueur.class);
		De d1 = mock(De.class);
		De d2 = mock(De.class);
		
		Jeu jeu = new Jeu(b);
		
		when(b.est_solvable()).thenReturn(true);
		when(d1.lancer()).thenReturn(4);
		when(d2.lancer()).thenReturn(3);
		when(j.mise()).thenReturn(100);
		
		jeu.jouer(j, d1, d2);
		
		verify(b).crediter(100);
		verify(j).debiter(100);
		verify(b).debiter(200);
		verify(j).crediter(200);
		
	}
	
	//test joueur qui perd
	@Test
	public void testJouerLeJoueurNeFaitPas7() throws JeuFermeException, DebitImpossibleException {
		Banque b = mock(Banque.class);
		Joueur j = mock(Joueur.class);
		De d1 = mock(De.class);
		De d2 = mock(De.class);
		
		Jeu jeu = new Jeu(b);
		
		when(b.est_solvable()).thenReturn(true);
		when(d1.lancer()).thenReturn(3);
		when(d2.lancer()).thenReturn(3);
		when(j.mise()).thenReturn(100);
		
		jeu.jouer(j, d1, d2);
		
		verify(b).crediter(100);
		verify(j).debiter(100);
	}
	// test jeu ferme
	@Test(expected = JeuFermeException.class)
	public void testBanqueNonSolvable() throws JeuFermeException, DebitImpossibleException {
		Banque b = mock(Banque.class);
		Joueur j = mock(Joueur.class);
		De d1 = mock(De.class);
		De d2 = mock(De.class);
		
		Jeu jeu = new Jeu(b);
		
		when(b.est_solvable()).thenReturn(false);
		
		jeu.jouer(j, d1, d2);
	}
	
	//test joueur non solvable
	@Test
	public void testJoueurDebitImpossible() throws JeuFermeException, DebitImpossibleException {
		Banque b = mock(Banque.class);
		Joueur j = mock(Joueur.class);
		De d1 = mock(De.class);
		De d2 = mock(De.class);
		
		Jeu jeu = new Jeu(b);
		
		when(b.est_solvable()).thenReturn(true);
		doThrow(new DebitImpossibleException()).when(j).debiter(anyInt());

		jeu.jouer(j, d1, d2);
	}
	
	//test joueur qui gagne avec banque
	@Test
	public void testBanqueIntegrationJouerLeJoueurFait7() throws JeuFermeException, DebitImpossibleException{
		Joueur j = mock(Joueur.class);
		De d1 = mock(De.class);
		De d2 = mock(De.class);
		
		Banque b = new Banque(1000, 100);
		Jeu jeu = new Jeu(b);
		
		when(d1.lancer()).thenReturn(4);
		when(d2.lancer()).thenReturn(3);
		when(j.mise()).thenReturn(100);
		
		jeu.jouer(j, d1, d2);
		
		verify(j).debiter(100);
		verify(j).crediter(200);
		assertEquals(b.getFond(),900);
	}
	
	//test joueur qui perd avec banque
	@Test
	public void testBanqueIntegrationJouerLeJoueurNeFaitPas7() throws JeuFermeException, DebitImpossibleException{
		Joueur j = mock(Joueur.class);
		De d1 = mock(De.class);
		De d2 = mock(De.class);
		
		Banque b = new Banque(1000, 100);
		Jeu jeu = new Jeu(b);
		
		when(d1.lancer()).thenReturn(3);
		when(d2.lancer()).thenReturn(3);
		when(j.mise()).thenReturn(100);
		
		jeu.jouer(j, d1, d2);
		
		verify(j).debiter(100);
		assertEquals(b.getFond(),1100);
	}
	
	//test jeu ferme avec banque
	@Test(expected = JeuFermeException.class)
	public void testBanqueIntegrationBanqueNonSolvable() throws JeuFermeException, DebitImpossibleException{
		Joueur j = mock(Joueur.class);
		De d1 = mock(De.class);
		De d2 = mock(De.class);
		
		Banque b = new Banque(0, 100);
		Jeu jeu = new Jeu(b);
		
		when(d1.lancer()).thenReturn(4);
		when(d2.lancer()).thenReturn(3);
		when(j.mise()).thenReturn(100);
		
		jeu.jouer(j, d1, d2);
	}
	
}
