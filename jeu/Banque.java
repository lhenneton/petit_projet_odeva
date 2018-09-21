package jeu;

public class Banque {
	
	private int fond;
	private int fondMin;
	
	public Banque(int fond, int fondMin){
		this.fond = fond;
		this.fondMin = fondMin;
	}
	
	public void crediter(int somme){
		fond += somme;
	}
	
	public boolean est_solvable(){
		if(this.fondMin < this.fond){
			return true;
		}
		return false;
	}
	
	public void debiter(int somme){
		fond -= somme;
	}
	
	public int getFond(){
		return this.fond;
	}
}