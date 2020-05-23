import java.io.*;
import java.util.*;

public class blocked {
	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("billboard.in"));
		PrintWriter out = new PrintWriter(new File("billboard.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int lowX = Integer.parseInt(read.nextToken());
		int lowY = Integer.parseInt(read.nextToken());
		int highX = Integer.parseInt(read.nextToken());
		int highY = Integer.parseInt(read.nextToken());
		read = new StringTokenizer(in.readLine());
		int lowX1 = Integer.parseInt(read.nextToken());
		int lowY1 = Integer.parseInt(read.nextToken());
		int highX1 = Integer.parseInt(read.nextToken());
		int highY1 = Integer.parseInt(read.nextToken());
		int minX = lowX;
		int minY = lowY;
		int maxX = highX;
		int maxY = highY;
		if (lowX1 <= lowX && lowX < highX1) {
			if (lowY1 <= lowY && highY <= highY1) {
				minX = highX1;
			}
		}
		if (lowX1 < highX && highX <= highX1) {
			if (lowY1 <= lowY && highY <= highY1) {
				maxX = lowX1;
			}
		}
		if (lowY1 <= lowY && lowY < highY1) {
			if (lowX1 <= lowX && highX <= highX1) {
				minY = highY1;
			}
		}
		if (lowY1 < highY && highY <= highY1) {
			if (lowX1 <= lowX && highX <= highX1) {
				maxY = lowY1;
			}
		}
		if (maxX <= minX || maxY <= minY) {
			out.println(0);
		} else {
			out.println((maxX - minX) * (maxY - minY));
		}
		in.close();
		out.close();
	}

}
