
/*
ID: dingarv1
LANG: JAVA
TASK: rockers
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class rockers {
	private static int n;
	private static int m;
	private static int t;
	private static int[] songs;

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("rockers.in"));
		PrintWriter out = new PrintWriter(new File("rockers.out"));
		String[] noname = in.readLine().split(" ");
		n = Integer.parseInt(noname[0]);
		t = Integer.parseInt(noname[1]);
		m = Integer.parseInt(noname[2]);
		songs = new int[n];
		int index = 0;
		for (String a : in.readLine().split("\\s")) {
			songs[index] = Integer.parseInt(a);
			index++;
		}
		out.println(recursion(t, m-1, 0));
		in.close();
		out.close();
	}

	private static int recursion(int leftM, int leftD, int songI) {
		if (songI > n - 1) {
			return 0;
		}

		if (leftM == 0 && leftD == 0) {
			return 0;
		}

		if (leftM - songs[songI] < 0) {
			if (leftD !=0) {
				return Math.max(recursion(t, leftD - 1, songI), recursion(leftM, leftD, songI + 1));
			} else {
				return recursion(leftM, leftD, songI + 1);
			}
		} else {
			return Math.max(recursion(leftM - songs[songI], leftD, songI + 1) + 1, recursion(leftM, leftD, songI + 1));
		}

	}

}
