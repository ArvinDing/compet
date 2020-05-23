package Test;

import java.io.*;
import java.util.*;

public class math {
	public static void main(String[] args) throws Exception {
		choose=new long [11][11];
		choose[0][0] = 1;
		long[] derrange = new long[11];
		derrange[0] = 1;
		long[] factorial = new long[11];
		factorial[0] = 1;
		for (int i = 1; i < 11; i++) {
			factorial[i] = factorial[i - 1] * i;
		}
		for (int i = 1; i < 11; i++) {
			long sum = factorial[i];
			for (int k = 1; k <= i; k++) {
				sum -= derrange[i-k] * c(i, k);
			}
			derrange[i]=sum;
			System.out.println(i + " " + ((double) sum) / factorial[i]+ " "+sum);
		}
	}

	public static long[][] choose;

	public static long c(int n, int k) {
		if (k == 0)
			return 1;
		if(n<k)
			return 0;
		if (choose[n][k] != 0)
			return choose[n][k];
		choose[n][k] = c(n - 1,k) + c(n - 1,k - 1);
		return choose[n][k];
	}
}
