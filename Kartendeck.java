package BlackJack;

import java.util.*;

public class Kartendeck {

	String[] kartenart = {"Herz","Karo","Pik","Kreuz"};
	String[] bildkarten = {"King","Queen","Jack","Ass"};
	
	List<String> kartendeck = new ArrayList<String>();
	int anzahlDecks;
	
	public Kartendeck(int anzahlDecks) {
		this.anzahlDecks = anzahlDecks;
	}

	public void KartendeckErstellen() {
		
		for (int i = 0; i < anzahlDecks; i++) {
			for(int j = 0; j < 4; j++) {
				for (int k = 0; k < 9; k++) {
					kartendeck.add(String.valueOf(k+2) + " " + kartenart[j]);
				}
				for (int l = 0; l < 3; l++) {
					kartendeck.add(bildkarten[l] +  " " + kartenart[j]);
				}
			}
		}
		Collections.shuffle(kartendeck);
	}
	
	  public String giveNextCard(){
	        String nextCard = kartendeck.get(0);
	        kartendeck.remove(0);
	        return nextCard;
	    }
	  
	    public List<String> getKartendeck() {
	        return kartendeck;
	    }
	
}

