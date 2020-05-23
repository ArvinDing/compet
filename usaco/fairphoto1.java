
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class fairphoto1 {
	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("fairphoto.in"));
		PrintWriter out = new PrintWriter(new File("fairphoto.out"));
		int lines = Integer.parseInt(in.readLine());

		TreeMap<Integer, Boolean> info = new TreeMap<Integer, Boolean>();
		for (int i = 0; i < lines; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int index = Integer.parseInt(read.nextToken());
			boolean type = read.nextToken().equals("W");
			info.put(index, type);

		}
		int[] save = new int[lines + 1];
		Iterator<Entry<Integer, Boolean>> temp = info.entrySet().iterator();
		int[] important = new int[lines];
		save[0] = 0;

		int index = 1;
		while (temp.hasNext()) {
			Entry<Integer, Boolean> curr = temp.next();

			important[index - 1] = curr.getKey();
			save[index] = save[index - 1];
			if (curr.getValue()) {
				save[index]++;
			} else {
				save[index]--;
			}
			index++;
		}
		int max = 0;

		System.out.println(System.currentTimeMillis());
		for (int i = 0; i < important.length; i++) {
			if (important[important.length - 1] - important[i] <= max) {
				break;
			}
			for (int k = important.length - 1; k > i; k--) {
				if (i + k % 2 == 0)
					continue;
				int tempAns = important[k] - important[i];
				if (tempAns <= max) {
					break;
				}

				if (save[k] >= save[i]) {
					max = Math.max(max, tempAns);
					break;
				}
			}
		}
		System.out.println(System.currentTimeMillis());
		out.println(max);
		in.close();
		out.close();
	}
}