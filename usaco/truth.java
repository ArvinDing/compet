
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class truth {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("truth.in"));
		PrintWriter out = new PrintWriter(new File("truth.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		read.nextToken();
		int statements = Integer.parseInt(read.nextToken());
		List<HashMap<Integer, Boolean>> info = new ArrayList<HashMap<Integer, Boolean>>();
		name: {
			for (int i = 0; i < statements; i++) {
				read = new StringTokenizer(in.readLine());
				int x = Integer.parseInt(read.nextToken());
				int y = Integer.parseInt(read.nextToken());
				boolean truth = read.nextToken().equals("T");
				int xIndex = -1;
				int yIndex = -1;
				for (int k = 0; k < info.size(); k++) {
					if (info.get(k).containsKey(x)) {
						xIndex = k;
					}
					if (info.get(k).containsKey(y)) {
						yIndex = k;
					}
				}
				if (xIndex == -1 && yIndex == -1) {
					HashMap<Integer, Boolean> temp = new HashMap<Integer, Boolean>();
					if (truth) {
						temp.put(x, true);
						temp.put(y, true);
					} else {
						temp.put(x, true);
						temp.put(y, false);
					}
					info.add(temp);

				} else if (xIndex == -1) {
					boolean save = info.get(yIndex).get(y);
					if (truth) {
						info.get(yIndex).put(x, save);
					} else {
						info.get(yIndex).put(x, !save);
					}
				} else if (yIndex == -1) {
					boolean save = info.get(xIndex).get(x);
					if (truth) {
						info.get(xIndex).put(y, save);
					} else {
						info.get(xIndex).put(y, !save);
					}
				} else {
					boolean save = info.get(xIndex).get(x) == info.get(yIndex).get(y);
					if (xIndex == yIndex) {
						if (truth != save) {
							out.println(i);
							break name;
						}
					} else {
						if (save == truth) {
							info.get(xIndex).putAll(info.get(yIndex));
						} else {
							for (Entry<Integer, Boolean> a : info.get(yIndex).entrySet()) {
								info.get(xIndex).put(a.getKey(), !a.getValue());
							}
						}
						info.remove(yIndex);
					}
				}

			}
			out.println(statements);
		}
		in.close();
		out.close();
	}

}