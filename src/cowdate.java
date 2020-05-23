import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class cowdate {
	private static int p6 = 1000000;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cowdate.in"));
		PrintWriter out = new PrintWriter(new File("cowdate.out"));
		int n = Integer.parseInt(in.readLine());
		int[] prob = new int[n];
		boolean flag = false;
		for (int i = 0; i < n; i++) {
			int a = Integer.parseInt(in.readLine());
			if (a == p6)
				flag = true;
			prob[i] = a;
		}
		if (flag)
			out.println(p6);
		else {
			double max = 0;
			for (int i = 0; i < n; i++) {
				double prob1 = 0;
				double prob0 = 1;
				for (int k = i; k < n; k++) {
					double curr1 = ((double) prob[k] / p6);
					double curr0 = ((double) (p6 - prob[k]) / p6);
					prob1 = curr1 * prob0 + curr0 * prob1;
					prob0 = curr0 * prob0;
					max = Math.max(max, prob1);
					System.out.println(prob1+ " "+i+" "+k);
				}
			}
			out.println((int) (max * p6));
		}
		in.close();
		out.close();
	}
}
