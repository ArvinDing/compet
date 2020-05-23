
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Random;

public class censor3 {
	private static int length;
	private static char[] info;
	private static long divide;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("censor.in"));
		PrintWriter out = new PrintWriter(new File("censor.out"));
		StringBuilder last = new StringBuilder(in.readLine());
		StringBuilder first = new StringBuilder();
		String remove = in.readLine();
		long compare = 0;
		long prime = BigInteger.probablePrime(31, new Random()).longValue();
		for (int i = 0; i < remove.length(); i++) {
			compare = compare * 27 + (remove.charAt(i) - 96);
			if (compare >= prime)
				compare = compare % prime;
		}
		String intial = last.substring(0, remove.length());
		long curr = 0;
		long[] save = new long[last.length()];
		for (int i = 0; i < remove.length(); i++) {
			curr = curr * 27 + (intial.charAt(i) - 96);
			if (curr >= prime)
				curr = curr % prime;
			save[i] = curr;
		}
		long divide = 1;
		for (int i = 0; i < remove.length() - 1; i++) {
			divide *= 27;
			if (divide >= prime)
				divide = divide % prime;
		}

		for (int i = 0; i < last.length() - remove.length() + 1; i++) {

			if (curr == compare) {
				if (last.substring(i, i + remove.length()).equals(remove)) {
					for (int k = remove.length() - 2; k >= 0; k--) {
						if (first.length() - (remove.length() - 1) + k < 0) {
							i += k + 1;
							break;
						}
						last.setCharAt(i + k + 1, first.charAt(first.length() - (remove.length() - 1) + k));
					}

					if (i - remove.length() + 1 == -1) {
						curr = 0;
					} else {
						curr = save[i + 1 - remove.length()];
					}

					if (first.length() - (remove.length() - 1) < 0) {
						first.setLength(0);
					} else {
						first.setLength(first.length() - (remove.length() - 1));
					}
				}
			}
			if (i == last.length() - remove.length()+1 )
				break;
			first.append(last.charAt(i));
			save[i] = curr;
			curr = curr % divide;
			
			curr = (curr * 27) + (last.charAt(i + remove.length()) - 96);

		}
		if ((first + "" + last.substring(last.length() - remove.length() + 1)).equals(remove))
			out.print("");
		out.println(first + "" + last.substring(last.length() - remove.length() + 1));
		out.close();
		in.close();
	}

	
}