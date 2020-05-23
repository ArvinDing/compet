import java.io.*;
import java.util.*;

public class generate {
	public static void main(String[] args) throws Exception {
		PrintWriter out = new PrintWriter(new File("sprinklers2.in"));
		int n = 200;
		out.println(n );
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
					out.print('.');
			}
			out.println();
		}
		out.close();

	}
}
