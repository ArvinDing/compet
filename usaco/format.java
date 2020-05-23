import java.io.*;
import java.util.*;

public class format {

	public static void main(String[] args) throws Exception {
		BufferedReader in=new  BufferedReader(new InputStreamReader(System.in));
		double a=Double.parseDouble(in.readLine());
		System.out.println(String.format("%7.3f", a));
		in.close();
	}
}