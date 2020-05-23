package Testing;

import java.util.Arrays;
import java.util.Iterator;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.stream.Stream;

public class DataUtil {
	public static int getRandomNumber(int lower, int upper) {
		return (int) (Math.round(Math.random() * (upper - lower)) + lower);
	}
	public static int getRandomNumber(int upper) {
		return getRandomNumber(0, upper);
	}
	
	public static boolean isStreamsEquals(Stream<?> s1, Stream<?> s2) {
	    Iterator<?> iter1 = s1.iterator(), iter2 = s2.iterator();
	    while(iter1.hasNext() && iter2.hasNext())
	        if (! iter1.next().equals(iter2.next())) return false;
	    return !iter1.hasNext() && !iter2.hasNext();
	}
	
	public static void printArray(int [] a) {
		OptionalInt max = Arrays.stream(a).max(), min = Arrays.stream(a).min();
		int len = Math.max(String.valueOf(max.getAsInt()).length(), String.valueOf(min.getAsInt()).length()) + 1;
		Arrays.stream(a).forEach(one -> System.out.printf("%" + len + "s,", one));
		System.out.println();
	}
	public static void printArray(int [][] a2) {
		Arrays.stream(a2).forEach(a -> printArray(a));
	}

	public static void printArray(long [] a) {
		OptionalLong max = Arrays.stream(a).max(), min = Arrays.stream(a).min();
		int len = Math.max(String.valueOf(max.getAsLong()).length(), String.valueOf(min.getAsLong()).length()) + 1;
		Arrays.stream(a).forEach(one -> System.out.printf("%" + len + "s,", one));
		System.out.println();
	}
	public static void printArray(long [][] a2) {
		Arrays.stream(a2).forEach(a -> printArray(a));
	}
}
