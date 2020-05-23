
/*
ID: dingarv1
LANG: JAVA
TASK: picture
*/

import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.Map.Entry;

public class picture2 {
	private static int lines;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("picture.in"));
		PrintWriter out = new PrintWriter(new File("picture.out"));
		lines = Integer.parseInt(in.readLine());
		int[][] info = new int[lines][4];
		int[][] yEqualsx = new int[lines][4];
		for (int i = 0; i < lines; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			info[i] = new int[] { Integer.parseInt(read.nextToken()), Integer.parseInt(read.nextToken()),
					Integer.parseInt(read.nextToken()), Integer.parseInt(read.nextToken()) };
			yEqualsx[i] = new int[] { info[i][1], info[i][0], info[i][3], info[i][2] };
		}
		out.println(solve(info) + solve(yEqualsx));
		in.close();
		out.close();
	}

	private static int solve(int[][] info) {
		Arrays.sort(info, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		boolean[][] outside = new boolean[lines][2];
		TreeMap<Integer, LinkedList<int[]>> sweep = new TreeMap<Integer, LinkedList<int[]>>();
		for (int i = 0; i < lines; i++) {
			int left = info[i][0];
			int right = info[i][2];
			if (!sweep.containsKey(left))
				sweep.put(left, new LinkedList<int[]>());
			if (!sweep.containsKey(right))
				sweep.put(right, new LinkedList<int[]>());
			sweep.get(left).addLast(new int[] { info[i][1], info[i][3], i });
			sweep.get(right).addFirst(new int[] { i });
		}
		TreeMap<Integer, int[]> check = new TreeMap<Integer, int[]>();
		for (LinkedList<int[]> curr : sweep.values()) {
			Iterator<int[]> itr = curr.iterator();
			while (itr.hasNext()) {
				int[] temp = itr.next();
				if (temp.length == 1)
					continue;
				check.put(temp[2], new int[] { temp[0], temp[1] });
			}
			while (!curr.isEmpty()) {
				int[] temp = curr.poll();
				if (temp.length == 1) {
					check.remove(temp[0]);
				} else {
					int down = temp[0];
					int up = temp[1];
					int index = temp[2];
					boolean flag = true;
					for (Entry<Integer, int[]> thing : check.entrySet()) {
						if (thing.getKey() == index)
							continue;
						int[] loop = thing.getValue();
						if (loop[0] < down && down <= loop[1]) {
							flag = false;
							break;
						}
					}
					outside[index][0] = flag;
					flag = true;
					for (Entry<Integer, int[]> thing : check.entrySet()) {
						if (thing.getKey() == index)
							continue;
						int[] loop = thing.getValue();
						if (loop[0] <= up && up < loop[1]) {
							flag = false;
							break;
						}
					}
					outside[index][1] = flag;
				}
			}
		}
		sweep = new TreeMap<Integer, LinkedList<int[]>>();
		for (int i = 0; i < lines; i++) {
			if (!sweep.containsKey(info[i][0]))
				sweep.put(info[i][0], new LinkedList<int[]>());
			sweep.get(info[i][0]).addFirst(new int[] { info[i][1], info[i][3] });
			sweep.get(info[i][0]).add(new int[] { 2 * i, info[i][1], (outside[i][0]) ? 1 : 0 });
			sweep.get(info[i][0]).add(new int[] { 2 * i + 1, info[i][3], (outside[i][1]) ? 1 : 0 });

			if (!sweep.containsKey(info[i][2]))
				sweep.put(info[i][2], new LinkedList<int[]>());
			sweep.get(info[i][2]).addFirst(new int[] { 2 * i });
			sweep.get(info[i][2]).addFirst(new int[] { 2 * i + 1 });
			sweep.get(info[i][2]).add(new int[] { info[i][1], info[i][3] });
		}
		int[] sum = new int[2 * lines];
		int[] previous = new int[2 * lines];
		boolean[] open = new boolean[2 * lines];
		int[] indextovalue = new int[2 * lines];
		TreeMap<Integer, List<Integer>> link = new TreeMap<Integer, List<Integer>>();
		int ans = 0;
		for (Entry<Integer, LinkedList<int[]>> curr : sweep.entrySet()) {
			int x = curr.getKey();
			LinkedList<int[]> loop = curr.getValue();
			for (int[] a : loop) {
				if (a.length == 1) {
					int index = a[0];
					if (open[index])
						sum[index] += x - previous[index];
					Iterator<Integer> itr = link.get(indextovalue[index]).iterator();
					while (itr.hasNext()) {
						int temp = itr.next();
						if (temp == index) {
							itr.remove();
							break;
						}
					}
					if (link.get(indextovalue[index]).isEmpty())
						link.remove(indextovalue[index]);
					ans += sum[index];
				} else if (a.length == 2) {
					int bottom = a[0];
					int top = a[1];
					Collection<List<Integer>> flipped = link.subMap(bottom, false, top, false).values();
					for (List<Integer> flip : flipped) {
						for (int flipIndex : flip) {
							if (open[flipIndex]) {
								sum[flipIndex] += x - previous[flipIndex];
								open[flipIndex] = false;
							} else {
								open[flipIndex] = true;
								previous[flipIndex] = x;
							}
						}
					}
				} else if (a.length == 3) {
					int index = a[0];
					int y = a[1];
					int isOutside = a[2];
					if (!link.containsKey(y))
						link.put(y, new ArrayList<Integer>());
					link.get(y).add(index);
					open[index] = isOutside == 1;
					previous[index] = x;
					indextovalue[index] = y;
				}
			}
		}
		return ans;
	}
}
