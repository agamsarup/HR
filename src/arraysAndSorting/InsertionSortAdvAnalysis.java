package arraysAndSorting;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class InsertionSortAdvAnalysis {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

static long mergeSort(int[] arr, int start, int end){
		
		int t;
		if(start>=end){
			return 0;
		}else if(start==end-1){
			if(arr[start]> arr[end]){
				t=arr[start];
				arr[start]=arr[end];
				arr[end]=t;
				return 1;
			}
		}else{
			
			int mid=start+(end-start)/2;
			int inversions=0;
			inversions+=mergeSort(arr, start, mid);
			inversions+=mergeSort(arr, mid+1, end);
			
			// now merge
			int l=start;
			int r=mid+1;
			int k=0;
			int[] tempArr=new int[end-start+1];
			while(l<=mid && r<=end){
				if(arr[l] <= arr[r]){
					tempArr[k]=arr[l];
					k++;
					l++;
				}
				else{
					tempArr[k]=arr[r];
					k++;
					r++;
					inversions+=mid-l+1;
				}
			}
			while(l<=mid){
				tempArr[k]=arr[l];
				k++;
				l++;
//				inversions++;
			}
			while(r<=end){
				tempArr[k]=arr[r];
				k++;
				r++;
			}
			
			for(int i=start;i<=end;i++){
				arr[i]=tempArr[i-start];
			}
			return inversions;
		}
		return 0;
	}

	static void solve() throws IOException {
		int t = ni(),n;
		int[] arr,sortedArr;
		int temp;
		long swaps;
		while(--t>=0){
			n=ni();
			arr=na(n);
//			sortedArr=arr.clone();
			
//			arr[0]=0;
//			System.out.println(Arrays.toString(arr));
//			System.out.println(Arrays.toString(sortedArr));
			swaps=mergeSort(arr, 0, n-1);
			out.println(swaps);
//			for(int i=1;i<n;i++){
//				for(int j=i-1;j>=0;j--){
//					if(arr[j] > arr[j+1]){
//						temp=arr[j];
//						arr[j]=arr[j+1];
//						arr[j+1]=temp;
//						swaps++;
//					}
//					else
//						break;
//				}
//			}
			
//			System.out.println(Arrays.toString(arr));
//			out.println(swaps);
		}
		
	}

	public static void main(String[] args) throws Exception {
		long S = System.currentTimeMillis();
		is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(
				INPUT.getBytes());
		out = new PrintWriter(System.out);

		solve();
		out.flush();
		long G = System.currentTimeMillis();
		tr(G - S + "ms");
	}

	private static boolean eof() {
		if (lenbuf == -1)
			return true;
		int lptr = ptrbuf;
		while (lptr < lenbuf)
			if (!isSpaceChar(inbuf[lptr++]))
				return false;

		try {
			is.mark(1000);
			while (true) {
				int b = is.read();
				if (b == -1) {
					is.reset();
					return true;
				} else if (!isSpaceChar(b)) {
					is.reset();
					return false;
				}
			}
		} catch (IOException e) {
			return true;
		}
	}

	private static byte[] inbuf = new byte[1024];
	static int lenbuf = 0, ptrbuf = 0;

	private static int readByte() {
		if (lenbuf == -1)
			throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0)
				return -1;
		}
		return inbuf[ptrbuf++];
	}

	private static boolean isSpaceChar(int c) {
		return !(c >= 33 && c <= 126);
	}

	private static int skip() {
		int b;
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
	}

	private static double nd() {
		return Double.parseDouble(ns());
	}

	private static char nc() {
		return (char) skip();
	}

	private static String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b !=
									// ' ')
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	private static char[] ns(int n) {
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while (p < n && !(isSpaceChar(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}

	private static char[][] nm(int n, int m) {
		char[][] map = new char[n][];
		for (int i = 0; i < n; i++)
			map[i] = ns(m);
		return map;
	}

	private static long[] nal(int n) {
		long[] a = new long[n];
		for (int i = 0; i < n; i++)
			a[i] = nl();
		return a;
	}

	private static int[] na(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = ni();
		return a;
	}

	private static int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private static long nl() {
		long num = 0;
		int b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private static void tr(Object... o) {
		if (INPUT.length() != 0)
			System.out.println(Arrays.deepToString(o));
	}
}
