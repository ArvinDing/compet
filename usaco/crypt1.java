
/*
ID: dingarv1
LANG: JAVA
TASK: crypt1
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

public class crypt1 {

	public static void main(String[] argv) throws Exception {
		 crypt1 main = new crypt1();
	        main.solve2();
	        System.exit(0);

	}
	public void solve1() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("crypt1.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("crypt1.out"));
        int N = Integer.parseInt(in.readLine());
        String strLine = in.readLine();
        strLine = strLine.replaceAll("\\s", "");
        int count = 0;
        for (int i = 100; i < 1000; i++) {
            if (checkString(Integer.toString(i), strLine) == false)
                continue;
            for (int j = 10; j < 100; j++) {
                if (checkString(Integer.toString(j), strLine) == false)
                    continue;
            
                String a = Integer.toString(j % 10 * i);
                String b = Integer.toString(j / 10 * i);
                String c = Integer.toString(j % 10 * i + (j / 10 * i) * 10);
                if (a.length() == 3 && b.length() == 3 && c.length() == 4
                        && checkString(a, strLine) && checkString(b, strLine)
                        && checkString(c, strLine))
                    count++;
            }
        }
        out.write("" + count + "\n");
        out.close();
    }

    public boolean checkString(String a, String b) {
        for (int i = 0; i < a.length(); i++) {
            if (b.contains("" + a.charAt(i)) == false)
                return false;
        }
        return true;
    }

    public void solve2() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("crypt1.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("crypt1.out"));
        int N = Integer.parseInt(in.readLine());
        String strLine = in.readLine();
        strLine = strLine.replaceAll("\\s", "");
        int count = 0;
        for (int a = 0; a < N; a++) {
            for (int b = 0; b < N; b++) {
                for (int c = 0; c < N; c++) {
                    int p = Integer.parseInt("" + strLine.charAt(a)
                            + strLine.charAt(b) + strLine.charAt(c));
                    for (int d = 0; d < N; d++) {
                        String tmp1 = Integer.toString(p
                                * (strLine.charAt(d) - '0'));
                        if (tmp1.length() != 3
                                || checkString(tmp1, strLine) == false)
                            continue;
                        for (int e = 0; e < N; e++) {
                            String tmp2 = Integer.toString(p
                                    * (strLine.charAt(e) - '0'));
                            if (tmp1.length() != 3
                                    || checkString(tmp2, strLine) == false)
                                continue;
                            String tmp3 = Integer.toString(p
                                    * (strLine.charAt(d) - '0' + 10 * (strLine
                                            .charAt(e) - '0')));
                            if (tmp3.length() == 4
                                    && checkString(tmp3, strLine))
                                count++;
                        }
                    }
                }
            }
        }
        out.write("" + count + "\n");
        out.close();
    }
}
