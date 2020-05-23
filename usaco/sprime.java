
/*
ID: dingarv1
LANG: JAVA
TASK: sprime
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
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class sprime {

	public static void main(String[] args) throws Exception {
		int[] init = new int[] { 2, 3, 5, 7 };
		BufferedReader in = new BufferedReader(new FileReader("sprime.in"));
		BufferedWriter out = new BufferedWriter(new FileWriter("sprime.out"));

		int N = Integer.parseInt(in.readLine());
		for (int i : init) {
			checkAndWrite(i, N, out);
		}
		in.close();
		out.close();
		System.exit(0);
	}

	private static void checkAndWrite(int n, int N, BufferedWriter out) throws IOException {
		if (n != 2 && n % 2 == 0){
			return;
		}
		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0)
				return;
		}

		if (Integer.toString(n).length() == N) {
			out.write(n + "");
			out.newLine();
		}
		for (int i = 0; i <= 9; i++)
			checkAndWrite(10 * n + i, N, out);
	}
}
