
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class square {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("square.in"));
		PrintWriter out = new PrintWriter(new File("square.out"));
		int minX = 0;
		int minY = 0;
		int maxX = 0;
		int maxY = 0;
		StringTokenizer read = new StringTokenizer(in.readLine());
		int[] rect = new int[4];
		for (int i = 0; i < 4; i++) {
			rect[i] = Integer.parseInt(read.nextToken());
		}
		int[] rect1 = new int[4];
		read = new StringTokenizer(in.readLine());
		for (int i = 0; i < 4; i++) {
			rect1[i] = Integer.parseInt(read.nextToken());
		}
		minX=Math.min(rect[0], rect1[0]);
		minY=Math.min(rect[1], rect1[1]);
		maxX=Math.max(rect[2], rect1[2]);
		maxY=Math.max(rect[3], rect1[3]);
		int max=Math.max(maxX-minX, maxY-minY);
		out.println(max*max);
		in.close();
		out.close();

	}

}
