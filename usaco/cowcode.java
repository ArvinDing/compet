
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class cowcode {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cowcode.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out")));
		StringTokenizer a = new StringTokenizer(in.readLine());
		String start = a.nextToken();
		long d = Long.parseLong(a.nextToken());
		long length = start.length();
		int times = 0;
		while (length < d) {
			length *= 2;
			times++;
		}

		times--;
		while (length != start.length()) {
			if (d > start.length() * Math.pow(2, times) ) {
				if (d - 1 == start.length() * Math.pow(2, times)) {
					d = (long) (start.length() * Math.pow(2, times));
				} else {
					d = (d - (long) (start.length() * (Math.pow(2, times)))) - 1;
				}
			}
			times--;
			length /= 2;
		}

		out.println(start.charAt((int) d - 1));
		out.close();
		in.close();

	}
}