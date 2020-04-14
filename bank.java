package BlackJack;

public class bank extends Spielteilnehmer {
    public bank() {
        super(); 
    }
    
    
    public void HandkartenWert() {
    	
    	Karte = kartefuerHand.get(kartefuerHand.size() - 1);
    	Karte = Karte.substring(0,1);
    	
    	if (Karte.equals("A")) {
    		if(Kartenwert + 11 > 21)
    		Kartenwert = Kartenwert + 1;
    		else 
    		Kartenwert = Kartenwert + 1;
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
    
}