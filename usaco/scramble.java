
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class scramble {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("scramble.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("scramble.out")));
		int lines = Integer.parseInt(in.readLine());
		String[] sorted = new String[lines];
		String[] revSorted = new String[lines];
		String[] orignal = new String[lines];
		for (int i = 0; i < lines; i++) {
			char[] temp = in.readLine().toCharArray();
			Arrays.sort(temp);
			sorted[i] = String.valueOf(temp);
			revSorted[i] = new StringBuilder(sorted[i]).reverse().toString();
			orignal[i] = sorted[i];
		}
		Arrays.sort(sorted);
		Arrays.sort(revSorted);
		for (String a : orignal) {
			char[] temp = a.toCharArray();
			Arrays.sort(temp);
			int saveS = Arrays.binarySearch(sorted, new StringBuilder(String.valueOf(temp)).reverse().toString());
			if (saveS < 0) {
				saveS = -1 * saveS - 1;
			} else {
				saveS++;
			}
			int saveR = Arrays.binarySearch(revSorted, String.valueOf(temp));
			if (saveR < 0)
				saveR = -1 * saveR -1;
			saveR++;

			out.println(saveR + " " + saveS);
		}
		in.close();
		out.close();

	}
}