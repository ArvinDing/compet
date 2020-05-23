
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class mirrors {
	private static HashMap<Integer, TreeSet<Poss>> yKey;
	private static HashMap<Integer, TreeSet<Poss>> xKey;
	private static int targetX;
	private static int targetY;

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("mirrors.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mirrors.out")));
		yKey = new HashMap<Integer, TreeSet<Poss>>();
		xKey = new HashMap<Integer, TreeSet<Poss>>();
		StringTokenizer read = new StringTokenizer(in.readLine());
		int mirrorCnt = Integer.parseInt(read.nextToken());
		targetX = Integer.parseInt(read.nextToken());
		targetY = Integer.parseInt(read.nextToken());
		List<Position> input = new ArrayList<Position>();
		for (int i = 0; i < mirrorCnt; i++) {
			read = new StringTokenizer(in.readLine());
			int currX = Integer.parseInt(read.nextToken());
			int currY = Integer.parseInt(read.nextToken());
			boolean back = read.nextToken().equals("/");
			if (!yKey.containsKey(currY)) {
				yKey.put(currY, new TreeSet<Poss>());
			}
			if (!xKey.containsKey(currX)) {
				xKey.put(currX, new TreeSet<Poss>());
			}
			yKey.get(currY).add(new Poss(currX, back));
			xKey.get(currX).add(new Poss(currY, back));
			input.add(new Position(currX, currY, back));
		}

		outer: if (function()) {
			out.println(0);
		} else {

			for (int i = 0; i < input.size(); i++) {

				Position a = input.get(i);
				yKey.get(a.y).remove(new Poss(a.x, a.back));
				xKey.get(a.x).remove(new Poss(a.y, a.back));
				yKey.get(a.y).add(new Poss(a.x, !a.back));
				xKey.get(a.x).add(new Poss(a.y, !a.back));
				if (function()) {
					out.println(i + 1);
					break outer;
				}
				yKey.get(a.y).remove(new Poss(a.x, !a.back));
				xKey.get(a.x).remove(new Poss(a.y, !a.back));
				yKey.get(a.y).add(new Poss(a.x, a.back));
				xKey.get(a.x).add(new Poss(a.y, a.back));

			}
			out.println(-1);
		}
		out.close();
		in.close();
	}

	private static boolean function() {
		TreeSet<Custom> done = new TreeSet<Custom>();
		int x = 0;
		int y = 0;
		int direction = 1;
		// 1= +x 2=-x 3=+y 4 =-y
		// for (Poss lol : yKey.get(y)) {
		// System.out.println(lol.back + " " + lol.coord);
		// }

		while (true) {
			if (done.contains(new Custom(x, y, direction))) {
				return false;
			}
			done.add(new Custom(x, y, direction));
		

			
			switch (direction) {

			case 1: {
				if (yKey.get(y) == null) {
					return false;
				}
				if (x <= targetX && y == targetY)
					return true;
				if (yKey.get(y).higher(new Poss(x, true)) == null) {
					return false;
				}
				Poss save = yKey.get(y).higher(new Poss(x, true));
				x = save.coord;
				if (save.back) {
					direction = 3;
				} else {
					direction = 4;
				}
				continue;
			}
			case 2: {
				if (yKey.get(y) == null) {
					return false;
				}
				if (x >= targetX && y == targetY)
					return true;
				if (yKey.get(y).lower(new Poss(x, true)) == null) {
					return false;
				}
				Poss save = yKey.get(y).lower(new Poss(x, true));
				x = save.coord;
				if (save.back) {
					direction = 4;
				} else {
					direction = 3;
				}
				continue;
			}
			case 3: {
				if (xKey.get(x) == null) {
					return false;
				}
				if (x == targetX && y <= targetY)
					return true;

				if (xKey.get(x).higher(new Poss(y, true)) == null) {
					return false;
				}
				Poss save = xKey.get(x).higher(new Poss(y, true));
				y = save.coord;
				if (save.back) {
					direction = 1;
				} else {
					direction = 2;
				}
				continue;
			}
			case 4: {
				if (xKey.get(x) == null) {
					return false;
				}
				if (x == targetX && y >= targetY)
					return true;

				if (xKey.get(x).lower(new Poss(y, true)) == null) {
					return false;
				}
				Poss save = xKey.get(x).lower(new Poss(y, true));
				y = save.coord;
				if (save.back) {
					direction = 2;
				} else {
					direction = 1;
				}
				continue;
			}
			}
		}
	}

	private static class Position implements Comparable<Position> {
		int x;
		int y;
		boolean back;

		private Position(int x, int y, boolean back) {
			this.x = x;
			this.y = y;
			this.back = back;
		}

		@Override
		public int compareTo(Position o) {
			if (x == o.x) {
				return y - o.y;
			}
			return x - o.x;

		}

	}

	private static class Poss implements Comparable<Poss> {
		int coord;
		boolean back;

		private Poss(int coord, boolean back) {
			this.coord = coord;
			this.back = back;
		}

		@Override
		public int compareTo(Poss o) {
			return coord - o.coord;
		}
	}

	private static class Custom implements Comparable<Custom> {
		int x;
		int y;
		int direction;

		private Custom(int x, int y, int back) {
			this.x = x;
			this.y = y;
			this.direction = back;
		}

		@Override
		public int compareTo(Custom o) {
			if (x == o.x) {
				if (y == o.y) {
					return direction - o.direction;
				}
				return y - o.y;
			}
			return x - o.x;

		}
	}

}