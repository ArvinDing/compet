
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class crossroad {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("crossroad.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crossroad.out")));
		int observations = Integer.parseInt(in.readLine());
		int[] info = new int[11];

		int crossing = 0;
		for (int i = 0; i < observations; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int cow = Integer.parseInt(read.nextToken());
			int side = Integer.parseInt(read.nextToken());
			if (side == 0)
				side = -1;
			if (info[cow] == -side)
				crossing++;
			info[cow] = side;
		}
		out.println(crossing);
		in.close();
		out.close();
	}

}