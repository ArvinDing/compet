
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class cowrect {
	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("palsquare.in"));
		PrintWriter out = new PrintWriter(new File("palsquare.out"));
		int cows = Integer.parseInt(in.readLine());
		boolean[][] info = new boolean[1001][1001];
		TreeSet<Integer> xS = new TreeSet<Integer>();
		TreeSet<Integer> onlyHxS = new TreeSet<Integer>();
		List<Integer>[] xKey = new List[1001];
		for (int i = 0; i < cows; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(read.nextToken());
			boolean isH = (read.nextToken().equals("H"));
			xS.add(x);
			if (isH)
				onlyHxS.add(x);
			if (xKey[x] == null)
				xKey[x] = new ArrayList<Integer>();
			xKey[x].add(y);
			info[x][y] = isH;
		}
		for (int x : xS) {
			Collections.sort(xKey[x]);
		}
		ArrayList<Integer> better = new ArrayList<Integer>();
		better.addAll(onlyHxS);
		int maxCows = Integer.MIN_VALUE;
		int minArea = -1;
		for (int i = 0; i < better.size(); i++) {

			int smallX = better.get(i);
			int size = xKey[smallX].size();
			for (int o = 0; o < size; o++) {
				int y = xKey[smallX].get(o);
				if (!info[smallX][y])
					continue;
				TreeMap<Integer, Integer> yKey = new TreeMap<Integer, Integer>();
				for (int k = i; k < better.size(); k++) {

					int bigX = better.get(k);
					for (int currY : xKey[bigX]) {
						if (!info[bigX][currY])
							yKey.put(currY, -1);
						else if (!(yKey.containsKey(currY) && yKey.get(currY) == -1)) {
							if (!yKey.containsKey(currY))
								yKey.put(currY, 1);
							else
								yKey.put(currY, yKey.get(currY) + 1);
						}
					}

					outer: for (int z = 0; z < size; z++) {
						int otherY = xKey[bigX].get(z);
						if (!info[bigX][otherY])
							continue;
						int smaller = Math.min(y, otherY);
						int bigger = Math.max(y, otherY);
						Collection<Integer> thing = yKey.subMap(smaller, true, bigger, true).values();
						for (int a : thing) {
							if (a == -1)
								break outer;
						}
						

					}

				}
			}
		}
		in.close();
		out.close();
	}
}
