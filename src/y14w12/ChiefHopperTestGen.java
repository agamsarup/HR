package y14w12;

import java.util.Random;

public class ChiefHopperTestGen {

	public static void main(String[] args) {
		
		Random rand=new Random(System.currentTimeMillis());
		System.out.println(100000);
		
		for(int i=0;i<100000;i++)
			System.out.print((rand.nextInt(100000)+1)+" ");
			

	}

}
