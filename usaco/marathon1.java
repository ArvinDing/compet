
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class marathon1 {
	private static List<Location> info;
	private static int N;
	private static int[][] done;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("marathon.in"));
		PrintWriter out = new PrintWriter(new File("marathon.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		N = Integer.parseInt(read.nextToken());
		int K = Integer.parseInt(read.nextToken());
		info = new ArrayList<Location>();
		done = new int[N + 1][K + 1];
		for (int i = 0; i < N; i++) {
			read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(read.nextToken());
			info.add(new Location(x, y));
		}

		out.println(function(0, K));
		out.close();
		in.close();
	}

	private static int function(int start, int skips) {

	
		if (done[start][skips] != 0) {
			return done[start][skips];
		}

		if (skips == 0) {
			int sum = 0;
			for (int i = start; i < N - 1; i++) {
				sum += Math.abs(info.get(i + 1).y - info.get(i).y) + Math.abs(info.get(i + 1).x - info.get(i).x);
			}
			done[start][0] = sum;
			return sum;
		}
		if (N - start - 1 <= skips) {

			int save = (Math.abs(info.get(N - 1).y - info.get(start).y)
					+ Math.abs(info.get(N - 1).x - info.get(start).x));
			done[start][skips] = save;
			return save;
		}
		int save = Integer.MAX_VALUE;
		for (int i = 1; i <= skips+1; i++) {
			
			save = Math.min(function(start + i, skips - (i - 1)) + Math.abs(info.get(start + i).y - info.get(start).y)
					+ Math.abs(info.get(start + i).x - info.get(start).x), save);
		}

		done[start][skips] = save;
		return save;

	}

	private static class Location {
		int x, y;

		Location(int x, int y) {
			this.x = x;
			this.y = y;

		}

	}
}