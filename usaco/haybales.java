
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class haybales {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("haybales.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
		String[] a = in.readLine().split(" ");
		int n = Integer.parseInt(a[0]);
		int q = Integer.parseInt(a[1]);
		StringTokenizer b = new StringTokenizer(in.readLine());
		List<amazing> questions = new ArrayList<amazing>();
		int []queries=new int [q];
		for (int i = 0; i < q; i++) {
			StringTokenizer p = new StringTokenizer(in.readLine());
			questions.add(new amazing(Integer.parseInt(p.nextToken()), Integer.parseInt(p.nextToken())));
		}
		while (b.hasMoreTokens()) {
			int current = Integer.parseInt(b.nextToken());
			for (int z = 0; z < questions.size(); z++) {
				if (current >= questions.get(z).start && current <= questions.get(z).end) {
					queries[z]++;
				}
			}
		}
		for(int i:queries){
			out.println(i);
		}
		in.close();
		out.close();
	}

	private static class amazing {
		int start, end;

		amazing(int start, int end) {
			this.start = start;
			this.end = end;
	
		}
	}
}