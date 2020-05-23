
import java.io.*;
import java.util.*;

public class dquery {
	private static int[] BIT;
	private static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());
		BIT = new int[n + 1];
		int[] position = new int[1000001];
		int[] next = new int[n];
		int[] info = new int[n];
		StringTokenizer read = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			info[i] = Integer.parseInt(read.nextToken());
			if (position[info[i]] == 0) {
				position[info[i]] = 1;
				update(i, 1);
			}
		}
		Arrays.fill(position, -1);
		for (int i = n - 1; i >= 0; i--) {
			next[i] = position[info[i]];
			position[info[i]] = i;
		}
		int queries = Integer.parseInt(in.readLine());
		LinkedList<int[]> qInfo = new LinkedList<int[]>();
		for (int i = 0; i < queries; i++) {
			read = new StringTokenizer(in.readLine());
			qInfo.add(new int[] { Integer.parseInt(read.nextToken()) - 1, Integer.parseInt(read.nextToken()) - 1, i });
		}
		Collections.sort(qInfo, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		int[] answers = new int[queries];
		outer: for (int i = 0; i < n; i++) {
			while (qInfo.peek()[0] == i) {
				int[] question = qInfo.poll();
				answers[question[2]] = query(question[1]);
				if (qInfo.isEmpty())
					break outer;
			}
			update(i, -1);
			if (next[i] != -1) {
				update(next[i], 1);
			}
		}
		for (int curr : answers) {
			System.out.println(curr);
		}
		in.close();
	}

	private static int query(int i) {
		int sum = 0;
		i++;
		while (i != 0) {
			sum += BIT[i];
			i &= i - 1;
		}
		return sum;
	}

	private static void update(int i, int value) {
		i++;
		while (i <= n) {
			BIT[i] += value;
			i += (i & -i);
		}
	}
}