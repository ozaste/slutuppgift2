package slutuppgift2;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class finalexam {

	public static String[] easy = { "häst", "åsna", "gook", "hej", "kung","barbie", "dåre" };			//här är en array som håller orden för den lätta svårighetsgraden
				
	public static String[] hard = { "smörgåsen", "fågelhålken", "hejsan" };		//här är en array som håller orden för den svåra svårighetsgraden

	public static String[] words = new String[5];		//en array som får plats med en string

	public static Random randomWord = new Random();  //här kallar jag på random metoden. 

	static int guesses = 8;    // här sätter jag antalet gissningar som spelaren ska ha. 
	public static String correctWord;  //detta är ordet den hämtar från listan som spelaren ska gissa. 

	public static char[] charFound;  //en array för de bokstäver som hittas. 

	static int failCount;  //Denna ska räkna hur många gånger man skriver fel. 
	static int diff;   //Denna indikerar svårighetsgrad. 

	private static ArrayList<String> characters = new ArrayList<>();   //här kallar jag på arraylist metoden. characters sparar bokstäverna som spelaren gissar på. 

	public static void main(String[] args) {  	 //här körs spelet med hjälp av min metoder.
		
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

	public static String getWords() { // Här konfigureras svårighetsgraden.
		System.out.println("choose difficulty");
		System.out.println("(1) - Easy");
		System.out.println("(2) - Hard");
		Scanner input = new Scanner(System.in);
		int i = diff;
		diff = input.nextInt();
		if (i == 1) {
			words[i] = easy[i];     //om man skriver 1 blir det lätt svårighetgrad. 
			return easy[randomWord.nextInt(easy.length)];    //här återgers svårighetsgraden som spelaren har valt. 
		}
		if (i == 2) {
			words[i] = hard[i];		//om man skriver 2 blir det svår svårighetgrad.
			return hard[randomWord.nextInt(hard.length)];
		}
		return null;    //här måste någonting returnas, därför returnar jag noll då jag inte har någonting annat att returna. 
	}

	public static void gameReset() {  // Denna metod ställer om till ett nytt spel, alltså t.ex. ställer den om antalet fel till noll. 

		failCount = 0;
		characters.clear();	//här rensas listan.  	
		correctWord = getWords();		//här använder jag min metod getWords() som anpassar det rätta ordet till den svårighetsgrad man har valt. 
		charFound = new char[correctWord.length()];		//anpassar längden utifrån det ordet som ska hittas. 	
		for (int i = 0; i < charFound.length; i++) {	//variabeln "i" ökas fram tills den har nått längden på ordet. 
			charFound[i] = '_';    		//när "i" räknat alla bokstäver görs de om till "_". 
		}

	}

	public static boolean wordFound() {    //denna metod kollar om ordet matchar det ord spelaren letar efter.
		return correctWord.contentEquals(new String(charFound));	//contentEquals jämför det korrekta ordet med de hittade bokstäverna. 
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

		if (! characters.contains(x)) {		//här uppdaterar den endast om x inte redan blivit skriven. Alltså kan man inte gissa på samma bokstav igen och förlora en gissning. 
			if (correctWord.contains(x)) {   //här kollar den om ordet innehåler x med contains metoden. 
				int i = correctWord.indexOf(x);     //indexOf letar upp bokstäverna och orienterar dem in i ordet. Alltså byts då "_" ut mot x om ordet innehåler x. 
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

			while (failCount < guesses) {		//så länge som du har gissningar ska den fortsätta fråga om en bokstav med while loopen. 
				System.out.println("guess a character : ");
				
				String string = input.next();	// får nästa input från spelaren

				
				if (string.length() > 1) {
					string = string.substring(0, 1);		//"substring": om spelaren skriver in fler än en boksatv så behåller metoden den första. 
				}

				// update word found
				checkGuess(string);		

				// display current state
				System.out.println(wordFoundContent());		

				if (wordFound()) {
					System.out.println("You win!");   //om ordet är korrekt kommer detta visas. 
					System.out.println("The correct word was: " + correctWord);	 //här visar programmet också vad det rätta ordet var. 
					break;		
				} else {
					// här visas hur många försök användaren har kvar. 
					System.out.println("you have " + (guesses - failCount) +" tries left ");
				}

				if (failCount == guesses) {		//om antalet försök blir lika mycket som antalet givna gissningar kommer detta visas. 
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
	
	// if (gissningen är fel) {
	// lägg till bilden 
	//}
	
	
}
}



