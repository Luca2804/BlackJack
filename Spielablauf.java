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

	public void BlackJack() {

		System.out.println("Herzlich Willkommen bei Black Jack");
		GeldEingeben();
		SpielerAnzahl();
		setSpieler();
		bankerstellen();
		Einsaetzen();
		KartenausteilenundKartenzeigen();
		bankzieht();
		nochmalziehenSpieler();
		Banknochmalziehen();

	}

	private void GeldEingeben() {

		System.out.println();
		System.out.println("Geben sie das Geld ein für jeden Spieler das gleiche Startkapital");
		Geld = scan.nextInt();
		System.out.println("Jeder Spieler hat " + Geld + " Chips");

	}

	private void SpielerAnzahl() {

		System.out.println();

		while (Spieleranzahl < 7) {
			System.out.println("Geben sie die Anzahl der Spieler ein (1-7)");
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
			System.out.println("Spieler " + (i + 1) + " wie ist dein Name");
			if (i == 0) {
				scan.nextLine();
			}
			spielername = scan.nextLine();
			spieler[i].setName(spielername);

			spieler[i].setGeld(Geld);
		}
	}

	private void bankerstellen() {

		bank = new bank();

	}

	private void Einsaetzen() {

		for (int i = 0; i < spieler.length; i++) {
			do {
				System.out.println(spieler[i].name + " Geben sie ihren Einsatz ein:");
				spieler[i].Einsatz = scan.nextInt();
				if (spieler[i].Einsatz > spieler[i].geld) {
					System.out.println("Sie haben nicht so viele Chips");
				} else {
					spieler[i].geld = spieler[i].geld - spieler[i].Einsatz;
				}
			} while (spieler[i].Einsatz > spieler[i].geld);
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
		System.out.println();
		System.out.println("Die Bank hat: " + bank.getPunkte() + " Punkte auf der Hand");
	}

	public void nochmalziehenSpieler() {

		scan.nextLine();
		for (Zaehler = 0; Zaehler < Spieleranzahl; Zaehler++) {
			verloren = false;
			do {
				System.out.println(spieler[Zaehler].getName() + " Wollen sie nochmal ziehen (J oder N)");
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

	private void Ueberkauft() {
		if (21 < spieler[Zaehler].getPunkte()) {
			System.out.println(spieler[Zaehler].getName() + " Sie haben verloren!");
			spieler[Zaehler].verloren(verloren);
		}
	}

	private void Banknochmalziehen() {

		while (16 > bank.getPunkte()) {
			bank.HandKarteHinzufuegen(kartendeck.giveNextCard());
			bank.HandkartenWert();
			System.out.println("Die Bank hat: " + bank.getPunkte());
			BankUeberkauft();
			if (verlorenbank) {
				break;
			}
		}
	}

	private void BankUeberkauft() {

		if (21 < bank.getPunkte()) {
			System.out.println("Die Bank hat sich überkauft");
			verlorenbank = true;
		}
	}

	private void ErmittelenGewinner() {

		for (int i = 0; i < Spieleranzahl; i++) {
			if (spieler[i].verloren) {
				System.out.println(spieler[i].getName() + " sie haben verloren!");
			} else if (verlorenbank == true) {
				System.out.println(spieler[i].getName() + "sie haben gewonnen!");
				spieler[i].geld = spieler[i].geld + spieler[i].Einsatz * 2;
			} else if (spieler[i].getPunkte() > bank.getPunkte() && verlorenbank == false) {
				System.out.println(spieler[i].getName() + "sie haben gewonnen!");
				spieler[i].geld = spieler[i].geld + spieler[i].Einsatz * 2;
			}
		}
	}
}
