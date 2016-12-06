package forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample {

	public static void main(String[] args) {
		
		int [] myArray = SingleThreadedExample.getFilledArray();

		ForkJoinPool pool = new ForkJoinPool();
		
		FindMaxTask findMaxTask = new FindMaxTask(myArray, 0, myArray.length -1 , myArray.length/2);
		
		Integer result = pool.invoke(findMaxTask);
		
		System.out.println(result);
		

	}

	static class FindMaxTask extends RecursiveTask<Integer> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private final int[] myArray;
		private int start, end;
		private final int threshold;

		public FindMaxTask(int[] myArray, int start, int end, int threshold) {
			super();
			this.myArray = myArray;
			this.start = start;
			this.end = end;
			this.threshold = threshold;
		}

		@Override
		protected Integer compute() {
			if (end - start < threshold)
				return SingleThreadedExample.computeMaxSingleThreaded(myArray);
			else {
				int midway = (end - start) / 2 + start;

				FindMaxTask leftSideOfTheArray = new FindMaxTask(myArray, start, midway, threshold);
				leftSideOfTheArray.fork();

				FindMaxTask rightSideOfTheArray = new FindMaxTask(myArray, midway + 1, end, threshold);

				return Math.max(rightSideOfTheArray.compute(), leftSideOfTheArray.join());

			}

			

		}

	}
}
