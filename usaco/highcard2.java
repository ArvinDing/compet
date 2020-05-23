import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class highcard2 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("highcard.in"));
		PrintWriter out = new PrintWriter(new FileWriter("highcard.out"));
		int card = Integer.parseInt(in.readLine());
		boolean[] bessie = new boolean[2 * card];
		Arrays.fill(bessie, true);
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < card; i++) {
			int curr = Integer.parseInt(in.readLine()) - 1;
			bessie[curr] = false;
			min = Math.min(min, curr);

		}
		int wins = 0;
		int bessieIndex = 0;
		outer: for (int i = 0; i < 2 * card; i++) {
			if (!bessie[i]) {
				bessieIndex = Math.max(i + 1, bessieIndex + 1);
				if (bessieIndex == 2 * card) {
					break outer;
				}
				while (!bessie[bessieIndex]) {
					bessieIndex++;
					if (bessieIndex == 2 * card) {
						break outer;
					}
				}
				wins++;

			}

		}

		out.println(wins);
		in.close();
		out.close();
	}

}
