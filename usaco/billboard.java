
import java.io.*;
import java.util.*;

public class billboard {
	private static int cleft;
	private static int cbottom;
	private static int cright;
	private static int ctop;

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("billboard.in"));
		PrintWriter out = new PrintWriter(new File("billboard.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int leftX = Integer.parseInt(read.nextToken());
		int bottomY = Integer.parseInt(read.nextToken());
		int rightX = Integer.parseInt(read.nextToken());
		int topY = Integer.parseInt(read.nextToken());
		read = new StringTokenizer(in.readLine());
		int leftX1 = Integer.parseInt(read.nextToken());
		int bottomY1 = Integer.parseInt(read.nextToken());
		int rightX1 = Integer.parseInt(read.nextToken());
		int topY1 = Integer.parseInt(read.nextToken());
		read = new StringTokenizer(in.readLine());
		cleft = Integer.parseInt(read.nextToken());
		cbottom = Integer.parseInt(read.nextToken());
		cright = Integer.parseInt(read.nextToken());
		ctop = Integer.parseInt(read.nextToken());
		int area = (rightX - leftX) * (topY - bottomY);
		int area1 = (rightX1 - leftX1) * (topY1 - bottomY1);
		out.println(modified(area, leftX, bottomY, rightX, topY) + modified(area1, leftX1, bottomY1, rightX1, topY1));
		in.close();
		out.close();
	}

	private static int modified(int area, int leftX, int bottomY, int rightX, int topY) {
		if (cleft >= rightX)
			return area;
		if (cright <= leftX)
			return area;
		if (ctop <= bottomY)
			return area;
		if (cbottom >= topY)
			return area;
		int saveX;
		if (leftX <= cleft && rightX >= cright) {
			saveX = cright - cleft;
		} else if (leftX > cleft && rightX < cright) {
			saveX = rightX - leftX;
		} else {
			saveX = Math.min(cright - leftX, rightX - cleft);
		}
		int saveY;
		if (bottomY <= cbottom && topY >= ctop) {
			saveY = ctop - cbottom;
		} else if (bottomY > cbottom && topY < ctop) {
			saveY = topY - bottomY;
		} else {
			saveY = Math.min(ctop - bottomY, topY - cbottom);
		}
		return area - (saveX * saveY);

	}
}