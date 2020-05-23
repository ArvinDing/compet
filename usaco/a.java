import java.util.PriorityQueue;
import java.io.*;
import java.util.*;

public class a {
	public static void main(String[] args) {
		// String[][] shoppers = new String[2][2];
		// String[][] orders = new String[2][2];
		// int[] leadTime = new int[] { 15, 30 };
		// shoppers[0] = new String[] { "15:10", "16:00" };
		// shoppers[1] = new String[] { "17:40", "22:30" };
		// orders[0] = new String[] { "17:30", "18:00" };
		// orders[1] = new String[] { "15:00", "15:45" };
		// busyHolidays(shoppers, orders, leadTime);
	//	System.out.println(dailyIntake(new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }));
	}
	private static int[] dailyIntake2(int[] caloricValue) {
		int[] dp = new int[1 << (caloricValue.length)];
		LinkedList<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { 0, 0, -1 });
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			// System.out.println(curr[1]);
			for (int k = curr[2] + 1; k < caloricValue.length; k++) {
				int change = (curr[0] | 1 << k);
				if (dp[change] == 0) {
					queue.add(new int[] { change, curr[1] + caloricValue[k], k });
					dp[change] = curr[1] + caloricValue[k];
				}
			}
		}
		int closest = 0;
		LinkedList<Integer> ans = new LinkedList<Integer>();
		for (int i = 0; i < dp.length; i++) {
			if (Math.abs(2000 - closest) > Math.abs(2000 - dp[i])) {
				closest = dp[i];
				ans = helper(i, caloricValue.length);
			} else if (Math.abs(2000 - closest) == Math.abs(2000 - dp[i])) {
				LinkedList<Integer> temp = helper(i, caloricValue.length);
				LinkedList<Integer> copy = new LinkedList<Integer>(ans);
				while (!temp.isEmpty() && !copy.isEmpty()) {
					int a = temp.poll();
					int b = copy.poll();
					if (a > b) {
						break;
					} else {
						ans = helper(i, caloricValue.length);
						break;
					}
				}
			}

		}

		int[] real = new int[ans.size()];
		int index = 0;
		for (int curr : ans) {
			real[index] = curr;
			index++;
		}
		return real;

	}

	private static LinkedList<Integer> helper(int index, int length) {
		int start = 1;
		LinkedList<Integer> ans = new LinkedList<Integer>();
		for (int k = 0; k < length; k++) {
			if ((index & start) == start)
				ans.add(k);
			start = start << 1;
		}
		return ans;
	}

	private static double shoppingList(String items) {
		double ans = 0;
		char[] info = items.toCharArray();
		int start = 0;
		boolean isStreak = false;
		for (int i = 0; i < info.length; i++) {
			if (Character.isDigit(info[i]) || (info[i] == '.' && isStreak)) {
				if (!isStreak)
					start = i;
				isStreak = true;
			} else if (isStreak) {
				ans += Double.parseDouble(items.substring(start, i));
				// System.out.println(Double.parseDouble(items.substring(start, i)));
				isStreak = false;
			}
		}
		if (isStreak) {
			ans += Double.parseDouble(items.substring(start));
		}
		return ans;

	}

	private static boolean deliveryFee(int[] intervals, int[] fees, int[][] deliveries) {
		int boundary = 1;
		int[] cnt = new int[intervals.length];
		for (int i = 0; i < deliveries.length; i++) {
			int upperbound;
			if (boundary == intervals.length)
				upperbound = 60 * 24;
			else
				upperbound = 60 * intervals[boundary];
			int curr = deliveries[i][0] * 60 + deliveries[i][1];
			if (upperbound <= curr) {
				boundary++;
			}
			cnt[boundary - 1]++;
		}
		double check = (double) cnt[0] / fees[0];
		for (int i = 0; i < cnt.length; i++) {
			if (check != ((double) cnt[i] / fees[i])) {
				return false;
			}
		}
		return true;
	}

	private static boolean isIPv4Address(String inputString) {
		String[] info = inputString.split("\\.");
		if (info.length != 4)
			return false;
		for (String a : info) {
			if (!isInteger(a) || a.length() > 3 || 0 > Integer.parseInt(a) || 255 < Integer.parseInt(a))
				return false;
		}
		return true;
	}

	public static boolean isInteger(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}
		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}

	private static int champernowneDigit(int n) {

		for (int i = 1;; i++) {
			int number = i;
			ArrayList<Integer> digits = new ArrayList<Integer>();
			while (number != 0) {
				digits.add(number % 10);
				number = number / 10;
			}
			Collections.reverse(digits);
			if (n < digits.size()) {
				return digits.get(n);
			}
			n -= digits.size();
		}
	}

	public static class position implements Comparable {
		int x;
		int y;
		int distance;

		public position(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}

		public int compareTo(Object o) {

			return distance - ((position) o).distance;
		}

		@Override
		public boolean equals(Object p) {
			return this.x == ((position) p).x && this.y == ((position) p).y;
		}

	}

	private static boolean busyHolidays(String[][] shoppers, String[][] orders, int[] leadTime) {
		int[][] info = new int[shoppers.length][2];
		LinkedList<int[]> oInfo = new LinkedList<int[]>();
		for (int i = 0; i < shoppers.length; i++) {
			info[i] = new int[] { Integer.parseInt(shoppers[i][0].substring(0, 2) + shoppers[i][0].substring(3)),
					Integer.parseInt(shoppers[i][1].substring(0, 2) + shoppers[i][1].substring(3)) };

		}
		for (int i = 0; i < orders.length; i++) {
			oInfo.add(new int[] { Integer.parseInt(orders[i][0].substring(0, 2) + orders[i][0].substring(3)),
					Integer.parseInt(orders[i][1].substring(0, 2) + orders[i][1].substring(3)), leadTime[i] });
		}
		Arrays.sort(info, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[0] - arg1[0];
			}
		});
		Collections.sort(oInfo, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[1] - arg1[1];
			}
		});

		int i = 0;
		outer: while (!oInfo.isEmpty()) {
			int[] curr = oInfo.peek();
			while (i < shoppers.length && info[i][0] > curr[1]) {
				i++;
			}
			if (i == shoppers.length)
				return false;
			int k = i;
			while (k < shoppers.length && info[k][0] < curr[1]) {
				if (Math.min(curr[1], info[k][1]) - Math.max(info[k][0], curr[0]) >= curr[2])
					continue outer;
			}
			return false;
		}
		return true;

	}

	private static boolean isAdmissibleOverpayment(double[] prices, String[] notes, double x) {
		double overpay = 0;
		for (int i = 0; i < notes.length; i++) {
			StringTokenizer read = new StringTokenizer(notes[i]);
			String important = read.nextToken();
			if (!important.equals("Same")) {
				Double info = Double.parseDouble(important.substring(0, important.length() - 1));
				if (read.nextToken().equals("lower")) {
					info *= -1;
				}
				overpay += prices[i] - ((prices[i] * 100) / (100 + info));
			}
		}
		return (x >= overpay);

	}

	private static String[] addBorder(String[] picture) {
		int width = picture[0].length() + 2;
		int length = picture.length + 2;
		String[] ans = new String[length];
		String s = "";
		for (int i = 0; i < width; i++)
			s += "*";
		ans[0] = s;
		ans[length - 1] = s;
		for (int i = 1; i < length - 1; i++) {
			ans[i] = "*" + picture[i - 1] + "*";
		}
		return ans;
	}

	private static String reverseParentheses(String s) {
		char[] info = s.toCharArray();
		LinkedList<Integer> stack = new LinkedList<Integer>();
		for (int i = 0; i < info.length; i++) {
			if (info[i] == '(')
				stack.push(i);
			if (info[i] == ')') {
				char[] old = Arrays.copyOf(info, info.length);
				int start = stack.pop();
				for (int k = 1; k < i - start; k++) {
					info[k + start] = old[i - k];
				}
				info[start] = '*';
				info[i] = '*';
			}

		}
		String ans = "";
		for (char a : info) {
			if (a != '*')
				ans += a;
		}
		return ans;
	}

	private static String encode(String word) {
		char[] info = word.toCharArray();
		int[] cnt = new int[128];
		for (int i = 0; i < info.length; i++) {
			if (Character.isAlphabetic(info[i]))
				info[i] = Character.toLowerCase(info[i]);
			cnt[info[i]]++;
		}
		String ans = "";
		for (int i = 0; i < info.length; i++) {
			if (cnt[info[i]] > 1) {
				ans += ")";
			} else {
				ans += "(";
			}

		}
		return ans;
	}

	public static String oneTwo(String str) {
		String str1 = "";
		if (str.length() < 3) {
			return "";
		}
		if (str.length() % 3 != 0) {
			str = str.substring(0, 3 * (str.length() / 3));
		}
		for (int i = 0; i < str.length(); i = i + 3) {
			str1 = str1 + str.substring(i + 1, i + 3) + str.substring(i, i + 1);

		}
		return str1;
	}
}
