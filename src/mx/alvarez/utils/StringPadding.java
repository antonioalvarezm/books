package mx.alvarez.utils;

public class StringPadding {

	public static String left(String input, char ch, int L) {
		if (input == null) {
			input = "";
		}
		String result = String
				// First left pad the string
				// with space up to length L
				.format("%" + L + "s", input)
				// Then replace all the spaces
				// with the given character ch
				.replace(' ', ch);
		// Return the resultant string
		return result;
	}

	public static String right(String input, char ch, int L) {
		if (input == null) {
			input = "";
		}
		String result = String
				// First right pad the string
				// with space up to length L
				.format("%" + (-L) + "s", input)
				// Then replace all the spaces
				// with the given character ch
				.replace(' ', ch);
		// Return the resultant string
		return result;
	}
}
