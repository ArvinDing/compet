import java.util.*;
import java.io.*;

public class car {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int duration = Integer.parseInt(read.nextToken());
			if (duration < 0)
				break;
			double inital = Double.parseDouble(read.nextToken());
			double loan = Double.parseDouble(read.nextToken());
			double car = loan + inital;
			int records = Integer.parseInt(read.nextToken());
			LinkedList<double[]> queue = new LinkedList<double[]>();
			for (int i = 0; i < records; i++) {
				read = new StringTokenizer(in.readLine());
				queue.add(new double[] { Integer.parseInt(read.nextToken()), Double.parseDouble(read.nextToken()) });
			}
			double payment = loan / duration;
			double previous = 0;
		
			for (int i = 0; i <= duration; i++) {
				double owed = loan - (i * payment);
				if (!queue.isEmpty() && queue.peek()[0] == i) {
					double lower = queue.poll()[1];
					car -= (car * lower);
					previous = lower;
				} else {
					car -= (car * previous);
				}
				if (car > owed) {
					System.out.println(i + " month" + ((i == 1) ? "" : "s"));
					break;
				}
			}
		}
		in.close();
	}
}