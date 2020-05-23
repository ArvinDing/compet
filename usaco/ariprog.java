
/*
ID: dingarv1
LANG: JAVA
TASK: ariprog
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ariprog {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("ariprog.in"));
		BufferedWriter out = new BufferedWriter(new FileWriter("ariprog.out"));
		int N = Integer.parseInt(in.readLine());
		int M = Integer.parseInt(in.readLine());

		int help = M * M * 2;

		boolean[] bisquares = new boolean[help + 1];
		for (int p = 0; p <= M; p++)
			for (int q = p; q <= M; q++)
				bisquares[p * p + q * q] = true;

		List<int[]> res = new ArrayList<int[]>();
		for (int a = 0; a < help; a++) {
			if (!bisquares[a])
				continue;
			label: for (int b = 1; b <= (help - a) / (N - 1); b++) {

				for (int i = 1; i <= N - 1; i++) {
					if (!bisquares[a + b * i])
						continue label;
				}
				res.add(new int[] { a, b });
			}
		}

		Collections.sort(res, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				if (o1[1] < o2[1])
					return -1;
				if (o1[1] > o2[1])
					return 1;
				if (o1[0] < o2[0])
					return -1;
				if (o1[0] < o2[0])
					return 1;
				return 0;
			}
		});
		if (res.size() == 0) {
			out.write("NONE");
			out.newLine();
		}
		for (int[] ab : res) {
			out.write(ab[0] + " " + ab[1]);
			out.newLine();
		}
		out.close();
		in.close();
		System.exit(0);
	}

}
