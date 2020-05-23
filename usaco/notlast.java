
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class notlast {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("notlast.in"));
		PrintWriter out = new PrintWriter(new File("notlast.out"));
		int lines = Integer.parseInt(in.readLine());
		HashMap<String, Integer> info = new HashMap<String, Integer>();
		for (int i = 0; i < lines; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			String name = read.nextToken();
			if (!info.containsKey(name)) {
				info.put(name, 0);
			}
			info.put(name, info.get(name) + Integer.parseInt(read.nextToken()));
		}
		TreeMap<Integer, List<String>> newIdea = new TreeMap<Integer, List<String>>();
		for (Entry<String, Integer> curr : info.entrySet()) {
			if (!newIdea.containsKey(curr.getValue())) {
				newIdea.put(curr.getValue(), new ArrayList<String>());
			}
			newIdea.get(curr.getValue()).add(curr.getKey());
		}
		outer: {
			boolean thing = false;
			for (Entry<Integer, List<String>> curr : newIdea.entrySet()) {
				if (thing) {
					if (curr.getValue().size() != 1) {
						out.println("Tie");
					} else {
						out.print(curr.getValue().get(0));
					}
					break outer;
				}
				thing = true;
			}
			out.println("Tie");
		}
		in.close();
		out.close();

	}

}
