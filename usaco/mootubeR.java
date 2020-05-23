
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class mootubeR {
	private static int[] disjoint;

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("mootube.in"));
		PrintWriter out = new PrintWriter(new File("mootube.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int q = Integer.parseInt(read.nextToken());
		int[][] connected = new int[n - 1][3];
		for (int i = 0; i < n - 1; i++) {
			read = new StringTokenizer(in.readLine());
			connected[i] = new int[] { Integer.parseInt(read.nextToken())-1, Integer.parseInt(read.nextToken())-1,
					Integer.parseInt(read.nextToken()) };
		}
		Arrays.sort(connected, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[2] - o1[2];
			}
		});
		int[] ans = new int[q];
		int[][] question = new int[q][3];
		for (int i = 0; i < q; i++) {
			read = new StringTokenizer(in.readLine());
			question[i] = new int[] { Integer.parseInt(read.nextToken()), Integer.parseInt(read.nextToken())-1, i };
		}
		Arrays.sort(question, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[0] - o1[0];
			}
		});
		int cIndex = 0;
		int[] size = new int[n];
		Arrays.fill(size, 1);
		disjoint = new int[n];
		for (int i = 0; i < n; i++) {
			disjoint[i] = i;
		}
		for (int qIndex = 0; qIndex < q; qIndex++) {
			int k = question[qIndex][0];
			int source = question[qIndex][1];
			int index = question[qIndex][2];
			if (cIndex != n - 1)
				while (k <= connected[cIndex][2]) {
					int first = connected[cIndex][0];
					int second = connected[cIndex][1];
					int a = getParent(first);
					int b = getParent(second);
					while (disjoint[first] != first) {
						int old = first;
						first = disjoint[first];
						disjoint[old] = a;
					}
					while (disjoint[second] != second) {
						int old = second;
						second = disjoint[second];
						disjoint[old] = b;
					}
					if(size[first]>size[second]){
						disjoint[second]=first;
						size[first]+=size[second];
					}else{
						disjoint[first]=second;
						size[second]+=size[first];
					}
					cIndex++;
					if(cIndex==n-1)break;
				}
			ans[index]=size[getParent(source)]-1;
		}
		for(int print:ans)
			out.println(print);
		out.close();
		in.close();
	}

	private static int getParent(int i) {
		while (disjoint[i] != i) {
			i = disjoint[i];
		}
		return i;

	}
}