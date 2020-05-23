
/*
ID: dingarv1
LANG: JAVA
TASK: combo
*/

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class nocow {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("nocow.in"));
		PrintWriter out = new PrintWriter(new FileWriter("nocow.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int lines = Integer.parseInt(read.nextToken());
		int position = Integer.parseInt(read.nextToken()) - 1;
		List<List<String>> info = new ArrayList<List<String>>();

		String[] split = in.readLine().split(" ");
		int adjectives = split.length - 5;
		HashMap[] bInfo = new HashMap[adjectives];
		String[][] dont = new String[lines][adjectives];
		for (int k = 4; k < split.length - 1; k++) {
			dont[0][k - 4] = split[k];
			List<String> init = new ArrayList<String>();
			init.add(split[k]);
			info.add(init);

		}

		for (int i = 1; i < lines; i++) {
			split = in.readLine().split(" ");
			for (int k = 4; k < split.length - 1; k++) {
				dont[i][k - 4] = split[k];
				if (!info.get(k - 4).contains(split[k])) {
					info.get(k - 4).add(split[k]);
				}
			}
		}
		for (int i = 0; i < adjectives; i++) {
			bInfo[i] = new HashMap<String, Integer>();
		}
		for (int k = 0; k < info.size(); k++) {
			Collections.sort(info.get(k));
			List<String> a = info.get(k);

			int i = 0;

			for (String curr : a) {
				bInfo[k].put(curr, i);
				i++;

			}
		}
		int[] nAdjectives = new int[adjectives];
		int total = 1;
		int index = 0;
		for (List<String> a : info) {

			nAdjectives[index] = a.size();
			index++;
			total *= a.size();
		}
		int temp = total;
		TreeSet<Integer> dontIndex = new TreeSet<Integer>();
		for (int i = 0; i < lines; i++) {
			int sum = 0;
			temp = total;
			for (int k = 0; k < adjectives; k++) {
				temp /= nAdjectives[k];
				sum += ((int) bInfo[k].get(dont[i][k])) * temp;
			}
			dontIndex.add(sum);
		}
		for (int curr : dontIndex) {
			if (curr <= position) {
				position++;
			}
		}
		index = 0;
		temp = total;
		for (List<String> a : info) {
			if (index != 0)
				out.print(" ");
			temp /= nAdjectives[index];
			if (index == info.size() - 1)
				out.print(info.get(index).get(position));
			else
				out.print(info.get(index).get(position / temp));
			position = position % temp;
			index++;
		}
		out.println();
		in.close();
		out.close();
	}

}
