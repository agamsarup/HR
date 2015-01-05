package util.test;

import java.util.Scanner;
class Primality {
	public static void main(String ar[]) {
		Scanner in = new Scanner(System.in);
		while(true) {
			int n = in.nextInt();
			System.out.println(isPrime(n));
		}
	}
	public static boolean isPrime(int n) {
	    System.out.println("s"+new String(new char[n])+"e");
	    System.out.println("aa".matches("(a|b)\\1+"));
	    System.out.println("a".matches("(a|b)\\1+"));
		return !new String(new char[n]).matches(".?|(..+?)\\1+");
	}
}