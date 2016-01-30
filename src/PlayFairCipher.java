import java.util.Scanner;

public class PlayFairCipher {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner (System.in);
		System.out.print("Please, insert a keyword to cipher by: ");
		String key = input.nextLine();
		System.out.println();
		System.out.println("Now please insert a word/sentence to cipher using PlayFair: ");
		String sentence = input.nextLine();
		char[][] table = TableGenerator.tableGenerator(key);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(table[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println(CipherMagic.convert(sentence, table));
		
		

	}

}
