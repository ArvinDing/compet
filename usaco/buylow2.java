
/*
ID: dingarv1
LANG: JAVA
TASK: buylow
*/

import java.io.File;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class buylow2 {
	private static HashMap<Integer, List<Integer>> info;
	private static BigInteger[][] dp;
	private static int[] data;

	public static void main(String[] args) throws Exception {

		Scanner in = new Scanner(new File("buylow.in"));
		PrintWriter out = new PrintWriter(new File("buylow.out"));
		// Integer in HashMap is <Index,List<Index>>
		info = new HashMap<Integer, List<Integer>>();
		int length = in.nextInt();
		data = new int[length];
		// <Object, Index>
		HashMap<Integer, Boolean> BooleanA = new HashMap<Integer, Boolean>();
		PriorityQueue<link> temp = new PriorityQueue<link>();
		List<Integer> abc = new ArrayList<Integer>();
		for (int index = 0; index < length; index++) {
			int object = in.nextInt();
			data[index] = object;
			if (BooleanA.get(object) == null) {
				BooleanA.put(object, true);
				abc.add(index);
			}
			temp.add(new link((((long) object) * 10000 + index), index));
		}
		List<Integer> orderI = new ArrayList<Integer>();
		while (!temp.isEmpty()) {
			orderI.add(temp.poll().index);
		}
		long x = System.currentTimeMillis();
		for (int i = 0; i < length; i++) {

			// remove index from lists that are before the index
			List<Integer> lol = new ArrayList<Integer>();
			List<Integer> rn = orderI.subList(0, i);

			for (int w = 0; w < rn.size(); w++) {
				if (orderI.get(i) < rn.get(w)) {
					lol.add(rn.get(w));
				}
			}
			// put

			info.put(orderI.get(i), lol);

		}
		System.out.println("first:" + String.valueOf(System.currentTimeMillis() - x));
		dp = new BigInteger[5001][2];

		BigInteger max = BigInteger.ZERO;
		BigInteger times = BigInteger.ZERO;
		BigInteger[] wow;
		long sum = 0;
 
		for (int i : abc) {
			long j = System.currentTimeMillis();
			wow = howDeep(i);
			sum += System.currentTimeMillis() - j;
			// System.out.print(i + "|" + wow[0] + "|" + wow[1] + " ");
			if (wow[0].compareTo(max) > 0) {
				max = wow[0];
				times = wow[1];
			} else if (wow[0].compareTo(max) == 0) {
				times = times.add(wow[1]);
			}
		}
		System.out.println("time: " + sum);
		out.println(max + " " + times);
		out.close();
		in.close();
	}

	public static BigInteger[] howDeep(int i) {
		if (dp[i][0] != null) {
			return dp[i];
		}
		if (info.get(i).size() == 0) {
			dp[i][0] = BigInteger.ONE;
			dp[i][1] = BigInteger.ONE;
			return dp[i];
		}
		BigInteger max = BigInteger.ZERO;
		BigInteger time = BigInteger.ZERO;
		// HashMap<Long,Boolean> doublecount = new HashMap<Long,Boolean>();
		int previous=0;
		for (int k : info.get(i)) {
		
			if (previous==data[k]) {
				continue;
			}
			BigInteger[] temp = howDeep(k);
			dp[k][0] = temp[0];
			dp[k][1] = temp[1];
			if (max.compareTo(temp[0]) < 0) {
				max = temp[0];
				time = temp[1];

			} else if (temp[0].compareTo(max) == 0) {

				time = time.add(temp[1]);

			}
			previous=data[k];
		
		}
	
		dp[i][0] = max.add(BigInteger.ONE);
		dp[i][1] = time;
		return dp[i];
	}

	public static class link implements Comparable {
		long object;
		int index;

		public link(long object, int index) {

			this.object = object;
			this.index = index;
		}

		public int compareTo(Object o) {

			return (int) (object - ((link) o).object);
		}
	}
}