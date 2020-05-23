/*
 ID: six.sal2
 PROB: fence
 LANG: JAVA
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class fence1 {
	private static int vCnt = 0, vMin = 501;
	private static int nextIdx(StringTokenizer stk) {
		int num = Integer.parseInt(stk.nextToken());
		if (num > vCnt) {
			vCnt = num;
		}
		if (num < vMin) {
			vMin = num;
		}
		return num-1;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("fence.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence.out")));
		int fNum = Integer.parseInt(in.readLine());
		int [][] edges = new int[500][500];
		int [] degrees = new int[500];
		for (int i = 0; i < fNum; i ++) {
			StringTokenizer stk = new StringTokenizer(in.readLine());
			int i1 = nextIdx(stk), i2 = nextIdx(stk);
			edges[i1][i2]++;
			edges[i2][i1]++;
			degrees[i1] ++;
			degrees[i2] ++;
		}
		int current = vMin - 1;
		for (int i = 0; i < degrees.length; i++) {
			if (degrees[i] % 2 != 0) {
				current = i;
				break;
			}
		}
		int [] result = new int[fNum + 1];
		euler(current, edges, result);
		for (int i = result.length -1; i >= 0; i --) {
			out.println(result[i]);
		}
		out.close();
		in.close();
	}
	private static int idx = 0;
	private static ArrayList<Integer> stack = new ArrayList<Integer>();
	private static void euler(int current, int[][] edges, int[] result) {
		System.out.println("stack: " + stack);
		System.out.println("location: " + (current + 1));
		System.out.println("circiut: " + Arrays.asList(result));
		for (int i = vMin-1; i < vCnt; i ++) {
			while (edges[current][i] > 0) {
				edges[current][i]--;
				edges[i][current]--;
				stack.add(current + 1);
				euler(i, edges, result);
				stack.remove(stack.size() - 1);
			}
		}
		result[idx ++] = current + 1;
	}
}
