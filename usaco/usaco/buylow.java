package usaco;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

/*
ID: six.sal2
TASK: buylow
LANG: JAVA
*/

public class buylow {
	private static int [] prices = new int[5003];
	public static void main(String[] args) throws Exception {
		long start=System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("buylow.in"));
		PrintWriter out = new PrintWriter(new FileWriter("buylow.out"));
		in.readLine();
		StringTokenizer stk = null;
		int i = 0;
		while (true) {
			if (stk == null || (! stk.hasMoreTokens())) {
				String line = in.readLine();
				if (line == null) {
					break;
				}
				stk = new StringTokenizer(line);
			}
			prices[i ++] = Integer.parseInt(stk.nextToken());
		}
		solve(i, in, out);
		out.close();
		in.close();
		System.out.println(System.currentTimeMillis()-start);
	}

	private static void solve(int cnt, BufferedReader in, PrintWriter out) throws IOException {
		int [] sCnts = new int[cnt];
		ArrayList<Integer>[] tracks = new ArrayList[cnt];
		for (int i = 0; i < cnt; i ++) {
			for (int j = 0; j < i; j ++) {
				if (prices[j] > prices[i]) {
					int tmpCnt = sCnts[j] + 1;
					if (tmpCnt > sCnts[i]) {
						sCnts[i] = tmpCnt;
						tracks[i] = new ArrayList<Integer>();
						tracks[i].add(j);
					} else if (tmpCnt == sCnts[i]) {
						if (tracks[i] == null) {
							tracks[i] = new ArrayList<Integer>();
						}
						tracks[i].add(j);
					}
				}
			}
			if (sCnts[i] == 0) {
				sCnts[i] = 1;
			}
		}
		// Get high list/count
		ArrayList<Integer> highList = new ArrayList<Integer>();
		int highCnt = 0;
		for (int i = 0; i < cnt; i ++) {
			if (sCnts[i] > highCnt) {
				highList.clear();
				highCnt = sCnts[i];
				highList.add(i);
			} else if (sCnts[i] == highCnt) {
				highList.add(i);
			}
		}
		boolean [] inFlag = new boolean[cnt];
		HashSet<Integer> flagS = new HashSet<Integer>(highList);
		while (! flagS.isEmpty()) {
			HashSet<Integer> tmpS = new HashSet<Integer>();
			for (int high : flagS) {
				inFlag[high] = true;
				if (tracks[high] != null) {
					tmpS.addAll(tracks[high]);
				}
			}
			flagS = tmpS;
		}
		Map<Integer, Integer> valueIdxMap = new HashMap<Integer, Integer>();
		for (int i : highList) {
			Integer idx;
			if ((idx = valueIdxMap.get(prices[i])) == null) {
				valueIdxMap.put(prices[i], i);
			} else {
				inFlag[idx] = false;
				valueIdxMap.put(prices[i], i);
			}
		}
		Map<Integer, BigInteger>[] valueCntMaps = new HashMap[highCnt + 1];
		BigInteger tCnt = BigInteger.ZERO;
		for (int i = 0; i < cnt; i ++) {
			if (inFlag[i]) {
				if (valueCntMaps[sCnts[i]] == null) {
					valueCntMaps[sCnts[i]] = new HashMap<Integer, BigInteger>();
				}
				if (sCnts[i] == 1) {
					valueCntMaps[sCnts[i]].put(prices[i], BigInteger.ONE);
					if (highCnt == 1) {
						tCnt = tCnt.add(BigInteger.ONE);
					}
				} else {
					BigInteger soFarCnt = BigInteger.ZERO;
					for (Entry<Integer, BigInteger> entry : valueCntMaps[sCnts[i] - 1].entrySet()) {
						if (entry.getKey() > prices[i]) {
							soFarCnt = soFarCnt.add(entry.getValue());
						}
					}
					if (sCnts[i] == highCnt) {
						tCnt = tCnt.add(soFarCnt);
					} else {
						valueCntMaps[sCnts[i]].put(prices[i], soFarCnt);
					}
				}
			}
		}
		out.println(highCnt + " " + tCnt);
	}
}