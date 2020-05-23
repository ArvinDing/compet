
import java.io.*;
import java.util.*;

public class hps2 {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("hps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		int lines = Integer.parseInt(in.readLine());
		int[][] info = new int[3][3];
		for (int i = 0; i < lines; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			info[Integer.parseInt(read.nextToken())-1][Integer.parseInt(read.nextToken())-1]++;
		}
		int max = Integer.MIN_VALUE;
		// RPS|RSP|PSR|PRS|SRP|SPR
		max = Math.max(max, info[0][2] + info[2][1] + info[1][0] );
		max = Math.max(max, info[1][2] + info[2][0] + info[0][1]);
		max = Math.max(max, info[2][0] + info[0][1] + info[1][2] );
		out.println(max);
		in.close();
		out.close();
	}

}