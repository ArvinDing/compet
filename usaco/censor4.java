
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Random;

public class censor4 {
	private static long prime;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("censor.in"));
		PrintWriter out = new PrintWriter(new File("censor.out"));
		StringBuilder info = new StringBuilder(in.readLine());
		String remove = in.readLine();
		prime = BigInteger.probablePrime(31, new Random()).longValue();
		long compare = getHash(remove);
		long curr = getHash(info.substring(0, remove.length()));
		long divide = 1;
		for (int i = 0; i < remove.length() - 1; i++) {
			divide *= 27;
			divide %= prime;
		}
		int length = remove.length();
		int i = remove.length();
		while (i <= info.length()) {

			if (curr == compare)
				if (info.substring(i - remove.length(), i).equals(remove)) {
					info.delete(i - remove.length(), i);
					i -= remove.length();
					length = i - Math.max(0, i - remove.length() + 1);
					curr = getHash(info.substring(i - length, i));
				}
			if (i == info.length())
				break;
			if (length == remove.length()) {
				if (i - remove.length() >= 0) {
					curr -= (info.charAt(i - remove.length()) - 96) * divide;
					curr += 27 * prime;
					curr %= prime;
				}
			} else {
				length++;
			}

			curr = (curr * 27) + (info.charAt(i) - 96);
			curr %= prime;
			i++;

		}
		out.print(info.toString());
		out.close();
		in.close();
	}

	private static long getHash(String in) {
		long curr = 0;
		for (int i = 0; i < in.length(); i++) {
			curr = curr * 27 + (in.charAt(i) - 96);
			curr = curr % prime;
		}
		return curr;
	}

}