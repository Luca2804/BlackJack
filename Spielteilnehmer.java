package BlackJack;

import java.util.*;

public abstract class Spielteilnehmer {

	List<String> kartefuerHand = new ArrayList<String>();
    String name;
    int geld;
    String Karte;
    int Kartenwert;
	boolean verloren;
    
	public void setName(String name ) {
		
		this.name = name;
		
	}
	
	public void setGeld(int geld) {
		
		this.geld = geld;
		
	}
    
	public void HandKarteHinzufuegen(String karte) {
		
		kartefuerHand.add(karte);
		 		
	}
	
	public List<String> HandkarteErhalten() {
		
		return kartefuerHand;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public int getPunkte() {
		
		return Kartenwert; 
	}
	
	public String getKarte() {
		
		return Karte;
		
	}
	

	
}
