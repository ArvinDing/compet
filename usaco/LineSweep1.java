
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class LineSweep1 {
	private static class Point implements Comparable<Point> {
		public double x;
		public double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public double distance(Point b) {
			return Math.hypot(b.x - x, b.y - y);
		}

		@Override
		public int compareTo(Point o) {
			if (x == o.x && y == o.y)
				return 0;
			if (y - o.y < 0) {
				return -1;
			}
			return 1;
		}

	}

	public static void main(String[] argv) throws IOException {
		Point[] data;
		try (BufferedReader in = new BufferedReader(new FileReader("input"))) {
			int cnt = Integer.parseInt(in.readLine());
			data = new Point[cnt];
			for (int i = 0; i < data.length; i++) {
				String[] line = in.readLine().split(" ");
				data[i] = new Point(Double.parseDouble(line[0]), Double.parseDouble(line[1]));
			}
		}
		System.out.println(String.format("%.5f", closestDist(data)));// Limit
																		// accuracy
																		// at 5
																		// digits
																		// after
																		// decimal
																		// point
	}

	private static double closestDist(Point[] data) {
		List<Point> info = new ArrayList<Point>();
		for (Point a : data) {
			info.add(a);
		}
		Comparator<Point> myComparator = new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				if (o1.x > o2.x) {
					return 1;
				}
				;
				return -1;
			}

		};

		Collections.sort(info, myComparator);
		
		Double minimumDistance = info.get(0).distance(info.get(1));
		TreeSet<Point> inXrange = new TreeSet<Point>();

		int nextIndex = 2;
		inXrange.add(info.get(0));
		inXrange.add(info.get(1));

		for (int i = 0; i < info.size(); i++) {
			Point a = info.get(i);
			double y = a.y;
			inXrange.remove(a);
			if (inXrange.size() == 0) {
				nextIndex = i + 1;
			}
			while (nextIndex < info.size()) {
				if (info.get(nextIndex).x >= a.x + minimumDistance) {
					break;
				}
				inXrange.add(info.get(nextIndex));
				nextIndex++;
				
			}

			while (inXrange.size() >= 1) {
				if (info.get(nextIndex - 1).x < a.x + minimumDistance) {
					break;
				}
				inXrange.remove(info.get(nextIndex - 1));
				nextIndex--;
			
			}

			SortedSet<Point> finale = inXrange.subSet(new Point(0, y - minimumDistance),
					new Point(0, y + minimumDistance));

			for (Point z : finale) {
				if (minimumDistance > a.distance(z)) {
					minimumDistance = a.distance(z);
				}
			}
			// System.out.println(minimumDistance);
			// for (Point d : inXrange) {
			// System.out.print(d.x + " " + d.y + "|");
			// }
			// System.out.println();
			// System.out.println(nextIndex);
			// System.out.println();

		}
		return minimumDistance;
	}
}