import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class relay {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new FileReader("relay.in"));
		PrintWriter out = new PrintWriter(new File("relay.out"));
		int cows = Integer.parseInt(in.readLine());
		int[] info = new int[cows + 1];
		for (int i = 1; i <= cows; i++) {
			info[i] = Integer.parseInt(in.readLine());
		}
		boolean[] loopy = new boolean[cows + 1];
		int loopies = 0;
		for (int i = 1; i <= cows; i++) {
			boolean[] done = new boolean[cows + 1];
			int cow = i;
			while (true) {
				if (cow == 0) {
					break;
				}
				if (done[cow] || loopy[cow]) {
					loopy[i] = true;
					loopies++;
					break;
				}
				done[cow] = true;
				cow = info[cow];

			}
		}
		out.println(cows-loopies);
		out.close();
		in.close();
	}
}
