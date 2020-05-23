import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class lightsout {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new FileReader("lightsout.in"));
		PrintWriter out = new PrintWriter(new File("lightsout.out"));
		int vertexes = Integer.parseInt(in.readLine());
		int[][] info = new int[vertexes][2];
		for (int i = 0; i < vertexes; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			info[i][0] = Integer.parseInt(read.nextToken());
			info[i][1] = Integer.parseInt(read.nextToken());
		}
		int[] least = new int[vertexes];
		int sum = 0;

		int[] distance = new int[vertexes];
		for (int i = 0; i < vertexes - 1; i++) {
			least[i] = sum;
			distance[i] = Math.abs((info[i + 1][0] - info[i][0])) + Math.abs((info[i + 1][1] - info[i][1]));
			sum += distance[i];
		}
		distance[vertexes - 1] = Math.abs((info[0][0] - info[vertexes - 1][0]))
				+ Math.abs((info[0][1] - info[vertexes - 1][1]));
		least[vertexes - 1] = sum;
		sum = 0;
		for (int i = vertexes - 1; i >= 1; i--) {
			sum += Math.abs((info[(i + 1) % vertexes][0] - info[i][0]))
					+ Math.abs((info[(i + 1) % vertexes][1] - info[i][1]));
			least[i] = Math.min(least[i], sum);
		}
		int[] degree = new int[vertexes];
		// 0-90,1-270
		LinkedList<Integer> distances = new LinkedList<Integer>();
		StringBuilder thing = new StringBuilder();
		for (int i = 0; i < vertexes; i++) {
			int[] previous = new int[2];
			int[] next = new int[2];
			previous[0] = info[i][0] - info[(i - 1 + vertexes) % vertexes][0];
			previous[1] = info[i][1] - info[(i - 1 + vertexes) % vertexes][1];
			next[0] = info[i][0] - info[(i + 1 + vertexes) % vertexes][0];
			next[1] = info[i][1] - info[(i + 1 + vertexes) % vertexes][1];
			if (i != vertexes - 1) {
				distances.add(distance[i]);
			}
			if (((previous[0] * next[1]) - (previous[1] * next[0])) < 0) {
				degree[i] = 1;
				thing.append('1');
			} else {
				thing.append('0');
			}

		}

		HashMap<String, HashMap<String, List<Integer>>> correlation = new HashMap<String, HashMap<String, List<Integer>>>();
		for (int i = 0; i < vertexes; i++) {
			int real = (i + vertexes - 1) % vertexes;
			if (real == 0) {
				thing = new StringBuilder();
				thing.append(degree[(real + 1) % vertexes]);
				distances = new LinkedList<Integer>();
				continue;
			}

			StringBuilder temp = new StringBuilder();
			Iterator itr = distances.descendingIterator();
			for (int k = thing.length() - 1; k >= 0; k--) {
				if (!correlation.containsKey(thing.substring(k)))
					correlation.put(thing.substring(k), new HashMap<String, List<Integer>>());

				if (!correlation.get(thing.substring(k)).containsKey(temp.toString()))
					correlation.get(thing.substring(k)).put(temp.toString(), new ArrayList<Integer>());
				correlation.get(thing.substring(k)).get(temp.toString()).add(real);
				if (k != 0) {
					temp.insert(0, itr.next() + " ");
				}
			}
			if (thing.length() == vertexes) {
				thing.delete(0, 1);
			}
			if (distances.size() == vertexes - 1) {
				distances.removeFirst();
			}
			thing.append(degree[(real + 1) % vertexes]);
			distances.add(distance[real]);
		}

		int max = Integer.MIN_VALUE;
		for (int i = 1; i < vertexes; i++) {
			int cost = 0;
			thing = new StringBuilder();
			StringBuilder currDistance = new StringBuilder();
			int done = 0;
			for (int add = 0; add < vertexes; add++) {
				int real = (i + add) % vertexes;
				if (real == 0) {
					break;
				}
				thing.append(degree[real]);

				if (correlation.get(thing.toString()) != null
						&& correlation.get(thing.toString()).get(currDistance.toString()) != null
						&& correlation.get(thing.toString()).get(currDistance.toString()).size() == 1) {
					done = correlation.get(thing.toString()).get(currDistance.toString()).get(0);
					break;
				}
				currDistance.append(distance[real]+" ");
				cost += distance[real];
			}
			if (done != 0) {
				cost += least[done];
			}
			max = Math.max(max, cost - least[i]);
		}
		out.println(max);
		in.close();
		out.close();
	}
}
