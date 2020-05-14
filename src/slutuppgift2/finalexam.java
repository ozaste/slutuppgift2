package slutuppgift2;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class finalexam {

	public static String[] easy = { "h�st", "�sna", "gook", "hej", "kung","barbie", "d�re" };			//h�r �r en array som h�ller orden f�r den l�tta sv�righetsgraden
				
	public static String[] hard = { "sm�rg�sen", "f�gelh�lken", "hejsan" };		//h�r �r en array som h�ller orden f�r den sv�ra sv�righetsgraden

	public static String[] words = new String[5];		//en array som f�r plats med en string

	public static Random randomWord = new Random();  //h�r kallar jag p� random metoden. 

	static int guesses = 8;    // h�r s�tter jag antalet gissningar som spelaren ska ha. 
	public static String correctWord;  //detta �r ordet den h�mtar fr�n listan som spelaren ska gissa. 

	public static char[] charFound;  //en array f�r de bokst�ver som hittas. 

	static int failCount;  //Denna ska r�kna hur m�nga g�nger man skriver fel. 
	static int diff;   //Denna indikerar sv�righetsgrad. 

	private static ArrayList<String> characters = new ArrayList<>();   //h�r kallar jag p� arraylist metoden. characters sparar bokst�verna som spelaren gissar p�. 

	public static void main(String[] args) {  	 //h�r k�rs spelet med hj�lp av min metoder.
		
		Scanner input = new Scanner(System.in);		
		int choise;
		
		System.out.println("Choose a alternetive: ");
		System.out.println("(1) - Singleplayer");
		System.out.println("(2) - Multiplayer");
		System.out.println("(3) - Instructions");
		System.out.println("(4) - Wordlist");
		System.out.println("(5) - Exit");
		
		choise = input.nextInt();
		
		switch (choise) {
		case 1:
			
			gameReset();
			game();
			break;

		case 2:
			
			
			
			break;
			
		case 3:
			
			System.out.println("instructions loading...");
			
			break;
			
		case 4:
			
			wordList();
			
			break;

		case 5:
			
			System.out.println("quiting game...");
			
			break;

		default:
			
			break;
		}

	}

	public static String getWords() { // H�r konfigureras sv�righetsgraden.
		System.out.println("choose difficulty");
		System.out.println("(1) - Easy");
		System.out.println("(2) - Hard");
		Scanner input = new Scanner(System.in);
		int i = diff;
		diff = input.nextInt();
		if (i == 1) {
			words[i] = easy[i];     //om man skriver 1 blir det l�tt sv�righetgrad. 
			return easy[randomWord.nextInt(easy.length)];    //h�r �tergers sv�righetsgraden som spelaren har valt. 
		}
		if (i == 2) {
			words[i] = hard[i];		//om man skriver 2 blir det sv�r sv�righetgrad.
			return hard[randomWord.nextInt(hard.length)];
		}
		return null;    //h�r m�ste n�gonting returnas, d�rf�r returnar jag noll d� jag inte har n�gonting annat att returna. 
	}

	public static void gameReset() {  // Denna metod st�ller om till ett nytt spel, allts� t.ex. st�ller den om antalet fel till noll. 

		failCount = 0;
		characters.clear();	//h�r rensas listan.  	
		correctWord = getWords();		//h�r anv�nder jag min metod getWords() som anpassar det r�tta ordet till den sv�righetsgrad man har valt. 
		charFound = new char[correctWord.length()];		//anpassar l�ngden utifr�n det ordet som ska hittas. 	
		for (int i = 0; i < charFound.length; i++) {	//variabeln "i" �kas fram tills den har n�tt l�ngden p� ordet. 
			charFound[i] = '_';    		//n�r "i" r�knat alla bokst�ver g�rs de om till "_". 
		}

	}

	public static boolean wordFound() {    //denna metod kollar om ordet matchar det ord spelaren letar efter.
		return correctWord.contentEquals(new String(charFound));	//contentEquals j�mf�r det korrekta ordet med de hittade bokst�verna. 
	}

	private static String wordFoundContent() {			
		StringBuilder stringbuilder = new StringBuilder();   
		for (int i = 0; i < charFound.length; i++) {		
			stringbuilder.append(charFound[i]);			
			if (i < charFound.length - 1) {		
				stringbuilder.append(" ");		
			}

		}
		return stringbuilder.toString();
	}

	private static void checkGuess(String x) {

		if (! characters.contains(x)) {		//h�r uppdaterar den endast om x inte redan blivit skriven. Allts� kan man inte gissa p� samma bokstav igen och f�rlora en gissning. 
			if (correctWord.contains(x)) {   //h�r kollar den om ordet inneh�ler x med contains metoden. 
				int i = correctWord.indexOf(x);     //indexOf letar upp bokst�verna och orienterar dem in i ordet. Allts� byts d� "_" ut mot x om ordet inneh�ler x. 
				while (i >= 0) {		
					charFound[i] = x.charAt(0);
					i = correctWord.indexOf(x, i + 1);
				}
			} else {
				failCount++;
				
				switch(guesses){

				case 8:
					System.out.println("=========");
					break;
				case 7:
					System.out.println("  +---+\r\n" + "  |   |\r\n" + "      |\r\n" + "      |\r\n" + "      |\r\n"
		                    + "      |\r\n" + "=========");
					break;
				case 6:
					System.out.println("  +---+\r\n" + "  |   |\r\n" + "  O   |\r\n" + "      |\r\n" + "      |\r\n"
		                    + "      |\r\n" + "=========");
					break;
				case 5:
					System.out.println("  +---+\r\n" + "  |   |\r\n" + "  O   |\r\n" + "  |   |\r\n" + "      |\r\n"
		                    + "      |\r\n" + "=========");
					break;
				case 4:
					System.out.println("  +---+\r\n" + "  |   |\r\n" + "  O   |\r\n" + " /|   |\r\n" + "      |\r\n"
		                    + "      |\r\n" + "=========");
					break;
				case 3:
					System.out.println("  +---+\r\n" + "  |   |\r\n" + "  O   |\r\n" + " /|\\  |\r\n" + "      |\r\n"
		                    + "      |\r\n" + "=========");
					break;
				case 2:
					System.out.println("  +---+\r\n" + "  |   |\r\n" + "  O   |\r\n" + " /|\\  |\r\n" + " /    |\r\n"
		                    + "      |\r\n" + "=========");
					break;
				case 1:
					System.out.println();
					break;
				}
			}
			characters.add(x);
		}
		
	
		
		
		}
		

	

	public static void game() {

		try (Scanner input = new Scanner(System.in)) {

			while (failCount < guesses) {		//s� l�nge som du har gissningar ska den forts�tta fr�ga om en bokstav med while loopen. 
				System.out.println("guess a character : ");
				
				String string = input.next();	// f�r n�sta input fr�n spelaren

				
				if (string.length() > 1) {
					string = string.substring(0, 1);		//"substring": om spelaren skriver in fler �n en boksatv s� beh�ller metoden den f�rsta. 
				}

				// update word found
				checkGuess(string);		

				// display current state
				System.out.println(wordFoundContent());		

				if (wordFound()) {
					System.out.println("You win!");   //om ordet �r korrekt kommer detta visas. 
					System.out.println("The correct word was: " + correctWord);	 //h�r visar programmet ocks� vad det r�tta ordet var. 
					break;		
				} else {
					// h�r visas hur m�nga f�rs�k anv�ndaren har kvar. 
					System.out.println("you have " + (guesses - failCount) +" tries left ");
				}

				if (failCount == guesses) {		//om antalet f�rs�k blir lika mycket som antalet givna gissningar kommer detta visas. 
					System.out.println("You lose");
					System.out.println("The correct word was: " + correctWord);     
				}

			}

		}

	}
	
public static void wordList() {
		
		for (int i = 0; i < words.length ; i++) {
            System.out.println(words[i]);
        }
		
	}

public static void man() {
	
	// if (gissningen �r fel) {
	// l�gg till bilden 
	//}
	
	
}
}



