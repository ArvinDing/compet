
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class cowroute {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cowroute.in"));
		PrintWriter out = new PrintWriter(new File("cowroute.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int start = Integer.parseInt(read.nextToken());
		int end = Integer.parseInt(read.nextToken());
		int routes = Integer.parseInt(read.nextToken());
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < routes; i++) {
			read = new StringTokenizer(in.readLine());
			int cost = Integer.parseInt(read.nextToken());
			int cities = Integer.parseInt(read.nextToken());
			read = new StringTokenizer(in.readLine());
			boolean flag = false;
			for (int k = 0; k < cities; k++) {
				int city = Integer.parseInt(read.nextToken());
				if (city == start) {
					flag = true;
				}
				if (city == end) {
					if (flag) {
						min = Math.min(cost, min);
					} else {
						break;
					}
				}
			}
		}
		if(min==Integer.MAX_VALUE){
			out.println(-1);
		}else{
		out.println(min);
		}
		out.close();
		in.close();
	}
}