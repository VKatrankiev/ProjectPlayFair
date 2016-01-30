
public class CipherMagic {

	public static String convert(String sentence, char[][] table) {
		// checking for incorrect input
		if (sentence.length() == 0 || sentence == null) {
			System.out.println("The the word to cipher must have symbols!");
			return null;
		}
		// checking for other symbols than alphabet Upper Case characters
		sentence = sentence.toUpperCase();
		StringBuilder segments = new StringBuilder(sentence);
		int length = segments.length();
		for (int i = 0; i < length; i++) {
			if (segments.charAt(i) < 65 || segments.charAt(i) > 90) {
				segments.deleteCharAt(i);
				length--;
				i--;
			}

		}

		// checking for "J" and turning it into "I"
		for (int i = 0; i < length; i++) {
			if (segments.charAt(i) == 'J') {
				segments.deleteCharAt(i);
				segments.insert(i, 'I');
			}
		}
		
		// checking for odd length and adding "X" if so
		// if the last letter is "X" putting "Q" instead
		if (length % 2 == 1) {
			if (segments.charAt(length - 1) != 'X') {
				segments.append('X');
				length++;
			} else {
				segments.append('Q');
				length++;
			}

		}
		
		// checking for same letters next to each other in the word to cipher
		// and putting "X" if so (if X -> Q)
		for (int i = 1; i < length; i++) {
			if (segments.charAt(i - 1) == segments.charAt(i)) {
				if(segments.charAt(i - 1) != 'X'){
				segments.insert(i, 'X');
				length++;
				}else { 
					segments.insert(i,'Q');
					length++;
				}
			}
		}

		System.out.println(segments);
		// swapping letters according to the rules of PlayFair
		int row1 = 0, row2 = 0, column1 = 0, column2 = 0;
		byte flag1 = 0, flag2 = 0;
		char[] result = new char[length];
		for (int i = 0; i < length - 1; i += 2) {
			flag1 = 0;
			flag2 = 0;
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					// first finding every two letters in the table
					if (segments.charAt(i) == table[j][k]) {
						row1 = j;
						column1 = k;
						flag1 = 1;
					}

					if (segments.charAt(i + 1) == table[j][k]) {
						row2 = j;
						column2 = k;
						flag2 = 1;
						if (flag1 == 1) {
							break;
						}

					}

				}

				if (flag2 == 1 && flag1 == 1) {
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
