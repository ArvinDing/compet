
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class paint1 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("paint.in"));
		PrintWriter out = new PrintWriter(new File("paint.out"));
		int lines = Integer.parseInt(in.readLine());
		TreeMap<Integer, Integer> Map = new TreeMap<Integer, Integer>();
		int current = 0;
		for (int i = 0; i < lines; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int add = Integer.parseInt(read.nextToken());
			String save = read.nextToken();
			if (save.equals("L"))
				current -= add;
			if (!Map.containsKey(current)) {
				Map.put(current, 1);
			} else {
				Map.put(current, Map.get(current) + 1);
			}
			current += add;

			if (!Map.containsKey(current)) {
				Map.put(current, -1);
			} else {
				Map.put(current, Map.get(current) - 1);
			}
			if (save.equals("L"))
				current -= add;
		}
		int answer = 0;
		int layers = 0;
		boolean flag = false;
		int start = 0;
		for (Entry<Integer, Integer> curr : Map.entrySet()) {
			layers += curr.getValue();
			if (layers >= 2 && !flag) {
				flag = true;
				start = curr.getKey();
			}
			if (layers <= 1 && flag) {
				answer += curr.getKey() - start ;
				flag = false;
			}
		}
		out.println(answer);
		in.close();
		out.close();
	}

}