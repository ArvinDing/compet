
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class cowrace {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cowrace.in"));
		PrintWriter out = new PrintWriter(new File("cowrace.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int bessie = Integer.parseInt(read.nextToken());
		int elsie = Integer.parseInt(read.nextToken());
		int[] bPosition = new int[1000001];
		int[] ePosition = new int[1000001];

		int bTime = 1;

		for (int i = 0; i < bessie; i++) {
			read = new StringTokenizer(in.readLine());
			int speed = Integer.parseInt(read.nextToken());
			int times = Integer.parseInt(read.nextToken());
			for (int k = 0; k < times; k++) {

				bPosition[bTime] = bPosition[bTime - 1] + speed;
				bTime++;

			}
		}

		int eTime = 1;
		for (int i = 0; i < elsie; i++) {
			read = new StringTokenizer(in.readLine());
			int speed = Integer.parseInt(read.nextToken());
			int times = Integer.parseInt(read.nextToken());
			for (int k = 0; k < times; k++) {
				ePosition[eTime] = ePosition[eTime - 1] + speed;
				eTime++;

			}
		}
		int max = Math.max(eTime, bTime);
		int old = 0;
		int changes = 0;
		// bessie=-1, elsie =1
		for (int i = 1; i < max; i++) {
			int curr = 0;
			if (bPosition[i] > ePosition[i]) {
				curr = -1;
			} else if (bPosition[i] < ePosition[i]) {
				curr = 1;
			}
			if (old != curr && curr != 0 && old != 0) {
				changes++;
			}
			if (curr != 0) {
				old = curr;
			}

		}
		out.println(changes);
		in.close();
		out.close();
	}

}