
/*
ID: dingarv1
LANG: JAVA
TASK: buylow
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class buylow3 {
	private static HashMap<Integer, List<Integer>> info;
	private static int[] dp;
	private static int[] data;
	private static int max;

	public static void main(String[] args) throws Exception {
		long start=System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("buylow.in"));
		PrintWriter out = new PrintWriter(new File("buylow.out"));
		info = new HashMap<Integer, List<Integer>>();
		
		int length =	Integer.parseInt(in.readLine());
		data = new int[length];
		HashMap<Integer, Boolean> BooleanA = new HashMap<Integer, Boolean>();
		PriorityQueue<link> temp = new PriorityQueue<link>();
		List<Integer> abc = new ArrayList<Integer>();
		StringTokenizer a=new StringTokenizer(in.readLine());
		for (int index = 0; index < length; index++) {
			if(!a.hasMoreTokens()){
				a=new StringTokenizer(in.readLine());
			}
			int object = Integer.parseInt(a.nextToken());
			data[index] = object;
			if (BooleanA.get(object) == null) {
				BooleanA.put(object, true);
				abc.add(index);
			}
			temp.add(new link((((long) object) * 10000 + index), index));
		}
		System.out.println(System.currentTimeMillis()-start);
		List<Integer> orderI = new ArrayList<Integer>();
		while (!temp.isEmpty()) {
			orderI.add(temp.poll().index);
		}
	
			for (int i = 0; i < length; i++) {

			// remove index from lists that are before the index
			List<Integer> lol = new ArrayList<Integer>();
			for (int w =0;w<i;w++) {
				if (orderI.get(i) < orderI.get(w)) {
					lol.add(orderI.get(w));
				}
			}
			// put
			info.put(orderI.get(i), lol);

		}
		

		dp = new int[5001];
		max = 0;
		int wow;
		for (int i : abc) {
			wow = howDeep(i);
			if (wow > max) {
				max = wow;
			}
		}
		BigInteger times = BigInteger.ZERO;
		for (int i : abc) {
			if (dp[i] == max) {
				times = times.add(times(i, 1));
			}
		}
		out.println(max + " " + times);
		out.close();
		in.close();
	

	}

	private static BigInteger[] dpTime = new BigInteger[5001];

	private static BigInteger times(int i, int layer) {
		if (dpTime[i] != null) {
			return dpTime[i];
		}
		if (info.get(i).size() == 0) {
			return BigInteger.ONE;
		}
		int previous = 0;
		BigInteger temp = BigInteger.ZERO;
		for (int k : info.get(i)) {

			if (previous == data[k]) {
				continue;
			}
			if (dp[k] == max - layer) {
				temp = temp.add(times(k, layer + 1));
			}
			previous = data[k];
		}
		dpTime[i] = temp;
		return temp;
	}

	public static int howDeep(int i) {
		if (dp[i] != 0) {
			return dp[i];
		}
		if (info.get(i).size() == 0) {
			dp[i] = 1;

			return 1;
		}
		int max = 0;

		int previous = 0;
		for (int k : info.get(i)) {

			if (previous == data[k]) {
				continue;
			}
			int temp = howDeep(k);
			dp[k] = temp;

			if (max < temp) {
				max = temp;
			}
			previous = data[k];
		}
		dp[i] = max + 1;
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