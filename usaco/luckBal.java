
import java.io.*;
import java.util.*;

public class luckBal {
	static int luckBalance(int contest, int loses, int[][] contests) {
		int sum = 0;
		LinkedList<Integer> important = new LinkedList<Integer>();
		for (int[] a : contests) {
			if (a[1] == 1) {
				important.add(a[0]);
			} else {
				sum += a[0];
			}
		}
		int wins = important.size() - loses;
		Collections.sort(important);
		for (int i = 0; i < wins; i++) {
			sum -= important.poll();
		}
		while (!important.isEmpty()) {
			sum += important.poll();
		}
		return sum;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int[][] contests = new int[n][2];
		for (int contests_i = 0; contests_i < n; contests_i++) {
			for (int contests_j = 0; contests_j < 2; contests_j++) {
				contests[contests_i][contests_j] = in.nextInt();
			}
		}
		int result = luckBalance(n, k, contests);
		System.out.println(result);
		in.close();
	}

}
