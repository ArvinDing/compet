
/*
ID: dingarv1
LANG: JAVA
TASK: inflate
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class inflate {
	private static Scanner in;
	private static int max;
	private static int classes;
	private static int[] cost;

	public static void main(String[] args) throws Exception {
		in = new Scanner(new File("inflate.in"));
		PrintWriter out = new PrintWriter(new File("inflate.out"));

		max = in.nextInt();
		classes = in.nextInt();
		cost = new int[max + 1];
		
		for (int i = 0; i < classes; i++) {
			int point = in.nextInt();
			int time = in.nextInt();
			for (int j = time; j <= max; j++) {
				cost[j] = Math.max(cost[j], cost[j - time] + point);
			}

		}

		out.println(cost[max]);
		out.close();
		in.close();
	}


}