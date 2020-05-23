
import java.io.*;
import java.util.*;

public class fencing {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new FileReader("fencing.in"));
		PrintWriter out = new PrintWriter(new File("fencing.out"));
		int n = in.nextInt();
		int q = in.nextInt();
		int[][] info = new int[n][2];
		int lowestY = Integer.MAX_VALUE;
		int lowYlowX = -1;
		for (int i = 0; i < n; i++) {
			info[i][1] = in.nextInt();
			info[i][0] = in.nextInt();
			if (info[i][0] < lowestY) {
				lowestY = info[i][0];
				lowYlowX = info[i][1];
			} else if (info[i][0] == lowestY) {
				if (info[i][1] < lowYlowX) {
					lowYlowX = info[i][1];
				}
			}
		}
		TreeMap<Double, List<int[]>> sort = new TreeMap<Double, List<int[]>>();

		for (int i = 0; i < n; i++) {
			double hash = 0;
			if (info[i][1] - lowYlowX >= 0) {
				hash = Integer.MIN_VALUE;
			}
			if (info[i][0] - lowestY != 0)
				hash = -((double) info[i][1] - lowYlowX) / (info[i][0] - lowestY);

			if (!sort.containsKey(hash))
				sort.put(hash, new ArrayList<int[]>());
			sort.get(hash).add(info[i]);
		}
		int[][] points = new int[n][2];
		int index = 0;
		for (List<int[]> curr : sort.values()) {
			for (int[] a : curr) {
				points[index] = a;
				index++;
			}
		}
		LinkedList<int[]> stack = new LinkedList<int[]>();
		stack.push(points[0]);
		stack.push(points[1]);
	
		for (int i = 2; i < n; i++) {
			if (orientation(stack.get(stack.size()-2), stack.peekLast(), points[i]) != 2) {
				stack.pop();
			}
			stack.push(points[i]);
		}
		while(!stack.isEmpty()){
			int[] curr=stack.pollLast();
			System.out.println(curr[1] + " " +curr[0]);
		}
		in.close();
		out.close();
	}

	private static int orientation(int[] p1, int[] p2, int[] p3) {
		int val = (p2[0] - p1[0]) * (p3[1] - p2[1]) - (p2[1] - p1[1]) * (p3[0] - p2[0]);
		if (val == 0)
			return 0;
		return (val > 0) ? 1 : 2;
	}

}