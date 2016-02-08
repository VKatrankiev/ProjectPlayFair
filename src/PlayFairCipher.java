import java.util.Scanner;

public class PlayFairCipher {

	public static void main(String[] args) {	
		Scanner input = new Scanner(System.in);
		//the method (inputTable) uses different methods for the two words
		System.out.print("Please, insert a keyword to cipher by: ");
		
		String key = TableGenerator.synthesizeKeyword(inputData(input));
		System.out.print("Please, insert a word/sentence to be ciphered: ");
		String sentence = CipherLogic.synthesizeCipherWord(inputData(input));
		viewTable(key, sentence);
	}

	private static void viewTable(String key, String sentence) {
		char[][] table = TableGenerator.tableGenerator(key);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(table[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println(CipherLogic.cipher(sentence, table));
	}

	private static String inputData(Scanner input) {
		String key = input.nextLine();
		while (!isInputCorrect(key)) {
			System.out.println("There are no symbols or they are not English letters. Please insert a correct input: ");
			key = input.nextLine();
			System.out.println();
		}
		return key;
	}

	private static boolean isInputCorrect(String str) {
		if (str.length() == 0 || str == null) {
			return false;
		} else {
			str = str.toUpperCase();
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) > 64 && str.charAt(i) < 91) {
					return true;
				}
			}
			return false;
		}
	}

	public static String makeCipherable(String sentence) {
		StringBuilder sbSentence = new StringBuilder (sentence);
		int length = sbSentence.length();
		// checking for other symbols than alphabet Upper Case characters
		// and replacing J with I
		for (int i = 0; i < length; i++) {
			if (sbSentence.charAt(i) < 65 || sbSentence.charAt(i) > 90) {
				sbSentence.deleteCharAt(i);
				length--;
				i--;
				continue;
			}
			if (sbSentence.charAt(i) == 'J') {
				sbSentence.deleteCharAt(i);
				sbSentence.insert(i, 'I');
			}
		}
		return sbSentence.toString();
	}

}
