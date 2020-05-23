
/*
ID: dingarv1
LANG: JAVA
TASK: shopping
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class shopping {

	private static ArrayList<offers> info = new ArrayList<offers>();
	private static boolean[] flagged = new boolean[1000000];
	private static int[] filter = new int[1000000];
	private static PrintWriter out;
	private static int[] items;
	private static int[] gAmounts;
	private static int gNumber, tmpcnt = 0;
	private static long start = System.currentTimeMillis();

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new FileReader("shopping.in"));
		 out = new PrintWriter(new File("shopping.out"));
		Arrays.fill(filter, Integer.MAX_VALUE);
		int offers = Integer.parseInt(in.readLine());
		String[] input = new String[offers];
		for (int i = 0; i < offers; i++) {
			input[i] = in.readLine();
		}
		int[] index = new int[1000];
		int number = Integer.parseInt(in.readLine());
		items = new int[number];
		gAmounts = new int[number];
		for (int i = 0; i < number; i++) {

			StringTokenizer a = new StringTokenizer(in.readLine());
			int item = Integer.parseInt(a.nextToken());
			int numbers = Integer.parseInt(a.nextToken());
			int value = Integer.parseInt(a.nextToken());

			items[i] = item;
			index[item] = i;
			gAmounts[i] = numbers;
			int[] goods = new int[number];
			goods[i] = 1;

			info.add(new offers(goods, value));
		}
		int p = 0;
		for (int i = 0; i < gAmounts.length; i++) {
			p += gAmounts[i] * Math.pow(10, i);
		}
		gNumber = p;

		for (int i = 0; i < offers; i++) {
			StringTokenizer a = new StringTokenizer(input[i]);
			int combos = Integer.parseInt(a.nextToken());
			int[] goods = new int[number];
			for (int k = 0; k < combos; k++) {

				goods[index[Integer.parseInt(a.nextToken())]] = Integer.parseInt(a.nextToken());
			}
			info.add(new offers(goods, Integer.parseInt(a.nextToken())));

		}

		out.println(solve(number));
		
		out.close();
		in.close();
	}

	private static int solve(int number) {

		PriorityQueue<dragon> solution = new PriorityQueue<dragon>();
		solution.add(new dragon(new int[number], 0));
		while (true) {

			dragon meat = solution.poll();
		
			int[] amounts = meat.amounts;
			int cost = meat.cost;
			// testing
			int k = 0;
			for (int i = 0; i < amounts.length; i++) {
				k += amounts[i] * Math.pow(10, i);
			}

			if (flagged[k] == true) {
				continue;
			}
			// testing
			if (k == gNumber) {
				return cost;
			}
			for (offers a : info) {
				int[] goods = a.goods;

				int[] copy = Arrays.copyOf(amounts, number);

				for (int i = 0; i < goods.length; i++) {
					copy[i] += goods[i];
				}
				int z = 0;
				for (int i = 0; i < copy.length; i++) {
					z += copy[i] * Math.pow(10, i);
				}
				if (filter[z] > cost + a.price) {
					if (compare(copy, gAmounts)) {
						filter[z] = -1;
						
					} else {
					
						solution.add(new dragon(copy, cost + a.price));
						filter[z] = cost + a.price;
					}
				}

			}
			// setting
			int d = 0;
			for (int i = 0; i < amounts.length; i++) {
				d += amounts[i] * Math.pow(10, i);
			}

			flagged[d] = true;
			filter[d] = cost;

			// setting
		}

	}

	public static class dragon implements Comparable {
		int[] amounts;
		int cost;

		public dragon(int[] amounts, int cost) {
			this.amounts = amounts;
			this.cost = cost;
		}

		public int compareTo(Object o) {

			return cost - ((dragon) o).cost;
		}

	
	}

	public static boolean compare(int[] test, int[] goal) {
		for (int i = 0; i < test.length; i++) {
			if (test[i] > goal[i]) {
				return true;
			}
		}
		return false;
	}

	public static class offers {
		int[] goods;
		int price;

		public offers(int[] goods, int price) {
			this.goods = goods;
			this.price = price;
		}
	}
}