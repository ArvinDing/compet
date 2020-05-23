package save;
import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) {
		int[] a = new int[] { 1, 3, 1 };

		System.out.println(smallestDistancePair(a, 1));
	}

	// Definition for a binary tree node.
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public static int smallestDistancePair(int[] nums, int k) {
		Arrays.sort(nums);
		int smallestDist = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			smallestDist = Math.min(smallestDist, nums[i + 1] - nums[i]);
		}
		int lDist = 1000001;
		while (smallestDist< lDist) {
			int mid = (smallestDist + lDist) >> 1;
			int under = 0;
			int end = 0;
			for (int i = 0; i < nums.length; i++) {
				while (end != nums.length && nums[end] - nums[i] <= mid) {
					end++;
				}
				under += end - i - 1;
			}
			if (under < k) {
				smallestDist = mid+1;
			} else {
				lDist = mid;
			}
		}
		return smallestDist;
	}

	public static int findLength(int[] A, int[] B) {
		int ans = 0;
		int[][] dp = new int[A.length + 1][B.length + 1];
		for (int i = 1; i <= A.length; i++) {
			for (int k = 1; k <= B.length; k++) {
				if (A[i - 1] == B[k - 1]) {
					dp[i][k] = dp[i - 1][k - 1] + 1;
					ans = Math.max(dp[i][k], ans);
				}
			}
		}
		return ans;
	}

	public static int compress(char[] chars) {
		char[] old = Arrays.copyOf(chars, chars.length);
		char previous = ' ';
		int cnt = 0;
		int index = 0;
		for (int i = 0; i < chars.length; i++) {
			char a = old[i];
			if (previous == a) {
				cnt++;
			} else {

				if (cnt != 1 && cnt != 0) {
					String add = ("" + cnt);
					for (int k = 0; k < add.length(); k++) {
						chars[index] = (add).charAt(k);
						index++;
					}
				}
				cnt = 1;
				chars[index] = a;
				index++;
				previous = a;
			}
		}
		if (cnt != 1 && cnt != 0) {
			String add = ("" + cnt);
			for (int k = 0; k < add.length(); k++) {
				chars[index] = (add).charAt(k);
				index++;
			}
		}
		return index;
	}

	public static boolean isOneBitCharacter(int[] bits) {
		if (bits.length == 1)
			return true;
		if (bits[bits.length - 2] == 0)
			return true;
		boolean[] possible = new boolean[bits.length + 1];
		possible[0] = true;
		for (int i = 0; i < bits.length; i++) {
			if (possible[i]) {
				if (i + 2 <= bits.length
						&& ((bits[i] == 1 && bits[i + 1] == 0) || (bits[i] == 1 && bits[i + 1] == 1))) {
					possible[i + 2] = true;
				}
				if (bits[i] == 0)
					possible[i + 1] = true;
			}
		}
		return !possible[bits.length - 2];
	}

	public static int numBusesToDestination(int[][] routes, int S, int T) {
		TreeSet<Integer>[] info = new TreeSet[100001];
		for (int i = 0; i < routes.length; i++) {
			TreeSet<Integer> neighbors = new TreeSet<Integer>();
			for (int k = 0; k < routes[i].length; k++) {
				neighbors.add(routes[i][k]);
			}
			for (int k = 0; k < routes[i].length; k++) {
				if (info[routes[i][k]] == null)
					info[routes[i][k]] = new TreeSet<Integer>();
				info[routes[i][k]].addAll(neighbors);
			}
		}
		BitSet done = new BitSet(100001);
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[1] - arg1[1];
			}
		});
		queue.add(new int[] { S, 0 });
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int position = curr[0];
			if (done.get(position))
				continue;
			done.set(position);
			int cost = curr[1];
			if (position == T)
				return cost;
			if (info[position] != null)
				for (int neighbor : info[position]) {
					if (!done.get(neighbor)) {
						queue.add(new int[] { neighbor, cost + 1 });
					}
				}
		}
		return -1;
	}

	public static double largestSumOfAverages(int[] A, int K) {
		double[][] dp = new double[A.length + 1][K + 1];
		for (int i = 0; i <= A.length; i++) {
			for (int k = 0; k <= K; k++) {
				dp[i][k] = -1;
			}
		}
		dp[0][0] = 0;
		for (int i = 0; i < A.length; i++) {
			for (int k = 1; k <= K; k++) {
				double average = 0;
				for (int z = i; z >= 0; z--) {
					average += A[z];
					if (dp[z][k - 1] != -1)
						dp[i + 1][k] = Math.max(dp[z][k - 1] + average / (i - z + 1), dp[i + 1][k]);
				}
			}
		}
		return dp[A.length][K];
	}

	public static TreeNode pruneTree(TreeNode root) {
		if (only0(root)) {
			return null;
		} else {
			if (root.right != null)
				root.right = pruneTree(root.right);
			if (root.left != null)
				root.left = pruneTree(root.left);
		}
		return root;
	}

	public static boolean only0(TreeNode root) {

		if (root.right != null) {
			if (!only0(root.right))
				return false;
		}
		if (root.left != null) {
			if (!only0(root.left))
				return false;
		}
		return (root.val == 0);

	}

	public static double largestTriangleArea(int[][] points) {
		double max = 0;
		for (int i = 0; i < points.length; i++) {
			for (int k = i + 1; k < points.length; k++) {
				for (int j = k + 1; j < points.length; j++) {
					max = Math.max(Math.abs(
							points[i][0] * (points[k][1] - points[j][1]) + points[k][0] * (points[j][1] - points[i][1])
									+ points[j][0] * (points[i][1] - points[k][1]))
							/ (double) 2, max);
				}
			}
		}
		return max;
	}

	public static int numTilings(int N) {
		int[] dp = new int[N + 1];
		dp[0] = 1;
		dp[1] = 2;
		dp[2] = 5;
		for (int i = 1; i <= N; i++) {
			if (i - 1 >= 0)
				dp[i] += 2 * dp[i - 1];
			if (i - 3 >= 0)
				dp[i] += dp[i - 3];
		}
		return dp[N];
	}

	public String customSortString(String S, String T) {
		int[] info = new int[26];
		for (char a : T.toCharArray()) {
			info[a - 97]++;
		}
		String ans = "";
		for (char a : S.toCharArray()) {
			while (info[a - 97] != 0) {
				ans += a;
				info[a - 97]--;
			}
		}
		for (int i = 0; i < info.length; i++) {
			while (info[i] != 0) {
				ans += Character.toString((char) (i + 97));
				info[i]--;
			}
		}
		return ans;
	}

	public boolean escapeGhosts(int[][] ghosts, int[] target) {
		int time = target[0] + target[1];
		for (int[] ghost : ghosts) {
			if ((Math.abs(ghost[0] - target[0]) + Math.abs(ghost[1] - target[1])) <= time)
				return false;
		}
		return true;
	}

	public static int rotatedDigits(int N) {
		int sum = 0;
		outer: for (int i = 1; i <= N; i++) {
			char[] help = (i + "").toCharArray();
			boolean flag = false;
			for (char a : help) {
				if (!(a == '2' || a == '5' || a == '6' || a == '9')) {
					if (!(a == '0' || a == '1' || a == '8'))
						continue outer;
				} else {
					flag = true;
				}
			}
			if (flag)
				sum++;
		}
		return sum;
	}

	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> solution = new ArrayList<Integer>();
		if (root != null) {
			solution.add(root.val);
			if (root.left != null)
				solution.addAll(preorderTraversal(root.left));
			if (root.right != null)
				solution.addAll(preorderTraversal(root.right));
		}
		return solution;
	}

	public static int maxIncreaseKeepingSkyline(int[][] grid) {
		int l = grid.length;
		int w = grid[0].length;
		int[] lBest = new int[l];
		int[] wBest = new int[w];
		for (int i = 0; i < l; i++) {
			for (int k = 0; k < w; k++) {
				lBest[i] = Math.max(grid[i][k], lBest[i]);
				wBest[k] = Math.max(grid[i][k], wBest[k]);
			}
		}
		int sum = 0;
		for (int i = 0; i < l; i++) {
			for (int k = 0; k < w; k++) {
				sum += Math.min(lBest[i], wBest[k]) - grid[i][k];
			}
		}
		return sum;
	}
}
