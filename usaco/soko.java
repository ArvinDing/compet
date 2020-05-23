import java.io.*;
import java.util.*;

public class soko {
	public static void main(String[] args) throws Exception {
		// BufferedReader in = new BufferedReader(new
		// InputStreamReader(System.in));
		// StringTokenizer read = new StringTokenizer(in.readLine());
		int size = 5;// Integer.parseInt(read.nextToken());
		int moves = 0;// Integer.parseInt(read.nextToken());
		boolean[][] empty = new boolean[size][size];
		Random rand = new Random();
		int y = rand.nextInt(size);
		int x = rand.nextInt(size);
		empty[y][x] = true;
		LinkedList<int[]> spread = new LinkedList<int[]>();
		spread.add(new int[] { y, x });
		while (!spread.isEmpty()) {
			int[] curr = spread.poll();
			for (int yAdd = -1; yAdd <= 1; yAdd++) {
				for (int xAdd = -1; xAdd <= 1; xAdd++) {

					if ((yAdd == 0) ^ (xAdd == 0)) {
						rand = new Random();
					if (rand.nextInt(10) < 6)
						continue;
						if (curr[0] + yAdd < size && curr[0] + yAdd >= 0 && curr[1] + xAdd < size && curr[1] + xAdd >= 0
								&& !empty[curr[0] + yAdd][curr[1] + xAdd]) {
							spread.add(new int[] { curr[0] + yAdd, curr[1] + xAdd });
							empty[curr[0] + yAdd][curr[1] + xAdd] = true;
						}
					}
				}
			}
		}
		for (int i = 0; i < size; i++) {
			for (int k = 0; k < size; k++) {
				if (empty[i][k])
					System.out.print(' ');
				else
					System.out.print('#');
			}
			System.out.println();
		}
		// in.close();
	}
}
