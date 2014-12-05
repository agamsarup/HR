package codesprint14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BiggerGreater {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long t = Long.parseLong(br.readLine());

		String word;
		int wordLength;
		boolean found;
		StringBuilder sb;
		while (--t >= 0) {
			word = br.readLine().trim();
			sb = new StringBuilder(word);
	
			wordLength = word.length();

			if(wordLength==1)
			{
				System.out.println("no answer");
				continue;
			}
			
//			if (word.charAt(wordLength-1) > word.charAt(wordLength - 2))
//			{
//				sb.setCharAt(wordLength-1, word.charAt(wordLength-2));
//				sb.setCharAt(wordLength-2, word.charAt(wordLength-1));
//				System.out.println(sb.toString());
//				continue;
//			}
			found=false;
			// System.out.println(wordLength);
			for (int i = wordLength - 1; i > 0; i--) {
				if (word.charAt(i) > word.charAt(i - 1)) {
					found = true;

					int smallestGTI = i;
					for (int j = smallestGTI + 1; j < wordLength; j++) {
						if (word.charAt(smallestGTI) > word.charAt(j) && word.charAt(j) > word.charAt(i-1))
							smallestGTI = j;
					}
//					System.out.println((i-1)+" "+smallestGTI);
					sb.setCharAt(i-1, word.charAt(smallestGTI));
					sb.setCharAt(smallestGTI, word.charAt(i-1));

					char[] toSort = new char[wordLength - i];

					sb.getChars(i , wordLength, toSort, 0);
					sb.setLength(i);
					Arrays.sort(toSort);
					sb.append(toSort);
					break;
				}
			}

			if (found)
				System.out.println(sb.toString());
			else
				System.out.println("no answer");
		}
	}

}
