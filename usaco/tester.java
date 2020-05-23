
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.io.*;

public class tester {
	public static void main(String[] args) throws Exception {
		String zipName = "unlock_silver";
		String problem = "unlock";
		ZipFile zipFile = new ZipFile(zipName + ".zip");
		int inputs = zipFile.size() / 2;
		for (int i = 1; i <= inputs; i++) {
			InputStream in = zipFile.getInputStream(new ZipEntry(i + ".in"));
			byte[] buffer = new byte[in.available()];
			in.read(buffer);

			File inFile = new File(problem + ".in");
			BufferedReader outFile = new BufferedReader(new FileReader(new File(problem + ".out")));
			OutputStream outStream = new FileOutputStream(inFile);
			outStream.write(buffer);
			unlock.main(args);
			BufferedReader thing = new BufferedReader(new FileReader(problem + ".out"));
			System.out.print(thing.readLine()+" ");
			System.out.println(outFile.readLine());
			outFile.close();
			thing.close();
			in.close();
			outStream.close();
		}

	}
}
