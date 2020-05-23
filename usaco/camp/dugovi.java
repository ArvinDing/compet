
package camp;

import java.io.*;
import java.util.*;

public class dugovi {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		int n = Integer.parseInt(in.readLine());
		int[][] info = new int[n][2];
		int[] notPayedOff = new int[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int owedTo = Integer.parseInt(read.nextToken());
			int value = Integer.parseInt(read.nextToken());
			info[i] = new int[] { owedTo, value };
			notPayedOff[owedTo]++;
		}

	}
}
