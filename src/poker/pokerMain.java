package poker;

import java.util.Arrays;
import java.util.Random;

public class pokerMain {

	public static void main(String[] args) {
		kartendeck(52);
		ziehung(5);
		System.out.println();
		String ausgabe="High Card";
		if (onePair()) ausgabe="One Pair";
		if (twoPair()) ausgabe="Two Pair";
		if (threeOfAKind()) ausgabe="Three of a Kind";
		if (straight()) ausgabe="Straight";
		if (flush()) ausgabe="Flush";
		//if (fullHouse()) ausgabe="Full House";
		if (fourOfAKind()) ausgabe="Four of a Kind";
		if (straight() && flush()) ausgabe="Straight Flush";
		System.out.println(ausgabe);
	}
	static int[] karten;
	static int kANZAHL;
	static int[] hand={0,13,26,14,1};
	static int hANZAHL;

	public static void kartendeck(int LAENGE) {
		kANZAHL=LAENGE;
		karten=new int[kANZAHL];
		for (int i=0;i<kANZAHL;i++) {
			karten[i]=i;
		}
	}
	public static void ziehung(int gezogeneKarten) {
		hANZAHL=gezogeneKarten;
		Random zufall=new Random();
		/*hand=new int[hANZAHL];
		int moeglKarten=kANZAHL;
		for (int i=0;i<hANZAHL;i++) {
			int z=zufall.nextInt(kANZAHL);
			int[] ph=kartendeck;
			hand[i]=ph[z];
			ph[z]=kartendeck[moeglKarten-1];
			ph[moeglKarten-1]=kartendeck[z];
			moeglKarten--;
		}*/
		System.out.print("Gezogene Karten : ");
		for (int i=0;i<hANZAHL;i++) {
			System.out.print(hand[i]+" ");
		}
		System.out.println();
	}
	public static int farbe(int karte) {
		return karte/13;
	}
	public static int kartensymbolik(int karte) {
		return karte%13;
	}
	public static boolean highCard() {
		boolean highCard=true;
		return highCard;
	}
	public static boolean onePair() {
		boolean onePair=false;
		int zaehler=0;
		for(int i=0;i<hANZAHL;i++) {
			for (int j=(i+1);j<hANZAHL;j++) {
				if(kartensymbolik(hand[i])==kartensymbolik(hand[j])) {
					zaehler++;
				}
			}
		}
		if (zaehler==1) {
			onePair=true;
		}
		return onePair;
	}
	public static boolean twoPair() {
		boolean twoPair=false;
		int zaehler=0;
		int paare=0;
		int alteKarte=14;						//Sicherstellung, dass zaehler beim ersten Schleifendurchlauf reseted wird
		for (int i=0;i<hANZAHL;i++) {
			for (int j=(i+1);j<hANZAHL;j++) {
				if (kartensymbolik(hand[i])==kartensymbolik(hand[j])) {
					int neueKarte=kartensymbolik(hand[i]);
					if (alteKarte!=neueKarte) {
						if (zaehler<2) {
							paare++;
						}
						if (zaehler==2) {
							paare--;
						}
						zaehler=0;
						alteKarte=neueKarte;
					}
					zaehler++;
				}
			}
		}
		if (paare==2) {
			twoPair=true;
		}
		return twoPair;
	}
	public static boolean threeOfAKind() {
		boolean threeOfAKind=false;
		int zaehler=0;
		for (int i=0;i<hANZAHL;i++) {
			for (int j=0;j<hANZAHL;j++) {
				if (i==j) {
					continue;
				}
				if (kartensymbolik(hand[i])==kartensymbolik(hand[j])) {
					zaehler++;
					if (zaehler==2) {
						threeOfAKind=true;
					}
					if (zaehler>2) {
						threeOfAKind=false;
					}
				}
			}
			zaehler=0;
		}
		return threeOfAKind;
	}
	public static boolean fourOfAKind() {
		boolean fourOfAKind=false;
		int zaehler=0;
		for (int i=0;i<hANZAHL;i++) {
			for (int j=0;j<hANZAHL;j++) {
				if (i==j) {
					continue;
				}
				if (kartensymbolik(hand[i])==kartensymbolik(hand[j])) {
					zaehler++;
					if (zaehler==3) {
						fourOfAKind=true;
					}
					if (zaehler>3) {
						fourOfAKind=false;
					}
				}
			}
			zaehler=0;
		}
		return fourOfAKind;
	}
	public static boolean flush() {
		boolean flush=true;
		int letzteFarbe=farbe(hand[0]);
		for (int i=1;i<hANZAHL;i++) {
			if(farbe(hand[i])!=letzteFarbe) {
				flush=false;
			}
			letzteFarbe=farbe(hand[i]);
		}
		return flush;
	}
	public static boolean straight() {
		boolean straight=true;
		int[] handSort=new int[hANZAHL];
		for (int i=0;i<hANZAHL;i++) {
			handSort[i]=hand[i];
		}
		for (int i=0;i<hANZAHL;i++) {
			handSort[i]=kartensymbolik(handSort[i]);
		}
		Arrays.sort(handSort);
		int letzteKarte=handSort[0];
		for(int i=1;i<hANZAHL;i++) {
			if(handSort[i]!=letzteKarte+1) {
				straight=false;
			}
			letzteKarte=handSort[i];
		}
		return straight;
	}
	public static boolean fullHouse() {
		boolean fullHouse=true;
		if (threeOfAKind()) fullHouse=true;
		return fullHouse;
	}
}