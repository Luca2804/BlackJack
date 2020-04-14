package BlackJack;

import java.util.*;

public class Spieler extends Spielteilnehmer {
    public Spieler() {
        super();
        
        
    }

    
    public void HandkartenWert() {
    	
    	Karte = kartefuerHand.get(kartefuerHand.size() - 1);
    	Karte = Karte.substring(0,1);
    	
    	if (Karte.equals("A")) {
    		Kartenwert = Kartenwert + Asswertwaehlen();
    	}
    	else if (Karte.equals("K") || Karte.equals("Q") || Karte.equals("J")) {
    		Kartenwert = Kartenwert + 10;
    	}
    	else {
    		switch(Karte) {
    		
    		case "2":
    		Kartenwert = Kartenwert + 2;
    		break;
    		
    		case "3":
        	Kartenwert = Kartenwert + 3;
        	break;
        		
    		case "4":
        	Kartenwert = Kartenwert + 4;
        	break;
        		
    		case "5":
        	Kartenwert = Kartenwert + 5;
        	break;
        		
    		case "6":
        	Kartenwert = Kartenwert + 6;
        	break;
        		
    		case "7":
        	Kartenwert = Kartenwert + 7;
        	break;
        		
    		case "8":
        	Kartenwert = Kartenwert + 8;
        	break;
        		
    		case "9":
        	Kartenwert = Kartenwert + 9;
        	break;
        		
    		case "10":
        	Kartenwert = Kartenwert + 10;
        	break;
        		
        	default:
        	break;
    		}
    	}
    }
    
    public int Asswertwaehlen() {
    	
    	Scanner scan = new Scanner(System.in);
    	int Asswert = 0;
    	
    	do {
    		System.out.println(name + "Handkarte:" + kartefuerHand);
    		System.out.println("Welchen Wert soll das Ass hanben ");
    		Asswert = scan.nextInt();
    		if (Asswert != 11 && Asswert != 1) {
    			System.out.println("Ungueltig Eingabe");
    		}
    	}while(Asswert != 1 && Asswert != 11);
    	
    	return Asswert;
    }

	public void verloren(boolean verloren) {
		
		this.verloren = verloren;
		this.verloren = true;
	}

}


