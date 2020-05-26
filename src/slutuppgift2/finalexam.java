package slutuppgift2;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class finalexam {

	public static String[] easy = { "häst", "åsna", "gook", "hej", "kung", "barbie", "dåre" }; // här är en array som
																								// håller orden för den
																								// lätta
																								// svårighetsgraden

	public static String[] hard = { "smörgåsen", "fågelhålken", "hejsan", "grodorna", "halsduken", "morötter" }; // här
																													// är
																													// en
																													// array
																													// som
																													// håller
																													// orden
																													// för
																													// den
																													// svåra
																													// svårighetsgraden
	static Scanner input = new Scanner(System.in);
	public static String[] words = new String[5]; // en array som får plats med en string

	public static ArrayList<String> mpWord = new ArrayList<>(); // det inskrivna multiplayer ordet kommer sparas här.

	public static Random randomWord = new Random(); // här kallar jag på random metoden.

	static int guesses = 8; // här sätter jag antalet gissningar som spelaren ska ha.
	public static String correctWord; // detta är ordet den hämtar från listan som spelaren ska gissa.
	public static String s;

	public static char[] charFound; // en array för de bokstäver som hittas.

	static int failCount; // Denna ska räkna hur många gånger man skriver fel.
	static int diff; // Denna indikerar svårighetsgrad.
	static int restart;

	private static ArrayList<String> characters = new ArrayList<>(); // här kallar jag på arraylist metoden. characters
																		// sparar bokstäverna som spelaren gissar på.

	public static void main(String[] args) { // här körs spelet med hjälp av min metoder.

		startGame();

	}

	/**
	 * I denna metod konfigureras svårighetsgraden.
	 * 
	 * @return
	 */
	public static String getWordsDifficulty() {
		System.out.println("choose difficulty");
		System.out.println("(1) - Easy");
		System.out.println("(2) - Hard");

		while (true) {
			diff = getValidIntegerInput();

			if (diff < 1 || diff > 2) {
				System.out.println("write an integer representing the alternative you want to choose");
				continue;
			}
			break;
		}
		if (diff == 1) { // om man skriver 1 blir det lätt svårighetgrad.
			return easy[randomWord.nextInt(easy.length)]; // här återgers svårighetsgraden som spelaren har valt.

		}
		if (diff == 2) { // om man skriver 2 blir det svår svårighetgrad.
			return hard[randomWord.nextInt(hard.length)];

		}

		return ""; // här måste någonting returnas, därför returnar jag "" då jag inte har
					// någonting annat att returna.
	}

	/**
	 * Denna metod ställer om till ett nytt spel, alltså t.ex. ställer den om
	 * antalet fel till noll.
	 * 
	 * @return
	 */
	public static void gameReset() {

		failCount = 0;
		characters.clear(); // här rensas listan.
		correctWord = getWordsDifficulty(); // här använder jag min metod getWords() som anpassar det rätta ordet till
											// den
		// svårighetsgrad man har valt.
		charFound = new char[correctWord.length()]; // anpassar längden utifrån det ordet som ska hittas.
		for (int i = 0; i < charFound.length; i++) { // variabeln "i" ökas fram tills den har nått längden på ordet.
			charFound[i] = '_'; // när "i" räknat alla bokstäver görs de om till "_".
		}

	}

	/**
	 * denna metod kollar om ordet matchar det ord spelaren letar efter.
	 * 
	 * @return
	 */
	public static boolean wordCheck() {
		return correctWord.contentEquals(new String(charFound)); // contentEquals jämför det korrekta ordet med de
																	// hittade bokstäverna.
	}

	/**
	 * Denna metod returnerar tillståndet för ordet som hittades av användaren
	 * 
	 * @return
	 */
	private static String wordState() {
		StringBuilder stringbuilder = new StringBuilder();
		for (int i = 0; i < charFound.length; i++) {
			stringbuilder.append(charFound[i]);
			if (i < charFound.length - 1) {
				stringbuilder.append(" ");
			}

		}
		return stringbuilder.toString();
	}

	/**
	 * Denna metoden används för att kolla ifall gissningen är rätt
	 * 
	 * @return
	 */
	private static void checkGuess(String x) {

		if (!characters.contains(x)) { // här uppdaterar den endast om x inte redan blivit skriven. Alltså kan man inte
										// gissa på samma bokstav igen och förlora en gissning.
			if (correctWord.contains(x)) { // här kollar den om ordet innehåler x med contains metoden.
				int i = correctWord.indexOf(x); // indexOf letar upp bokstäverna och orienterar dem in i ordet. Alltså
												// byts då "_" ut mot x om ordet innehåler x.
				while (i >= 0) {
					charFound[i] = x.charAt(0);
					i = correctWord.indexOf(x, i + 1);
				}
			} else {

				switch (guesses - failCount) { // Här är det grafiska. För varje gång som man gissar fel tar den antal
												// gissningar minus antal gånger man har svarat fel. Och uppdaterar åtta
												// gånger.

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
					System.out.println("  +---+\r\n" + "  |   |\r\n" + "  O   |\r\n" + " /|\\  |\r\n" + " / \\  |\r\n"
							+ "      |\r\n" + "=========");
					break;
				}
				failCount++;
			}
			characters.add(x);

		}
		System.out.println("characters you already guessed: " + characters); // här har "characters" sparat de bokstäver
																				// du gissat på och lägger ut de så du
																				// ser.

	}

	/**
	 * Denna metod är för multiplayer som jag inte är klar med
	 * 
	 * @return
	 */
	private static void multiCheckGuess(String x) {

		if (!characters.contains(x)) { // här uppdaterar den endast om x inte redan blivit skriven. Alltså kan man inte
										// gissa på samma bokstav igen och förlora en gissning.
			if (correctWord.contains(x)) { // här kollar den om ordet innehåler x med contains metoden.
				int i = correctWord.indexOf(x); // indexOf letar upp bokstäverna och orienterar dem in i ordet. Alltså
												// byts då "_" ut mot x om ordet innehåler x.
				while (i >= 0) {
					charFound[i] = x.charAt(0);
					i = correctWord.indexOf(x, i + 1);
				}
			} else {

				switch (guesses - failCount) {

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
					System.out.println("  +---+\r\n" + "  |   |\r\n" + "  O   |\r\n" + " /|\\  |\r\n" + " /'\\  |\r\n"
							+ "      |\r\n" + "=========");
					break;
				}
				failCount++;
			}
			characters.add(x);

		}
		System.out.println("characters you already guessed: " + characters);

	}

	/**
	 * I denna metod sker skälva spelet
	 * 
	 * @return
	 */
	public static void game() {

		String end;

		while (failCount < guesses) { // så länge som du har gissningar ska den fortsätta fråga om en bokstav med
										// while loopen.

			if (diff == 1) {
				System.out.println("you chose easy");
			} else {
				System.out.println("you chose hard");
			}

			System.out.println("guess a character : ");

			String string = input.next(); // får nästa input från spelaren

			if (string.length() > 1) {
				System.out.println(
						"you can only guess one character for a guess, the first character you wrote will count as your guess");
				string = string.substring(0, 1); // "substring": om spelaren skriver in fler än en boksatv så
													// behåller metoden den första.
			}

			checkGuess(string);

			System.out.println(wordState());

			if (wordCheck()) {
				System.out.println("You win!"); // om ordet är korrekt kommer detta visas.
				System.out.println("The correct word was: " + correctWord); // här visar programmet också vad det
																			// rätta ordet var.
				break;
			} else {
				// här visas hur många försök användaren har kvar.
				System.out.println("you have " + (guesses - failCount) + " tries left ");
			}

			if (failCount == guesses) { // om antalet försök blir lika mycket som antalet givna gissningar kommer
										// detta visas.
				System.out.println("You lose");
				System.out.println("The correct word was: " + correctWord);

			}

		}

	}

	/**
	 * Denna metod är för multiplayer som jag inte är klar med
	 * 
	 * @return
	 */
	public static void multiGame() {

		correctWord = mpGetWords();

		while (failCount < guesses) { // så länge som du har gissningar ska den fortsätta fråga om en bokstav med
										// while loopen.

			for (int i = 0; i < 5; i++) {
				System.out.println("\n");
			}

			System.out.println("guess a character : ");

			String guess = input.next(); // får nästa input från spelaren

			if (guess.length() > 1) {

				guess = guess.substring(0, 1); // "substring": om spelaren skriver in fler än en boksatv så behåller
												// metoden den första bokstaven.

			}

			// update word found
			multiCheckGuess(guess);

			// display current state
			System.out.println(wordState());

			if (wordCheck()) {
				System.out.println("You win!"); // om ordet är korrekt kommer detta visas.
				System.out.println("The correct word was: " + s); // här visar programmet också vad det rätta ordet
																	// var.
				break;
			} else {
				// här visas hur många försök användaren har kvar.
				System.out.println("you have " + (guesses - failCount) + " tries left ");
			}

			if (failCount == guesses) { // om antalet försök blir lika mycket som antalet givna gissningar kommer
										// detta visas.
				System.out.println("You lose");
				System.out.println("The correct word was: " + s);
			}

		}

	}

	/**
	 * Denna metod är för multiplayer som jag inte är klar med
	 * 
	 * @return
	 */
	public static String mpGetWords() {
		String s;

		System.out.println("Enter a string");
		s = input.nextLine();

		mpWord.add(s);
		return s;

	}

	/**
	 * Denna metod innehåler texten till alternativet "instructions från menyn
	 * 
	 * @return
	 */
	public static void rules() {
		System.out.println("Singleplayer:");
		System.out.println(
				"You choose easy or hard. \neasy: short words \nhard: long words \nThen you start guessing the word by filling in the spaces with a character. \nYou have 8 tries(wont count down if you guess right character) \nIf you lose, the man is hanged!");
		System.out.println("\nmultiplayer:");
		System.out.println("Under development");
	}

	/**
	 * Denna metod visar hela ordlistan, både från easy och hard.
	 * 
	 * @return
	 */
	public static void wordList() {

		System.out.println("Ordlistan för easy:"); // Ordlistan från easy
		for (int i = 0; i < words.length; i++) {

			System.out.println(easy[i]);
		}
		System.out.println("");
		System.out.println("Ordlistan för hard:"); // Ordlistan från hard
		for (int i = 0; i < words.length; i++) {

			System.out.println(hard[i]);
		}

	}

	/**
	 * Denna metod innehåller alla metoder som startar spelet
	 * 
	 * @return
	 */
	public static void startGame() {

		while (true) { // En while loop som ser till att spelet loopas igen ifall man vill spela igen.
			int choise;

			while (true) { // En while loop som gör det möjligt att kunna identifiera dumma inputs och låta
							// spelaren skriva rätt input.
				System.out.println("Choose a alternetive: "); // Här är menyn som jag gjorde med en switch case.
				System.out.println("(1) - Singleplayer");
				System.out.println("(2) - Multiplayer");
				System.out.println("(3) - Instructions");
				System.out.println("(4) - Wordlist");
				System.out.println("(5) - Exit");

				choise = getValidIntegerInput();

				if (choise < 1 || choise > 5) { // Om "choise" är mindre än 1 eller större än 5, ska programmet säga
												// ifrån.
					System.out.println("Choose a number between 1 and 5. ");
					continue;
				}
				break;
			}
			switch (choise) {
			case 1:

				gameReset();
				game();
				break;

			case 2:

				System.out.println("this function is under development");

				break;

			case 3:

				rules();

				break;

			case 4:

				wordList();

				break;

			case 5:

				System.out.println("Are you sure?");

				break;

			default:

				break;
			}

			System.out.println("\n Do you want to go back to the menu?");
			System.out.println("(1) yes");
			System.out.println("(2) no");
			restart = getValidIntegerInput();
			int i = restart;

			if (i != 1) {
				System.out.println("Quitting game....");
				System.exit(0);
			}
		}
	}

	/**
	 * Denna metod är en trycatch som används ifall att användaren skriver en
	 * ogliltig input.
	 * 
	 * @return
	 */
	public static int getValidIntegerInput() {

		int error;
		while (true) {
			try {
				error = input.nextInt(); // "input.nextInt()" byts till "error" vilket gör att jag kan använda metoden
											// där det står "input.nextInt().
				return error;

			} catch (Exception e) { // denna kod identifierar ogiltig input.
				System.out.println("write an input representing the alternative you want to choose");
				input.nextLine();
			}
		}

	}

}
