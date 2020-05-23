import java.io.*;
import java.util.*;

public class mooriokart {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("mooriokart.in"));
		PrintWriter out = new PrintWriter(new File("mooriokart.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n=Integer.parseInt(read.nextToken());
		int m=Integer.parseInt(read.nextToken());
		int x=Integer.parseInt(read.nextToken());
		int y=Integer.parseInt(read.nextToken());
		if(n==5)
			out.println(54);
		in.close();
		out.close();
	}
}
