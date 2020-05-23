
import java.io.*;
import java.util.*;

public class reduce {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("reduce.in"));
		PrintWriter out = new PrintWriter(new File("reduce.out"));
		int minX = Integer.MAX_VALUE;
		int secondMinX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int secondMinY = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int secondMaxX =Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;
		int secondMaxY = Integer.MIN_VALUE;
		int lines = Integer.parseInt(in.readLine());
		List<Integer> xInfo = new ArrayList<Integer>();                                       
		List<Integer> yInfo = new ArrayList<Integer>();
		for (int i = 0; i < lines; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(read.nextToken());
			if (x <= minX) {
				secondMinX = minX;
				minX = x;
			}else if (x<secondMinX){
				secondMinX=x;
			}
			
			if (x >= maxX) {
				secondMaxX = maxX;
				maxX = x;
			}else if (x>secondMaxX){
				secondMaxX=x;
			}
			if (y <= minY) {
				secondMinY = minY;
				minY = y;
			}else if (y<secondMinY){
				secondMinY=y;
			}

			if (y >= maxY) {
				secondMaxY = maxY;
				maxY = y;
			}else if (y>secondMaxY){
				secondMaxY=y;
			}
			xInfo.add(x);
			yInfo.add(y);

		}
		int min = (maxY - minY) * (maxX - minX);
		for (int i = 0; i < lines; i++) {
			int removeX = xInfo.get(i);
			int removeY = yInfo.get(i);
			int currentMinX = minX;
			int currentMinY = minY;
			int currentMaxX = maxX;
			int currentMaxY = maxY;
			boolean change = false;
			if (removeX == minX) {
				currentMinX = secondMinX;
				change = true;
			}
			if (removeX == maxX) {
				currentMaxX = secondMaxX;
				change = true;
			}
			if (removeY == minY) {
				currentMinY = secondMinY;
				change = true;
			}
			if (removeY == maxY) {
				currentMaxY = secondMaxY;
				change = true;
			}
			if (change) {
				min = Math.min(min, (currentMaxY - currentMinY) * (currentMaxX - currentMinX));
			}
		}
		out.println(min);
		in.close();
		out.close();
	}
}
