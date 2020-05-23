
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class pails {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("pails.in"));
		PrintWriter out = new PrintWriter(new File("pails.out"));
		int max = 0;
		StringTokenizer read = new StringTokenizer(in.readLine());
		int X = Integer.parseInt(read.nextToken());
		int Y = Integer.parseInt(read.nextToken());
		int M = Integer.parseInt(read.nextToken());
		int times = M / X;
		for (int i = times; i >= 0; i--) {
			max = Math.max(max, M - ((M - (i * X)) % Y));
		}
		out.println(max);
		out.close();
		in.close();
	}

}