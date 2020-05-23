
import java.io.*;
import java.util.*;

 class agrcow {
	private static int[] info;
	private static int cows;
	private static int places;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(in.readLine());
		for (int z = 0; z < t; z++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			places = Integer.parseInt(read.nextToken());
			cows = Integer.parseInt(read.nextToken());
			info = new int[places];
			for (int i = 0; i < places; i++) {
				info[i] = Integer.parseInt(in.readLine());
			}
			Arrays.sort(info);
			int lowTrue = 0;
			int highFalse = (info[places - 1]-info[0])+1;
			while (lowTrue != highFalse - 1) {
				int mid = (highFalse + lowTrue) / 2;
				boolean save = check(mid);
				if (save)
					lowTrue = mid;
				else
					highFalse = mid;
			}
			System.out.println(lowTrue);
		}
		in.close();
	}

	private static boolean check(int minDist) {
		int placed = 1;
		int last = info[0];
		for (int i = 1; i < places; i++) {
			if (info[i] >= last + minDist) {
				last = info[i];
				placed++;
			}
			if (placed == cows)
				return true;
		}
		return false;
	}
}
