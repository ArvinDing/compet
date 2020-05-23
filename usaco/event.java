import java.io.*;
import java.util.*;

public class event {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		outer: for (String line = in.readLine(); line != null; line = in.readLine()) {
			StringTokenizer read = new StringTokenizer(line);
			int people = Integer.parseInt(read.nextToken());
			int budget = Integer.parseInt(read.nextToken());
			int hotels = Integer.parseInt(read.nextToken());
			int weeks = Integer.parseInt(read.nextToken());

			int[][] info = new int[hotels][weeks + 1];
			for (int i = 0; i < hotels; i++) {
				info[i][0] = Integer.parseInt(in.readLine());
				read = new StringTokenizer(in.readLine());
				for (int k = 0; k < weeks; k++) {
					info[i][k + 1] = Integer.parseInt(read.nextToken());
				}
			}
			Arrays.sort(info, new Comparator<int[]>() {
				@Override
				public int compare(int[] arg0, int[] arg1) {
					return arg0[0] - arg1[0];
				}

			});
			for (int hotel = 0; hotel < hotels; hotel++) {
				if (people * info[hotel][0] > budget) {
					System.out.println("stay home");
					continue outer;
				}
				for (int k = 0; k < weeks; k++) {
					if (info[hotel][k + 1] >= people) {
						System.out.println(people * info[hotel][0]);
						continue outer;
					}
				}
			}
			System.out.println("stay home");

		}
		in.close();
	}
}