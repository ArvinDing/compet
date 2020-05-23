
import java.io.*;
import java.util.*;

public class superbull2 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("superbull.in"));
		PrintWriter out = new PrintWriter(new File("superbull.out"));
		int teams = Integer.parseInt(in.readLine());
		int[] info = new int[teams];
		for (int i = 0; i < teams; i++) {
			info[i] = Integer.parseInt(in.readLine());
		}
		int[] distance = new int[teams];
		long answer = 0;
		Arrays.fill(distance, -1);
		for (int i = 0; i < teams; i++) {
			long maxDistance = Integer.MIN_VALUE;
			int index = -1;
			for (int k = 0; k < teams; k++) {
				if (distance[k] != 0) {
					if (distance[k] > maxDistance) {
						maxDistance = distance[k];
						index = k;
					}
				}
			}
			distance[index] = 0;
			if (maxDistance == -1)
				maxDistance = 0;
			answer += maxDistance;
			for (int k = 0; k < teams; k++) {
				if (distance[k] != 0) {
					distance[k] = Math.max(distance[k], info[index] ^ info[k]);
				}
			}
		}
		out.println(answer);
		in.close();
		out.close();
	}
}