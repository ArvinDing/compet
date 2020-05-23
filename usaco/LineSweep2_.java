
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class LineSweep2_ {
	private static class Point implements Comparable<Point> {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "(" + x + "," + y + ")";
		}

		@Override
		public int compareTo(Point o) {
			return x - o.x;
		}
	}

	private static class Line implements Comparable<Line> {
		int x1, y1, x2, y2;

		Line(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

		@Override
		public int compareTo(Line o) {
			return x1 - o.x1;
		}

	}

	private static List<Point> intersections(List<Line> lines) {
		List<Point> horizontal = new ArrayList<Point>();
		List<Line> vertical = new ArrayList<Line>();
		for (Line a : lines) {
			if (a.y1 == a.y2) {
				horizontal.add(new Point(a.x1, a.y1));
				horizontal.add(new Point(a.x2, a.y2));
			} else if (a.x1 == a.x2) {

				vertical.add(new Line(a.x1, Math.min(a.y1, a.y2), a.x2, Math.max(a.y1, a.y2)));
			}
		}
		Collections.sort(horizontal);
		Collections.sort(vertical);
		int start = Math.min(horizontal.get(0).x, vertical.get(0).x1);
		int end = Math.max(horizontal.get(horizontal.size() - 1).x, vertical.get(vertical.size() - 1).x1);
		int verticalIndex = 0;
		int horizontalIndex = 0;
		Comparator<Point> comparator = new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				return o1.y - o2.y;
			}

		};
		TreeSet<Point> active = new TreeSet<Point>(comparator);
		HashMap<Integer, Integer> check = new HashMap<Integer, Integer>();
		List<Point> answer = new ArrayList<Point>();
		Point currentH = horizontal.get(horizontalIndex);
		Line currentV = vertical.get(verticalIndex);
		for (int i = start; i <= end; i++) {

			while (currentV.x1 == i) {
			
				SortedSet<Point> a = active.subSet(new Point(0, currentV.y1), new Point(0, currentV.y2));
				for (Point z : a) {
					answer.add(new Point(currentV.x1, z.y));
				}
				verticalIndex++;
			
				if (verticalIndex >= vertical.size())
					break;
				currentV = vertical.get(verticalIndex);

			}
			while (currentH.x == i) {
				if (check.containsKey(currentH.y)) {
					active.remove(new Point(check.get(currentH.y), currentH.y));
					check.remove(currentH.y);

				} else {
					active.add(currentH);
					check.put(currentH.y, currentH.x);

				}
				horizontalIndex++;

				if (horizontalIndex >= horizontal.size())
					currentH.x = -1;
				else
					currentH = horizontal.get(horizontalIndex);

			}

		}
		return answer;

	}

	public static void main(String[] argv) throws NumberFormatException, IOException {
		ArrayList<Line> lines;
		try (BufferedReader in = new BufferedReader(new FileReader("input"))) {
			int cnt = Integer.parseInt(in.readLine());
			lines = new ArrayList<Line>();
			for (int i = 0; i < cnt; i++) {
				String[] line = in.readLine().split(" ");
				lines.add(new Line(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]),
						Integer.parseInt(line[3])));
			}
		}
		System.out.println(intersections(lines));
	}
}