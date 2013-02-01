package org.example.provider;

public class Timer {

	static long start;
	
	public static void start() {
		System.out.println("Timer started!");
		start = System.currentTimeMillis();
	}
	
	public static void stop() {
		long duration = System.currentTimeMillis() - start;
		System.out.println("Timer duration: " + duration + "ms.");
	}
}
