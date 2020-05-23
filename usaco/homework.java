
import java.io.*;
import java.util.*;

public class homework {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("homework.in"));
		PrintWriter out = new PrintWriter(new File("homework.out"));
		int questions = Integer.parseInt(in.readLine());
		long sum = 0;
		int[] info = new int[questions];
		StringTokenizer read = new StringTokenizer(in.readLine());
		int[] lowest = new int[10001];
		for (int i = 0; i < questions; i++) {
			info[i] = Integer.parseInt(read.nextToken());
			lowest[info[i]]++;
			sum += info[i];
		}
		TreeMap<Integer, Integer> easier = new TreeMap<Integer, Integer>();
		for (int i = 0; i < 10001; i++) {
			if (lowest[i] != 0)
				easier.put(i, lowest[i]);
		}

		double[] save = new double[questions];
		double max = -1;
		for (int i = 1; i <= questions - 2; i++) {
			sum -= info[i - 1];
			int howManyLeft = easier.get(info[i - 1]);
			if (howManyLeft == 1) {
				easier.remove(info[i - 1]);
			} else {
				easier.put(info[i - 1], howManyLeft--);
			}

			save[i] = (double)(sum - easier.firstKey()) / (questions - i - 1);
			max= Math.max(max, save[i]);
		}
		for (int i = 1; i <= questions - 2; i++) {
			if(save[i]==max)out.println(i);
		}
		out.close();
		in.close();
	}
}