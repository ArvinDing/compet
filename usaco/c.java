
public class c {
	public static void main(String[] args) {
		System.out.println(countTriple("abcXXXabc")); // 1
		System.out.println(countTriple("xxxabyyyycd"));// 3
		System.out.println(countTriple("a"));// 0

	}

	public static int countTriple(String str) {
		int count = 0;
		if (str.length() < 3)
			return 0;
		for (int i = 0; i < str.length() - 2; i++) {
			if (str.charAt(i) == str.charAt(i + 1) && str.charAt(i) == str.charAt(i + 2)) {
				count++;
				
			}

		}
		return count;
	}

}
