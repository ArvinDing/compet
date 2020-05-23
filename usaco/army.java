import java.io.*;
import java.util.*;

public class army {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());

		for (int i = 0; i < test; i++) {
			in.readLine();
			StringTokenizer read = new StringTokenizer(in.readLine());
			int god = Integer.parseInt(read.nextToken());
			int mecha = Integer.parseInt(read.nextToken());
			int godMax = Integer.MIN_VALUE;
			read = new StringTokenizer(in.readLine());
			for (int k = 0; k < god; k++) {
				godMax = Math.max(godMax, Integer.parseInt(read.nextToken()));
			}
			read = new StringTokenizer(in.readLine());
			int mechaMax = Integer.MIN_VALUE;
			for (int k = 0; k < mecha; k++) {
				mechaMax = Math.max(mechaMax, Integer.parseInt(read.nextToken()));
			}
			if (godMax >= mechaMax) {
				System.out.println("Godzilla");
			} else {
				System.out.println("MechaGodzilla");
			}
		}
		in.close();
	}
}