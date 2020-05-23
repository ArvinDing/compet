package slitherlink;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.concurrent.TimeUnit;

public class cursor {
	public static void main(String[] args) throws InterruptedException {
		
		for(int i=0;i<5;i++) {
			TimeUnit.SECONDS.sleep(1);
			PointerInfo a = MouseInfo.getPointerInfo();
			Point b = a.getLocation();
			int x = (int) b.getX();
			int y = (int) b.getY();
			System.out.println(x+" "+y);
		}
	}
}
//997 348
//1224 348