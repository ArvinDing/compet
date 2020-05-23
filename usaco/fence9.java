
/*
ID: dingarv1
LANG: JAVA
TASK: fence9
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class fence9 {

	public static void main(String[] argv) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("fence9.in"));
		PrintWriter out = new PrintWriter(new File("fence9.out"));
		String[] noname = in.readLine().split(" ");
		int n=Integer.parseInt(noname[0]);
		int m=Integer.parseInt(noname[1]);
		int p=Integer.parseInt(noname[2]);
		int A=(m*p)/2;
		int B=gcd(p, 0)-1 ;
		B+=gcd(n,m)-1;
		B+=gcd(Math.abs(n-p),Math.abs(m-0))-1;
		out.println(A-((B+3)/2)+1);
		out.close();
		in.close();
	}

	private static int gcd(int a, int b) {
		 if (b == 0)
		       return a;
		    return gcd(b, a%b);
	}
}
