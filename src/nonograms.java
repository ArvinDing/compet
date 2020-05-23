import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.*;

public class nonograms {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("nonogram.in"));
		PrintWriter out = new PrintWriter(new File("nongram.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int w = Integer.parseInt(read.nextToken());
		int h = Integer.parseInt(read.nextToken());
		int[][] answerR = new int[h][w];
		int[][] answerC = new int[w][h];

		int[][] row = new int[h][w];
		int[][] column = new int[w][h];

		int[] rowD = new int[h];
		int[] columnD = new int[w];

		for (int i = 0; i < w; i++) {
			for (int k = 0; k < h; k++) {
				row[k][i] = -1;
				column[i][k] = -1;
			}
		}
		
		for (int i = 0; i < w; i++) {
			read = new StringTokenizer(in.readLine());
			int idx = 0;
			while (read.hasMoreTokens()) {
				column[i][idx] = Integer.parseInt(read.nextToken());
				idx++;
			}
		}
		for (int i = 0; i < h; i++) {
			read = new StringTokenizer(in.readLine());
			int idx = 0;
			while (read.hasMoreTokens()) {
				row[i][idx] = Integer.parseInt(read.nextToken());
				idx++;
			}
		}
		while (true) {
			boolean change = false;
			for (int i = 0; i < w; i++) {
				if (columnD[i] != h)
					if (test(answerC[i], column[i], columnD[i], answerR, i))
						change = true;
			}

			for (int i = 0; i < h; i++) {
				if (rowD[i] != w) {
					if (test(answerR[i], row[i], rowD[i], answerC, i))
						change = true;
				}
			}
			if (change == false)
				break;
		}
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {

				if (answerR[i][j] == 1)
					out.print("*");
				else if (answerR[i][j] == 0)
					out.print("?");
				else
					out.print(".");
			}
			out.println();
		}
		in.close();
		out.close();

	}

	public static boolean test(int[] answer, int[] rules, int doneCnt, int[][] other, int rcIdx) {
		int[] temp = new int[answer.length];
		int[] overlap = new int[answer.length];

		Arrays.fill(overlap, -2);

		boolean change = false;
		recursion(temp, 0, rules, answer, 0, 0, overlap);
		for (int i = 0; i < overlap.length; i++) {
			if (answer[i] == 0 && overlap[i] != 0) {
				answer[i] = overlap[i];
				other[i][rcIdx] = overlap[i];
				doneCnt++;
				change = true;
			}
		}
		return change;

	}

	public static void recursion(int[] temp, int idx, int[] rules, int[] answer, int ruleIdx, int streak,
			int[] overlap) {
		if (idx == temp.length) {
			if (rules[ruleIdx] == -1 || (rules[ruleIdx + 1] == -1 && rules[ruleIdx] == streak)) {
				for (int i = 0; i < temp.length; i++) {
					if (overlap[i] == -2)
						overlap[i] = temp[i];
					else if (temp[i] != overlap[i])
						overlap[i] = 0;
				}
			}
			return;
		}
		if (answer[idx] != 0) {
			temp[idx] = answer[idx];
			if (answer[idx] == 1) {
				if (rules[ruleIdx] == -1)
					return;
				if (rules[ruleIdx] < streak + 1)
					return;
				temp[idx] = 1;
				recursion(temp, idx + 1, rules, answer, ruleIdx, streak + 1, overlap);
			} else {
				if (streak != 0 && rules[ruleIdx] != streak)
					return;
				temp[idx] = -1;
				if (streak == 0)
					recursion(temp, idx + 1, rules, answer, ruleIdx, 0, overlap);
				else
					recursion(temp, idx + 1, rules, answer, ruleIdx + 1, 0, overlap);

			}
			temp[idx] = 0;

		} else {
			if (rules[ruleIdx] >= streak + 1) {
				temp[idx] = 1;
				recursion(temp, idx + 1, rules, answer, ruleIdx, streak + 1, overlap);
			}
			if (streak == 0) {
				temp[idx] = -1;
				recursion(temp, idx + 1, rules, answer, ruleIdx, 0, overlap);
			} else if (rules[ruleIdx] == streak) {
				temp[idx] = -1;
				recursion(temp, idx + 1, rules, answer, ruleIdx + 1, 0, overlap);

			}
		}

	}

}
