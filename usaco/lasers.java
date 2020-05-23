import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class lasers {
	private static int barnX;
	private static int barnY;
	private static int laserX;
	private static int laserY;
	private static HashMap<Integer, TreeSet<Integer>> yInfo;
	private static HashMap<Integer, TreeSet<Integer>> xInfo;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("lasers.in"));
		PrintWriter out = new PrintWriter(new File("lasers.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int fences = Integer.parseInt(read.nextToken());
		laserX = Integer.parseInt(read.nextToken());
		laserY = Integer.parseInt(read.nextToken());
		barnX = Integer.parseInt(read.nextToken());
		barnY = Integer.parseInt(read.nextToken());
		xInfo = new HashMap<Integer, TreeSet<Integer>>();
		yInfo = new HashMap<Integer, TreeSet<Integer>>();

		for (int i = 0; i < fences; i++) {
			read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(read.nextToken());
			if (!xInfo.containsKey(x))
				xInfo.put(x, new TreeSet<Integer>());
			if (!yInfo.containsKey(y))
				yInfo.put(y, new TreeSet<Integer>());
			xInfo.get(x).add(y);
			yInfo.get(y).add(x);
		}
		if (!xInfo.containsKey(barnX))
			xInfo.put(barnX, new TreeSet<Integer>());
		if (!yInfo.containsKey(barnY))
			yInfo.put(barnY, new TreeSet<Integer>());
		xInfo.get(barnX).add(barnY);
		yInfo.get(barnY).add(barnX);
		int print = Integer.MAX_VALUE;
		for (int i = 0; i < 4; i++) {
			int answer = solve(i);
			if (answer != -1)
				print = Math.min(print, answer);
		}
		out.println((print == Integer.MAX_VALUE) ? -1 : print);
		in.close();
		out.close();
	}

	private static int solve(int startDirect) {
		LinkedList<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { laserX, laserY, startDirect, 0 });
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int currX = curr[0], currY = curr[1], direction = curr[2], cost = curr[3];
			// 0-up,1-right,2-down,3-left

			NavigableSet<Integer> save;
			if (direction == 0) {
				if (xInfo.get(currX) == null)
					continue;
				save = xInfo.get(currX).tailSet(currY, false);
			} else if (direction == 1) {
				if (yInfo.get(currY) == null)
					continue;
				save = yInfo.get(currY).tailSet(currX, false);
			} else if (direction == 2) {
				if (xInfo.get(currX) == null)
					continue;
				save = xInfo.get(currX).headSet(currY, false);
			} else {
				if (yInfo.get(currY) == null)
					continue;
				save = yInfo.get(currY).headSet(currX, false);
			}
			if (direction == 0 || direction == 2) {
				for (int y : save) {
					if (currX == barnX && y == barnY)
						return cost;
					queue.addLast(new int[] { currX, y, 1, cost + 1 });
					queue.addLast(new int[] { currX, y, 3, cost + 1 });
				}
			} else {
				for (int x : save) {
					if (x == barnX && currY == barnY)
						return cost;
					queue.addLast(new int[] { x, currY, 2, cost + 1 });
					queue.addLast(new int[] { x, currY, 0, cost + 1 });
				}
			}
		}
		return -1;
	}
}