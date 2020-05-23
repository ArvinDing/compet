
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

public class hps {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("hps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		StringTokenizer a = new StringTokenizer(in.readLine());
		int games = Integer.parseInt(a.nextToken());
		int changes=Integer.parseInt(a.nextToken());
		int[] winningCombo = new int[games];
		// -1-Rock,0-Paper,1-Siss

		for (int i = 0; i < games; i++) {
			String b = in.readLine();
			if (b.equals("P")) {
				winningCombo[i] = 1;
			} else if (b.equals("H")) {
				winningCombo[i] = 0;
			} else if (b.equals("S")) {
				winningCombo[i] = -1;
			}
		}
		int[][] info = new int[winningCombo.length][3];

		for (int i = 0; i < winningCombo.length; i++) {
			if (i != 0) {
				info[i] = Arrays.copyOf(info[i - 1],3);
			}
			if (winningCombo[i] == -1) {
				info[i][0]++;
			} else if (winningCombo[i] == 0) {
				info[i][1]++;
			} else if (winningCombo[i] == 1) {
				info[i][2]++;
			}
		}
		int overall = Integer.MIN_VALUE;
		for (int split = 0; split < winningCombo.length; split++) {
			int max = Math.max(info[split][0], info[split][1]);
			max = Math.max(max, info[split][2]);
			int max1 = Math.max(info[winningCombo.length - 1][0] - info[split][0],
					info[winningCombo.length - 1][1] - info[split][1]);
			max1 = Math.max(max1, info[winningCombo.length - 1][2] - info[split][2]);
			overall = Math.max(overall, max+max1);
			

		}
		out.println(overall);
		in.close();
		out.close();
	}

}