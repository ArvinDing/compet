
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class censor5 {
	private static long prime;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("censor.in"));
		PrintWriter out = new PrintWriter(new File("censor.out"));
		String info = in.readLine();

		prime = BigInteger.probablePrime(31, new Random()).longValue();
		int removes = Integer.parseInt(in.readLine());
		TreeMap<Integer, TreeSet<Long>> solution = new TreeMap<Integer, TreeSet<Long>>();
		TreeMap<Integer, Long> lengths = new TreeMap<Integer, Long>();
		for (int i = 0; i < removes; i++) {
			String remove = in.readLine();
			long compare = getHash(remove);
			if (!solution.containsKey(remove.length()))
				solution.put(remove.length(), new TreeSet<Long>());
			solution.get(remove.length()).add(compare);
			if (!lengths.containsKey(remove.length()))
				lengths.put(remove.length(), getDivide(remove.length()));
		}
		long sum = 0;
		/// TreeMap<Integer, Long> indexes = new TreeMap<Integer, Long>();
		StringBuilder curr = new StringBuilder();
		curr.append(info.substring(0, lengths.firstKey()));
		long temp = getHash(curr.toString());
		long[] indexes = new long[100000];
		for (int lengthPoss : lengths.keySet()) {
			indexes[lengthPoss] = temp;
		}

		for (int i = lengths.firstKey() - 1; i < info.length(); i++) {
		//	sum -= System.nanoTime();
			for (int currLength : lengths.keySet()) {

				if (currLength > curr.length())
					break;

				long currHashCode = indexes[currLength];

				if (solution.get(currLength).contains(currHashCode)) {
					curr.setLength(curr.length() - currLength);

					long redo = 0;
					long multiply = 1;
					Iterator itr = lengths.keySet().iterator();
					int currGoal = (int) itr.next();
					for (int lengthPoss = 0; lengthPoss < Math.min(curr.length(),
							lengths.lastKey() + 1); lengthPoss++) {

						int currChar = curr.charAt(curr.length() - lengthPoss - 1);
						if (currGoal == lengthPoss) {
							indexes[lengthPoss] = redo;
							if (!itr.hasNext())
								break;
							currGoal = (int) itr.next();
						}
						redo += multiply * (currChar - 96);
						redo %= prime;
						multiply *= 27;
						multiply %= prime;
					}
					while (true) {
						indexes[currGoal] = redo;
						if (!itr.hasNext())
							break;
						currGoal = (int) itr.next();
					}

					break;
				}

			}
			//sum += System.nanoTime();

			if (i + 1 == info.length())
				break;

			for (int lengthPoss : lengths.keySet()) {
				if (lengthPoss <= curr.length()) {
					indexes[lengthPoss] += (-((curr.charAt(curr.length() - lengthPoss) - 96) * lengths.get(lengthPoss))
							+ 27 * prime)%prime;

				}
				indexes[lengthPoss] *= 27;
				indexes[lengthPoss] = (indexes[lengthPoss] % prime) + (info.charAt(i + 1) - 96);

			}
			curr.append(info.charAt(i + 1));

		}
	//	System.out.print(sum);
		out.println(curr.toString());
		out.close();
		in.close();
	}

	private static long getDivide(int length) {
		long divide = 1;
		for (int i = 0; i < length - 1; i++) {
			divide *= 27;
			divide %= prime;
		}
		return divide;
	}

	private static long getHash(String in) {
		long curr = 0;
		for (int i = 0; i < in.length(); i++) {
			curr = curr * 27 + (in.charAt(i) - 96);
			curr = curr % prime;
		}
		return curr;
	}

}