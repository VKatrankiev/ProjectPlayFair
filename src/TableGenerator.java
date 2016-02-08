
public class TableGenerator {

	public static String synthesizeKeyword(String keyword) {
		keyword = keyword.toUpperCase();
		keyword = PlayFairCipher.makeCipherable(keyword);
		StringBuilder sbKeyword = new StringBuilder(keyword);
		int length = sbKeyword.length();
		// removing duplicates from the keyword
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				if (sbKeyword.charAt(i) == sbKeyword.charAt(j)) {
					sbKeyword.deleteCharAt(j);
					length--; // the length is length - 1 because of the delete
					j--;
				}
			}
		}
		return sbKeyword.toString();
	}

	public static char[][] tableGenerator(String keyword) {
		int length = keyword.length();
		StringBuilder sbKeyword = new StringBuilder(keyword);
		// alphabet to get letters from (without J)
		StringBuilder alphabet = new StringBuilder("ABCDEFGHIKLMNOPQRSTUVWXYZ");
		// excluding the keyword symbols from the alphabet
		int lengthAlpha = alphabet.length();
		for (int i = 0; i < lengthAlpha; i++) {
			for (int j = 0; j < length; j++) {
				if (alphabet.charAt(i) == sbKeyword.charAt(j)) {
					alphabet.deleteCharAt(i);
					i--; // by deleting char at i => charAt(i) = charAt(i+1)
					lengthAlpha--;
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
