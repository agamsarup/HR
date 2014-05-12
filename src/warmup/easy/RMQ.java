package warmup.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RMQ {

	public static void method1() throws NumberFormatException, IOException {
		// O(N) preprocessing, O(sqrt(n)) query

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nt = br.readLine().split(" ");
		int n = Integer.parseInt(nt[0]);
		int t = Integer.parseInt(nt[1]);
		String[] width = br.readLine().split(" ");
		int[] widthI = new int[width.length];

		double sqrt = Math.sqrt(n);
		int isqrt = (int) sqrt;
		int mLength=0;
		
		if(sqrt > isqrt)
			mLength=1;
		if(sqrt - isqrt > 0.5)
			isqrt+=1;
		
//		System.out.println("isqrt="+isqrt);
		mLength+=n/isqrt;
		
		int[] M = new int[mLength]; // sqrt(n) sections storing pos of min in
									// each section
		int mIndex;
		int nIndex;
		for (int i = 0; i < M.length; i++) {
			M[i] = i * isqrt;
//			System.out.println("M["+i+"]="+M[i]);
		}
		for (int i = 0; i < n; i++) {
			widthI[i] = Integer.parseInt(width[i]);
		}
		for (int i = 0; i < n; i++) {
			mIndex = i / isqrt;
			if (widthI[i] < widthI[M[mIndex]]) {
				M[mIndex] = i;
			}
		}

		for (int l = 0; l < t; l++) {
			String[] ij = br.readLine().split(" ");
			int i = Integer.parseInt(ij[0]);
			int j = Integer.parseInt(ij[1]);

			int leftIndex = i / isqrt+1;
			int rightIndex = j / isqrt;
			// min of A[i] to A[isqrt*(leftIndex+1)-1]
			// A[M[leftIndex]] to A[M[rightIndex]]
			// min of A[isqrt*rightIndex] to A[j]
			int mark1 = Math.min(isqrt * leftIndex,j+1);
			int mark2 = Math.max(isqrt * rightIndex,i);
			int min = widthI[i];
//			System.out.println("leftIndex="+leftIndex);
//			System.out.println("rightIndex="+rightIndex);
//			System.out.println("mark1="+mark1+" mark2="+mark2);
			for (int k = i; k < mark1; k++) {
				if (widthI[k] < min)
					min = widthI[k];
			}
			for (int k = mark2; k <= j; k++) {
				if (widthI[k] < min)
					min = widthI[k];
			}
			for (int k = leftIndex; k < rightIndex; k++) {
				if (widthI[M[k]] < min)
					min = widthI[M[k]];
			}
			System.out.println(min);
		}
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {

		method1();
	}

}
