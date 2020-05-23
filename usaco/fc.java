
/*
ID: dingarv1
LANG: JAVA
TASK: fc
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class fc {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("fc.in"));
		PrintWriter out = new PrintWriter(new File("fc.out"));
		int a = Integer.parseInt(in.readLine());
		List<Point> curr = new ArrayList<Point>();
		TreeMap<Double, Point> info = new TreeMap<Double, Point>();
		for (int i = 0; i < a; i++) {
			StringTokenizer temp = new StringTokenizer(in.readLine());
			double x = Double.parseDouble(temp.nextToken());
			double y = Double.parseDouble(temp.nextToken());
			curr.add(new Point(x, y));
		}
		double Cx = 0;
		double Cy = 0;
		for (Point d : curr) {
			Cx += d.x;
			Cy += d.y;
		}
		Cx /= a;
		Cy /= a;
		for (Point d : curr) {
			info.put(Math.atan2(d.y - Cy, d.x - Cx), new Point(d.x, d.y));

		}
		curr = new ArrayList<Point>();
		for (Point lol : info.values()) {
			curr.add(lol);
			System.out.println(lol.x + " " + lol.y);
		}
		boolean flag = false;
		while (true) {
			flag = true;
			for (int i = 2; i < curr.size(); i++) {

				if (greater180( curr.get(i),curr.get(i-2), curr.get(i - 1))) {
					curr.remove(i - 1);
					flag = false;
				}

			}

			while (greater180(curr.get(0), curr.get(curr.size() - 2), curr.get(curr.size() - 1))) {
				curr.remove(curr.size() - 1);
				flag = false;
			}

			while (greater180(curr.get(1), curr.get(curr.size() - 1), curr.get(0))) {
				curr.remove(0);
				flag = false;
			}
			
			if (flag)
				break;
		}

		double distanceT = 0;
		for (int i = 0; i < curr.size(); i++) {
			if (i == curr.size() - 1) {
				distanceT += distance(curr.get(i), curr.get(0));
			} else {
				distanceT += distance(curr.get(i), curr.get(i + 1));
			}
		}

		DecimalFormat df = new DecimalFormat("0.00");
		out.println(df.format(distanceT));
		in.close();
		out.close();

	}

	private static class Point {
		public double x;
		public double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

	}

	private static double distance(Point a, Point b) {
		return Math.pow(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2), 0.5);
	}

	private static boolean greater180(Point a, Point b, Point c) {
		return (a.x - c.x) * (b.y - c.y) - (b.x - c.x) * (a.y - c.y) < 0;
	}

}
