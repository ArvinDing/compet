
/*
ID: dingarv1
LANG: JAVA
TASK: kimbits
*/

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class kimbits1 {
	private static int[][] dp = new int[65536][];
	private static int curF=0, halfF = 0;

	public static void main(String[] args) throws Exception {
		long startTS = System.currentTimeMillis();

		Scanner in = new Scanner(new FileReader("kimbits.in"));
		PrintWriter out = new PrintWriter(new File("kimbits.out"));
		int n = in.nextInt();
		int l = in.nextInt();
		int i = in.nextInt();
		int p = 1;
		String b = "";
		int lol = (int) Math.round(Math.pow(2, n));
		dp[curF] = new int[65536];
		dp[curF][0] = 0;
		dp[curF][1] = 1;
		while (i > 0) {
			if (convert(p) <= l && p <= lol) {
				if (i == 2) {
					b = Integer.toString(Integer.parseInt(p + "", 10), 2);
				}
				i--;
			}
			p++;
		}
		while (b.length() < n) {
			b = "0" + b;
		}

		out.println(b);
		in.close();
		out.close();
		System.out.println(System.currentTimeMillis()-startTS);

	}

	private static boolean keepAddingFrames = true;
	
	private static int halfNum = 0;
	public static int convert(int number) {
		if(number<2){
			return dp[curF][number];
		}
		int half = number/2;
		if (half > halfNum) {
			if (half >= (halfF + 1)*65536) {
				if (keepAddingFrames) {
					dp[halfF] = null;
					halfF ++;
				}
			}
			halfNum = half;
		}
		int result;
		if (keepAddingFrames) {
			int j = dp[halfF][half%65536];
			result = j + countOnes(number%2);
		} else {
			int pF;
			int remFact = 2;
			while ((pF = half/65536)>curF) {
				half = half/2;
				remFact *= 2;
			}
//System.out.println(pF + ":"+ remFact);
			result = dp[pF][half%65536] + countOnes(number%remFact);
		}
		if (keepAddingFrames && number >= (curF + 1)*65536) {
			try {
				dp[curF + 1] = new int[65536];
				curF ++;
			} catch (Throwable e) {
				keepAddingFrames = false;
			}
		}
		if (keepAddingFrames) {dp[curF][number%65536] = result;}
		return result;
	}

	private static int countOnes(int num) {
		if (num < 2) {
			return num;
		}
		return num%2 + countOnes(num/2);
	}
}