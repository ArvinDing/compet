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

public class highcard {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("highcard.in"));
		PrintWriter out = new PrintWriter(new FileWriter("highcard.out"));
		int card = Integer.parseInt(in.readLine());
		boolean[] bessie = new boolean[2 * card];
		Arrays.fill(bessie, true);
		boolean[] elsie = new boolean[2 * card];
		for (int i = 0; i < card; i++) {
			int curr = Integer.parseInt(in.readLine()) - 1;
			elsie[curr] = true;
			bessie[curr] = false;
		}
		int wins = 0;
		int max = findMax(bessie);
		int min = findMin(bessie);
		outer: for (int i = 0; i < 2 * card; i++) {
			if (elsie[i]) {
				if (max < i) {
					bessie[min] = false;
					min = findMin(bessie);
					continue outer;
				}
				for (int k = i + 1; k < 2 * card; k++) {
					if (bessie[k]) {
						bessie[k] = false;
						if (max == k) {
							max = findMax(bessie);
						}
						if (min == k) {
							min = findMin(bessie);
						}
						wins++;
						break;

					}
				}

			}
		}
		out.println(wins);
		in.close();
		out.close();
	}

	private static int findMax(boolean[] bessie) {
		for (int i = bessie.length - 1; i >= 0; i--) {
			if (bessie[i])
				return i;
		}
		return -1;
	}

	private static int findMin(boolean[] bessie) {
		for (int i = 0; i < bessie.length; i++) {
			if (bessie[i])
				return i;
		}
		return -1;
	}
}
