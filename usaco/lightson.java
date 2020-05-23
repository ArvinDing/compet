import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class lightson {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("lightson.in"));
		PrintWriter out = new PrintWriter(new FileWriter("lightson.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int dimension = Integer.parseInt(read.nextToken());
		int lines = Integer.parseInt(read.nextToken());
		List<Integer>[][] xS = new List[dimension][dimension];
		List<Integer>[][] yS = new List[dimension][dimension];
		for (int i = 0; i < lines; i++) {
			read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken()) - 1;
			int y = Integer.parseInt(read.nextToken()) - 1;
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			if (xS[y][x] == null) {
				xS[y][x] = new ArrayList<Integer>();
			}
			if (yS[y][x] == null) {
				yS[y][x] = new ArrayList<Integer>();
			}
			xS[y][x].add(a);
			yS[y][x].add(b);
		}
		boolean[][] connected = new boolean[dimension][dimension];
		boolean[][] litUp = new boolean[dimension][dimension];

		LinkedList<Integer> xQueue = new LinkedList<Integer>();
		LinkedList<Integer> yQueue = new LinkedList<Integer>();
		xQueue.add(0);
		yQueue.add(0);
		litUp[0][0] = true;
		connected[0][0] = true;
		while (!xQueue.isEmpty()) {
			int x = xQueue.poll();
			int y = yQueue.poll();
			if (x - 1 >= 0 && !connected[y][x - 1] && litUp[y][x - 1]) {
				connected[y][x - 1] = true;
				xQueue.add(x - 1);
				yQueue.add(y);
			}
			if (y - 1 >= 0 && !connected[y - 1][x] && litUp[y - 1][x]) {
				connected[y - 1][x] = true;
				xQueue.add(x);
				yQueue.add(y - 1);
			}
			if (x + 1 < dimension && !connected[y][x + 1] && litUp[y][x + 1]) {
				connected[y][x + 1] = true;
				xQueue.add(x + 1);
				yQueue.add(y);
			}
			if (y + 1 < dimension && !connected[y + 1][x] && litUp[y + 1][x]) {
				connected[y + 1][x] = true;
				xQueue.add(x) ;
				yQueue.add(y+1);
			}
			if (xS[y][x] != null) {
				for (int i = 0; i < xS[y][x].size(); i++) {
					int targetX = xS[y][x].get(i);
					int targetY = yS[y][x].get(i);
					litUp[targetY][targetX] = true;
					if (!connected[targetY][targetX] && neighborLit(connected, targetX, targetY)) {
						connected[targetY][targetX] = true;
						xQueue.add(targetX);
						yQueue.add(targetY);
					}
				}
			}
		}
		int cnt = 0;
		for (int i = 0; i < dimension; i++) {
			for (int k = 0; k < dimension; k++) {
				if (litUp[i][k])
					cnt++;
			}
		}
		out.println(cnt);
		out.close();
		in.close();

	}

	private static boolean neighborLit(boolean[][] connected, int x, int y) {
		if (x - 1 >= 0 && connected[y][x - 1]) {
			return true;
		}
		if (y - 1 >= 0 && connected[y - 1][x]) {
			return true;
		}
		if (x + 1 < connected.length && connected[y][x + 1]) {
			return true;
		}
		if (y + 1 < connected.length && connected[y + 1][x]) {
			return true;
		}
		return false;
	}

}
