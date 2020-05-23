
import java.io.*;
import java.util.*;

public class where {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("where.in"));
		PrintWriter out = new PrintWriter(new File("where.out"));
		int cnt = 0;
		int dimensions = Integer.parseInt(in.readLine());
		int[][] info = new int[dimensions][dimensions];
		for (int i = 0; i < dimensions; i++) {
			String read = in.readLine();
			for (int k = 0; k < dimensions; k++) {
				info[i][k] = read.charAt(k) - 65;
			}
		}

		boolean[][][][] checked = new boolean[dimensions][dimensions][dimensions][dimensions];
		for (int sum = 0; sum <= 2 * (dimensions - 1); sum++) {
			for (int beginX = 0; beginX <= sum; beginX++) {
				if (beginX >= dimensions || sum - beginX >= dimensions)
					continue;
				if (checked[beginX][sum - beginX][dimensions - 1][dimensions - 1])
					continue;
				int startX = beginX;
				int startY = sum - beginX;

				for (int eSum = 2 * (dimensions - 1); eSum >= 0; eSum--) {
					for (int endX = 0; endX <= eSum; endX++) {
						int endY = eSum - endX;
						if (endX >= dimensions || endY >= dimensions)
							continue;
						
						if (endX < startX || endY < startY || checked[startX][startY][endX][endY])
							continue;
						checked[startX][startY][endX][endY] = true;
						
						boolean[][] done = new boolean[dimensions][dimensions];
						HashMap<Integer, Integer> thing = new HashMap<Integer, Integer>();
						for (int y = startY; y <= endY; y++) {
							for (int x = startX; x <= endX; x++) {

								if (!done[y][x]) {
									int curr = info[y][x];
									if (!thing.containsKey(curr))
										thing.put(curr, 0);
									thing.put(curr, thing.get(curr) + 1);
									LinkedList<Integer> xCoords = new LinkedList<Integer>();
									LinkedList<Integer> yCoords = new LinkedList<Integer>();
									xCoords.add(x);
									yCoords.add(y);
									done[y][x] = true;
									while (!xCoords.isEmpty()) {
										int currX = xCoords.poll();
										int currY = yCoords.poll();

										if (currX - 1 >= startX && !done[currY][currX - 1]
												&& info[currY][currX - 1] == curr) {
											xCoords.add(currX - 1);
											yCoords.add(currY);
											done[currY][currX - 1] = true;
										}
										if (currY - 1 >= startY && !done[currY - 1][currX]
												&& info[currY - 1][currX] == curr) {
											xCoords.add(currX);
											yCoords.add(currY - 1);
											done[currY - 1][currX] = true;
										}
										if (currX + 1 <= endX && !done[currY][currX + 1]
												&& info[currY][currX + 1] == curr) {
											xCoords.add(currX + 1);
											yCoords.add(currY);
											done[currY][currX + 1] = true;
										}
										if (currY + 1 <= endY && !done[currY + 1][currX]
												&& info[currY + 1][currX] == curr) {
											xCoords.add(currX);
											yCoords.add(currY + 1);
											done[currY + 1][currX] = true;
										}
									}
								}
							}
						}
						Collection<Integer> name = thing.values();
						if (name.size() == 2) {
							boolean contigous = false;
							boolean multContigous = false;
							for (int a : name) {
								if (a == 1)
									contigous = true;
								else if (a >= 2) {
									multContigous = true;
								}
							}
							if (contigous && multContigous) {
								cnt++;
		//						System.out.println(startX + " " + startY + " " + endX + " " + endY);
								for (int eX = endX; eX >= startX; eX--) {
									for (int eY = endY; eY >= startY; eY--) {
										for (int sX = startX; sX <= eX; sX++) {
											for (int sY = startY; sY <= eY; sY++) {
												checked[sX][sY][eX][eY] = true;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		out.println(cnt);
		in.close();
		out.close();

	}

}