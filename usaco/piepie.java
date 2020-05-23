
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class piepie {
	private static int pies;
	private static TreeSet<int[]> bessie;
	private static TreeSet<int[]> elsie;
	private static int [] bCnt, eCnt, rtn;
	private static Comparator<int[]> cmp = new Comparator<int[]>() {
		public int compare(int[] o1, int[] o2) {
			int d = o1[0] - o2[0];
			return d == 0 ? o1[2] - o2[2] : d;
		}
	};

	private static int difference;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("piepie.in"));
		PrintWriter out = new PrintWriter(new File("piepie.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		pies = Integer.parseInt(read.nextToken());
		difference = Integer.parseInt(read.nextToken());

		bessie =new TreeSet<int []>(cmp);
		for (int i = 0; i < pies; i++) {
			read = new StringTokenizer(in.readLine());
			int b = Integer.parseInt(read.nextToken());
			bessie.add(new int[] {Integer.parseInt(read.nextToken()), b, i });
			
		}

		elsie =new TreeSet<int []>(cmp);
		for (int i = 0; i < pies; i++) {
			read = new StringTokenizer(in.readLine());
			elsie.add(new int[] { Integer.parseInt(read.nextToken()), Integer.parseInt(read.nextToken()), i });
		}

		bCnt = new int [pies]; eCnt = new int[pies]; rtn = new int[pies];
		Arrays.fill(rtn, -1);
		for (int [] a:bessie)
			if (a[0] == 0)
				recursion(a, true, 1);
		bCnt = new int [pies]; eCnt = new int[pies];
		for (int [] a:elsie)
			if (a[1] == 0)
				recursion(a, false, 1);
		for (int i = 0; i < pies; i++) out.println(rtn[i]);
		in.close();
		out.close();
	}

	private static void recursion(int [] curN, boolean isBessie, int curr) {
		if (isBessie) {
			bCnt[curN[2]] = curr;
			if (rtn[curN[2]] == -1) rtn[curN[2]] = curr;
			else if (rtn[curN[2]] > curr) rtn[curN[2]] = curr;
			
			for (int [] one : elsie.subSet(new int [] {curN[1]-difference, 0, -1} , new int [] {curN[1], 0, pies})) {
				if (eCnt[one[2]] == 0) recursion(one, false, curr+ 1);
			}
			bCnt[curN[2]] = 0;
		} else {
			eCnt[curN[2]] = curr;
			for (int [] one : bessie.subSet(new int [] {curN[1]-difference, 0, -1} , new int [] {curN[1], 0, pies})) {
				if (bCnt[one[2]] == 0) recursion(one, true, curr+ 1);
			}
			eCnt[curN[2]] = 0;
		}
	}

}
