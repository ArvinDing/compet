
/*
ID: dingarv1
LANG: JAVA
TASK: fence
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class fencedin {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("fencedin.in"));
		PrintWriter out = new PrintWriter(new File("fencedin.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int maxX = Integer.parseInt(read.nextToken());
		int maxY = Integer.parseInt(read.nextToken());
		int vertical = Integer.parseInt(read.nextToken());
		int horizontal = Integer.parseInt(read.nextToken());
		int[] verticalInfo = new int[vertical + 2];
		for (int i = 0; i < vertical; i++) {
			verticalInfo[i] = Integer.parseInt(in.readLine());
		}
		verticalInfo[vertical + 1] = 0;
		verticalInfo[vertical] = maxX;
		Arrays.sort(verticalInfo);
		int[] horizontalInfo = new int[horizontal + 2];
		for (int i = 0; i < horizontal; i++) {
			horizontalInfo[i] = Integer.parseInt(in.readLine());
		}
		horizontalInfo[horizontal] = maxY;
		horizontalInfo[horizontal + 1] = 0;
		Arrays.sort(horizontalInfo);
		LinkedList<int[]> segments = new LinkedList<int[]>();
		for (int i = 0; i < vertical + 1; i++) {
			int[] curr = new int[(horizontal + 1) + 1];
			curr[0] = verticalInfo[i + 1] - verticalInfo[i];
			int index = i;
			for (int k = 1; k < horizontal + 2; k++) {
				curr[k] = index;
				index += vertical + 1;
			}
			segments.add(curr);
		}

		for (int i = 0; i < horizontal + 1; i++) {
			int[] curr = new int[(vertical + 1) + 1];
			curr[0] = horizontalInfo[i + 1] - horizontalInfo[i];
			int index = (horizontal - i) * (vertical + 1);
			for (int k = 1; k < vertical + 2; k++) {
				curr[k] = index;
				index++;
			}
			segments.add(curr);
		}
		Collections.sort(segments, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		long answer = 0;
		int[] disjoint = new int[(horizontal + 1) * (vertical + 1)];
		Arrays.fill(disjoint, -1);
		int merged = ((horizontal + 1) * (vertical + 1)) - 1;
		outer: while (!segments.isEmpty()) {
			int[] curr = segments.poll();
			int cost = curr[0];
			int previous = curr[1];
			for (int i = 2; i < curr.length; i++) {
				int current = curr[i];
				int parentPrev = previous;
				int parentCurr = current;
				while (disjoint[parentPrev] >= 0) {
					parentPrev = disjoint[parentPrev];
				}
				while (disjoint[parentCurr] >= 0) {
					parentCurr = disjoint[parentCurr];
				}
				int redo = previous;
				while (disjoint[redo] >= 0) {
					int old = disjoint[redo];
					disjoint[redo] = parentPrev;
					redo = old;
				}
				redo = current;
				while (disjoint[redo] >= 0) {
					int old = disjoint[redo];
					disjoint[redo] = parentCurr;
					redo = old;
				}
				if (parentCurr != parentPrev) {
					merged--;
					answer += cost;
					if (merged == 0) {
						out.println(answer);
						break outer;
					}
					if (disjoint[parentCurr] > disjoint[parentPrev]) {
						disjoint[parentCurr] = parentPrev;
					} else if (disjoint[parentCurr] < disjoint[parentPrev]) {
						disjoint[parentPrev] = parentCurr;
					} else {
						disjoint[parentPrev] = parentCurr;
						disjoint[parentCurr]--;
					}

				}
			}
		}
		out.close();
		in.close();

	}
}