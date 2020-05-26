package slutuppgift2;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class finalexam {

	public static String[] easy = { "h�st", "�sna", "gook", "hej", "kung", "barbie", "d�re" }; // h�r �r en array som
																								// h�ller orden f�r den
																								// l�tta
																								// sv�righetsgraden

	public static String[] hard = { "sm�rg�sen", "f�gelh�lken", "hejsan", "grodorna", "halsduken", "mor�tter" }; // h�r
																													// �r
																													// en
																													// array
																													// som
																													// h�ller
																													// orden
																													// f�r
																													// den
																													// sv�ra
																													// sv�righetsgraden
	static Scanner input = new Scanner(System.in);
	public static String[] words = new String[5]; // en array som f�r plats med en string

	public static ArrayList<String> mpWord = new ArrayList<>(); // det inskrivna multiplayer ordet kommer sparas h�r.

	public static Random randomWord = new Random(); // h�r kallar jag p� random metoden.

	static int guesses = 8; // h�r s�tter jag antalet gissningar som spelaren ska ha.
	public static String correctWord; // detta �r ordet den h�mtar fr�n listan som spelaren ska gissa.
	public static String s;

	public static char[] charFound; // en array f�r de bokst�ver som hittas.

	static int failCount; // Denna ska r�kna hur m�nga g�nger man skriver fel.
	static int diff; // Denna indikerar sv�righetsgrad.
	static int restart;

	private static ArrayList<String> characters = new ArrayList<>(); // h�r kallar jag p� arraylist metoden. characters
																		// sparar bokst�verna som spelaren gissar p�.

	public static void main(String[] args) { // h�r k�rs spelet med hj�lp av min metoder.

		startGame();

	}

	/**
	 * I denna metod konfigureras sv�righetsgraden.
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
		if (diff == 1) { // om man skriver 1 blir det l�tt sv�righetgrad.
			return easy[randomWord.nextInt(easy.length)]; // h�r �tergers sv�righetsgraden som spelaren har valt.

		}
		if (diff == 2) { // om man skriver 2 blir det sv�r sv�righetgrad.
			return hard[randomWord.nextInt(hard.length)];

		}

		return ""; // h�r m�ste n�gonting returnas, d�rf�r returnar jag "" d� jag inte har
					// n�gonting annat att returna.
	}

	/**
	 * Denna metod st�ller om till ett nytt spel, allts� t.ex. st�ller den om
	 * antalet fel till noll.
	 * 
	 * @return
	 */
	public static void gameReset() {

		failCount = 0;
		characters.clear(); // h�r rensas listan.
		correctWord = getWordsDifficulty(); // h�r anv�nder jag min metod getWords() som anpassar det r�tta ordet till
											// den
		// sv�righetsgrad man har valt.
		charFound = new char[correctWord.length()]; // anpassar l�ngden utifr�n det ordet som ska hittas.
		for (int i = 0; i < charFound.length; i++) { // variabeln "i" �kas fram tills den har n�tt l�ngden p� ordet.
			charFound[i] = '_'; // n�r "i" r�knat alla bokst�ver g�rs de om till "_".
		}

	}

	/**
	 * denna metod kollar om ordet matchar det ord spelaren letar efter.
	 * 
	 * @return
	 */
	public static boolean wordCheck() {
		return correctWord.contentEquals(new String(charFound)); // contentEquals j�mf�r det korrekta ordet med de
																	// hittade bokst�verna.
	}

	/**
	 * Denna metod returnerar tillst�ndet f�r ordet som hittades av anv�ndaren
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
	 * Denna metoden anv�nds f�r att kolla ifall gissningen �r r�tt
	 * 
	 * @return
	 */
	private static void checkGuess(String x) {

		if (!characters.contains(x)) { // h�r uppdaterar den endast om x inte redan blivit skriven. Allts� kan man inte
										// gissa p� samma bokstav igen och f�rlora en gissning.
			if (correctWord.contains(x)) { // h�r kollar den om ordet inneh�ler x med contains metoden.
				int i = correctWord.indexOf(x); // indexOf letar upp bokst�verna och orienterar dem in i ordet. Allts�
												// byts d� "_" ut mot x om ordet inneh�ler x.
				while (i >= 0) {
					charFound[i] = x.charAt(0);
					i = correctWord.indexOf(x, i + 1);
				}
			} else {

				switch (guesses - failCount) { // H�r �r det grafiska. F�r varje g�ng som man gissar fel tar den antal
												// gissningar minus antal g�nger man har svarat fel. Och uppdaterar �tta
												// g�nger.

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
		System.out.println("characters you already guessed: " + characters); // h�r har "characters" sparat de bokst�ver
																				// du gissat p� och l�gger ut de s� du
																				// ser.

	}

	/**
	 * Denna metod �r f�r multiplayer som jag inte �r klar med
	 * 
	 * @return
	 */
	private static void multiCheckGuess(String x) {

		if (!characters.contains(x)) { // h�r uppdaterar den endast om x inte redan blivit skriven. Allts� kan man inte
										// gissa p� samma bokstav igen och f�rlora en gissning.
			if (correctWord.contains(x)) { // h�r kollar den om ordet inneh�ler x med contains metoden.
				int i = correctWord.indexOf(x); // indexOf letar upp bokst�verna och orienterar dem in i ordet. Allts�
												// byts d� "_" ut mot x om ordet inneh�ler x.
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
	 * I denna metod sker sk�lva spelet
	 * 
	 * @return
	 */
	public static void game() {

		String end;

		while (failCount < guesses) { // s� l�nge som du har gissningar ska den forts�tta fr�ga om en bokstav med
										// while loopen.

			if (diff == 1) {
				System.out.println("you chose easy");
			} else {
				System.out.println("you chose hard");
			}

			System.out.println("guess a character : ");

			String string = input.next(); // f�r n�sta input fr�n spelaren

			if (string.length() > 1) {
				System.out.println(
						"you can only guess one character for a guess, the first character you wrote will count as your guess");
				string = string.substring(0, 1); // "substring": om spelaren skriver in fler �n en boksatv s�
													// beh�ller metoden den f�rsta.
			}

			checkGuess(string);

			System.out.println(wordState());

			if (wordCheck()) {
				System.out.println("You win!"); // om ordet �r korrekt kommer detta visas.
				System.out.println("The correct word was: " + correctWord); // h�r visar programmet ocks� vad det
																			// r�tta ordet var.
				break;
			} else {
				// h�r visas hur m�nga f�rs�k anv�ndaren har kvar.
				System.out.println("you have " + (guesses - failCount) + " tries left ");
			}

			if (failCount == guesses) { // om antalet f�rs�k blir lika mycket som antalet givna gissningar kommer
										// detta visas.
				System.out.println("You lose");
				System.out.println("The correct word was: " + correctWord);

			}

		}

	}

	/**
	 * Denna metod �r f�r multiplayer som jag inte �r klar med
	 * 
	 * @return
	 */
	public static void multiGame() {

		correctWord = mpGetWords();

		while (failCount < guesses) { // s� l�nge som du har gissningar ska den forts�tta fr�ga om en bokstav med
										// while loopen.

			for (int i = 0; i < 5; i++) {
				System.out.println("\n");
			}

			System.out.println("guess a character : ");

			String guess = input.next(); // f�r n�sta input fr�n spelaren

			if (guess.length() > 1) {

				guess = guess.substring(0, 1); // "substring": om spelaren skriver in fler �n en boksatv s� beh�ller
												// metoden den f�rsta bokstaven.

			}

			// update word found
			multiCheckGuess(guess);

			// display current state
			System.out.println(wordState());

			if (wordCheck()) {
				System.out.println("You win!"); // om ordet �r korrekt kommer detta visas.
				System.out.println("The correct word was: " + s); // h�r visar programmet ocks� vad det r�tta ordet
																	// var.
				break;
			} else {
				// h�r visas hur m�nga f�rs�k anv�ndaren har kvar.
				System.out.println("you have " + (guesses - failCount) + " tries left ");
			}

			if (failCount == guesses) { // om antalet f�rs�k blir lika mycket som antalet givna gissningar kommer
										// detta visas.
				System.out.println("You lose");
				System.out.println("The correct word was: " + s);
			}

		}

	}

	/**
	 * Denna metod �r f�r multiplayer som jag inte �r klar med
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
	 * Denna metod inneh�ler texten till alternativet "instructions fr�n menyn
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
	 * Denna metod visar hela ordlistan, b�de fr�n easy och hard.
	 * 
	 * @return
	 */
	public static void wordList() {

		System.out.println("Ordlistan f�r easy:"); // Ordlistan fr�n easy
		for (int i = 0; i < words.length; i++) {

			System.out.println(easy[i]);
		}
		System.out.println("");
		System.out.println("Ordlistan f�r hard:"); // Ordlistan fr�n hard
		for (int i = 0; i < words.length; i++) {

			System.out.println(hard[i]);
		}

	}

	/**
	 * Denna metod inneh�ller alla metoder som startar spelet
	 * 
	 * @return
	 */
	public static void startGame() {

		while (true) { // En while loop som ser till att spelet loopas igen ifall man vill spela igen.
			int choise;

			while (true) { // En while loop som g�r det m�jligt att kunna identifiera dumma inputs och l�ta
							// spelaren skriva r�tt input.
				System.out.println("Choose a alternetive: "); // H�r �r menyn som jag gjorde med en switch case.
				System.out.println("(1) - Singleplayer");
				System.out.println("(2) - Multiplayer");
				System.out.println("(3) - Instructions");
				System.out.println("(4) - Wordlist");
				System.out.println("(5) - Exit");

				choise = getValidIntegerInput();

				if (choise < 1 || choise > 5) { // Om "choise" �r mindre �n 1 eller st�rre �n 5, ska programmet s�ga
												// ifr�n.
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
	 * Denna metod �r en trycatch som anv�nds ifall att anv�ndaren skriver en
	 * ogliltig input.
	 * 
	 * @return
	 */
	public static int getValidIntegerInput() {

		int error;
		while (true) {
			try {
				error = input.nextInt(); // "input.nextInt()" byts till "error" vilket g�r att jag kan anv�nda metoden
											// d�r det st�r "input.nextInt().
				return error;

			} catch (Exception e) { // denna kod identifierar ogiltig input.
				System.out.println("write an input representing the alternative you want to choose");
				input.nextLine();
			}
		}

	}

}
