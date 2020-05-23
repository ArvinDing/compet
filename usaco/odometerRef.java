
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class odometerRef {
	public static void main(String[] args) throws Exception {
		long start, end;
		try (BufferedReader in = new BufferedReader(new FileReader("odometer.in"))) {
			String [] line = in.readLine().split(" ");
			start = Long.parseLong(line[0]);
			end = Long.parseLong(line[1]);
		}
//		long cnt = getCountBelow(end) - getCountBelow(start -1);
//		Files.write(Paths.get("odometer.out"), String.valueOf(cnt).getBytes());
		System.out.println(cntDP("20", '1', '2'));
	}

	private static long getCountBelow(long limit) {
		String lStr = String.valueOf(limit);
		long cnt = 0;
		for (char i = '0'; i <= '9'; i ++) {
			cnt += cntDP(lStr, i, (char) 0);
			System.out.println(i + ":" + cntDP(lStr, i, (char) 0));
		}
		for (char i = '0'; i < '9'; i ++) {
			for (char j = (char) (i + 1); j <= '9'; j ++) {
				cnt -= cntDP(lStr, i, j);
//				System.out.println(i + "," + j + ":" + cnt);
			}
		}
		return cnt;
	}

	private static long cntDP(String limit, char digit, char digit1) {
		int lLen = limit.length();
		int rLen = (lLen + 1)/2;
		long [][][][] dp = new long[lLen + 1][rLen + 1][2][2];//[curDigit][remaining repeat cnt][higher digit under limit][is 0]
		dp [0][rLen][0][1] = 1;
		for (int ci = 0; ci < lLen; ci ++) {
			for (int rr = 0; rr <= rLen; rr ++) {
				for (int ul = 0; ul < 2; ul ++) {
					for (int i0 = 0; i0 < 2; i0 ++) {
						for (char nxt = '0'; nxt <= '9'; nxt ++) {
							long cCnt = dp[ci][rr][ul][i0];
							if (cCnt == 0 || (digit1 != 0 && (nxt != digit && nxt != digit1 
									&& (i0 != 1 || nxt != '0')))) {
								continue;
							}
							int ni0 = i0 == 1 && nxt == '0' ? 1 : 0;
							if (digit1 != 0 && ni0 == 0 && i0 == 1) {
								if ((lLen - ci)%2 == 1) continue;
							}
							int nul = 1;
							if (ul == 0) {
								char lc = limit.charAt(ci);
								if (nxt > lc) {
									break;
								} else if ( nxt == lc) {
									nul = 0;
								}
							}
							int nrr;
							if (i0 == 1 && nxt == '0') {
								nrr = (lLen - ci)/2;
								if (digit1 != 0 && nrr == 0) continue;
							} else if (nxt == digit) {
								if (rr == 0) {
									if (digit1 != 0) continue;
									nrr = 0;
								} else {
									nrr = rr - 1;
								}
							} else {
								nrr = rr;
							}
							dp[ci + 1][nrr][nul][ni0] += cCnt;
							if (cCnt != 0) {
								System.out.printf("(%d,%d,%s,%s):%d -> (%d,%d,%s,%s) = %d\n", ci, rr, ul == 1 ? "T" : "F", i0 == 1 ? "T" : "F", dp[ci][rr][ul][i0],
									ci+1, nrr, nul == 1 ? "T" : "F", ni0 == 1 ? "T" : "F", dp[ci + 1][nrr][nul][ni0]);
							}
						}
					}
				}
			}
		}
		for (int ci = 0; ci <= lLen; ci ++) {
			for (int rr = 0; rr <= rLen; rr ++) {
				for (int ul = 0; ul < 2; ul ++) {
					for (int i0 = 0; i0 < 2; i0 ++) {
						System.out.printf("(%d,%d,%s,%s):%d\n", ci, rr, ul == 1 ? "T" : "F", i0 == 1 ? "T" : "F", dp[ci][rr][ul][i0]);
					}
				}
			}
		}
		return dp[lLen][0][0][0] + dp[lLen][0][1][0];
	}
}
