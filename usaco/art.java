
import java.util.*;
import java.io.*;

public class art {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("art.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("art.out")));
		int size = Integer.parseInt(in.readLine());
		int[][] info = new int[size][size];
		for (int i = 0; i < size; i++) {
			String read = in.readLine();
			for (int k = 0; k < size; k++) {
				info[i][k] = Integer.parseInt("" + read.charAt(k));
			}
		}
		int[][] minNmax = new int[10][4];
		for (int i = 1; i < 10; i++) {
			minNmax[i][0] = Integer.MIN_VALUE;
			minNmax[i][1] = Integer.MIN_VALUE;
			minNmax[i][2] = Integer.MAX_VALUE;
			minNmax[i][3] = Integer.MAX_VALUE;
		}
		// maxX,maxY,minX,minY
		for (int i = 0; i < size; i++) {
			for (int k = 0; k < size; k++) {
				if (info[i][k] != 0) {
					minNmax[info[i][k]][0] = Math.max(k, minNmax[info[i][k]][0]);
					minNmax[info[i][k]][1] = Math.max(i, minNmax[info[i][k]][1]);
					minNmax[info[i][k]][2] = Math.min(k, minNmax[info[i][k]][2]);
					minNmax[info[i][k]][3] = Math.min(i, minNmax[info[i][k]][3]);
				}
			}
		}
		int cnt = 0;
		boolean[] possible = new boolean[10];
		Arrays.fill(possible, true);
		for (int i = 1; i <= 9; i++) {
			if (minNmax[i][0] < 0) {
				possible[i] = false;
				continue;
			}
			for (int j = minNmax[i][3]; j <= minNmax[i][1]; j++) {
				for (int k = minNmax[i][2]; k <= minNmax[i][0]; k++) {
					if (info[j][k] != i) {
						possible[info[j][k]] = false;
					}
				}
			}
		}
		for (int i = 1; i <= 9; i++) {
			if (possible[i])
				cnt++;
		}
		out.println(cnt);
		out.close();
		in.close();
	}

}