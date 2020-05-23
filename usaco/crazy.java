
import java.io.*;
import java.util.*;

//import usaco.DrawTool;

public class crazy {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("crazy.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crazy.out")));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int fencesCnt = Integer.parseInt(read.nextToken());
		int cowsCnt = Integer.parseInt(read.nextToken());
		HashMap<Integer, HashMap<Integer, Integer>> info = new HashMap<Integer, HashMap<Integer, Integer>>();
		ArrayList<List<line>> shapes = new ArrayList<List<line>>();

		int[][] fences = new int[fencesCnt][4];
		int[][] cows = new int[cowsCnt][2];
		int index = -1;
		for (int i = 0; i < fencesCnt; i++) {
			read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(read.nextToken());
			int x1 = Integer.parseInt(read.nextToken());
			int y1 = Integer.parseInt(read.nextToken());
			fences[i][0] = x;
			fences[i][1] = y;
			fences[i][2] = x1;
			fences[i][3] = y1;

			if (!info.containsKey(x))
				info.put(x, new HashMap<Integer, Integer>());
			if (!info.get(x).containsKey(y))
				info.get(x).put(y, -1);

			if (!info.containsKey(x1))
				info.put(x1, new HashMap<Integer, Integer>());
			if (!info.get(x1).containsKey(y1))
				info.get(x1).put(y1, -1);

			int first = info.get(x).get(y);
			int second = info.get(x1).get(y1);
			if (first == -1 && second == -1) {
				index++;
				shapes.add(new ArrayList<line>());
				shapes.get(index).add(new line(x, y, x1, y1));
				info.get(x).put(y, index);
				info.get(x1).put(y1, index);
			} else if (first != -1 && second != -1) {
				if (first == second) {
					shapes.get(first).add(new line(x, y, x1, y1));
				} else if (first < second) {
					for (line a : shapes.get(second)) {
						info.get(a.x).put(a.y, first);
						info.get(a.x1).put(a.y1, first);
					}
					shapes.get(first).addAll(shapes.get(second));
					shapes.get(second).clear();
					shapes.get(first).add(new line(x, y, x1, y1));
				} else {
					for (line a : shapes.get(first)) {
						info.get(a.x).put(a.y, second);
						info.get(a.x1).put(a.y1, second);
					}
					shapes.get(second).addAll(shapes.get(first));
					shapes.get(first).clear();
					shapes.get(second).add(new line(x, y, x1, y1));

				}
			} else if (first != -1) {
				shapes.get(first).add(new line(x, y, x1, y1));
				info.get(x1).put(y1, first);
			} else if (second != -1) {
				shapes.get(second).add(new line(x, y, x1, y1));
				info.get(x).put(y, second);
			}
		}
		Iterator itr = shapes.iterator();
		while (itr.hasNext()) {
			List<line> e = (List<line>) itr.next();
			if (e.size() == 0) {
				itr.remove();
			}
		}
		int notInCommunities = 0;
		HashMap<BitSet, Integer> cnt = new HashMap<BitSet, Integer>();
		for (int i = 0; i < cowsCnt; i++) {
			read = new StringTokenizer(in.readLine());
			int pointX = Integer.parseInt(read.nextToken());
			int pointY = Integer.parseInt(read.nextToken());
			double solution = pointY - 0.0000001;
			cows[i][0] = pointX;
			cows[i][1] = pointY;
			boolean inCommunity = false;
			int[] mins = new int[shapes.size()];
			BitSet key = new BitSet(shapes.size());
			Arrays.fill(mins, Integer.MAX_VALUE);
			for (int a = 0; a < shapes.size(); a++) {
				boolean inside = false;

				for (line curr : shapes.get(a)) {
					int x = curr.x;
					int y = curr.y;
					int x1 = curr.x1;
					int y1 = curr.y1;
					if (x < pointX) {
						x1 = curr.x;
						y1 = curr.y;
						x = curr.x1;
						y = curr.y1;
					}
					if (x == x1) {
						if ((Math.max(y, y1) > solution && solution > Math.min(y, y1)) && x > pointX) {
							inside = !inside;
						}
					} else {
						double slope = (double) (y - y1) / (x - x1);
						if (pointX < ((solution - (y - (slope * x))) / slope)
								&& (Math.max(y, y1) > solution && solution > Math.min(y, y1))) {
							inside = !inside;
						}
					}
				}

				if (inside) {
					key.set(a);
					inCommunity = true;
				}
			}

			if (inCommunity) {
				if (!cnt.containsKey(key)) {
					cnt.put(key, 0);
				}
				cnt.put(key, cnt.get(key) + 1);
			} else {
				notInCommunities++;
				// System.out.println(pointX + " " + pointY);
			}
		}
		int max = 0;
		for (int a : cnt.values())
			max = Math.max(max, a);
//		DrawTool.setScale(fences, cows);
//		DrawTool.drawLines(fences);
//		DrawTool.drawPoints(cows);
		out.println(Math.max(notInCommunities, max));
		in.close();
		out.close();
	}

	private static class line {
		int x;
		int y;
		int x1;
		int y1;

		private line(int x, int y, int x1, int y1) {
			this.y = y;
			this.x = x;
			this.y1 = y1;
			this.x1 = x1;
		}
	}
}