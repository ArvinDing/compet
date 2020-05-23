
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class promote {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("promote.in"));
		PrintWriter out = new PrintWriter(new File("promote.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int bronze = Integer.parseInt(read.nextToken());
		int bronzeOut = Integer.parseInt(read.nextToken());
		read = new StringTokenizer(in.readLine());
		int silver = Integer.parseInt(read.nextToken());
		int silverOut = Integer.parseInt(read.nextToken());
		read = new StringTokenizer(in.readLine());
		int gold = Integer.parseInt(read.nextToken());
		int goldOut = Integer.parseInt(read.nextToken());
		read = new StringTokenizer(in.readLine());
		int plantinum = Integer.parseInt(read.nextToken());
		int plantinumOut = Integer.parseInt(read.nextToken());
		out.println(silverOut - silver + (goldOut - gold + (plantinumOut - plantinum)));
		out.println(goldOut - gold + (plantinumOut - plantinum));
		out.println(plantinumOut - plantinum);

		in.close();
		out.close();
	}

}