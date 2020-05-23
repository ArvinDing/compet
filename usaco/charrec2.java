import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

/*
ID: six.sal2
TASK: charrec
LANG: JAVA
*/

public class charrec2 {
    private static final String fChars = " abcdefghijklmnopqrstuvwxyz";  
	private static final int MAX = Integer.MAX_VALUE/10;
	public static void main(String[] args) throws Exception {
		boolean[][][] font = new boolean[27][20][20];
		long start = System.currentTimeMillis();
		BufferedReader reader = new BufferedReader(new FileReader("font.in"));
		reader.readLine();
		for (int i = 0; i < 27; i++) {
			for (int j = 0; j < 20; j++) {
				String c = reader.readLine();
				for (int k = 0; k < 20; k++) {
					font[i][j][k] = c.charAt(k) == '1';
				}
			}
		}
		reader.close();
		reader = new BufferedReader(new FileReader("charrec.in"));
		int iL = Integer.valueOf(reader.readLine()); // n: number of input lines
		boolean[][] input = new boolean[iL][20];
		for (int i = 0; i < iL; i++) {
			String c = reader.readLine();
			for (int j = 0; j < 20; j++) {
				input[i][j] = c.charAt(j) == '1';
			}
		}
		reader.close();
		// calculate diff for each line combination
		diff = new int[27][20][iL]; // diff[i][j][k] is the diff count of (letter i,line j) and input line k
		for (int i = 0; i < 27; i++) {
			for (int j = 0; j < 20; j++) {
				for (int k = 0; k < iL; k++) {
					for (int t = 0; t < 20; t++) {
						if (font[i][j][t] != input[k][t]) {
							diff[i][j][k]++;
						}
					}
				}
			}
		}
		// Let dp[i] be the the minimum error count up to line i
		// dp[i]=min(dp[i-19]+minErrorCnt19(i-18),dp[i-20]+minErrorCnt20(i-19),dp[i-21]+minErrorCnt21(i-20))
		// minErrorCnt19(start), minErrorCnt20(start),      minErrorCnt21 --- min count of errors for cases: 
		// deleted one row,                   regular, duplicated one row respectively
		int [] dp = new int[iL];
		Arrays.fill(dp, MAX);
		letters = new String[iL];
		int [] rtn = minErrorCnt19(0);
		dp[18] = rtn[0];
		letters[18] = String.valueOf(fChars.charAt(rtn[1]));
		if (iL > 19) {
			rtn = minErrorCnt20(0);
			dp[19] = rtn[0];
			letters[19] = String.valueOf(fChars.charAt(rtn[1]));
		}
		if (iL > 20) {
			rtn = minErrorCnt21(0);
			dp[20] = rtn[0];
			letters[20] = String.valueOf(fChars.charAt(rtn[1]));
			for (int i = 37; i < iL; i ++) {
				int min = MAX;
				String lets = null;
				if (dp[i-19] < MAX) {
					rtn = minErrorCnt19(i-18);
					min = dp[i-19] + rtn[0];
					lets = letters[i-19] + fChars.charAt(rtn[1]);
				}
				if (dp[i-20] < MAX) {
					rtn = minErrorCnt20(i-19);
					int one = dp[i-20] + rtn[0];
					if (one < min) {
						lets = letters[i-20] + fChars.charAt(rtn[1]);
						min = one;
					}
				}
				if (dp[i-21] < MAX) {
					rtn = minErrorCnt21(i-20);
					int one = dp[i-21] + rtn[0];
					if (one < min) {
						lets = letters[i-21] + fChars.charAt(rtn[1]);
						min = one;
					}
				}
				if (lets != null) {
					letters[i] = lets;
					dp[i] = min;
				}
			}
		}
		PrintWriter out = new PrintWriter(new FileWriter("charrec.out"));
		if (dp[iL-1] < MAX) {
			out.println(letters[iL-1]);
		} else {
			out.println("?");
		}
		out.close();
		System.out.println(System.currentTimeMillis() - start);
	}
	private static String [] letters;
	private static int[][][] diff;
	
	private static int[] minErrorCnt21(int start) {
		int minCnt = MAX, idx = -1;
		for (int i = 0; i < 27; i ++) {
			int cnt = 0;
			for (int j = start; j < start + 20; j ++) {// shift down one
				cnt += diff[i][j-start][j+1];
			}
			for (int dupR = 0; dupR < 20; dupR ++) {
				boolean firstSkip = diff[i][dupR][start + dupR] > diff[i][dupR][start + dupR + 1];
				int skip, counted;
				if (firstSkip) {
					skip = diff[i][dupR][start + dupR];
					counted = diff[i][dupR][start + dupR + 1];
				} else {
					counted = diff[i][dupR][start + dupR];
					skip = diff[i][dupR][start + dupR + 1];
					cnt += counted - skip;
				}
				if (minCnt > cnt) {
					minCnt = cnt;
					idx = i;
				}
				if (firstSkip) {
					cnt += skip - counted;
				}
			}
		}
		return new int[] {minCnt, idx};
	}

	private static int [] minErrorCnt20(int start) {
		int minCnt = MAX, idx = -1;
		for (int i = 0; i < 27; i ++) {
			int cnt = 0;
			for (int j = start; j < start + 20; j ++) {
				cnt += diff[i][j-start][j];
			}
			if (minCnt > cnt) {
				minCnt = cnt;
				idx = i;
			}
		}
		return new int [] {minCnt, idx};
	}

	private static int [] minErrorCnt19(int start) {
		int minCnt = MAX, idx = -1;
		for (int i = 0; i < 27; i ++) {
			int cnt = 0;
			for (int j = start; j < start + 19; j ++) {// skip first line
				cnt += diff[i][j-start + 1][j];
			}
			for (int missRn = 0; missRn < 20; missRn ++) {
				if (missRn > 0) {
					cnt += diff[i][missRn - 1][start + missRn - 1] - diff[i][missRn][start + missRn - 1];
				}
				if (minCnt > cnt) {
					minCnt = cnt;
					idx = i;
				}
			}
		}
		return new int [] { minCnt, idx };
	}
}