
import java.io.*;
import java.util.*;

public class substringCheck {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 24; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			String a = read.nextToken();
			String b = read.nextToken();
			if (a.contains(b))
				System.out.println(1);
			else
				System.out.println(0);
		}
	}

}