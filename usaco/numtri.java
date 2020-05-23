
/*
ID: dingarv1
LANG: JAVA
TASK: numtri
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class numtri {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("numtri.in"));
		BufferedWriter out = new BufferedWriter(new FileWriter("numtri.out"));

		int[] oldRow = null;
		int[] newRow = null;

		int N = Integer.parseInt(in.readLine());
		for (int i = 1; i <= N; i++) {

			if (i != 1)
				oldRow = newRow;

			newRow = new int[N + 2];
			newRow[0] = 0;
			newRow[N + 1] = 0;
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int j = 1; j <= i; j++)
				newRow[j] = Integer.parseInt(st.nextToken());

			if (i != 1)
				for (int j = 1; j <= i; j++)
					newRow[j] += Math.max(oldRow[j - 1], oldRow[j]);
		}

		int maxValue = 0;
		for (int i = 0; i < newRow.length; i++)
			maxValue = Math.max(newRow[i], maxValue);
		out.write(""+maxValue);
		out.newLine();
		out.close();
		in.close();

		System.exit(0);
	}

}
