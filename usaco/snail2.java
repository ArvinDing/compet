
import java.io.*;
import java.util.*;

public class snail2 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int well = Integer.parseInt(read.nextToken());
			if (well == 0)
				break;
			double power = Integer.parseInt(read.nextToken());
			int slide = Integer.parseInt(read.nextToken());
			double fatigue = (Double.parseDouble(read.nextToken()) / 100)*power;
			double height = 0;
			int day = 0;
			while ((0<= height) && height <= well) {
				day++;
				height += Math.max(0,power);
				if (height > well) {
					break;
				}
				height -= slide;
				power -=  fatigue;
			}
			System.out.println(((height < 0) ? "failure " : "success ") + "on day " + day);
		}
		in.close();
	}
}