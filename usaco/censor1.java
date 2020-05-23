
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class censor1 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("censor.in"));
		PrintWriter out = new PrintWriter(new File("censor.out"));
		String temp = new String(in.readLine());
		char[] info = temp.toCharArray();
		temp = in.readLine();
		char[] remove = temp.toCharArray();
		int i = 0;
		while (i < info.length - remove.length + 1) {

			if (compareChars(i, info, remove)) {
			
				int differ = 0;
				for (int k = i; k < i + remove.length; k++) {
					while (info[k + differ] == ' ') {
						differ++;
					}
					info[k + differ] = ' ';
				}

				int cnt = 0;

				while (cnt <= remove.length) {
					i--;
					if (i == -1)break;
					if (info[i] != ' ') {
						cnt++;
					}
				}

				if (i < 0) {
					i = -1;
				}
			}
			i++;
		}
		for (char a : info) {
			if (a == ' ')
				continue;
			out.print(a);
		}

		out.println();
		out.close();
		in.close();
	}

	private static boolean compareChars(int index, char[] info, char[] remove) {
		int index1 = index;
		int index2 = 0;
		while (index2 < remove.length) {
			if (index1 == info.length) {
				return false;
			}

			while (info[index1] == ' ') {
				index1++;
				if (index1 == info.length) {
					return false;
				}
			}

			if (info[index1] != remove[index2]) {
				return false;
			}
			index1++;
			index2++;
		}
		return true;
	}
}