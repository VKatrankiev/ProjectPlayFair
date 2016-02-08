
public class TableGenerator {

	public static String synthesizeKeyword(String keyword) {
		keyword = PlayFairCipher.makeCipherable(keyword.toUpperCase());
		keyword = keyword + "ABCDEFGHIKLMNOPQRSTUVWXYZ";
		StringBuilder sbKeyword = new StringBuilder(keyword);
		int length = sbKeyword.length();
		// removing duplicates from the keyword + alphabet
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
		int count = 0;
		// filling the matrix to return
		char[][] result = new char[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				result[i][j] = keyword.charAt(count);
				count++;
			}
		}
		return result;
	}
}
