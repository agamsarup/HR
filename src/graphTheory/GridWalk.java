package graphTheory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class GridWalk {
	static int count = 0;

	public static void main(String[] args) throws FileNotFoundException {
		String filename = "src/testcases.txt";// testcases is just a file
												// containing input
		File file = new File(filename);
		Scanner in = new Scanner(file);
		// in.useDelimiter("[^0-9]+");
		// -----------------------------------------------------------------
		int T = in.nextInt();
		for (int t = 0; t < 1; t++) {
			int N = in.nextInt();
			int M = in.nextInt();
			System.out.println("M=" + M);
			int[] X = new int[N];
			long max = 1000000007;
			int[] D = new int[N];
			for (int i = 0; i < N; i++)
				X[i] = in.nextInt();
			for (int i = 0; i < N; i++)
				D[i] = in.nextInt();
			int Dmax = D[0];
			int Dtotal = 1;
			for (int i = 0; i < N; i++)
				if (Dmax < D[i])
					Dmax = D[i];
			for (int i = 0; i < N; i++)
				X[i]--;
			for (int i = 0; i < N; i++)
				Dtotal *= D[i];// total number of fields
			long[] mainarray = new long[Dtotal];
			long[] mainarraynext = new long[Dtotal];
			int[][] ways = new int[N][Dmax];
			set(X, mainarray, D, 1);
			int temp[] = new int[N];
			for (int h = 0; h < 10; h++) {

				for (int j = 0; j < Dtotal; j++) {
					mainarraynext[j] = getsum(inverse(j, D), mainarray, D);
				}
				for (int j = 0; j < Dtotal; j++) {
					mainarray[j] = mainarraynext[j];
					mainarray[j] %= max;
				}
				System.out.println(Arrays.toString(mainarray));
			}
			long finalsum = 0;
			for (int j = 0; j < Dtotal; j++) {
				finalsum += mainarray[j];
				// System.out.println(finalsum);

			}
			System.out.println(finalsum);
			// System.out.println(Arrays.toString(inverse(44,D)));
		}
	}

	public static long get(int[] x, long[] mainarray, int[] D) {
		for (int i = 0; i < x.length; i++) {
			if (x[i] >= D[i])
				return 0;
			if (x[i] < 0)
				return 0;
		}
		int index = 0;
		for (int i = 0; i < D.length; i++) {
			index = (index * D[i]) + x[i];
		}
		return mainarray[index];
	}

	public static int[] inverse(int index, int[] D) {
		int[] temp = new int[D.length];
		for (int i = D.length - 1; i >= 0; i--) {
			temp[i] = index % D[i];
			index = index / D[i];
		}
		return temp;
	}

	public static void set(int[] x, long[] mainarray, int[] D, int value) {
		int index = 0;
		for (int i = 0; i < D.length; i++) {
			index = (index * D[i]) + x[i];
		}
		mainarray[index] = value;
	}

	public static long getsum(int[] x, long[] mainarray, int[] D) {
		int[] temp = new int[x.length];
		long sum = 0;
		// for 2n different sides
		for (int j = 0; j < x.length; j++) {// sum in each side
			temp[j] = x[j];
		}
		for (int j = 0; j < x.length; j++) {// sum in each side
			temp[j]--;
			sum += get(temp, mainarray, D);
			temp[j] += 2;
			sum += get(temp, mainarray, D);
			temp[j]--;
		}
		return sum;

	}
}