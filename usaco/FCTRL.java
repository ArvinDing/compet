

import java.io.*;
import java.util.*;

public class FCTRL {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int cnt = Integer.parseInt(in.readLine());
		for (int i = 0; i < cnt; i++) {
			System.out.println(zero(Integer.parseInt(in.readLine())));
		}

		in.close();
	}

	private static int zero(int parseInt) {
		int cnttwo = 0;
		int cntfive = 0;
		for (int i = 1; i < parseInt; i++) {
			while (i % 5 == 0) {
				cntfive++;
				i %= 5;
			}
			while (i % 2 == 0) {
				cnttwo++;
				i %= 2;
			}
		}
		return Math.min(cnttwo, cntfive);

	}
}
