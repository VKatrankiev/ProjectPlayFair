
public class CipherLogic {

	public static String synthesizeCipherWord(String sentence) {
		sentence = sentence.toUpperCase();
		sentence = PlayFairCipher.makeCipherable(sentence);
		StringBuilder sbSentence = new StringBuilder(sentence.toUpperCase());
		int length = sbSentence.length();
		// checking for same letters next to each other in the word to cipher
		// and putting "X" if so (if X -> Q)
		for (int i = 1; i < length; i++) {
			if (sbSentence.charAt(i - 1) == sbSentence.charAt(i)) {
				if (sbSentence.charAt(i - 1) != 'X') {
					sbSentence.insert(i, 'X');
					length++;
				} else {
					sbSentence.insert(i, 'Q');
					length++;
				}
			}
		}
		// checking for odd length and adding "X" if so
		// if the last letter is "X" putting "Q" instead
		fixOddLength(sbSentence, length);
		return sbSentence.toString();
	}

	private static void fixOddLength(StringBuilder sbSentence, int length) {
		if (length % 2 == 1) {
			if (sbSentence.charAt(length - 1) != 'X') {
				sbSentence.append('X');
			} else {
				sbSentence.append('Q');
			}
		}
	}

	public static String cipher(String sentence, char[][] table) {
		int length = sentence.length();
		System.out.println(sentence);
		// swapping letters according to the rules of PlayFair
		char[] result = new char[length];
		for (int i = 0; i < length - 1; i += 2) {
			//finding letters from the given table and putting their coordinates in an array
			int[] rowsNColumns = findLetters(sentence, table, i);
			System.out.println(
					"1" + table[rowsNColumns[0]][rowsNColumns[2]] + " " + table[rowsNColumns[1]][rowsNColumns[3]]);
			//the letters are ciphered using PlayFairLogic
			playFairRules(table, result, i, rowsNColumns);
		}
		return String.valueOf(result);
	}

	private static void playFairRules(char[][] table, char[] result, int i, int[] rowsNColumns) {
		// 1st rule if they are on the same row their columns are increased
		// by 1
		if (rowsNColumns[0] == rowsNColumns[1]) {
			if (rowsNColumns[2] == 4) {
				rowsNColumns[2] = 0;
			} else {
				rowsNColumns[2]++;
			}
			if (rowsNColumns[3] == 4) {
				rowsNColumns[3] = 0;
			} else {
				rowsNColumns[3]++;
			}
			// same goes if they are on the same column
		} else if (rowsNColumns[2] == rowsNColumns[3]) {
			if (rowsNColumns[0] == 4) {
				rowsNColumns[0] = 0;
			} else {
				rowsNColumns[0]++;
			}
			if (rowsNColumns[1] == 4) {
				rowsNColumns[1] = 0;
			} else {
				rowsNColumns[1]++;
			}
		} else {
			int temp = rowsNColumns[2];
			rowsNColumns[2] = rowsNColumns[3];
			rowsNColumns[3] = temp;
		}
		result[i] = table[rowsNColumns[0]][rowsNColumns[2]];
		result[i + 1] = table[rowsNColumns[1]][rowsNColumns[3]];
		System.out.println("2" + result[i] + " " + result[i + 1]);
	}

	private static int[] findLetters(String sentence, char[][] table, int i) {
		int[] rowsNColumns = new int[4];
		boolean flag1 = false;
		boolean flag2 = false;
		for (int j = 0; j < 5; j++) {
			for (int k = 0; k < 5; k++) {
				// first finding every two letters in the table
				if (sentence.charAt(i) == table[j][k]) {
					rowsNColumns[0] = j;
					rowsNColumns[2] = k;
					flag1 = true;
				}
				if (sentence.charAt(i + 1) == table[j][k]) {
					rowsNColumns[1] = j;
					rowsNColumns[3] = k;
					flag2 = true;
					if (flag1) {
						break;
					}
				}

			}
			if (flag2 && flag1) {
				break;
			}
		}
		return rowsNColumns;
	}
}
