package y14w10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HammingDistance {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
//		String s = br.readLine();
		StringBuilder sb = new StringBuilder(" "+br.readLine());
		int m = Integer.parseInt(br.readLine());

		String[] arr;
		int l, r, l1, r1, l2, r2, len, hamDist, len1, len2;
		char ch, temp;
		char[] temparr;
		while (--m >= 0) {
			arr = br.readLine().split(" ");
			if (arr[0].startsWith("C")) {
				l = Integer.parseInt(arr[1]);
				r = Integer.parseInt(arr[2]);
				ch = arr[3].charAt(0);

				for (int i = l; i <= r; i++)
					sb.setCharAt(i, ch);
				
				System.out.println("C "+sb.toString());
			} else if (arr[0].startsWith("S")) {
				l1 = Integer.parseInt(arr[1]);
				r1 = Integer.parseInt(arr[2]);
				l2 = Integer.parseInt(arr[3]);
				r2 = Integer.parseInt(arr[4]);
				len1 = r1 - l1+1;
				len2 = r2 - l2+1;
				System.out.println("len1 "+len1+" len2 "+len2);
				if (len1 < len2) {

					temparr=new char[len1];
					while(--len1>=0)
					{
						temparr[len1]=sb.charAt(l1+len1);
					}
					int i=-1;
					while(++i<len2)
					{
						sb.setCharAt(l1+i, sb.charAt(l2+i));
					}
					len1 = r1 - l1+1;
					while(--len1>=0)
					{
						sb.setCharAt(r2--, temparr[len1]);
					}
				}
				else if (len1 > len2) {

					temparr=new char[len2];
					while(--len2>=0)
					{
						temparr[len2]=sb.charAt(l2+len2);
					}
					len2 = r2 - l2+1;
					while(--len1>=0)
					{
						sb.setCharAt(r2--, sb.charAt(l1+len1));
					}
					
					while(--len2>=0)
					{
						System.out.println(r1+" "+len2+" "+temparr[len2]);
						sb.setCharAt(l1+len2, temparr[len2]);
					}
				}
				else
				{
					while(--len1>=0)
					{
						--len2;
						temp=sb.charAt(l1+len1);
						sb.setCharAt(l1+len1, sb.charAt(l2+len2));
						sb.setCharAt(l2+len2, temp);
					}
				}
				System.out.println("S "+sb.toString());
			} else if (arr[0].startsWith("R")) {
				l = Integer.parseInt(arr[1]);
				r = Integer.parseInt(arr[2]);
				while (l < r) {
					temp = sb.charAt(r);
					sb.setCharAt(r, sb.charAt(l));
					sb.setCharAt(l, temp);
					l++;
					r--;
				}
				System.out.println("R "+sb.toString());
			} else if (arr[0].startsWith("W")) {
				l = Integer.parseInt(arr[1]);
				r = Integer.parseInt(arr[2]);
				System.out.println(sb.substring(l, r + 1));

				System.out.println("W "+sb.toString());
			} else if (arr[0].startsWith("H")) {
				l1 = Integer.parseInt(arr[1]);
				l2 = Integer.parseInt(arr[2]);
				len = Integer.parseInt(arr[3]);
				hamDist = 0;
				while (--len >= 0) {
					if (sb.charAt(l1++) != sb.charAt(l2++))
						hamDist++;
				}
				System.out.println("H "+sb.toString());
				System.out.println(hamDist);
			}
		}

	}

}
