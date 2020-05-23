
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class squares1 {
	private static int size;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("squares.in"));
		PrintWriter out = new PrintWriter(new File("squares.out"));
		TreeMap<Integer, TreeSet<Integer>> xInfo = new TreeMap<Integer, TreeSet<Integer>>();
		TreeMap<Integer, TreeSet<Integer>> yInfo = new TreeMap<Integer, TreeSet<Integer>>();
		StringTokenizer read = new StringTokenizer(in.readLine());
		int lines = Integer.parseInt(read.nextToken());
		size = Integer.parseInt(read.nextToken());
		for (int i = 0; i < lines; i++) {
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
		int a = solve(xInfo);
		int b = solve(yInfo);
		if (a == -1 || b == -1) {
			out.println(-1);
		} else if (a == 0 && b == 0) {
			out.println(0);
		} else if (a > 0 && b > 0) {
			out.println(-1);
		} else {
			out.println(Math.max(a, b));
		}
		in.close();
		out.close();
	}

	private static int[] save = new int[] { -1, -1, -1, -1 };

	private static int solve(TreeMap<Integer, TreeSet<Integer>> info) {
		int index = 0;
		thing[] infoNew = new thing[info.size()];
		for (Entry<Integer, TreeSet<Integer>> curr : info.entrySet()) {
			infoNew[index] = new thing(curr.getKey(), curr.getValue());
			index++;
		}

		int answer = 0;

		int[] item = new int[4];
		for (int i = 0; i < infoNew.length; i++) {
		outer:	for (int k = i + 1; k < infoNew.length; k++) {
				int currKey = infoNew[k].key;
				TreeSet<Integer> currValue = infoNew[k].value;
				int old = infoNew[i].key;
				TreeSet<Integer> oldValue = infoNew[i].value;
				if (Math.abs(currKey - old) < size) {
					Iterator<Integer> currI = currValue.iterator();
					Iterator<Integer> previous = oldValue.iterator();
					int current = currI.next();
					int prev = previous.next();
					while (true) {
						if (Math.abs(current - prev) < size) {
							if ((save[1] == currKey && save[0] == current && save[3] == old && save[2] == prev)
									|| (save[3] == currKey && save[2] == current && save[1] == old
											&& save[0] == prev)) {
								if (!previous.hasNext() && !currI.hasNext()) {
									break;
								} else if (current > prev) {
									if (previous.hasNext()) {
										prev = previous.next();
									} else {
										continue outer;
									}
								} else {
									if (currI.hasNext()) {
										current = currI.next();
									} else {
										continue outer;
									}
								}
								continue;
							}
							if (answer != 0)
								return -1;

							answer = (size - Math.abs(currKey - old)) * (size - (Math.abs(current - prev)));
							item = new int[] { currKey, current, old, prev };
							if (!previous.hasNext() && !currI.hasNext()) {
								break;
							} else if (current > prev) {
								if (previous.hasNext()) {
									prev = previous.next();
								} else {
									continue outer;
								}
							} else {
								if (currI.hasNext()) {
									current = currI.next();
								} else {
									continue outer;
								}
							}
						} else if (!previous.hasNext() && !currI.hasNext()) {
							break;
						} else if (current > prev) {
							if (previous.hasNext()) {
								prev = previous.next();
							} else {
								continue outer;
							}
						} else {
							if (currI.hasNext()) {
								current = currI.next();
							} else {
								continue outer;
							}
						}
					}
				}
				break;
			}
		}
		if (answer >= 1) {
			save = item;
		}
		return answer;
	}

	private static class thing {
		int key;
		TreeSet<Integer> value;

		private thing(int key, TreeSet<Integer> value) {
			this.key = key;
			this.value = value;
		}
	}

}