
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class cowjog1 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cowjog.in"));
		PrintWriter out = new PrintWriter(new File("cowjog.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int cows = Integer.parseInt(read.nextToken());
		int time = Integer.parseInt(read.nextToken());
		int groups = 0;
		Long[][] info = new Long[cows][2];

		for (int i = 0; i < cows; i++) {
			read = new StringTokenizer(in.readLine());
			long start = Long.parseLong(read.nextToken());
			long speed = Long.parseLong(read.nextToken());
			info[i][0] = start;
			info[i][1] = speed;
		}

		long groupDistance = Long.MAX_VALUE;
		for (int i = info.length - 1; i >= 0; i--) {
			long start = info[i][0];
			long speed = info[i][1];
			long currentDistance = start + time * speed;
			if (currentDistance < groupDistance) {
				groups++;
				groupDistance = currentDistance;

			}

		}

		out.println(groups);
		out.close();
		in.close();
	}
}