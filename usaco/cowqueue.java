
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class cowqueue {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cowqueue.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowqueue.out")));
		TreeMap<Integer, Integer> info = new TreeMap<Integer, Integer>();
		TreeSet<Integer> waiting = new TreeSet<Integer>();
		int lines = Integer.parseInt(in.readLine());

		for (int i = 0; i < lines; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int arrive = Integer.parseInt(read.nextToken());
			int time = Integer.parseInt(read.nextToken());
			if (!info.containsKey(arrive)) {
				info.put(arrive, 0);
			}
			info.put(arrive, info.get(arrive) + time);
		}
		int time = 0;
		for (Entry<Integer, Integer> curr : info.entrySet()) {
			if (time <= curr.getKey()) {
				time = curr.getKey() + curr.getValue();
			} else {
				time += curr.getValue();
			}

		}
		out.print(time);
		in.close();
		out.close();
	}

}