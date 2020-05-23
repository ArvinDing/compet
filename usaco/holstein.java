
/*
ID: dingarv1
LANG: JAVA
TASK: holstein
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class holstein {
	static int[] min;
	static int[][] info;
	static boolean[] Comb;
	static int lowN;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("holstein.in"));
		BufferedWriter out = new BufferedWriter(new FileWriter("holstein.out"));
		int V = in.nextInt();
		min = new int[V];
		for (int i = 0; i < V; i++) {
			min[i] = in.nextInt();
		}
		int G = in.nextInt();
		info = new int[G][V];
		for (int i = 0; i < G; i++) {
			for (int j = 0; j < V; j++) {
				info[i][j] = in.nextInt();
			}
		}
		lowN = Integer.MAX_VALUE;
		int[] currV = new int[V];
		boolean[] answer = new boolean[G];
		recursion(0, 0, currV, answer);
		out.write(lowN+"");
		for (int i = 0; i < Comb.length; i++) {
			if (Comb[i]) {
				 out.write( " "+(i + 1));
			}
		}
		out.newLine();
		in.close();
		out.close();
	
	}

	public static void recursion(int curfeed, int curfeednum, int[] currV, boolean[] answer) {
		if (curfeednum < lowN && possible(currV)) {
			lowN = curfeednum;
			Comb = Arrays.copyOf(answer, answer.length);
			return;
		} else if (curfeednum >= lowN  || curfeed >= answer.length) {
			return;
		}
		int[] prvV = Arrays.copyOf(currV, currV.length);
		for (int i = 0; i < currV.length; i++) {
			currV[i] += info[curfeed][i];
		}
		answer[curfeed] = true;
		recursion(curfeed + 1, curfeednum + 1, currV, answer);
		answer[curfeed] = false;
		recursion(curfeed + 1, curfeednum, prvV, answer);

	}

	public static boolean possible(int[] vits) {
		for (int i = 0; i < vits.length; i++) {
			if (vits[i] < min[i])
				return false;
		}
		return true;
	}
}
