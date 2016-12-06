package forkjoin;

import java.util.concurrent.ThreadLocalRandom;

public class SingleThreadedExample {

	public static void main(String[] args) {
		
		computeMaxSingleThreaded(getFilledArray());

	}

	public static int [] getFilledArray(){
		
		int [] emptyArray = new int [1024*1024*256];
		
		for (int i = 0; i < emptyArray.length; i++)
			emptyArray[i] = ThreadLocalRandom.current().nextInt();
		
		return emptyArray;
		
	}

	public static int computeMaxSingleThreaded(int[] data) {

		int max = Integer.MIN_VALUE;

		for (int value : data) {
			if (value > max)
				max = value;
		}

//		System.out.println("Max value " + max);

		return max;
	}

}
