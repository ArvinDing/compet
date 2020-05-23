
import java.io.*;
import java.util.*;

public class grass {
	private static List<Integer>[] info;
	private static boolean[] visited;
	private static int index = 0;
	private static int[] lowIndex;
	private static int fields;
	private static Stack<Integer> stack;
	private static int[] convert;
	private static int length = 0;
	private static int[] weight;

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("grass.in"));
		PrintWriter out = new PrintWriter(new File("grass.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		fields = Integer.parseInt(read.nextToken());
		int paths = Integer.parseInt(read.nextToken());
		info = new List[fields];
		convert = new int[fields];
		for (int i = 0; i < fields; i++) {
			info[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < paths; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			info[a].add(b);
		}
		visited = new boolean[fields];
		stack = new Stack<Integer>();
		lowIndex = new int[fields];
		for (int i = 0; i < fields; i++) {
			if (!visited[i])
				dfs(i);
		}
		weight = new int[length];
		for (int i = 0; i < fields; i++) {
			weight[convert[i]]++;
		}
		Set<Integer>[] realInfo = new Set[length];
		Set<Integer>[] reverseRealInfo = new Set[length];
		for (int i = 0; i < fields; i++) {
			for (int neighbor : info[i]) {
				if (convert[i] == convert[neighbor])
					continue;
				if (realInfo[convert[i]] == null) {
					realInfo[convert[i]] = new HashSet<Integer>();
				}
				if (reverseRealInfo[convert[neighbor]] == null) {
					reverseRealInfo[convert[neighbor]] = new HashSet<Integer>();
				}
				realInfo[convert[i]].add(convert[neighbor]);
				reverseRealInfo[convert[neighbor]].add(convert[i]);
			}
		}
		int[] oneto = solve(realInfo);
		int[] toone = solve(reverseRealInfo);
		int max = weight[convert[0]];
		for (int i = 0; i < length; i++) {
			if (realInfo[i] != null)
				for (int neighbor : realInfo[i]) {
					if (oneto[neighbor] != -1 && toone[i] != -1) {
						max = Math.max((oneto[neighbor] + toone[i] + weight[convert[0]]), max);
					}
				}
		}
		out.println(max);
		in.close();
		out.close();
	}

	private static int[] solve(Set<Integer>[] info) {
		Stack<Integer> order = new Stack<Integer>();
		boolean[] visited = new boolean[length];

		for (int i = 0; i < length; i++) {
			if (!visited[i])
				recursion(i, visited, order, info);
		}

		int[] ans = new int[length];
		Arrays.fill(ans, -1);
		ans[convert[0]] = 0;
		while (!order.isEmpty()) {
			int next = order.pop();
			if (ans[next] == -1)
				continue;
			if (info[next] != null)
				for (int neighbor : info[next]) {
					ans[neighbor] = Math.max(ans[neighbor], ans[next] + weight[neighbor]);
				}
		}
		return ans;
	}

	private static void recursion(int i, boolean[] visited, Stack<Integer> order, Set<Integer>[] info) {
		visited[i] = true;
		// System.out.println(i);
		if (info[i] != null)
			for (int neighbor : info[i]) {
				if (!visited[neighbor])
					recursion(neighbor, visited, order, info);
			}
		order.push(i);

	}

	private static void dfs(int i) {
		index++;
		lowIndex[i] = index;
		visited[i] = true;
		stack.push(i);
		boolean isRoot = true;
		for (int v : info[i]) {
			if (!visited[v])
				dfs(v);
			if (lowIndex[i] > lowIndex[v]) {
				lowIndex[i] = lowIndex[v];
				isRoot = false;
			}
		}
		if (isRoot) {
			while (true) {
				int curr = stack.pop();
				convert[curr] = length;
				lowIndex[curr] = Integer.MAX_VALUE;
				if (curr == i)
					break;
			}
			length++;

		}
	}

}
