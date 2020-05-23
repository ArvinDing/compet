
/*
ID: dingarv1
LANG: JAVA
TASK: cryptcow
*/

import java.io.*;
import java.util.*;

public class cryptcow {
	static char[] goal = "Begin the Escape execution at the Break of Dawn".toCharArray();
	static BitSet[] hash;
	static final int mod = 1000007;
	static char[] info;
	static int infoL;
	static int[] prev;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cryptcow.in"));
		PrintWriter out = new PrintWriter(new File("cryptcow.out"));
		info = in.readLine().toCharArray();
		infoL = info.length;
		prev = new int[infoL + 1];
		for (int i = 0; i < info.length; i++) {
			if (info[i] == ' ')
				info[i] = '^';
		}
		for (int i = 0; i < goal.length; i++)
			if (goal[i] == ' ')
				goal[i] = '^';

		hash = new BitSet[goal.length + 1];
		for (int i = 1; i <= goal.length; i++) {
			hash[i] = new BitSet(mod);
			int hashC = 0;
			int k = 0;
			int pow = 1;
			for (k = 0; k < i; k++) {
				pow = (pow * 57) % mod;
				hashC = (hashC * 57) % mod;
				hashC += (goal[k] - 65);
			}
			for (; k < goal.length; k++) {
				hash[i].set(hashC);
				hashC = (hashC * 57) % mod;
				hashC = (hashC - (pow * (goal[k - i] - 65) % mod));
				hashC = (hashC + mod) % mod;
				hashC += (goal[k] - 65);
			}
			hash[i].set(hashC);
		}

		int[] number = new int[57];
		for (int i = 0; i < info.length; i++) {
			number[info[i] - 65]++;
		}
		for (int i = 0; i < goal.length; i++) {
			number[goal[i] - 65]--;
		}
		long begin = System.currentTimeMillis();
		int[] next = new int[info.length];
		for (int i = 0; i < info.length; i++)
			next[i] = i + 1;
		if (!firstC(number, info)) {
			out.println(0 + " " + 0);
		} else {
			int encryptions = number[2];
			boolean res = recursion(0, goal.length - 1, 0, next, encryptions);
			if (res) {
				out.println(1 + " " + encryptions);
			} else {
				out.println(0 + " " + 0);
			}
		}
		System.out.println(System.currentTimeMillis() - begin);
		out.close();
		in.close();
	}

	static boolean firstC(int[] number, char[] info) {
		if (infoL < 47 || (infoL - 47) % 3 != 0) {
			return false;
		}
		for (int i = 0; i < 57; i++) {
			if (i == 2 || i == 14 || i == 22)
				continue;
			if (number[i] != 0)
				return false;
		}
		return number[2] == number[14] && number[14] == number[22];
	}

	static int[] check(int start, int[] next, int encryptC) {
		int[] out = new int[encryptC * 3 + 2];

		out[0] = -1;
		int idx = 1;
		int pos = start;
		int previous = -1;
		while (pos != infoL) {
			prev[pos] = previous;
			char curr = info[pos];
			if (curr == 'C' || curr == 'O' || curr == 'W') {
				if (idx == 1 && curr != 'C')
					return null;
				out[idx++] = pos;
			}
			previous = pos;
			pos = next[pos];
		}
		if (encryptC != 0) {
			if (info[out[out.length - 2]] != 'W')
				return null;
		}
		prev[pos] = previous;

		out[out.length - 1] = infoL;
		return out;

	}

	static boolean recursion(int goalS, int goalE, int start, int[] next, int encryptC) {

		int[] imp = check(start, next, encryptC);
		if (imp == null)
			return false;
		int first = imp[1];
		int last = imp[imp.length - 2];
		if (encryptC != 0) {
			while (start != first) {
				if (info[start] != goal[goalS])
					return false;
				goalS++;
				start = next[start];
			}
			int curr = prev[infoL];
			while (last != curr) {
				if (goal[goalE] != info[curr])
					return false;
				curr = prev[curr];
				goalE--;
			}
		}

		for (int i = 0; i < imp.length - 1; i++) {
			int s;
			if (imp[i] == -1)
				s = start;
			else
				s = next[imp[i]];
			int e = imp[i + 1];
			int hashC = 0;
			int length = 0;
			while (s != e) {
				length++;
				hashC = (hashC * 57) % mod;
				hashC += (info[s] - 65);
				s = next[s];
			}
			if (length != 0)
				if (!hash[length].get(hashC))
					return false;
		}

		if (encryptC == 0)
			return true;
		int[] nextCopy = Arrays.copyOf(next, next.length);
		int[] prevCopy = Arrays.copyOf(prev, prev.length);
		prev[first] = -1;
		prev[infoL] = last;
		next[imp[imp.length - 2]] = infoL;

		int[][] seperate = new int[3][encryptC];
		int[][] sPrev = new int[2][encryptC];
		int cTemp = 0;
		int oTemp = 0;
		int wTemp = 0;
		for (int i = 1; i < imp.length - 1; i++) {
			int pos = imp[i];
			char temp = info[pos];
			if (temp == 'C') {
				seperate[0][cTemp++] = pos;
			} else if (temp == 'O') {
				seperate[1][oTemp] = pos;
				sPrev[0][oTemp++] = cTemp;
			} else if (temp == 'W') {
				seperate[2][wTemp] = pos;
				sPrev[1][wTemp++] = oTemp;
			}
		}
		for (int i = encryptC - 1; i >= 0; i--) {
			int wI = seperate[2][i];
			for (int k = 0; k < sPrev[1][i]; k++) {
				int oI = seperate[1][k];
				for (int j = sPrev[0][k] - 1; j >= 0; j--) {
					int cI = seperate[0][j];
					int tempS = start;
					int oldC = -1;
					if (prev[cI] != -1)
						oldC = next[prev[cI]];
					int oldO = next[prev[oI]];
					int oldW = next[prev[wI]];

					int prevC = prev[cI];
					int prevO = prev[oI];
					int prevW = prev[wI];
					int afterbC = next[oI];
					int startPrev = prev[afterbC];
					if (afterbC == wI) {
						if (next[cI] == oI) {
							afterbC = next[wI];
						} else {
							afterbC = next[cI];
						}
					}
					int bC = prev[afterbC];
					int afterW = next[wI];
					int afterC = next[cI];

					if (prevC == -1) {
						tempS = afterbC;
					} else {
						next[prevC] = afterbC;
					}
					if (prev[wI] != oI) {
						if (afterC == oI) {
							next[prevW] = afterW;
						} else {
							next[prevW] = afterC;
						}
					}
					next[prevO] = afterW;
					if (recursion(goalS, goalE, tempS, next, encryptC - 1))
						return true;
					if (prevC != -1) {
						next[prevC] = oldC;
						prev[oldC] = prevC;
					} else {
						prev[tempS] = startPrev;
					}
					next[prevO] = oldO;
					prev[oldO] = prevO;
					next[prevW] = oldW;
					prev[oldW] = prevW;
					prev[afterW] = wI;
					prev[afterC] = cI;
					prev[afterbC] = bC;
					prev[infoL] = last;
				}
			}
		}

		for (int i = 0; i < next.length; i++) {
			next[i] = nextCopy[i];
		}
		for (int i = 0; i < prev.length; i++) {
			prev[i] = prevCopy[i];
		}
		return false;
	}

}
