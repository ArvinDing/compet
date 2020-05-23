
/*
ID: dingarv1
LANG: JAVA
TASK: spin
*/

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class spin {

	public static void main(String[] args) throws Exception {

		Scanner in = new Scanner(new FileReader("spin.in"));
		PrintWriter out = new PrintWriter(new File("spin.out"));
		wheel[] wheels = new wheel[5];
		for (int i = 0; i < 5; i++) {
			wheel a = new wheel();
			a.speed = in.nextInt();
			a.gaps = new boolean[360];
			int numbers = in.nextInt();
			for (int k = 0; k < numbers; k++) {
				int z = in.nextInt();
				int end = in.nextInt();

				for (int y = z; y < z + end + 1; y++) {
					a.gaps[y % 360] = true;

				}
			}
			wheels[i] = a;
		}
		int speed;

		boolean[] gaps;
		abc: {
			for (int seconds = 0; seconds < 360; seconds++) {
				for (int degree = 0; degree < 360; degree++) {
					boolean through = true;

					for (int i = 0; i < 5; i++) {
						wheel t = wheels[i];
						gaps = t.gaps;
						if (through == true) {
							if (!gaps[degree]) {
								through = false;
								break;
							}
						}
					}
					if (through == true) {
						out.println(seconds);
						break abc;
					}

				}
				for (int i = 0; i < 5; i++) {
					wheel t = wheels[i];
					speed = t.speed;
					gaps = t.gaps;
					
					boolean[]easy=new boolean[360];
					for (int o = 0; o < 360; o++) {
						if (gaps[o]) {
							easy[(o + speed) % 360] = true;
							
							
						}
					}
					t.gaps=easy;

				}
				
			}
			out.println("none");
		}
		in.close();
		out.close();

	}

	public static class wheel {
		int speed;
		boolean[] gaps;
	}
}
