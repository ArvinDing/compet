
import java.io.*;
import java.util.*;

public class slimpify {
	public static void main(String[] args) throws Exception {

//		PrintWriter sIn = new PrintWriter(new FileWriter("C:\\Users\\Arvin\\Desktop\\c++\\snowcow.in", true));
//		PrintWriter mIn = new PrintWriter(new FileWriter("snowcow.in", true));
//		for (int z = 0; z < 95; z++) {
//			String line = "1 " + rand(1, 7) + " " + rand(1, 5);
//			sIn.println(line);
//			mIn.println(line);
//		}
//		for (int z = 0; z < 5; z++) {
//			String line = "2 " + (z + 1);
//			sIn.println(line);
//			mIn.println(line);
//		}
//		sIn.close();
//		mIn.close();
		snowcow.main(args);
		Runtime.getRuntime().exec("C:\\Users\\Arvin\\Desktop\\c++\\sol.exe", null,
				new File("C:\\Users\\Arvin\\Desktop\\c++\\"));
		BufferedReader sOut = new BufferedReader(new FileReader("C:\\Users\\Arvin\\Desktop\\c++\\snowcow.out"));
		BufferedReader mOut = new BufferedReader(new FileReader("snowcow.out"));
		String a = sOut.readLine();
		String b = mOut.readLine();

		while (a != null) {
			if (!a.equals(b)) {
				System.out.println("ahh");
				return;
			}
			a = sOut.readLine();
			b = mOut.readLine();
		}

		sOut.close();
		mOut.close();

	}

	public static int rand(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}
}
