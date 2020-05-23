
import java.io.*;
import java.util.*;

public class reduce2 {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("reduce.in"));
		PrintWriter out = new PrintWriter(new File("reduce.out"));
		int lines = Integer.parseInt(in.readLine());
		
		List<Integer> highestX = new ArrayList<Integer>();
		List<Integer> highestY = new ArrayList<Integer>();
		List<Integer> lowestX = new ArrayList<Integer>();
		List<Integer> lowestY = new ArrayList<Integer>();
		Comparator<Integer> a = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}

		};
		List<Integer> xs = new ArrayList<Integer>();
		List<Integer> ys = new ArrayList<Integer>();
		for (int i = 0; i < lines; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(read.nextToken());
			xs.add(x);
			ys.add(y);
			if (highestX.size() < 4 || (highestX.size() == 4 && highestX.get(highestX.size() - 1) < x)) {
				highestX.add(x);
			}
			if (highestY.size() < 4 || (highestY.size() == 4 && highestY.get(highestY.size() - 1) < y)) {
				highestY.add(y);
			}
			if (lowestX.size() < 4 || (lowestX.size() == 4 && lowestX.get(lowestX.size() - 1) > x)) {
				lowestX.add(x);
			}
			if (lowestY.size() < 4 || (lowestY.size() == 4 && lowestY.get(lowestY.size() - 1) > y)) {
				lowestY.add(y);
			}
			if (highestX.size() == 5) {
				Collections.sort(highestX, a);
				highestX.remove(4);
			}
			if (highestY.size() == 5) {
				Collections.sort(highestY, a);
				highestY.remove(4);
			}
			if (lowestY.size() == 5) {
				Collections.sort(lowestY);
				lowestY.remove(4);
			}
			if (lowestX.size() == 5) {
				Collections.sort(lowestX);
				lowestX.remove(4);
			}

		}
		int min = Integer.MAX_VALUE;
		for (int minX : lowestX) {
			for (int minY : lowestY) {
				for (int maxX : highestX) {
					for (int maxY : highestY) {
						int cnt = 0;
						for (int i = 0; i < lines; i++) {
							if (xs.get(i) < minX || xs.get(i) > maxX) {
								cnt++;
							} else if (ys.get(i) < minY || ys.get(i) > maxY) {
								cnt++;
							}
						}
						if (cnt <= 3) {
							min = Math.min(min, (maxX - minX) * (maxY - minY));
						}
					}
				}
			}
		}
		out.println(min);
		in.close();
		out.close();
	}
}
