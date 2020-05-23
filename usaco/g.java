
public class g {
	public static void main(String[] args) {

		System.out.println(sameEnds("x"));// "ab"
		// System.out.println(sameEnds("xx"));// "x"
		// System.out.println(sameEnds("xxx"));// "x"

	}

	public static String sameEnds(String string) {
		String a = "";
		if (string.length() == 0)
			return "";
		for (int i = 0; i < (string.length()); i++) {
			if (string.charAt(i) == string.charAt(string.length() - i - 1)) {
				a = a + string.charAt(i);
				continue;
			}
			break;
		}
		return a;
	}
}
