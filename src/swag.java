import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class swag {
	public static void main(String[] args) throws MalformedURLException, IOException {
		// recursion(15, 15, "");
		test("https://bit.ly/2XfNzXs");
	}

	static int done = 0;

	public static void test(String a) throws IOException {

		done++;
		try {
			long start = System.nanoTime();
			URL oracle = new URL(a/* "https://bit.ly/2XfNzXs" */);
			HttpURLConnection yc = (HttpURLConnection) oracle.openConnection();
			System.out.println( System.nanoTime() - start);

			yc.setInstanceFollowRedirects(false);
			System.out.println(yc.getHeaderField("Location"));
			
		} catch (Exception e) {
			return;
		}

		// String inputLine;
		// while ((inputLine = in.readLine()) != null)
		// System.out.println(inputLine);

	}

	public static void recursion(int I, int l, String a) throws IOException {
		if (I == 0 && l == 0) {
			test(convert(a));
		}
		if (I > 0)
			recursion(I - 1, l, a + "I");
		if (l > 0)
			recursion(I, l - 1, a + "l");
	}

	private static String convert(String a) {
		return "https://bit.ly/" + a;
	}
}
