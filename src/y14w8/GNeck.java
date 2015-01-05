package y14w8;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GNeck {

	public static long[][] multMatrixMod(long[][] A,long[][] B,long mod)
	{
		int rows=A.length;
		int cols=B[0].length; 
		int Acols=A[0].length;
		//System.out.println("rows="+rows+"cols="+cols);
		long[][] result=new long[rows][cols];
		
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				result[i][j]=0;
				for(int k=0;k<Acols;k++)
				{
					result[i][j]=(result[i][j]+A[i][k]*B[k][j])%mod;
				}
			}
		}
		return result;
	}
	
	public static long[][] matrixPowMod(long[][] A, long n, long mod)
	{
		if (n == 0)
			return new long[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
		if (n == 1)
			return A;
		if (n % 2 == 0) {
			long[][] temp = matrixPowMod(A, n/2, mod);
			return multMatrixMod(temp, temp, mod);
		} else {
			long[][] temp = matrixPowMod(A, n/2, mod);
			temp = multMatrixMod(temp, temp, mod);
			return multMatrixMod(temp, A, mod);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine().trim());
		long n;
		/*
		long totalBRending;
		long totalRRending;
		long totalRBending;
		long newBRending;
		long newRRending;
		long newRBending;
		long cur;
		*/
		long mod=1000000007;
		long[][] A = {{0, 1, 0}, {0, 0, 1}, {1, 0, 1}};
		long[][] seq={{3},{4},{6}};
		while(--tc>=0)
		{
			n = Long.parseLong(br.readLine().trim());
			
			/*cur=2;
			totalBRending=1;
			totalRRending=1;
			totalRBending=1;
			while(++cur<=n)
			{
				newBRending=totalRBending;
				newRRending=(totalBRending+totalRRending)%mod;
				newRBending=totalRRending;
				totalBRending=newBRending;
				totalRRending=newRRending;
				totalRBending=newRBending;
			}
			
			//System.out.println("totalBRending="+totalBRending);
			//System.out.println("totalRRending="+totalRRending);
			//System.out.println("totalRBending="+totalRBending);
			System.out.println((totalBRending+totalRBending+totalRRending)%mod);
			
			//System.out.println("total1="+4*Math.pow(1.46557, n-3));
			//System.out.println("total2="+BigDecimal.valueOf(3).multiply(BigDecimal.valueOf(1.4555555555).po));

			*/
			System.out.println(multMatrixMod(matrixPowMod(A, n-2, mod), seq, mod)[0][0]);
		}
	}

}
