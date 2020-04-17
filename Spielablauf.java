package BlackJack;

import java.util.*;

public class Spielablauf {

	Scanner scan = new Scanner(System.in);

	Kartendeck kartendeck = new Kartendeck(6);
	int Geld = 0;
	int Spieleranzahl;
	Spieler[] spieler;
	bank bank;
	String ziehen;
	boolean verloren;
	boolean verlorenbank;
	int Zaehler = 0;
	String weiterspielen = "";

	public void BlackJack() {

		System.out.println("Herzlich Willkommen bei Black Jack");
		GeldEingeben();
		SpielerAnzahl();
		setSpieler();
		bankerstellen();
		do {
			Einsetzen();
			KartenausteilenundKartenzeigen();
			bankzieht();
			Verdoppeln();
			nochmalziehenSpieler();
			Banknochmalziehen();
			ErmittelnGewinner();
			resetPunkte();
			Spielerraus();
			System.out.print("Wollen sie weiter spielen (J oder N): ");
			System.out.println();
			weiterspielen = scan.nextLine();
		} while (!weiterspielen.equals("N"));
	}

	private void GeldEingeben() {

		System.out.println();
		System.out.print("Geben sie das Geld ein für jeden Spieler das gleiche Startkapital: ");
		Geld = scan.nextInt();
		System.out.println("Jeder Spieler hat " + Geld + " Chips");

	}

	private void SpielerAnzahl() {

		System.out.println();

		while (Spieleranzahl < 7) {
			System.out.print("Geben sie die Anzahl der Spieler ein (1-7): ");
			Spieleranzahl = scan.nextInt();
			if (Spieleranzahl > 7) {
				System.out.println("Ungueltige Spieleranzahl");
				Spieleranzahl = 0;
			} else {
				break;
			}
		}
		spieler = new Spieler[Spieleranzahl];
	}

	private void setSpieler() {
		for (int i = 0; i < spieler.length; i++) {

			String spielername = "";

			spieler[i] = new Spieler();

			System.out.println();
			System.out.print("Spieler " + (i + 1) + " wie ist dein Name: ");
			if (i == 0) {
				scan.nextLine();
			}
			spielername = scan.nextLine();
			spieler[i].setName(spielername);

			spieler[i].setGeld(Geld);
		}
		System.out.println();
	}

	private void bankerstellen() {

		bank = new bank();

	}

	private void Einsetzen() {

		int Geld = 0;

		for (int i = 0; i < Spieleranzahl; i++) {
			do {
				System.out.print(spieler[i].name + " Geben sie ihren Einsatz ein: ");
				spieler[i].Einsatz = scan.nextInt();
				System.out.println();
				Geld = spieler[i].geld;
				if (spieler[i].Einsatz > spieler[i].geld) {
					System.out.println("Sie haben nicht so viele Chips");
				} else {
					spieler[i].geld = spieler[i].geld - spieler[i].Einsatz;
				}
			} while (spieler[i].Einsatz > Geld);
		}
	}

	private void KartenausteilenundKartenzeigen() {
		kartendeck.KartendeckErstellen();
		for (int i = 0; i < Spieleranzahl; i++) {
			for (int j = 0; j < 2; j++) {
				spieler[i].HandKarteHinzufuegen(kartendeck.giveNextCard());
				spieler[i].HandkartenWert();
				System.out.println(spieler[i].getName() + " Handkarte: " + spieler[i].getKarte() + " Handkartenwert: "
						+ spieler[i].getPunkte());
			}
			System.out.println();
		}
	}

	private void bankzieht() {
		for (int j = 0; j < 2; j++) {
			bank.HandKarteHinzufuegen(kartendeck.giveNextCard());
			bank.HandkartenWert();
		}
		System.out.println("Die Bank hat: " + bank.getPunkte() + " Punkte auf der Hand");
		System.out.println();
	}

	public void nochmalziehenSpieler() {

		scan.nextLine();
		for (Zaehler = 0; Zaehler < Spieleranzahl; Zaehler++) {
			spieler[Zaehler].verloren = false;
			if (spieler[Zaehler].verdoppeln == false) {
				do {
					System.out.print(spieler[Zaehler].getName() + " Wollen sie nochmal ziehen (J oder N): ");
					System.out.println();
					ziehen = scan.nextLine();
					if (ziehen.equals("J") || ziehen.equals("j")) {
						spieler[Zaehler].HandKarteHinzufuegen(kartendeck.giveNextCard());
						spieler[Zaehler].HandkartenWert();
						System.out.println(spieler[Zaehler].getName() + " Handkarte: " + spieler[Zaehler].getKarte()
								+ " Handkartenwert: " + spieler[Zaehler].getPunkte());
						Ueberkauft();
						if (spieler[Zaehler].verloren) {
							break;
						}
					}
				} while (!ziehen.equals("N"));
			}
		}
	}

	private void Ueberkauft() {
		if (21 < spieler[Zaehler].getPunkte()) {
			System.out.println(spieler[Zaehler].getName() + " Sie haben verloren!");
			System.out.println();
			spieler[Zaehler].verloren(verloren);
		}
	}

	private void Banknochmalziehen() {

		while (17 > bank.getPunkte()) {
			bank.HandKarteHinzufuegen(kartendeck.giveNextCard());
			bank.HandkartenWert();
			System.out.println("Die Bank hat: " + bank.getPunkte());
			System.out.println();
			BankUeberkauft();
			if (verlorenbank) {
				break;
			}
		}
	}

	private void BankUeberkauft() {

		if (21 < bank.getPunkte()) {
			System.out.println("Die Bank hat sich überkauft");
			System.out.println();
			verlorenbank = true;
		}
	}

	private void ErmittelnGewinner() {

		for (int i = 0; i < Spieleranzahl; i++) {
			if (spieler[i].verloren) {
				System.out.println(spieler[i].getName() + " sie haben verloren!");
				System.out.println(spieler[i].getName() + " sie haben noch " + spieler[i].geld + " Chips");
				System.out.println();
			} else if (verlorenbank == true && spieler[i].verloren == false) {
				System.out.println(spieler[i].getName() + " sie haben gewonnen!");
				spieler[i].geld = spieler[i].geld + spieler[i].Einsatz * 2;
				System.out.println(spieler[i].getName() + " sie haben jetzt " + spieler[i].geld + " Chips");
				System.out.println();
			} else if (spieler[i].getPunkte() > bank.getPunkte() && verlorenbank == false) {
				System.out.println(spieler[i].getName() + " sie haben gewonnen!");
				spieler[i].geld = spieler[i].geld + spieler[i].Einsatz * 2;
				System.out.println(spieler[i].getName() + " sie haben jetzt " + spieler[i].geld + " Chips");
				System.out.println();
			} else if (bank.getPunkte() > spieler[i].getPunkte()) {
				System.out.println(spieler[i].getName() + " sie haben verloren!");
				System.out.println(spieler[i].getName() + " sie haben noch " + spieler[i].geld + " Chips");
				System.out.println();
			} else if (bank.getPunkte() == spieler[i].getPunkte()) {
				System.out.println(spieler[i].getName() + " Unentschieden");
				System.out.println();
				spieler[i].geld = spieler[i].geld + spieler[i].Einsatz;
				System.out.println(spieler[i].getName() + " sie haben noch " + spieler[i].geld + " Chips");
				System.out.println();
			}
		}
		verlorenbank = false;
	}

	private void resetPunkte() {
		for (int i = 0; i < Spieleranzahl; i++) {
			spieler[i].resetKartenwert();
			spieler[i].resetKartenaufhand();
		}
		bank.resetKartenwert();
	}

	private void Spielerraus() {

		int anzahlspielerraus = 0;

		for (int i = 0; i < Spieleranzahl; i++) {
			if (spieler[i].geld == 0) {
				System.out.println(spieler[i].getName() + " sie sind raus");
				System.out.println();
				anzahlspielerraus++;

				for (int j = i; j < Spieleranzahl; j++) {
					if (j < (Spieleranzahl - 1)) {
						spieler[i] = spieler[i + 1];
					}
				}
				if (i < Spieleranzahl - 1) {
					i--;
				}
			}
			if (anzahlspielerraus == Spieleranzahl) {
				System.out.println("Alle Spieler haben keine Chips mehr Porgramm wird beendet");
				System.exit(0);
			}
		}
		Spieleranzahl -= anzahlspielerraus;
	}

	private void Verdoppeln() {

		String verdoppeln = " ";

		scan.nextLine();
		for (int i = 0; i < Spieleranzahl; i++) {
			System.out.print(spieler[i].getName() + " wollen sie verdoppeln (J oder N): ");
			verdoppeln = scan.nextLine();
			System.out.println();
			if (verdoppeln.equals("J")) {
				spieler[i].geld = spieler[i].geld - spieler[i].Einsatz;
				spieler[i].Einsatz = spieler[i].Einsatz * 2;
				spieler[i].verdoppeln = true;
				System.out.println(spieler[i].getName() + " sie haben ihren Einsatz veroppelt!");
				spieler[i].HandKarteHinzufuegen(kartendeck.giveNextCard());
				spieler[i].HandkartenWert();
				System.out.println(spieler[i].getName() + " Handkarte: " + spieler[i].getKarte() + " Handkartenwert: "
						+ spieler[i].getPunkte());
				if (21 < spieler[i].getPunkte()) {
					System.out.println(spieler[i].getName() + " Sie haben verloren!");
					System.out.println();
					verloren = true;
					spieler[i].verloren(verloren);
				}
			} else {
				spieler[i].verdoppeln = false;
			}
		}
	}
}
