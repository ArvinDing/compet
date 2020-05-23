
/*
ID: dingarv1
LANG: JAVA
TASK: skidesign
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class skidesign {

	  public static void main(String[] args) throws IOException {
	        Scanner in = new Scanner(new File("skidesign.in"));
	        PrintWriter out = new PrintWriter(new FileWriter("skidesign.out"));

	        int N = in.nextInt();
	        int[] hills = new int[N];
	        for (int i = 0; i < N; i++) {
	            in.nextLine();
	            hills[i] = in.nextInt();
	        }
	        int sum = Integer.MAX_VALUE;
	        for (int min = 0; min <= 100; min++) {
	            int tmpSum = 0;
	            for (int i = 0; i < N; i++) {
	                int tmp = hills[i] - min;
	                if (tmp > 17) {
	                    tmp -= 17;
	                    tmpSum += tmp * tmp;
	                } else if (tmp < 0) {
	                    tmpSum += tmp * tmp;
	                }
	            }
	            sum = Math.min(sum, tmpSum);
	        }
	        out.println(sum);

	        in.close();
	        out.close();
	        System.exit(0);
	    }

	   

}
