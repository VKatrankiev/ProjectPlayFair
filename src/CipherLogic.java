
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
		if (length % 2 == 1) {
			if (sbSentence.charAt(length - 1) != 'X') {
				sbSentence.append('X');
				length++;
			} else {
				sbSentence.append('Q');
				length++;
			}
		}
		return sbSentence.toString();
	}

	public static String cipher(String sentence, char[][] table) {
		int length = sentence.length();
		System.out.println(sentence);
		// swapping letters according to the rules of PlayFair
		int row1 = 0, row2 = 0, column1 = 0, column2 = 0;
		boolean flag1, flag2 ;
		char[] result = new char[length];
		for (int i = 0; i < length - 1; i += 2) {
			flag1 = false;
			flag2 = false;
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					// first finding every two letters in the table
					if (sentence.charAt(i) == table[j][k]) {
						row1 = j;
						column1 = k;
						flag1 = true;
					}
					if (sentence.charAt(i + 1) == table[j][k]) {
						row2 = j;
						column2 = k;
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
			System.out.println("1" + table[row1][column1] + " " + table[row2][column2]);
			// 1st rule if they are on the same row their columns are increased
			// by 1
			if (row1 == row2) {
				if (column1 == 4) {
					column1 = 0;
				} else {
					column1++;
				}
				if (column2 == 4) {
					column2 = 0;
				} else {
					column2++;
				}
				// same goes if they are on the same column
			} else if (column1 == column2) {
				if (row1 == 4) {
					row1 = 0;
				} else {
					row1++;
				}
				if (row2 == 4) {
					row2 = 0;
				} else {
					row2++;
				}
			} else {
				int temp = column1;
				column1 = column2;
				column2 = temp;
			}
			result[i] = table[row1][column1];
			result[i + 1] = table[row2][column2];
			System.out.println("2" + result[i] + " " + result[i + 1]);
		}

		StringBuilder realResult = new StringBuilder();
		for (int i = 0; i < length; i++) {
			realResult.append(result[i]);
		}
		return realResult.toString();
	}

}
