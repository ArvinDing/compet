
/*
ID: dingarv1
LANG: JAVA
TASK: game1
*/

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class game1 {
	private static List<Integer> info = new ArrayList<Integer>();
	private static int [][] dp=new int [101][101];

	public static void main(String[] argv) throws IOException {

		Scanner in = new Scanner(new FileReader("game1.in"));
		PrintWriter out = new PrintWriter(new File("game1.out"));
		int size = in.nextInt();
		for (int i = 0; i < size; i++) {
			info.add(in.nextInt());
		}
		int answer = best(info,0,info.size());
		out.println(answer + " " + (sum(info) - answer));
		in.close();
		out.close();
	}

	public static int best(List<Integer> a,int start,int end) {
		if (a.size() == 2) {
			return a.get(0) > a.get(1) ? a.get(0) : a.get(1);
		}
		if(dp[start][end]!=0){
			return dp[start][end];
		}

		int aBest = a.get(0) + sum(a.subList(1, a.size())) - best(a.subList(1, a.size()),start+1,end);
		int bBest = a.get(a.size() - 1) + sum(a.subList(0, a.size() - 1)) - best(a.subList(0, a.size() - 1),start,end-1);
		dp[start][end]=(aBest > bBest ? aBest : bBest);
		return aBest > bBest ? aBest : bBest;
	}

	public static int sum(List<Integer> a) {
		int sum = 0;
		for (int i : a) {
			sum += i;
		}
		return sum;
	}
}