package Test;

import java.io.*;
import java.util.*;

public class expected {
	public static void main(String[] args) throws Exception {
		long sum=0;
		long value=0;
		for(int i=1;i<=90;i++) {
			sum+=choose3(i-1)*(90-i);
			value+=i*choose3(i-1)*(90-i);
		}
		System.out.println((double)value/sum);
	}

	public static long choose3(int n) {
		if (n < 3)
			return 0;
		return n * (n - 1) * (n - 2) / 6;
	}
}
