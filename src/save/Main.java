package save;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for (String nString = in.readLine(); !nString.equals("0"); nString = in.readLine()) {
			int n = Integer.parseInt(nString);
			for (String line = in.readLine(); !line.equals("0"); line = in.readLine()) {
				StringTokenizer read = new StringTokenizer(line);
				Deque<Integer> info = new ArrayDeque<Integer>();
				for (int i = 0; i < n; i++)
					info.add(Integer.parseInt(read.nextToken()));
				Stack<Integer> simulation = new Stack<Integer>();
				for (int i = 1; i <= n; i++) {
					simulation.push(i);
					while (info.peek().equals( simulation.peek())) {
						info.pop();
						simulation.pop();
						if (info.isEmpty() || simulation.isEmpty())
							break;
					}
					if (info.isEmpty())
						break;
				}
				System.out.println((info.isEmpty()) ? "Yes" : "No");
			}
			System.out.println();
		}
		in.close();
	}
}
