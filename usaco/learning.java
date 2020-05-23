
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class learning {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("learning.in"));
		PrintWriter out = new PrintWriter(new File("learning.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(read.nextToken());
		int A = Integer.parseInt(read.nextToken());
		int B = Integer.parseInt(read.nextToken());
		TreeMap<Integer, Boolean> info = new TreeMap<Integer, Boolean>();

		for (int i = 0; i < N; i++) {
			read = new StringTokenizer(in.readLine());
			boolean save = read.nextToken().equals("S");
			info.put(Integer.parseInt(read.nextToken()), save);

		}
		info.put(-1000000001, false);
		info.put(1000000001, false);

		List<cow> newInfo = new ArrayList<cow>();
		for (Entry<Integer, Boolean> entry : info.entrySet()) {
			int key = entry.getKey();
			boolean value = entry.getValue();
			newInfo.add(new cow(key, value));
		}
		int sum = 0;
		for (int i = 0; i < newInfo.size() - 1; i++) {
			cow a = newInfo.get(i);
			cow b = newInfo.get(i + 1);
			int key = a.key;
			boolean value = a.value;

			int nextKey = b.key;
			boolean nextValue = b.value;
			if (key > B) {
				break;
			}
			if (A >= nextKey) {
				continue;
			}
			int start;
			int end;
			if (!nextValue && !value) {
				continue;
			} else if (nextValue != value) {
				int mid = key + ((nextKey - key) / 2);
				if (value) {
					start = key + 1;
					end = mid;
				} else {
					if ((nextKey - key) % 2 == 0) {
						start = mid;
					} else {
						start = mid + 1;
					}

					end = nextKey;
				}
			} else {
				start = key + 1;
				end = nextKey;
			}
			if (key == A && start == key + 1) {
				start = A;
			}
			if (A > start) {
				start = A;
			}
			if (B < end) {
				end = B;
			}
			if (start <= end) {
				sum += end - start + 1;
//				System.out.println(end - start + 1);
			}
		}
		System.out.println(sum);
		out.println(sum);
		out.close();
		in.close();
	}

	public static class cow {
		int key;
		boolean value;

		public cow(int key, boolean value) {
			this.key = key;
			this.value = value;
		}

	}

}