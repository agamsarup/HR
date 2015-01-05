package numberTheory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class PermDivByEight {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

//	static int[] multiples = { 000, 002, 004, 006, 8, 012, 014, 016, 023, 024,
//			025, 027, 28, 29, 34, 036, 044, 045, 046, 047, 48, 49, 056, 067,
//			68, 69, 88, 112, 123, 125, 126, 127, 128, 129, 136, 144, 146, 148,
//			166, 167, 168, 223, 224, 227, 234, 235, 236, 238, 239, 244, 246,
//			247, 248, 255, 256, 257, 258, 259, 267, 269, 278, 279, 288, 289,
//			299, 336, 344, 348, 356, 367, 368, 369, 445, 446, 447, 448, 449,
//			456, 458, 466, 468, 469, 478, 488, 489, 566, 567, 568, 669, 677,
//			678, 679, 688, 689, 888 };

	static String[] multiplesStr = { "000", "002", "004", "006", "008", "012",
			"014", "016", "023", "024", "025", "027", "028", "029", "034",
			"036", "044", "045", "046", "047", "048", "049", "056", "067",
			"068", "069", "088", "112", "123", "125", "126", "127", "128",
			"129", "136", "144", "146", "148", "166", "167", "168", "223",
			"224", "227", "234", "235", "236", "238", "239", "244", "246",
			"247", "248", "255", "256", "257", "258", "259", "267", "269",
			"278", "279", "288", "289", "299", "336", "344", "348", "356",
			"367", "368", "369", "445", "446", "447", "448", "449", "456",
			"458", "466", "468", "469", "478", "488", "489", "566", "567",
			"568", "669", "677", "678", "679", "688", "689", "888" };

	static int reverseInt(int n)
	{
		int left = n;
		int rev = 0;
		
		int r;
		while(left>0)
		{
		   r = left % 10;   
		   rev = rev * 10 + r;
		   left = left / 10;  //left = Math.floor(left / 10); 
		}
		
		return rev;
	}
	
	static void solve() {
		
//		System.out.println(reverseInt(1));
		
		int[][] digitFreq=new int[95][10];
//		System.out.println(multiplesStr.length);
		
		for(int i=0;i<95;i++)
		{
			for(int j=0;j<3;j++)
			{
				digitFreq[i][multiplesStr[i].charAt(j)-'0']++;
			}
		}
		
		int t = ni();

		
		String numStr;
		int num;
		int numDigits;
		while (--t >= 0) {
			numStr = ns();
			numDigits=numStr.length();
			if(numDigits < 3)
			{
				num=Integer.parseInt(numStr);
				
				if(num%8==0 || reverseInt(num)%8==0)
					out.println("YES");
				else
					out.println("NO");
			}
			else
			{
				boolean yes=false;
				int[] numDigitsSeen=new int[95];
				int[] digNumInstances=new int[10];
				int d;
				for(int i=0;i<numDigits && !yes;i++)
				{
//					numDigitsSeen=0;
					d=numStr.charAt(i)-'0';
					digNumInstances[d]++;
					if(digNumInstances[d]<4)
					{
						for(int j=0;j<95;j++)
						{
							if(digitFreq[j][d] >= digNumInstances[d])
							{
								numDigitsSeen[j]++;
								if(numDigitsSeen[j]==3)
								{
									//System.out.println("i="+i);
									yes=true;
									break;
								}
							}
						}
					}
				}
				
				if(yes)
					out.println("YES");
				else
					out.println("NO");
				
			}
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
