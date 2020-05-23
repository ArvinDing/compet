import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
ID: six.sal2
TASK: buylow
LANG: JAVA
*/

public class buylow123 {
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
	private static BigInteger [] paths;
	private static HashMap<Integer, Integer>[] tracks;

	private static void solve(int cnt, BufferedReader in, PrintWriter out) throws IOException {
		int [] sCnts = new int[cnt];
		int highCnt = 1;
		tracks = new HashMap[cnt];
		for (int i = 0; i < cnt; i ++) {
			for (int j = 0; j < i; j ++) {
				if (prices[j] > prices[i]) {
					int tmpCnt = sCnts[j] + 1;
					if (tmpCnt > sCnts[i]) {
						if (tmpCnt > highCnt) {
							highCnt = tmpCnt;
						}
						sCnts[i] = tmpCnt;
					}
				}
			}
			if (sCnts[i] == 0) {
				sCnts[i] = 1;
			} else {
				int pCnt = sCnts[i] - 1;
				for (int j = 0; j < i; j ++) {
					if (pCnt == sCnts[j] && prices[j] > prices[i]) {
						if (tracks[i] == null) {
							tracks[i] = new HashMap<Integer, Integer>();
						}
						tracks[i].put(prices[j], j);
					}
				}
			}
		}
		HashMap<Integer, Integer> highMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < cnt; i ++) {
			if (sCnts[i] == highCnt) {
				highMap.put(prices[i], i);
			}
		}
		BigInteger tCnt = BigInteger.ZERO;
		paths = new BigInteger[cnt];
		for (int one : highMap.values()) {
			BigInteger oneCnt = getPathsCnt(one);
			System.out.println(one + ":" + oneCnt);
			tCnt = tCnt.add(getPathsCnt(one));
		}
		out.println(highCnt + " " + tCnt);
	}

	private static BigInteger getPathsCnt(int idx) {
		if (paths[idx] != null) {
			return paths[idx];
		}
		if (tracks[idx] == null) {
			return BigInteger.ONE;
		}
		BigInteger total = BigInteger.ZERO;
		for (int one : tracks[idx].values()) {
			total = total.add(getPathsCnt(one));
		}
		paths[idx] = total;
		return total;
	}
}