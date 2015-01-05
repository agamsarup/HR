import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sparse {

	static int getLSOneBitPos(int n) {
		int shift = 0;
		int temp = n;
		while (shift < 32) {
			if ((temp & 1) == 1)
				return shift;
			temp = temp >>> 1;
			shift++;
		}
		return shift;
	}

	static int getMSOneBitPos(String s) {
		int pos=31;
		while(pos >=0){
			if(s.charAt(pos)=='1')
				return pos;
			pos--;
		}
		return pos;
	}
	
	static int getMSOneBitPos(int n) {
		int shift = 31;
		while (shift >= 32) {
			if (((n>>>shift) & 1) == 1)
				return shift;
			shift--;
		}
		return shift;
	}
	
	static int strToInt(String s) {
		int length = s.length();
		int num = 0;
		int multipleOfTwo = 1;
		for (int i = 0; i < length; i++) {
			if (s.charAt(i) == '1') {
				num += multipleOfTwo;
			}
			multipleOfTwo=multipleOfTwo<<1;
		}
		
		return num;
	}

	public static int solution(int n) {

//		int res;
		String str = Integer.toBinaryString(n);

		StringBuilder s = new StringBuilder(str).reverse();

		int length = s.length();
//		System.out.println(length);

//		System.out.println(s.toString());
		for (int i = length; i < 32; i++)
			s.append('0');

//		System.out.println(s.toString());
		int lsOneBitPos = getLSOneBitPos(n);
		if (lsOneBitPos == 32)
			return 1;

		boolean consecutiveOnes = false;
		int i = 31;
		for (; i > 0; i--) {
			if (s.charAt(i) == '1' && s.charAt(i - 1) == '1') {
				consecutiveOnes = true;
				for (int j = i; j >= 0; j--)
					s.setCharAt(j, '0');
				if (i + 1 < 32)
					s.setCharAt(i + 1, '1');
				break;
			}
		}

		if (consecutiveOnes) {
			i++;
//			System.out.println("i="+i);
			while (s.charAt(i + 1) == '1') {
				s.setCharAt(i, '0');
				s.setCharAt(i + 1, '0');
				s.setCharAt(i + 2, '1');
				i += 2;
			}
//			System.out.println("1 "+strToInt(s.substring(0, i + 1)));
//			System.out.println(" "+s.toString());
//			System.out.println(s.substring(0, i + 1));
			return strToInt(s.substring(0, 1+getMSOneBitPos(s.toString())));
		} else {
			i = 0;
			while (s.charAt(i) == '1') {
				s.setCharAt(i, '0');
				i++;
			}
			s.setCharAt(i, '1');
			while (s.charAt(i + 1) == '1') {
				s.setCharAt(i, '0');
				s.setCharAt(i + 1, '0');
				s.setCharAt(i + 2, '1');
				i += 2;
			}
//			System.out.println(s.toString());
//			System.out.println("2 "+s.substring(0, 1+getMSOneBitPos(s.toString())));
			return strToInt(s.substring(0, 1+getMSOneBitPos(s.toString())));
		}
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		System.out.println(solution(n));
	}

}
