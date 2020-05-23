
/*
ID: dingarv1
LANG: JAVA
TASK: fence8
*/
import java.io.*;
import java.util.*;

public class fence8 {
	static int[] railL;
	static int[] boardL;
	static int boardT;
	static int mostW;
	static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("fence8.in"));
		PrintWriter out = new PrintWriter(new File("fence8.out"));
		n = Integer.parseInt(in.readLine());
		int[] boards = new int[n];
		for (int i = 0; i < n; i++) {
			boards[i] = Integer.parseInt(in.readLine());
			boardT += boards[i];
		}
		Arrays.sort(boards);
		int r = Integer.parseInt(in.readLine());
		int[] rails = new int[129];
		for (int i = 0; i < r; i++) {
			int curr = Integer.parseInt(in.readLine());
			rails[curr]++;
		}
		int smallTrue = 0;
		int bigFalse = r + 1;
		while (smallTrue + 1 != bigFalse) {
			int mid = (smallTrue + bigFalse) / 2;
		//	System.out.println(smallTrue + " " + bigFalse);
			if (test(mid, rails, boards)) {
				smallTrue = mid;
			} else {
				bigFalse = mid;
			}
		}
		out.println(smallTrue);
		in.close();
		out.close();

	}

	static int railCnt;

	static boolean test(int rails, int[] railsInfo, int[] boards) {
		railL = new int[129];
		int add = 0;
		int railsT = 0;
		for (int i = 1; i < 129; i++) {
			if (add == rails)
				break;
			railL[i] += Math.min(rails - add, railsInfo[i]);
			railsT += railL[i] * i;
			add += railL[i];
		}
		boardL = Arrays.copyOf(boards, boards.length);
		mostW = boardT - railsT;
		railCnt = rails;
		return recursion(0, 1, 0);
	}

	static boolean recursion(int boardI, int railSize, int waste) {
		if (waste > mostW)
			return false;
		if (boardI == n)
			return true;
		if (railCnt == 0)
			return true;
		if (railSize == 129 || railSize > boardL[boardI])
			return recursion(boardI + 1, 1, waste + boardL[boardI]);
		boolean include = false;
		if (railL[railSize] != 0) {
			boardL[boardI] -= railSize;
			railL[railSize]--;
			railCnt--;
			include = recursion(boardI, railSize, waste);
			if (include)
				return true;
			boardL[boardI] += railSize;
			railL[railSize]++;
			railCnt++;
		}
		boolean ignore = recursion(boardI, railSize + 1, waste);
		return ignore;
	}

}
