
/*
ID: dingarv1
LANG: JAVA
TASK: race3
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class race3 {
	private static boolean[][] info = new boolean[50][50];

	private static int nodesT = 0;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new FileReader("race3.in"));
		PrintWriter out = new PrintWriter(new File("race3.out"));
		String[] line = in.readLine().split(" ");

		outerloop: while (true) {

			for (String a : line) {
				if (a.equals("-1")) {
					break outerloop;
				}
				if (a.equals("-2")) {
					break;
				}
				info[nodesT][Integer.parseInt(a)] = true;
			
			}
			line = in.readLine().split(" ");
			nodesT++;
		}

		List<Integer> unavoidable = unavoids(nodesT);
		StringBuilder line1 = new StringBuilder();
		line1.append(unavoidable.size() + " ");
		for (int i : unavoidable) {
			line1.append(i + " ");
		}

		out.println(line1.substring(0, line1.length() - 1));
		StringBuilder line2 = new StringBuilder();
		int length = 0;
		 lol:
		for (int i : unavoidable) {

			boolean[][] modified = new boolean[nodesT][nodesT];
			for (int k = 0; k < nodesT; k++) {
				for (int z = 0; z < nodesT; z++) {
					if ( z != i) {
						modified[k][z] = info[k][z];
					}
				}
			}
			boolean[]a=floodfillS(modified);
			boolean[]b=floodfillE(modified,i);
			for(int s=0;s<nodesT;s++){
				if(a[s]==b[s]){
					continue lol;
				}
			}
			
					line2.append(i + " ");
					length++;
				

			}
			if (length != 0) {
				out.println(length + " " + line2.substring(0, line2.length() - 1));
			} else {
				out.println(0);
			}

			out.close();
			in.close();
		}
	

	private static List<Integer> unavoids(int nodes) {
		List<Integer> a = new ArrayList<Integer>();
		for (int i = 1; i < nodes - 1; i++) {
			boolean[][] modified = new boolean[nodes][nodes];
			for (int k = 0; k < nodes; k++) {
				for (int z = 0; z < nodes; z++) {
					if (k != i && z != i) {
						modified[k][z] = info[k][z];
					}
				}
			}
			if (notconnected(modified)) {
				a.add(i);
			}

		}
		return a;
	}

	private static boolean[] floodfillS(boolean[][] modified) {
		boolean[] connected = new boolean[nodesT];

		Stack<Integer> stack = new Stack<Integer>();
		stack.add(0);
		connected[0] = true;
		while (!stack.isEmpty()) {
			int element = stack.pop();
		

			for (int i = 0; i < modified[element].length; i++) {
				if (modified[element][i] && !connected[i]) {
					stack.add(i);
					connected[i] = true;
				}
			}
		}
		return connected;
	}
	private static boolean[] floodfillE(boolean[][] modified,int splitting) {
		boolean[] connected = new boolean[nodesT];

		Stack<Integer> stack = new Stack<Integer>();
		stack.add(splitting);
		connected[splitting] = true;
		while (!stack.isEmpty()) {
			int element = stack.pop();
		

			for (int i = 0; i < modified[element].length; i++) {
				if (modified[element][i] && !connected[i]) {
					stack.add(i);
					connected[i] = true;
				}
			}
		}
		return connected;
	}
	private static boolean notconnected(boolean[][] modified) {
		boolean[] connected = new boolean[nodesT];
		Stack<Integer> stack = new Stack<Integer>();
		stack.add(0);
		connected[0] = true;
		while (!stack.isEmpty()) {
			int element = stack.pop();
			if (element == nodesT - 1)
				return false;

			for (int i = 0; i < modified[element].length; i++) {
				if (modified[element][i] && !connected[i]) {
					stack.add(i);
					connected[i] = true;
				}
			}
		}
		return true;
	}

}
