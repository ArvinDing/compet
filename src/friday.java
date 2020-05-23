
import java.io.*;
import java.util.*;

public class friday {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("friday.in"));
		PrintWriter out = new PrintWriter(new File("friday.out"));
		int n = Integer.parseInt(in.readLine());
		int[] months = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int[] cnt = new int[7];
		// monday,tuesday,wednesday,thurday, fri,sat,sun
		// 0,1,2,3,4,5,6
		int startingDay = 0;
		for (int i = 1900; i < 1900 + n; i++) {
			if (checkLeap(i)) {
				months[1] = 29;
			}
			int sum = 0;
			for (int k = 0; k < 12; k++) {
				cnt[(sum + 12 + startingDay) % 7]++;
				sum += months[k];
			}
			if(checkLeap(i))
				startingDay=(startingDay+366)%7;
			else 
				startingDay=(startingDay+365)%7;
			months[1] = 28;
		}
		out.println(cnt[5] + " " + cnt[6] + " " + cnt[0] + " " + cnt[1] + " " + cnt[2] + " " + cnt[3] + " " + cnt[4]);
		in.close();
		out.close();

	}

	public static boolean checkLeap(int year) {
		if (year % 4 != 0)
			return false;
		if (year % 400 == 0)
			return true;
		if (year % 100 != 0)
			return true;
		else
			return false;
		// return !(year%100==0);
	}
}
