
import java.io.*;
import java.util.*;

public class ogledala {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		Long basins = Long.parseLong(read.nextToken());
		int set = Integer.parseInt(read.nextToken());
		int queries = Integer.parseInt(read.nextToken());
		long[][] queue = new long[2 * 47 * set][3];

		long[] already = new long[set];
		read = new StringTokenizer(in.readLine());
		for (int i = 0; i < set; i++) {
			already[i] = Long.parseLong(read.nextToken());
		}
		long[] sorted = new long[set + 2];
		for (int i = 0; i < set; i++)
			sorted[i] = already[i];
		sorted[set] = 0;
		sorted[set + 1] = basins + 1;
		Arrays.sort(sorted);
		int index = 0;
		long previous = 0;
		for (int i = 1; i <= set + 1; i++) {
			long curr = sorted[i];
			long length = (curr - 1) - previous;
			LinkedList<long[]> queueL = new LinkedList<long[]>();
			queueL.add(new long[] { length, 1 });
			long previousL = Long.MAX_VALUE;
			long sizeL = 0;
			while (!queueL.isEmpty()) {
				long[] loop = queueL.poll();
			//	System.out.println(loop[0] + " " + loop[1]);
				if (loop[0] != previousL) {
					if (sizeL != 0)
						queue[index++] = new long[] { previousL, sizeL, i };
					sizeL=0;
					previousL = -1;
				}
				if (loop[0] == 0)
					break;
				previousL = loop[0];
				sizeL += loop[1];
				if (loop[0] % 2 == 0) {
					queueL.add(new long[] { loop[0] / 2, loop[1] });
					queueL.add(new long[] { loop[0] / 2 - 1, loop[1] });
				} else {
					queueL.add(new long[] { (loop[0] - 1) / 2, loop[1] * 2 });
				}
			}
			if (previousL != -1) {
				queue[index++] = new long[] { previousL, sizeL, i };
			}
			previous = curr;
		}
		// length,quant,seg
		long[][] intervals = new long[index][3];
		for (int i = 0; i < index; i++) {
			intervals[i] = queue[i];
		}
		Arrays.sort(intervals, new Comparator<long[]>() {
			@Override
			public int compare(long[] arg0, long[] arg1) {
				if (arg1[0] < arg0[0])
					return -1;
				else if (arg1[0] > arg1[0])
					return 1;
				return Long.compare(arg0[2], arg1[2]);

			}
		});

		long[][] query = new long[queries][2];
		read = new StringTokenizer(in.readLine());
		for (int i = 0; i < queries; i++) {
			query[i] = new long[] { Long.parseLong(read.nextToken()), i };
		}
		Arrays.sort(query, new Comparator<long[]>() {
			@Override
			public int compare(long[] arg0, long[] arg1) {
				return Long.compare(arg0[0], arg1[0]);
			}
		});
		long[] ans = new long[queries];
		index = 0;
		while (index < queries && query[index][0] <= set) {
			ans[(int) query[index][1]] = already[(int) (query[index][1])];
			index++;
		}
		long sum = set;
		outer: for (int i = 0; i < intervals.length; i++) {
			while (sum + intervals[i][1] >= query[index][0]) {
				long[] imp = intervals[i];
				long start = sorted[(int) (imp[2] - 1)];
				long end = sorted[(int) (imp[2])];
				ans[(int) query[index][1]] = queryH(imp[0], start + 1, end - 1, query[index][0] - sum)[1];
				index++;
				if (index == queries)
					break outer;
			}
			sum += intervals[i][1];
		}
		for (long a : ans)
			System.out.println(a);
		in.close();
	}

	private static long[] queryH(long length, long start, long end, long cnt) {
		long currL = (end - start) + 1;
		long mid = (start + end) / 2;
		if (currL == length) {
			if (cnt == 1) {
				return new long[] { 1, mid };
			} else {
				return new long[] { 1, -1 };
			}
		}
		if (currL < length)
			return new long[] { 0, -1 };
		long[] left = queryH(length, start, mid - 1, cnt);
		if (left[1] != -1)
			return left;
		return queryH(length, mid + 1, end, cnt - left[0]);

	}

}
