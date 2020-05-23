
import java.io.*;
import java.util.*;

public class castle1 {
	private static int y;
	private static int x;
	private static int rooms;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("castle.in"));
		PrintWriter out = new PrintWriter(new File("castle.out"));
		y = Integer.parseInt(in.readLine());
		x = Integer.parseInt(in.readLine());
		boolean[][] info = new boolean[y + y + 1][x + x + 1];
		for (int i = 1; i < y + y + 1; i += 2) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			for (int k = 1; k < x + x + 1; k += 2) {
				int curr = Integer.parseInt(read.nextToken());
				if ((1 & curr) == 1)
					info[i][k - 1] = true;
				if ((2 & curr) == 2)
					info[i - 1][k] = true;
				if ((4 & curr) == 4)
					info[i][k + 1] = true;
				if ((8 & curr) == 8)
					info[i + 1][k] = true;
			}
		}
		
		int bestX = -1;
		int bestY = -1;
		char direction = ' ';
		int bestRooms = easier(info);
		int roomsSize = rooms;
		int maxOfMax = 0;
		for (int u = 1; u < y + y; u++) {
			for (int o = 1; o < x + x; o++) {

				if (info[u][o]) {

					info[u][o] = false;
					int curr = easier(info);
					info[u][o] = true;
					if (maxOfMax < curr) {
						maxOfMax=curr;
						if (o % 2 == 1) {
							direction = 'N';
							bestY = ((u) / 2) + 1;
							bestX = (o+1 )/ 2;
						} else {
							direction = 'E';
							bestX = (o / 2);
							bestY = (u + 1) / 2;
						}
					}

				}
			}
		}
		out.println(roomsSize);
		out.println(bestRooms);
		out.print(bestY + " " + bestX + " " + direction);
		in.close();
		out.close();
	}

	private static int easier(boolean[][] info) {
		int max = 0;
		rooms = 0;
		boolean[][] done = new boolean[y + y + 1][x + x + 1];
		for (int i = 1; i < y + y + 1; i += 2) {
			for (int k = 1; k < x + x + 1; k += 2) {
				if (!done[i][k]) {
					done[i][k] = true;
					LinkedList<Integer> yS = new LinkedList<Integer>();
					LinkedList<Integer> xS = new LinkedList<Integer>();
					yS.add(i);
					xS.add(k);
					int cnt = 0;
					rooms++;
					while (!yS.isEmpty()) {
						int currY = yS.poll();
						int currX = xS.poll();
						cnt++;
						if (currX + 2 < x + x + 1 && !info[currY][currX + 1] && !done[currY][currX + 2]) {
							xS.add(currX + 2);
							yS.add(currY);
							done[currY][currX + 2] = true;
						}
						if (currX - 2 >= 0 && !info[currY][currX - 1] && !done[currY][currX - 2]) {
							xS.add(currX - 2);
							yS.add(currY);
							done[currY][currX - 2] = true;
						}
						if (currY + 2 < y + y + 1 && !info[currY + 1][currX] && !done[currY + 2][currX]) {
							yS.add(currY + 2);
							xS.add(currX);
							done[currY + 2][currX] = true;
						}
						if (currY - 2 >= 0 && !info[currY - 1][currX] && !done[currY - 2][currX]) {
							yS.add(currY - 2);
							xS.add(currX);
							done[currY - 2][currX] = true;
						}
					}
					max = Math.max(max, cnt);
				}
			}
		}
		return max;
	}

}