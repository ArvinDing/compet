
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class paint {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("paint.in"));
		PrintWriter out = new PrintWriter(new File("paint.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int a = Integer.parseInt(read.nextToken());
		int b = Integer.parseInt(read.nextToken());
		 read = new StringTokenizer(in.readLine());
		int c = Integer.parseInt(read.nextToken());
		int d = Integer.parseInt(read.nextToken());

		if (d < a || c > b) {
			out.println(d - c + b - a);
		} else {
			out.println(Math.max(d, b) - Math.min(c, a));
		}

		in.close();
		out.close();
	}

}