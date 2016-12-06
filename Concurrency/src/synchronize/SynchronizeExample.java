package synchronize;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizeExample {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();

		executorService.execute(new RunnableCounter());
		executorService.execute(new RunnableCounter());
		executorService.execute(new RunnableCounter());
		executorService.execute(new RunnableCounter());

		// executorService.execute(new RunnableCounter());

		// System.out.println(SynchronizedCounter.getValue());
		// SynchronizedCounter.decrement();
		executorService.shutdown();
	}

	static class SynchronizedCounter {
		private static int counter = 0;

		public static void increment() {
			counter++;
		}

		public static void decrement() {
			counter--;
		}

		public static int getValue() {
			return counter;
		}

	}

	static class RunnableCounter implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 20; i++) {
				synchronized (SynchronizedCounter.class) {
					SynchronizedCounter.increment();
					System.out.println(
							Thread.currentThread().getName() + " current count " + SynchronizedCounter.getValue());
				}

			}
		}

	}

}
