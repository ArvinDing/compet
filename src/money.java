import java.text.DecimalFormat;
import java.util.Scanner;

public class money {

	public static void main(String[] args) {
		int nickels;
		int dimes;
		int quarters;
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the number of nickels, dimes, and quarters in the following format: (n,d,q)");
		String[] counts = scan.nextLine().split(",");
		nickels = Integer.parseInt(counts[0]);
		dimes = Integer.parseInt(counts[1]);
		quarters = Integer.parseInt(counts[2]);
		double result = (0.25 * quarters) + (0.1 * dimes) + (0.05 * nickels);
		DecimalFormat display = new DecimalFormat("$#.##");
		System.out.println("the total is: " + display.format(result));
	}

}