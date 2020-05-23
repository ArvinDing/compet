
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class cowsignal {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cowsignal.in"));
		PrintWriter out = new PrintWriter(new File("cowsignal.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int m = Integer.parseInt(read.nextToken());
		int n = Integer.parseInt(read.nextToken());
		int amplfire = Integer.parseInt(read.nextToken());
		for (int i = 0; i < m; i++) {
			String a = in.readLine();

			for (int z = 0; z < amplfire; z++) {
				
					for (int k = 0; k < n; k++) {
						for (int y = 0; y < amplfire; y++) {
						out.print(a.charAt(k));
					}
				}
					out.println();
			}

		}
		out.close();
		in.close();
	}

}
