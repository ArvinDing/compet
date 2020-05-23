
import java.io.*;
import java.util.*;

public class ballet {
	private static int rotationX = 0;
	private static int rotationY = 0;
	private static int minX = 0;
	private static int minY = 0;
	private static int maxX = 1;
	private static int maxY = 1;
	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new FileReader("ballet.in"));
		PrintWriter out = new PrintWriter(new FileWriter("ballet.out"));
		int lines = Integer.parseInt(in.readLine());
		position FR = new position(1, 1);
		position FL = new position(0, 1);
		position RR = new position(1, 0);
		position RL = new position(0, 0);
		int[][] direction = new int[][] { new int[] { 0, 1 }, new int[] { 1, 0 }, new int[] { 0, -1 },
				new int[] { -1, 0 } };
		HashMap<Character, Integer> link = new HashMap<Character, Integer>();
		link.put('F', 0);
		link.put('R', 1);
		link.put('B', 2);
		link.put('L', 3);
		int currDirect = 0;
		
		name: {
			for (int i = 0; i < lines; i++) {
				String read = in.readLine();
				String save = read.substring(0, 2);
				Character instruction = read.charAt(2);
				if (instruction == 'P') {

					currDirect++;
					currDirect %= 4;
					switch (save) {
					case "FR":
						rotationX = FR.x;
						rotationY = FR.y;
						break;
					case "FL":
						rotationX = FL.x;
						rotationY = FL.y;
						break;
					case "RR":
						rotationX = RR.x;
						rotationY = RR.y;
						break;
					case "RL":
						rotationX = RL.x;
						rotationY = RL.y;
						break;
					}
				FR=	rotate(FR);
				FL=	rotate(FL);
				RR=	rotate(RR);
				RL=	rotate(RL);

				} else {
					int x = 0;
					int y = 0;
					switch (save) {
					case "FR":
						FR.x += direction[(currDirect + link.get(instruction))%4][0];
						FR.y += direction[(currDirect + link.get(instruction))%4][1];
						x = FR.x;
						y = FR.y;
						break;
					case "FL":
						FL.x += direction[(currDirect + link.get(instruction))%4][0];
						FL.y += direction[(currDirect + link.get(instruction))%4][1];
						x = FL.x;
						y = FL.y;
						break;
					case "RL":
						RL.x += direction[(currDirect + link.get(instruction))%4][0];
						RL.y += direction[(currDirect + link.get(instruction))%4][1];
						x = RL.x;
						y = RL.y;
						break;
					case "RR":
						RR.x += direction[(currDirect + link.get(instruction))%4][0];
						RR.y += direction[(currDirect + link.get(instruction))%4][1];
						x = RR.x;
						y = RR.y;
						break;

					}
					int cnt = 0;
					if (FR.x == x && FR.y == y)
						cnt++;
					if (FL.x == x && FL.y == y)
						cnt++;
					if (RL.x == x && RL.y == y)
						cnt++;
					if (RR.x == x && RR.y == y)
						cnt++;
					if (cnt >= 2) {
						out.println(-1);
						break name;
					}
					minX = Math.min(minX, x);
					minY = Math.min(minY, y);
					maxY = Math.max(maxY, y);
					maxX = Math.max(maxX, x);
				}
			}
			out.println((maxX - minX + 1) * (maxY - minY + 1));
		}
		out.close();
		in.close();
	}

	private static position rotate(position point) {
		int x = point.x;
		int y = point.y;
		point = new position(rotationX - (rotationY - y), rotationY + (rotationX - x));
		x=point.x;
		y=point.y;
		minX = Math.min(minX, x);
		minY = Math.min(minY, y);
		maxY = Math.max(maxY, y);
		maxX = Math.max(maxX, x);
		return point;
	}

	private static class position {
		int x;
		int y;

		private position(int x, int y) {
			this.x = x;
			this.y = y;
		}


	}

}