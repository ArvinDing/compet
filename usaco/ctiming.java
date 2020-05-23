
/*
ID: dingarv1
LANG: JAVA
TASK: ctiming
*/

import java.io.*;
import java.util.*;

public class ctiming {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new FileReader("ctiming.in"));
		PrintWriter out = new PrintWriter(new FileWriter("ctiming.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int day = Integer.parseInt(read.nextToken());
		int hour = Integer.parseInt(read.nextToken());
		int minute = Integer.parseInt(read.nextToken());

		out.println(Math.max((day - 11) * 1440 + 671 - (hour * 60 + minute), -1));
		out.close();
		in.close();
	}
}
