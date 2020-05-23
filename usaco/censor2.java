
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.LinkedList;

public class censor2 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("censor.in"));
		PrintWriter out = new PrintWriter(new File("censor.out"));
		StringBuilder info = new StringBuilder(in.readLine());
		char[] remove = in.readLine().toCharArray();
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(0);
		int index = 0;
		while (true) {
			if (index == info.length())
				break;
			int previous = queue.peekLast();
			if (info.charAt(index) == remove[previous]) {
				queue.pollLast();
				if (previous + 1 == remove.length) {
					info.delete(index - remove.length, index);
					index -= remove.length;
					if (index < 0) {
						index = -1;
					}
					index++;
					continue;
				}
				queue.add(previous + 1);
			} else {
				if (info.charAt(index) == remove[0]) {
					queue.add(1);
				} else {
					if (!queue.isEmpty()) {
						queue.clear();
					}
					queue.add(0);
				}
			}
			index++;

		}
		out.println(info.toString());
		out.close();
		in.close();
	}
}