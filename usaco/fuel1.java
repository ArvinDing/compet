
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class fuel1 {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("fuel.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fuel.out")));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int gasStations = Integer.parseInt(read.nextToken());
		int tank = Integer.parseInt(read.nextToken());
		int fuel = Integer.parseInt(read.nextToken());
		int distance = Integer.parseInt(read.nextToken());

		TreeMap<Integer, Integer> temp = new TreeMap<Integer, Integer>();
		for (int i = 0; i < gasStations; i++) {
			read = new StringTokenizer(in.readLine());
			int index = Integer.parseInt(read.nextToken());
			int cost = Integer.parseInt(read.nextToken());
			if (!temp.containsKey(index)) {
				temp.put(index, Integer.MAX_VALUE);
			}
			temp.put(index, Math.min(temp.get(index), cost));

		}
		temp.put(distance, -1);
		int[][] info = new int[temp.size()][2];
		int currIndex = 0;
		for (Entry<Integer, Integer> curr : temp.entrySet()) {
			info[currIndex][0] = curr.getKey();
			info[currIndex][1] = curr.getValue();
			currIndex++;
		}

		Arrays.sort(info, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		int[] nextSmaller = new int[gasStations];
		LinkedList<Integer> stack = new LinkedList<Integer>();
		for (int i = info.length - 1; i >= 0; i--) {
			int curr = info[i][1];
			if (stack.size() != 0) {
				while (stack.size() != 0 && curr <= info[stack.peek()][1]) {
					stack.remove();
				}

				nextSmaller[i] = stack.peek();
			}
			stack.addFirst(i);
		}

		fuel -= info[0][0];

		long cost = 0;
		for (int i = 0; i < info.length; i++) {
			if (fuel < 0) {
				out.println(-1);
				break;
			}
			if (info[i][0] == distance) {
				out.println(cost);
				break;
			}
			long gasBuy;
			if (nextSmaller[i] == info.length - 1)
				gasBuy = tank;
			else
				gasBuy = (info[nextSmaller[i]][0] - info[i][0]) - fuel;
			if (gasBuy > 0) {
				if (gasBuy + info[i][0] > distance)
					gasBuy = Math.max(0, (distance - info[i][0]) - fuel);
				gasBuy = Math.min(gasBuy, tank - fuel);
				fuel += gasBuy;
				cost += gasBuy * info[i][1];
				
			}
			fuel -= info[i + 1][0] - info[i][0];

		}

		in.close();
		out.close();
	}

}