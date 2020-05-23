import java.util.Scanner;
import java.lang.Math;

public class daniel {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double a, b, end;
		double amax = -90, amin = 90, bmax = -180, bmin = 180;
		boolean done = false;
		while (!done) {
			System.out.println("Please enter the latitude:");
			a = sc.nextDouble();
			System.out.println("Please enter the longitude:");
			b = sc.nextDouble();
			if ((a < -90 || a > 90) || (b < -180 || b > 180)) {
				System.out.println("Incorrect Latitude or Longitude");
				continue;
			}
			amax = Math.max(amax, a);
			amin = Math.min(amin, a);
			bmax = Math.max(bmax, b);
			bmin = Math.min(bmin, b);
			System.out.println("Would you like to enter another location?");
			end = sc.nextInt();
			if (end == 0) {
				done = true;
			}
		}
		System.out.println("Farthest North: " + amax);
		System.out.println("Farthest South: " + amin);
		System.out.println("Farthest East: " + bmax);
		System.out.println("Farthest West: " + bmin);
		sc.close();
	}
}