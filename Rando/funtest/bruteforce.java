package funtest;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.KeyEvent;

public class bruteforce {
	public static void main(String[] args) throws Exception {

		JOptionPane.showMessageDialog(null, "Open google docs or something to record keyboard inputs"
				+ ". It is time for the story of Alex Yan, the conquerer. It will start in 20 seconds");
		TimeUnit.SECONDS.sleep(20);
		Robot r = new Robot();
		String a = "There once was boy named Alex. He was a bright boy, beyond everyone from the very start."
				+ " An expert at lying and a even better truth teller he was truly unstoppable. This was the pinnacle of a human. He had everything from intelligence to strength."
				+ " people grew to fear this young man, for they could find no flaws with him. He was1A magnificent beast.1Flying over others.1With nothing left to learn.1With nothing left to gain"
				+ "1His speed unmatchable 1His height unreachable"
				+ "1His agility unfathomable 1He was a conqueror1Nothing more1Nothing Less1No word could or would be more precise.1Tired1Sad1Bored1Done with the monotony1He decided he would evolve into a mathematician1 The only mathematician1"
				+ "A true mathematician1"
				+ "but alas this cruel world would not go as planned.1He ended up 11s1u1c1k1i1n1g1at math 1Shortly thereafter quit.1The end.";
		char[] save = a.toUpperCase().toCharArray();
		TimeUnit.SECONDS.sleep(1);
		for (int i = 0; i < save.length; i++) {
			String code = "VK_" + save[i];
			if (save[i] == '.')
				code = "VK_PERIOD";
			if (save[i] == ',')
				code = "VK_COMMA";
			if (save[i] == ' ')
				code = "VK_SPACE";
			if (save[i] == '1')
				code = "VK_ENTER";

			Field f = KeyEvent.class.getField(code);
			int keyEvent = f.getInt(null);
			TimeUnit.MILLISECONDS.sleep(25);
			r.keyPress(keyEvent);
			r.keyRelease(keyEvent);
		}
		TimeUnit.MILLISECONDS.sleep(100);
		JOptionPane.showMessageDialog(null, "Made by me, Friendtodino. Thanks for listening to me. You can continue the perilous plot of alex yan at");
		try {

			URI uri = new URI("https://www.facebook.com/alexander.yan.94");

			java.awt.Desktop.getDesktop().browse(uri);
			System.out.println("Web page opened in browser");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
