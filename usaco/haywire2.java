
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class haywire2 {

	public static void main(String[] argv) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("haywire.in"));
		PrintWriter out = new PrintWriter(new FileWriter("haywire.out"));
		int cows = Integer.parseInt(in.readLine());
		int[][] info = new int[cows + 1][3];
		for (int i = 1; i < cows+1; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			info[i][0] = Integer.parseInt(read.nextToken());
			info[i][1] = Integer.parseInt(read.nextToken());
			info[i][2] = Integer.parseInt(read.nextToken());
		}

		switches a = permutations(0, 3, new switches(-1, -1, new ArrayList<switches>()), -1);
		//print(a,0);
		int []input=new int [cows+1];
		for(int i=1;i<cows+1;i++){
			input[i]=i;
		}
		
		int currAns=0;
		for(int i=1;i<cows+1;i++){
			currAns+=Math.abs(input[info[i][0]]-input[i]);
			currAns+=Math.abs(input[info[i][1]]-input[i]);
			currAns+=Math.abs(input[info[i][2]]-input[i]);
			
		}

		in.close();
		out.close();

	}
	

	private static void print(switches a ,int layer) {
		for(int i=0;i<layer;i++){
			System.out.print("|");
		}
		System.out.println(a.first+" "+a.second);
	
		if (a.child != null) {
			for (switches name : a.child) {
				
				print(name,layer+1);
			}
		}

	}

	private static switches permutations(int prefixL, int strL, switches a, int index) {
		if (index != -1) {
			a.first = prefixL - 1;
			a.second = index;
		}
		int n = strL;
		if (n == 0) {
			a.child = null;
			return a;
		} else {
			for (int i = 0; i < n; i++) {
				a.child.add(permutations(prefixL + 1, strL - 1,
						new switches(a.first, a.second, new ArrayList<switches>()), i));
			}
		}
		return a;
	}

	private static class switches {
		int first;
		int second;
		List<switches> child = new ArrayList<switches>();

		private switches(int first, int second, List<switches> child) {
			this.first = first;
			this.second = second;
			this.child = child;
		}
	}

}
