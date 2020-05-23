
import java.io.*;
import java.util.*;

public class maxpermTri {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] l = new int[n];
		for (int l_i = 0; l_i < n; l_i++) {
			l[l_i] = in.nextInt();
		}
		Arrays.sort(l);
		int i = l.length - 3;
		while (i != -1 && l[i] + l[i + 1] <= l[i + 2])
			i--;
		if (i == -1)
			System.out.println(-1);
		else
			System.out.println(l[i] + " " + l[i + 1] + " " + l[i + 2]);

		in.close();
	}

}
