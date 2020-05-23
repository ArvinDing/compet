
public class e {
	public static void main(String[] args) {

		System.out.println(sameEnds("x"));// "ab"
		// System.out.println(sameEnds("xx"));// "x"
		// System.out.println(sameEnds("xxx"));// "x"

	}

	public static String sameEnds(String string) {
		String a = "";
		if (string.length() == 0)
			return "";
		if (string.length() % 2 == 1) {
			for (int i = 0; i < ((string.length() + 1) / 2); i++) {
				if (string.substring(0, i).equals(string.substring(string.length() - i, string.length()))) {
					a = string.substring(0, i);

				}
			}
		} else {
			for (int i = 0; i < ((string.length() + 1) / 2) + 1; i++) {
				if (string.substring(0, i).equals(string.substring(string.length() - i, string.length()))) {
					a = string.substring(0, i);

				}
			}

		}
		return a;}
	}

