
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class censor_ref {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("censor.in"));
		PrintWriter out = new PrintWriter(new File("censor.out"));
		StringBuilder info = new StringBuilder(in.readLine());
		String remove = in.readLine();
		for (int i = 0; i < info.length() - remove.length() + 1; i++) {
				if (info.substring(i, i + remove.length()).equals(remove)) {
				
					info.delete(i, i + remove.length());
					i -= remove.length();
					if (i < 0) {
						i = -1;
					}
				}
		}
		out.println(info);
		out.close();
		in.close();
	}
}