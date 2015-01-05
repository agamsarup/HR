package contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CrazyHelix {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] nq = br.readLine().trim().split(" ");
		int n = Integer.parseInt(nq[0] + 1);
		int q = Integer.parseInt(nq[1]);

		int[] data = new int[n + 1];
		for (int i = 1; i <= n; i++)
			data[i] = i;

		int A, B;
		int i,j;
		int temp;
		String[] query;
		while (q-- > 0) {
			query = br.readLine().trim().split(" ");
			if (query[0].startsWith("1")) {
				A = Integer.parseInt(query[1]);
				B = Integer.parseInt(query[2]);
				i=A-1;
				j=B+1;
				while(++i<--j)
				{
					temp=data[i];
					data[i]=data[j];
					data[j]=temp;
				}
			} else if (query[0].startsWith("2")) {
				A = Integer.parseInt(query[1]);
				for (i = 1; i <= n; i++) {
					if (A == data[i]) {
						System.out.println("element "+A+" is at position "+i);
						continue;
					}
				}
			} else {
				A = Integer.parseInt(query[1]);
				System.out.println("element at position "+A+" is " +data[A]);
			}
		}
	}
}
