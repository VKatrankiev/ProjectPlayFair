import java.util.Scanner;

public class PlayFairCipher {

	public static void main(String[] args) {		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Please, insert a keyword to cipher by: ");
		String key = input.nextLine();
		while (!isInputCorrect(key)) {
			System.out.println("There are no symbols or they are not English letters. Please insert a keyword: ");
			key = input.nextLine();
			System.out.println();
		}
		key = TableGenerator.synthesizeKeyword(key);
		
		
		System.out.println("Now please insert a word/sentence to cipher using PlayFair: ");
		String sentence = input.nextLine();
		while (!isInputCorrect(sentence)) {
			System.out
					.println("There are no symbols, or they are not English letters. Please insert a word to cipher: ");
			sentence = input.nextLine();
			System.out.println();
		}
		sentence = CipherLogic.synthesizeCipherWord(sentence);
		
		char[][] table = TableGenerator.tableGenerator(key);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(table[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println(CipherLogic.cipher(sentence, table));
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
