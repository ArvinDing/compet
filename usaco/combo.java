
/*
ID: dingarv1
LANG: JAVA
TASK: combo
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class combo {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("combo.in"));
		PrintWriter out = new PrintWriter(new FileWriter("combo.out"));
		int N = Integer.parseInt(in.readLine());
		String[] farmer = in.readLine().split(" ");
		String[] master = in.readLine().split(" ");
		out.println(cnt(farmer, N) + cnt(master, N));
		out.close();
		in.close();
	}

	private static boolean[][][] save = new boolean[101][101][101];

	private static int cnt(String[] info, int N) {
		int cnt = 0;
		for (int i = Integer.parseInt(info[0]) - 2; i <= Integer.parseInt(info[0]) + 2; i++) {
			for (int k = Integer.parseInt(info[1]) - 2; k <= Integer.parseInt(info[1]) + 2; k++) {
				for (int j = Integer.parseInt(info[2]) - 2; j <= Integer.parseInt(info[2]) + 2; j++) {
					int newI = (i + N) % N;
					int newK = (k + N) % N;
					int newJ = (j + N) % N;
					if (!save[newI][newK][newJ]) {
						cnt++;
					}
					save[newI][newK][newJ] = true;

				}
			}
		}
		return cnt;
	}
}
