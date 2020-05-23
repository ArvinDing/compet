
/*
ID: dingarv1
LANG: JAVA
TASK: fence
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class fence {

	private static int[][] info = new int[501][501];
	private static List<Integer> circuit = new ArrayList<Integer>();

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new FileReader("fence.in"));
		PrintWriter out = new PrintWriter(new File("fence.out"));
		int F = Integer.parseInt(in.readLine());
		int[] degrees = new int[501];
		for (int i = 0; i < F; i++) {
			String[] a = in.readLine().split(" ");
			info[Integer.parseInt(a[0])][Integer.parseInt(a[1])]++;
			info[Integer.parseInt(a[1])][Integer.parseInt(a[0])]++;
			degrees[Integer.parseInt(a[0])]++;
			degrees[Integer.parseInt(a[1])]++;
		}
		int min = Integer.MAX_VALUE;
		boolean odd = false;
		for (int i = 1; i < 501; i++) {
			if (degrees[i] % 2 == 1) {
				if (odd == false) {
					min = i;
				}else{
					if(i<min){
						min=i;
					}
				}
				odd = true;

			} else if (odd == false && i < min && degrees[i] != 0) {
				min = i;
			}
		}
		recursion(min);
		for (int i = circuit.size() - 1; i >= 0; i--) {
			out.println(circuit.get(i));
		}

		out.close();
		in.close();

	}

	private static ArrayList<Integer> stack = new ArrayList<Integer>();

	public static void recursion(int location) {
//		System.out.println("stack: " + stack);
//		System.out.println("location: " + location);
//		System.out.println("circuit: " + circuit);
		for (int i = 1; i < 501; i++) {

			while (info[location][i] != 0) {
				info[location][i]--;
				info[i][location]--;
				stack.add(location);
				recursion(i);
				stack.remove(stack.size() - 1);
			}

		}

		circuit.add(location);

	}
}