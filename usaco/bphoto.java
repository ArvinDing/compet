import java.io.*;
import java.util.*;

public class bphoto {
	private static int[] bit;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new FileReader("bphoto.in"));
		PrintWriter out = new PrintWriter(new File("bphoto.out"));
		int cows = Integer.parseInt(in.readLine());
		bit = new int[cows + 1];
		int[][] info = new int[cows][2];
		for (int i = 0; i < info.length; i++) {
			info[i][0] = Integer.parseInt(in.readLine());
			info[i][1] = i;
		}
		Arrays.sort(info, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o2[0] - o1[0];
			}
		});
		int unbalanced = 0;
		for (int i = 0; i < info.length; i++) {
			int left = get(info[i][1]);
			int right = i - left;
			if (left > right * 2 || right > left * 2) {
				unbalanced++;
			}
			update(info[i][1], 1);

		}
		out.println(unbalanced);
		out.close();
		in.close();
	}

	private static void update(int index, int add) {
		index++;
		while (index < bit.length) {
			bit[index] += add;
			index += (index & -index);
		}
	}

	private static int get(int index) {
		index++;
		int sum = 0;
		while (index > 0) {
			sum += bit[index];
			index = (index & (index - 1));
		}
		return sum;
	}
}
