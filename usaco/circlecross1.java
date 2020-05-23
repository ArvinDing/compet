
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class circlecross1 {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("circlecross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("circlecross.out")));
		String read = in.readLine();
		boolean[] info = new boolean[26];
		int total = 0;

		boolean[] done = new boolean[52];
		for (int i = 0; i < 52; i++) {
			if (!done[i]) {
				int curr = read.charAt(i) - 65;
				int add = 0;
				boolean[] isntNew = new boolean[26];
				for (int k = i + 1; k < 52; k++) {
					int current = read.charAt(k) - 65;
					if (current == curr) {
						done[k] = true;
						total += add;
					}
					if (!isntNew[current]) {
						add++;
						isntNew[current] = true;
					} else {
						add--;
					}

				}
			}
		}
 		out.print(total / 2);
		in.close();
		out.close();
	}

}