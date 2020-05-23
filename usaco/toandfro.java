
import java.io.*;
import java.util.*;

public class toandfro {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for (int columns = Integer.parseInt(in.readLine()); columns != 0; columns = Integer.parseInt(in.readLine())) {
			char[] input = in.readLine().toCharArray();
			char[][] info = new char[input.length / columns][columns];
			int cnt = 0;
			for (int i = 0; i < input.length / columns; i++) {
				for (int k = 0; k < columns; k++) {
					if (i % 2 == 0)
						info[i][k] = input[cnt];
					else
						info[i][columns - 1 - k] = input[cnt];
					cnt++;
				}
			}
			StringBuilder ans = new StringBuilder();
			for (int k = 0; k < columns; k++) {
				for (int i = 0; i < input.length / columns; i++) {
					ans.append(info[i][k]);
				}
			}
			System.out.println(ans.toString());
		}
		in.close();

	}
}