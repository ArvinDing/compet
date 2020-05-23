
/*
ID: dingarv1
LANG: JAVA
TASK: concom
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class concom {

	static Scanner in;

	public static void main(String[] args) throws java.io.IOException {
		String prob = "concom";
		in = new Scanner(new File(prob + ".in"));
		PrintWriter out = new PrintWriter(new File("concom.out"));
		int[][] arr = new int[101][101];
		int n = in.nextInt();
		int a = 0;
		int b = 0;
		int c = 0;
		int max = 100;
		for (int i = 0; i < n; i++) {
			a = in.nextInt();
			b = in.nextInt();
			c = in.nextInt();
			arr[a][b] = c;
		}
		boolean v[][] = new boolean[101][101];
		for (int i = 1; i <= 100; i++) {
			for (int j = 1; j <= 100; j++) {
				if (i != j && !v[i][j] && arr[i][j] > 50) {
					v[i][j] = true;
					for (int k = 1; k <= max; k++) {
						arr[i][k] += arr[j][k];
						if (v[j][k])
							v[i][k] = true;
					}
					j = 0;
				}
			}
		}
		for (int i = 0; i < arr.length; i++)
			for (int j = 0; j < arr[0].length; j++)
				if (v[i][j] && i != j)
					out.println(i + " " + j);
		out.close();
	}

}
