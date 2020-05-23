


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class marathonN {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("marathon.in"));
		PrintWriter out = new PrintWriter(new File("marathon.out"));
		int n = Integer.parseInt(in.readLine());

		int sum = 0;
		StringTokenizer read = new StringTokenizer(in.readLine());

		int x = Integer.parseInt(read.nextToken());
		int y = Integer.parseInt(read.nextToken());
		read = new StringTokenizer(in.readLine());

		int x1 = Integer.parseInt(read.nextToken());
		int y1 = Integer.parseInt(read.nextToken());
		read = new StringTokenizer(in.readLine());

		int x2 = Integer.parseInt(read.nextToken());
		int y2 = Integer.parseInt(read.nextToken());
		int max = 0;
		sum = Math.abs(x1 - x) + Math.abs(y1 - y);
		for (int i = 3; i < n; i++) {
			sum += Math.abs(x2 - x1) + Math.abs(y2 - y1);

			int distanceOne = Math.abs(x1 - x) + Math.abs(y1 - y) + Math.abs(x2 - x1) + Math.abs(y2 - y1);
			int distanceTwo = Math.abs(x2 - x) + Math.abs(y2 - y);
			if (distanceOne - distanceTwo > max) {
				max = distanceOne - distanceTwo;
			}
			x = x1;
			y = y1;
			x1 = x2;
			y1 = y2;
			read = new StringTokenizer(in.readLine());
			x2 = Integer.parseInt(read.nextToken());
			y2 = Integer.parseInt(read.nextToken());

		}
		sum+=Math.abs(x2 - x1) + Math.abs(y2 - y1);
		out.println(sum - max);
		out.close();
		in.close();
	}
}