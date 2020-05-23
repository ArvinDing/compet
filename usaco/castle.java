
/*
ID: dingarv1
LANG: JAVA
TASK: castle
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
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class castle {

	public static void main(String[] args) throws Exception {

		Scanner in = new Scanner(new File("castle.in"));
		BufferedWriter out = new BufferedWriter(new FileWriter("castle.out"));

		int y = in.nextInt();
		int x = in.nextInt();
		int[][] direct = new int[x][y];

		for (int i = 0; i < x; i++) {
			for (int k = 0; k < y; k++) {
				direct[i][k] = in.nextInt();

			}
		}
		int nxtRm = 1;

		for (int i = 0; i < x; i++) {
			for (int k = 0; k < y; k++) {

				if (direct[i][k] < 100) {
					updateRm(i, k, direct, nxtRm);
					nxtRm++;
				}
			}
		}
		Map<Integer, Integer> keyMap = new HashMap<Integer, Integer>();
		for (int i = 1; i < nxtRm; i++) {
			keyMap.put(i, 0);
		}
		for (int i = 0; i < x; i++) {
			for (int k = 0; k < y; k++) {
				keyMap.put(direct[i][k] / 100, keyMap.get(direct[i][k] / 100) + 1);
			}
		}
		int max = 0;
		for (int i = 1; i < nxtRm; i++) {
			if (max < keyMap.get(i)) {
				max = keyMap.get(i);
			}

		}
		int maxComb = 0;
		int currI = 0;
		int currK = 0;
		char currD = 'W';
		
		for (int k = 0; k < y; k++) {
			for (int i = x-1; i >=0; i--) {
				if (!(isConnectedL(direct[i][k])) && k != 0) {
					if (direct[i][k] / 100 != direct[i][k - 1] / 100) {
						if (maxComb < keyMap.get(direct[i][k] / 100) + keyMap.get(direct[i][k - 1] / 100)) {
							maxComb = keyMap.get(direct[i][k] / 100) + keyMap.get(direct[i][k - 1] / 100);
							currI = i+1;
							currK = k+1;
							currD = 'W';
						}
					}
				}
				if (!(isConnectedB(direct[i][k])) && i != x - 1) {
					if (direct[i][k] / 100 != direct[i + 1][k] / 100) {
						if (maxComb < keyMap.get(direct[i][k] / 100) + keyMap.get(direct[i + 1][k] / 100)) {
							maxComb = keyMap.get(direct[i][k] / 100) + keyMap.get(direct[i + 1][k] / 100);
							currI = i+1;
							currK = k+1;
							currD = 'S';
						}
					}
				}
				if (!(isConnectedT(direct[i][k])) && i != 0) {
					if (direct[i][k] / 100 != direct[i - 1][k] / 100) {
						if (maxComb < keyMap.get(direct[i - 1][k] / 100) + keyMap.get(direct[i][k] / 100)) {
							maxComb = keyMap.get(direct[i - 1][k] / 100) + keyMap.get(direct[i][k] / 100);
							currI = i+1;
							currK = k+1;
							currD = 'N';
						}
					}
				}
				if (!(isConnectedR(direct[i][k])) && k != y - 1) {
					if (direct[i][k] / 100 != direct[i][k + 1] / 100) {
						if (maxComb < keyMap.get(direct[i][k] / 100) + keyMap.get(direct[i][k + 1] / 100)) {
							maxComb = keyMap.get(direct[i][k] / 100) + keyMap.get(direct[i][k + 1] / 100);
							currI = i+1;
							currK = k+1;
							currD = 'E';
						}
					}
				}
			}
		}

		in.close();

		out.write("" + (nxtRm - 1));
		out.newLine();
		out.write("" + max);
		out.newLine();
		out.write("" + maxComb);
		out.newLine();
		out.write(currI + " " + currK + " " + currD);
		out.newLine();
		out.close();
		System.exit(0);
	}

	private static boolean isConnectedL(int a) {
		a = a % 100;
		if ((a & 1) == 0) {
			return true;

		}
		return false;
	}

	private static void updateRm(int i, int k, int[][] direct, int rm) {
		if (direct[i][k] < 100) {
			direct[i][k] += rm * 100;

			if (isConnectedL(direct[i][k])) {
				updateRm(i, k - 1, direct, rm);
			}
			if (isConnectedT(direct[i][k])) {
				updateRm(i - 1, k, direct, rm);
			}
			if (isConnectedR(direct[i][k])) {
				updateRm(i, k + 1, direct, rm);
			}
			if (isConnectedB(direct[i][k])) {
				updateRm(i + 1, k, direct, rm);
			}
		}
	}

	private static boolean isConnectedT(int a) {
		a = a % 100;
		if ((a & 2) == 0) {
			return true;

		}
		return false;
	}

	private static boolean isConnectedR(int a) {
		a = a % 100;
		if ((a & 4) == 0) {
			return true;

		}
		return false;
	}

	private static boolean isConnectedB(int a) {
		a = a % 100;
		if ((a & 8) == 0) {
			return true;

		}
		return false;
	}

}
