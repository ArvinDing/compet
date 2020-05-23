
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class balancing1 {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("balancing.in"));
		PrintWriter out = new PrintWriter(new File("balancing.out"));
		int n = Integer.parseInt(in.readLine());
		int[][] xSort = new int[n][2];
		TreeSet<Integer> xS = new TreeSet<Integer>();
		for (int i = 0; i < n; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(read.nextToken());
			xSort[i][0] = x;
			xSort[i][1] = y;
			xS.add(x);
		}
		Arrays.sort(xSort, (new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		}));
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			List<int[]> up = new ArrayList<int[]>();
			List<int[]> down = new ArrayList<int[]>();
			for (int k = 0; k < n; k++) {
				if (xSort[i][1] < xSort[k][1]) {
					up.add(new int[] { xSort[k][0], xSort[k][1] });
				} else {
					down.add(new int[] { xSort[k][0], xSort[k][1] });
				}
			}
			int upIndex = 0;
			int downIndex = 0;
			for (int x : xS) {
				while (upIndex < up.size() && up.get(upIndex)[0] == x) {
					upIndex++;
				}
				while (downIndex < down.size() && down.get(downIndex)[0] == x) {
					downIndex++;
				}
				min = Math.min(min,
						Math.max(Math.max(up.size() - upIndex, upIndex), Math.max(down.size() - downIndex, downIndex)));
			}
		}
		out.println(min);
		in.close();
		out.close();
	}

}