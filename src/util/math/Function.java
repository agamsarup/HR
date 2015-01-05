package util.math;

public class Function {

	boolean isPrime(int n)
	{
		int sqrt=(int) Math.sqrt(n);
		
		for(int i=2;i<=sqrt;i++)
		{
			if(n%i==0)
				return false;
		}
		
		return true;
	}
	
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

	static int[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47,
		53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113,
		127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191,
		193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263,
		269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347,
		349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421,
		431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499,
		503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593,
		599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661,
		673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757,
		761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853,
		857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941,
		947, 953, 967, 971, 977, 983, 991, 997 };
	
	public int eulerTotient(int n)
	{
		int res=n;
		
		int sqrt=(int) Math.sqrt(n);
		
		for(int i=0;i<168;i++)
		{
			if(n%primes[i]==0)
				res=(res/primes[i])*(primes[i]-1);
		}
		
		return res;
	}
	
	public long gcd(long a, long b) {
		if (a < 0)
			a = -a;
		if (b < 0)
			b = -b;

		return (b > 0) ? gcd(b, a % b) : a;
	}
	
	public static long powMod(long n, long k, long m) {
		if (k == 0)
			return 1;
		if (k == 1)
			return n % m;
		if (k % 2 == 0) {
			long temp = powMod(n, k / 2, m);
			return (temp * temp) % m;
		} else {
			long temp = powMod(n, k / 2, m);
			temp = (temp * temp) % m;
			return (temp * n) % m;
		}
	}
	
	public long pow(long n, long k) {
		if (k == 0)
			return 1;
		if (k == 1)
			return n;
		if (k % 2 == 0) {
			long temp = pow(n, k / 2);
			return temp * temp;
		} else {
			long temp = pow(n, k / 2);
			temp = temp * temp;
			return temp * n;
		}
	}

	public static long[][] multMatrixMod(long[][] A,long[][] B,long mod)
	{
		int rows=A.length;
		int cols=B[0].length; 
		int Acols=A[0].length;
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
}
