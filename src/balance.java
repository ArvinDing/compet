import java.io.*;
import java.util.*;

public class balance {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("balance.in"));
		PrintWriter out = new PrintWriter(new File("balance.out"));
		int n = Integer.parseInt(in.readLine());
		int[][] info = new int[n][2];
		float[] expect = new float[n + 2];
		expect[0] = 0;
		expect[n+1 ] = 0;
		int max = 0;
		for (int i = 0; i < n; i++) {
			info[i] = new int[] { Integer.parseInt(in.readLine()), i + 1 };
			max = Math.max(max, info[i][0]);
		}
		TreeSet<Integer> imp = new TreeSet<Integer>();
		imp.add(0);
		imp.add(n +1);
		Arrays.sort(info, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[0] - o1[0];
			}
		});

		for (int i = 0; i < n; i++) {
			int[] curr = info[i];
			int value = curr[0];
			int index = curr[1];
			int low = imp.lower(index);
			int high = imp.higher(index);
			int length = high - low;
			float lowProb = (float) (index - low) / length;
			float highProb = (float) (high - index) / length;
			expect[index] = Math.max(value, (highProb * expect[low]) + (lowProb * expect[high]));
			imp.add(index);
		}
		for(int i=1;i<n+1;i++) {
			out.println((long)(Math.floor(expect[i]*100000)));
		}
		in.close();
		out.close();
	}
}
