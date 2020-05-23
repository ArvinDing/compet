
import java.io.*;
import java.util.*;

public class gates {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("gates.in"));
		PrintWriter out = new PrintWriter(new File("gates.out"));
		int length = Integer.parseInt(in.readLine());
		String read = in.readLine();
		boolean[][] gates = new boolean[4005][4005];
		int x = 2000;
		int y = 2000;
		gates[y][x] = true;
		if (read.charAt(0) == 'N') {
			y += 1;
		} else if (read.charAt(0) == 'S') {
			y += -1;
		} else if (read.charAt(0) == 'E') {
			x += -1;
		} else {
			x += 1;
		}
		gates[y][x] = true;
		int maxY = y;
		int minY = y;
		int maxX = x;
		int minX = x;
		for (int i = 1; i < length; i++) {
			if (read.charAt(i) == 'N') {
				gates[y + 1][x] = true;
				y += 2;
			} else if (read.charAt(i) == 'S') {
				gates[y - 1][x] = true;
				y -= 2;
			} else if (read.charAt(i) == 'E') {
				gates[y][x - 1] = true;
				x -= 2;
			} else {
				gates[y][x + 1] = true;
				x += 2;
			}
			maxY = Math.max(maxY, y);
			minY = Math.min(minY, y);
			maxX = Math.max(maxX, x);
			minX = Math.min(minX, x);
			gates[y][x] = true;
		}
		boolean[][] info = compact(minY, maxY, maxX, minX, gates);
		for (int i = 0; i < info.length; i++) {
			for (int k = 0; k < info[0].length; k++) {
				if (info[i][k])
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
		int sections = 0;
		boolean[][] done = new boolean[info.length][info[0].length];
		for (int i = 0; i < done.length; i++) {
			for (int k = 0; k < done[0].length; k++) {
				if (!info[i][k]&& !done[i][k]) {
					LinkedList<Integer> yS = new LinkedList<Integer>();
					LinkedList<Integer> xS = new LinkedList<Integer>();
					yS.add(i);
					xS.add(k);
					sections++;
					done[i][k] = true;
					while (!xS.isEmpty()) {
						int currY = yS.poll();
						int currX = xS.poll();
						if (currY + 1 < info.length && !done[currY + 1][currX] && !info[currY + 1][currX]) {
							yS.add(currY + 1);
							xS.add(currX);
							done[currY + 1][currX] = true;
						}
						if (currX + 1 < info[0].length && !done[currY][currX + 1] && !info[currY][currX + 1]) {
							yS.add(currY);
							xS.add(currX + 1);
							done[currY][currX + 1] = true;
						}
						if (currY - 1 >= 0 && !done[currY - 1][currX] && !info[currY - 1][currX]) {
							yS.add(currY - 1);
							xS.add(currX);
							done[currY - 1][currX] = true;
						}
						if (currX - 1 >= 0 && !done[currY][currX - 1] && !info[currY][currX - 1]) {
							yS.add(currY);
							xS.add(currX - 1);
							done[currY][currX - 1] = true;
						}
					}
					for (int z = 0; z < info.length; z++) {
						for (int j = 0; j < info[0].length; j++) {
							if (done[z][j])
								System.out.print("#");
							else
								System.out.print(" ");
						}
						System.out.println();
					}
				}
			}
		}
		out.println(sections - 1);
		in.close();
		out.close();
	}

	private static boolean[][] compact(int minY, int maxY, int maxX, int minX, boolean[][] gates) {
		boolean[][] thing = new boolean[maxY - minY + 3][maxX - minX + 3];
		for (int i = minY; i <= maxY; i++) {
			for (int k = minX; k <= maxX; k++) {
				thing[i - minY + 1][k - minX + 1] = gates[i][k];
			}
		}
		return thing;
	}
}
