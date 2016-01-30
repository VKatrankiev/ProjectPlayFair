
public class TableGenerator {

	public static char[][] tableGenerator(String keyword) {
		// checking for incorrect input
		if (keyword.length() == 0 || keyword == null) {
			System.out.println("The keyword must have symbols!");
			return null;
		}
		keyword = keyword.toUpperCase();
		StringBuilder sbKeyword = new StringBuilder(keyword);
		// checking for other characters than letters (upper case)
		// and replacing J with I
		int length = sbKeyword.length();
		for (int i = 0; i < length; i++) {
			if (sbKeyword.charAt(i) == 'J') {
				sbKeyword.deleteCharAt(i);
				sbKeyword.insert(i, 'I');
			}
			if (sbKeyword.charAt(i) < 65 || sbKeyword.charAt(i) > 90) {
				sbKeyword.deleteCharAt(i);
				length--;
				i--;
			}
		}

		// alphabet to get letters from (without J)
		StringBuilder alphabet = new StringBuilder("ABCDEFGHIKLMNOPQRSTUVWXYZ");

		// removing duplicates from the keyword
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				if (sbKeyword.charAt(i) == sbKeyword.charAt(j)) {
					sbKeyword.deleteCharAt(j);
					length--; // the length is length - 1 because of the delete
					j--;
				}
			}
			if (sbKeyword.charAt(i) == ' ') { // excluding the space too
				sbKeyword.deleteCharAt(i);
				i--;
				length--;
			}
		}

		// excluding the keyword symbols from the alphabet
		int lengthAlpha = alphabet.length();
		for (int i = 0; i < lengthAlpha; i++) {
			for (int j = 0; j < length; j++) {
				if (alphabet.charAt(i) == sbKeyword.charAt(j)) {
					alphabet.deleteCharAt(i);
					i--; // by deleting char at i => charAt(i) = charAt(i+1)
					lengthAlpha--; // same as "length"
					break;
				}
			}
		}

		keyword = sbKeyword.toString(); // returning to usual string again
		length = keyword.length();
		byte count = 0;
		byte countAlpha = 0;

		// filling the matrix to return
		char[][] result = new char[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				// first putting the synthesized keyword
				if (count < length) {
					result[i][j] = keyword.charAt(count);
				} else {
					// then putting the letters from the alphabet
					result[i][j] = alphabet.charAt(countAlpha);
					countAlpha++;
				}
				count++;
			}
		}
		return result;
	}
}
