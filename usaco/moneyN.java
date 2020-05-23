
/*
ID: dingarv1
LANG: JAVA
TASK: money
*/

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class moneyN {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("money.in"));
		PrintWriter out = new PrintWriter(new File("money.out"));

		int num = in.nextInt();
		int goal = in.nextInt();
		long[] cost = new long[goal+ 1];
		cost[0] = 1;

		for (int i = 0; i < num; i++) {
			int m = in.nextInt();
			for (int j = m; j <= goal; j++) {
				cost[j] += cost[j - m];
			}
		}
		out.println(cost[goal]);
		in.close();
		out.close();
	}
}
