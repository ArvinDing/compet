import java.io.*;
import java.util.*;

public class costCut {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for(int i=1;i<=test;i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int[] save = new int[] { Integer.parseInt(read.nextToken()), Integer.parseInt(read.nextToken()),
					Integer.parseInt(read.nextToken()) };
			Arrays.sort(save);
			System.out.println("Case "+i+": "+save[1]);
		}
		in.close();
	}
}