import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class minPartition {
	private static int minPartition(String input) {
		int[] endDp = new int[input.length() +1];
		
		Arrays.fill(endDp,Integer.MAX_VALUE);
		endDp[0]=-1;
		for (int i = 0; i < input.length(); i++) {
			for (int k = i + 1; k < input.length()+1; k++) {
				if (isPalindrome(input.substring(i, k))) {
					endDp[k] = Math.min(endDp[i] + 1, endDp[k]);
				}
			}
		}
		return endDp[input.length()];
	}

	private static boolean isPalindrome(String str) {
		int start = 0;
		int end = str.length() - 1;
		int half = end / 2;
		for (int i = 0; i <= half; i++, start++, end--) {
			if (str.charAt(start) != str.charAt(end))
				return false;
		}
		return true;
	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(new java.io.FileReader("input"))) {
			System.out.println(minPartition(in.readLine()));
		}
	}
}