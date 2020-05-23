
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class citystate {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("citystate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("citystate.out")));

		int [][][][] a = new int [26][26][26][26];
		int size = Integer.parseInt(in.readLine());
		int cnt = 0;
		for (int i = 0; i < size; i++) {
			StringTokenizer stk = new StringTokenizer(in.readLine());
			String[] read = new String[] {stk.nextToken(), stk.nextToken()};
			int a0 = read[0].charAt(0) - 'A';
			int a1 = read[0].charAt(1) - 'A';
			int b0 = read[1].charAt(0) - 'A';
			int b1 = read[1].charAt(1) - 'A';
						
			a[a0][a1][b0][b1] ++;
			if (a[b0][b1][a0][a1]!=0 && !(a0 == b0 && a1 == b1)) {
				cnt+=a[b0][b1][a0][a1];
//				a[a0][a1][b0][b1]=false;
//				a[b0][b1][a0][a1]=false;
			}

		}
		out.println(cnt);
		in.close();
		out.close();
	}

}