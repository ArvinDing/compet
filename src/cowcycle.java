
/*
ID: dingarv1
LANG: JAVA
TASK: cowcycle
*/

import java.io.*;
import java.util.*;

public class cowcycle {
	static int[] frontI, frontAns;
	static int[] revI, revAns;
	static int front, rev, f1, f2, r1, r2;
	static int var;
	static double best;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cowcycle.in"));
		PrintWriter out = new PrintWriter(new File("cowcycle.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		front = Integer.parseInt(read.nextToken());
		rev = Integer.parseInt(read.nextToken());
		read = new StringTokenizer(in.readLine());
		f1 = Integer.parseInt(read.nextToken());
		f2 = Integer.parseInt(read.nextToken());
		r1 = Integer.parseInt(read.nextToken());
		r2 = Integer.parseInt(read.nextToken());
		frontI = new int[front];
		revI = new int[rev];
		frontAns = new int[front];
		revAns = new int[rev];
		best = Double.MAX_VALUE;
		calF(0, f1);
		out.print(frontAns[0]);
		for (int i = 1; i < front; i++) {
			out.print(" " + frontAns[i]);
		}
		out.println();
		out.print(revAns[0]);
		for (int i = 1; i < rev; i++) {
			out.print(" " + revAns[i]);
		}
		out.println();
		in.close();
		out.close();
	}

	static void calF(int frontIdx, int i) {
		if (frontIdx == front) {
			calR(0, r1);
		} else {
			for (; i <= f2; i++) {
				frontI[frontIdx] = i;
				calF(frontIdx + 1, i + 1);
			}
		}

	}

	static void calR(int revIdx, int i) {
		if (revIdx == rev) {
			calc();
		} else {
			for (; i <= r2; i++) {
				revI[revIdx] = i;
				calR(revIdx + 1, i + 1);
			}
		}
	}

	static void calc() {
		if ( frontI[front - 1] * revI[rev - 1] < 3*frontI[0] * revI[0])
			return;
		double[] ratios = new double[front * rev];
		for (int i = 0; i < front; i++) {
			for (int k = 0; k < rev; k++) {
				ratios[i * rev + k] = (double) frontI[i] / revI[k];
			}
		}
		Arrays.sort(ratios);
		double sum = 0;
		double mean = (ratios[ratios.length - 1] - ratios[0]) / (front * rev - 1);
		for (int i = 0; i < front * rev - 1; i++) {
			double diff = ratios[i + 1] - ratios[i];
			sum += (diff - mean) * (diff - mean);
		}
		if (sum < best) {
			best = sum;
			for (int i = 0; i < front; i++)
				frontAns[i] = frontI[i];
			for (int i = 0; i < rev; i++)
				revAns[i] = revI[i];
		}
	}
}
