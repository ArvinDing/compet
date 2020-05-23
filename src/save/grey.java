package save;

import java.io.*;
import java.util.*;

public class grey {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for (int i = 0; i < test; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			read.nextToken();
			int temp = Integer.parseInt(read.nextToken()) ;
			System.out.println(temp ^ (temp >> 1));
		}
	}
}