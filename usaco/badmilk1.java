import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public class badmilk1 {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader("badmilk.in"));
		String[] strs = input.readLine().split(" ");
		int guests = Integer.valueOf(strs[0]);
		int milkTypes = Integer.valueOf(strs[1]);
		int eventLogs = Integer.valueOf(strs[2]);
		int sickLogs = Integer.valueOf(strs[3]);
		EventLog[] eLogs = new EventLog[eventLogs];
		SickLog[] sLogs = new SickLog[sickLogs];
		for (int i = 0; i < eventLogs; i++) {
			strs = input.readLine().split(" ");
			eLogs[i] = new EventLog(Integer.valueOf(strs[0]), Integer.valueOf(strs[1]), Integer.valueOf(strs[2]));
		}
		for (int i = 0; i < sickLogs; i++) {
			strs = input.readLine().split(" ");
			sLogs[i] = new SickLog(Integer.valueOf(strs[0]), Integer.valueOf(strs[1]));
		}
		input.close();
		boolean[] susFlags = new boolean[milkTypes], eFlags = new boolean[milkTypes];
		for (int i = 0; i < milkTypes; i++) {
			eFlags[i] = false;
		}
		int answer = 0;
		int a, b;
		for (int i = 0; i < sickLogs; i++) {
			a = sLogs[i].guest;
			b = sLogs[i].time;
			for (int j = 0; j < milkTypes; j++) {
				susFlags[i] = false;
			}
			for (int e = 0; e < eventLogs; e++) {
				if (eLogs[e].guest == a && eLogs[e].time <= b) {
					susFlags[eLogs[e].milk] = true;
				}
			}
			for (int j = 0; j < eFlags.length; j++) {
				if (!susFlags[j]) {
					eFlags[j] = true;
				}
			}
		}
		HashSet<Integer> sickPersons = new HashSet<Integer>();
		for (int i = 0; i < eventLogs; i++) {
			if (!eFlags[eLogs[i].milk]) {
				sickPersons.add(eLogs[i].guest);
			}
		}
		PrintWriter out = new PrintWriter(new FileWriter("badmilk.out"));
		out.println(sickPersons.size());
		out.close();
	}

	private static class EventLog {
		int guest, milk, time;

		EventLog(int guest, int milk, int time) {
			this.guest = guest;
			this.milk = milk - 1;
			this.time = time;
		}
	}

	private static class SickLog {
		int guest, time;

		SickLog(int guest, int time) {
			this.guest = guest;
			this.time = time;
		}
	}

}
