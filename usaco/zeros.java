
import java.io.*;
import java.util.*;

public class zeros {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int lines = Integer.parseInt(in.readLine());
		for (int i = 0; i < lines; i++) {
			int curr = Integer.parseInt(in.readLine());
			int cnt = 0;
			int mult = 5;
			while (mult <= curr) {
				cnt += (curr / mult);
				mult *= 5;
			}
			System.out.println(cnt);
		}
	}
}