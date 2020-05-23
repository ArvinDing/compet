
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class blocks {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("blocks.in"));
		PrintWriter out = new PrintWriter(new File("blocks.out"));
		int a = Integer.parseInt(in.readLine());
		int[] answer = new int[26];
		for (int i = 0; i < a; i++) {
			StringTokenizer b = new StringTokenizer(in.readLine());
			int[] curr = new int[26];
			String q = b.nextToken();
			for (int k = 0; k < q.length(); k++) {
				curr[q.charAt(k) - 97]++;
			}
			int[] curr1 = new int[26];
			q = b.nextToken();
			for (int k = 0; k < q.length(); k++) {

				curr1[q.charAt(k) - 97]++;
			}
			for (int k = 0; k < 26; k++) {

				answer[k] += Math.max(curr[k], curr1[k]);

			}

		}
		for (int i = 0; i < 26; i++) {
			out.println(answer[i]);
		}
		in.close();
		out.close();

	}

}
