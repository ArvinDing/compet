
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.Map.Entry;

public class piepie2 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("piepie.in"));
		PrintWriter out = new PrintWriter(new File("piepie.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int pies = Integer.parseInt(read.nextToken());
		int difference = Integer.parseInt(read.nextToken());
		TreeMap<Integer, List<Integer>> b = new TreeMap<Integer, List<Integer>>();
		TreeMap<Integer, List<Integer>> e = new TreeMap<Integer, List<Integer>>();
		int[][] bInfo = new int[pies][2];
		int[][] eInfo = new int[pies][2];

		for (int i = 0; i < pies; i++) {
			read = new StringTokenizer(in.readLine());
			int bThought = Integer.parseInt(read.nextToken());
			int eThought = Integer.parseInt(read.nextToken());
			if (!e.containsKey(eThought)) {
				e.put(eThought, new ArrayList<Integer>());
			}
			e.get(eThought).add(i);
			bInfo[i][0] = bThought;
			bInfo[i][1] = eThought;

		}
		for (int i = 0; i < pies; i++) {
			read = new StringTokenizer(in.readLine());
			int bThought = Integer.parseInt(read.nextToken());
			int eThought = Integer.parseInt(read.nextToken());
			if (!b.containsKey(bThought)) {
				b.put(bThought, new ArrayList<Integer>());
			}
			b.get(bThought).add(i);
			eInfo[i][0] = eThought;
			eInfo[i][1] = bThought;
		}
		int[] bess = new int[pies];
		int[] elsie = new int[pies];
		LinkedList<int[]> queue = new LinkedList<int[]>();

		List<Integer> thing = b.get(0);
		if (thing != null)
			for (int a : thing) {
				queue.add(new int[] { a, 1, 1 });
				elsie[a] = 1;
			}
		thing = e.get(0);
		if (thing != null)
			for (int a : thing) {
				queue.add(new int[] { a, 0, 1 });
				bess[a] = 1;
			}

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int currI = curr[0];
			boolean isBessie = (curr[1] == 0);
			int piesEat = curr[2];

			// System.out.println(piesEat);
			int value = (isBessie) ? bInfo[currI][0] : eInfo[currI][0];
			if (isBessie) {
				NavigableMap<Integer, List<Integer>> temp = b.tailMap(value - difference, true);
				Iterator<Entry<Integer, List<Integer>>> important = temp.entrySet().iterator();
				while (important.hasNext()) {
					Entry<Integer, List<Integer>> current = important.next();
					if (current.getKey() > value)
						break;
					Iterator<Integer> loop = current.getValue().iterator();
					while (loop.hasNext()) {
						int a = loop.next();
						if (elsie[a] == 0) {
							elsie[a] = piesEat + 1;
							loop.remove();
							queue.add(new int[] { a, 1, piesEat + 1 });
						}
					}
					if (current.getValue().isEmpty())
						important.remove();
				}
			} else {
				NavigableMap<Integer, List<Integer>> temp = e.tailMap(value - difference, true);
				Iterator<Entry<Integer, List<Integer>>> important = temp.entrySet().iterator();
				while (important.hasNext()) {
					Entry<Integer, List<Integer>> current = important.next();
					if (current.getKey() > value)
						break;
					Iterator<Integer> loop = current.getValue().iterator();
					while (loop.hasNext()) {
						int a = loop.next();
						if (bess[a] == 0) {
							bess[a] = piesEat + 1;
							loop.remove();
							queue.add(new int[] { a, 0, piesEat + 1 });
						}
					}
					if (current.getValue().isEmpty())
						important.remove();
				}
			}
		}
		for (int i = 0; i < pies; i++) {
			out.println((bess[i] == 0) ? -1 : bess[i]);
		}

		out.close();
		in.close();
	}
}