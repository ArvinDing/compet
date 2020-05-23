
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class speeding {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("speeding.in"));
		PrintWriter out = new PrintWriter(new File("speeding.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(read.nextToken());
		int M = Integer.parseInt(read.nextToken());
		int[] limits = new int[100];
		int index = 0;
		for (int i = 0; i < N; i++) {
			read = new StringTokenizer(in.readLine());
			int times = Integer.parseInt(read.nextToken());
			int limit = Integer.parseInt(read.nextToken());
			for (int k = index; k < index + times; k++) {
				limits[k] = limit;
			}
			index += times;
		}
		index = 0;
		int[] speeds = new int[100];
		for (int i = 0; i < M; i++) {
			read = new StringTokenizer(in.readLine());
			int times = Integer.parseInt(read.nextToken());
			int speed = Integer.parseInt(read.nextToken());
			for (int k = index; k < index + times; k++) {
				speeds[k] = speed;
			}
			index += times;
		}
		int max = 0;
		for (int i = 0; i < 100; i++) {
			max = Integer.max(max, speeds[i] - limits[i]);
		}
		out.println(max);
		out.close();
		in.close();
	}

}