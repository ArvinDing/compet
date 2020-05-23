
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class buylow {
	private static ArrayList<Integer> info;
	private static int max;
	private static int[] dp;
	private static HashMap<Integer, List<Integer>> bTrack;

	public static void main(String[] args) throws Exception {
long start=System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("buylow.in"));
		PrintWriter out = new PrintWriter(new File("buylow.out"));

		info = new ArrayList<Integer>();
		int length = Integer.parseInt(in.readLine());
		StringTokenizer a = new StringTokenizer(in.readLine());
		max = 0;

		dp = new int[5001];
		dp[0] = 1;
		Arrays.fill(dp, 1);
		bTrack = new HashMap<Integer, List<Integer>>();
		boolean[]flagged=new boolean [5001];
		for (int index = 0; index < length; index++) {

			if (!a.hasMoreTokens()) {
				a = new StringTokenizer(in.readLine());
			}
			int object = Integer.parseInt(a.nextToken());
			
			for (int i = info.size() - 1; i >= 0; i--) {

				if (info.get(i) > object) {
					if (dp[index] < dp[i] + 1) {
						dp[index] = dp[i] + 1;

					}
			
				}

			}
			max = Math.max(max, dp[index]);
			info.add(object);
		}
		for (int index = 0; index < length; index++) {
			HashMap<Integer, Boolean> yam = new HashMap<Integer, Boolean>();
			for (int i = index - 1; i >= 0; i--) {
				

				if (info.get(i) > info.get(index)&&dp[i]+1==dp[index]) {

				
					if (yam.get(info.get(i)) == null) {
						if (flagged[index]) {

							bTrack.get(index).add(i);

						} else {
							List<Integer> p = new ArrayList<Integer>();
							p.add(i);

							bTrack.put(index, p);
							flagged[index]=true;
						}
					}
					yam.put(info.get(i), true);

				}

			}
		
		}
		
		BigInteger times = BigInteger.ZERO;
		boolean[]done=new boolean[5001];
		for (int i = info.size()-1; i >=0; i--) {
			if (dp[i] == max) {
				if(!done[info.get(i)]){
				times = times.add(findlayer(i, 1));
				
				done[info.get(i)]=true;
				}
			}
		}
	
		out.println(max + " " + times);
		out.close();
		in.close();
		System.out.println(System.currentTimeMillis()-start);
	}

	private static BigInteger[] dptime = new BigInteger[5001];

	private static BigInteger findlayer(int i, int layer) {
		if(dptime[i]!=null){
			return dptime[i];
		}
		if (layer == max) {
	  		return BigInteger.ONE;
		}

		BigInteger sum = BigInteger.ZERO;
		if (bTrack.get(i) != null) {
			for (int k : bTrack.get(i)) {

				if (dp[k] == max - layer) {
					sum = sum.add(findlayer(k, layer + 1));
				}

			}
		}
		dptime[i] = sum;
		return sum;
	}
}