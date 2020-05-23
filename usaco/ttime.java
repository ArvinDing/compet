
import java.io.*;
import java.util.*;

public class ttime {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("ttime.in"));
		PrintWriter out = new PrintWriter(new File("ttime.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int cows = Integer.parseInt(read.nextToken());
		int previous = Integer.parseInt(read.nextToken());
		int queries = Integer.parseInt(read.nextToken());
		int[] disjoint = new int[cows];
		Arrays.fill(disjoint, -1);
		for (int i = 0; i < previous; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken())-1;
			int b = Integer.parseInt(read.nextToken())-1;
			int tempA = a;
			while (disjoint[tempA] >= 0) {
				tempA = disjoint[tempA];
			}
			while (disjoint[a] >= 0) {
				int old = disjoint[a];
				disjoint[a] = tempA;
				a = old;
			}
			int tempB = b;
			while (disjoint[tempB] >= 0) {
				tempB = disjoint[tempB];
			}
			while (disjoint[b] >= 0) {
				int old = disjoint[b];
				disjoint[b] = tempB;
				b = old;
			}
			if (tempA != tempB) {
				if (disjoint[tempA] < disjoint[tempB]) {
					disjoint[tempB] = tempA;
				} else if (disjoint[tempB] < disjoint[tempA]) {
					disjoint[tempA] = tempB;
				} else {
					disjoint[tempB]--;
					disjoint[tempA] = tempB;
				}
			}
		}
		for (int i = 0; i < queries; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken())-1;
			int b = Integer.parseInt(read.nextToken())-1;
			int tempA = a;
			while (disjoint[tempA] >= 0) {
				tempA = disjoint[tempA];
			}
			while (disjoint[a] >= 0) {
				int old = disjoint[a];
				disjoint[a] = tempA;
				a = old;
			}
			int tempB = b;
			while (disjoint[tempB] >= 0) {
				tempB = disjoint[tempB];
			}
			while (disjoint[b] >= 0) {
				int old = disjoint[b];
				disjoint[b] = tempB;
				b = old;
			}
			if (tempA == tempB)
				out.println("Y");
			else
				out.println("N");
		}
		in.close();
		out.close();
	}
}