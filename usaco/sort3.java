
/*
ID: dingarv1
LANG: JAVA
TASK: sort3
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

public class sort3 {

	static int[] arr;


	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("sort3.in"));
		PrintWriter pw = new PrintWriter(new File("sort3.out"));
		int n= in.nextInt();
		arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = in.nextInt();
		in.close();
		pw.println(sort(n));
		pw.close();
	}

	public static int sort(int n) {
		int[] sorted = new int[n];
		for (int i = 0; i < sorted.length; i++){
			sorted[i] = arr[i];
		}
		Arrays.sort(sorted);

		int ex12 = 0;
		int ex21 = 0;
		int ex13 = 0;
		int ex31 = 0;
		int ex23 = 0;
		int ex32 = 0;

		for (int i = 0; i < n; i++) {
			if (arr[i] == 1 && sorted[i] == 2)
				ex12++;
			if (arr[i] == 2 && sorted[i] == 1)
				ex21++;
			if (arr[i] == 1 && sorted[i] == 3)
				ex13++;
			if (arr[i] == 3 && sorted[i] == 1)
				ex31++;
			if (arr[i] == 2 && sorted[i] == 3)
				ex23++;
			if (arr[i] == 3 && sorted[i] == 2)
				ex32++;
		}

		return Math.min(ex12, ex21) + Math.min(ex13, ex31) + Math.min(ex23, ex32)
				+ 2 * (Math.max(ex12, ex21) - Math.min(ex12, ex21));
	}

}
