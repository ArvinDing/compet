
import java.io.*;
import java.util.*;

public class tooEasy {
	private static int[][] info;
	private static int maxTime;
	private static int problems;

	public static void main(String args[]) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer read = new StringTokenizer(in.readLine());
		problems = Integer.parseInt(read.nextToken());
		maxTime = Integer.parseInt(read.nextToken());
		info = new int[problems][3];
		for (int i = 0; i < problems; i++) {
			read = new StringTokenizer(in.readLine());
			info[i] = new int[] { Integer.parseInt(read.nextToken()), Integer.parseInt(read.nextToken()), i + 1 };
		}
		Arrays.sort(info, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		int lowTrue = 0;
		int highFalse = problems;
		LinkedList<Integer> save = check(highFalse);
		if (save != null)
			lowTrue = problems;
		while (highFalse - lowTrue > 1) {
			int mid = (lowTrue + highFalse) / 2;
			if (check(mid) != null) {
				lowTrue = mid;
			} else {
				highFalse = mid;
			}
		}
		save = check(lowTrue);
		System.out.println(lowTrue);
		System.out.println(save.size());
		if (!save.isEmpty()) {
			System.out.print(save.poll());
			while (!save.isEmpty()) {
				System.out.print(" " + save.poll());
			}
			System.out.println();
		}
		in.close();
	}

	private static LinkedList<Integer> check(int length) {
		LinkedList<int[]> possible = new LinkedList<int[]>();
		for (int i = 0; i < problems; i++) {
			if (info[i][0] >= length) {
				possible.add(new int[] { info[i][1], info[i][2] });
			}

		}
		Collections.sort(possible, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		LinkedList<Integer> ans = new LinkedList<Integer>();
		int sum = 0;
		for (int i = 0; i < length; i++) {
			int[] curr = possible.poll();
			if (curr == null)
				return null;
			sum += curr[0];
			if (sum > maxTime)
				return null;
			ans.add(curr[1]);
		}
		return ans;
	}
}
