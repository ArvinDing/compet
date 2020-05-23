
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.sound.midi.MidiDevice.Info;

public class censor {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("censor.in"));
		PrintWriter out = new PrintWriter(new File("censor.out"));
		StringBuilder last = new StringBuilder(in.readLine());
		StringBuilder first = new StringBuilder();
		String remove = in.readLine();
	
		for (int i = 0; i < last.length() - remove.length() + 1; i++) {
			if (last.substring(i, i + remove.length()).equals(remove)) {
				for (int k = remove.length() - 2; k >= 0; k--) {
					if (first.length() - (remove.length() - 1) + k < 0) {
						i += k + 1;
						break;
					}
					last.setCharAt(i + k + 1, first.charAt(first.length() - (remove.length() - 1) + k));
				}
				if (first.length() - (remove.length() - 1) < 0) {
					first.setLength(0);
				} else {
					first.setLength(first.length() - (remove.length() - 1));
				}
			} else {
				first.append(last.charAt(i));
			}

		}
		out.println(first + "" + last.substring(last.length() - remove.length() + 1));
		out.close();
		in.close();
	}
}