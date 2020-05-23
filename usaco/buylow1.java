
/*
ID: dingarv1
LANG: JAVA
TASK: buylow
*/

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class buylow1 {
	private static HashMap<Integer, List<Integer>> info;
	private static int[] dp;
	private static int debug;

	public static void main(String[] args) throws Exception {
	
		Scanner in = new Scanner(new File("buylow.in"));
		PrintWriter out = new PrintWriter(new File("buylow.out"));
		// Integer in HashMap is <Index,List<Index>>
		info = new HashMap<Integer, List<Integer>>();
		int length = in.nextInt();
		long[] data = new long[length];
		// <Object, Index>
		TreeMap<Long, Integer> temp = new TreeMap<Long, Integer>();
		for (int index = 0; index < length; index++) {
			int object = in.nextInt();
			data[index] = object;
			temp.put((((long) object) * 10000 + index), index);
		}
		List<Integer> orderI = new ArrayList<Integer>();
		for (int i : temp.values()) {
			orderI.add(i);
		}
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
			// remove integers from the list that are duplicates
			for (int a = i - 1; (a >= 0 && data[orderI.get(a)] == data[orderI.get(i)]); a--) {
				info.get(orderI.get(i)).remove(orderI.get(a));
			}
		}
		dp = new int[5001];
		Arrays.fill(dp, -1);
		int max = Integer.MIN_VALUE;
		int times = 0;
		int wow;
		
		for (int i : orderI) {
		
			wow = howDeep(i);
			System.out.print(i+"|"+wow+" ");
			if (wow > max) {
				max = wow;
				times = 1;
			} else if (wow == max) {
				times ++;
			}
		}

		out.println(max + " " + times);
		out.close();
		in.close();
	}
	private static int time;
	public static int howDeep(int i) {
		if (dp[i] != -1) {
			return dp[i];
		}
		if (info.get(i).size() == 0) {
			dp[i] = 1;
			return 1;
		}
		int max = Integer.MIN_VALUE;
		time=0;
		for (int k : info.get(i)) {
			int temp=howDeep(k);
			if(max<temp){
				max=temp;
				time=1;
			}else if(temp==max) {
				time++;
			}
			
		}
	
		dp[i] = max + 1;
		return max + 1;
	}

}