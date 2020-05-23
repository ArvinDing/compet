
import java.io.*;
import java.util.*;

public class feynman {
	public static void main(String args[]) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int curr = -1;
		while ((curr = Integer.parseInt(in.readLine())) != 0) {

			int ans = 0;
			for (int z = 1; z <= curr; z++) {
				ans += z * z;
			}
			System.out.println(ans);
		}
		in.close();
	}
}
