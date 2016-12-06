package concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

	public static void main(String[] args) {
		
		CyclicBarrier barrier = new CyclicBarrier(2,new RunnableBarrierActionExample(1));
		CyclicBarrier barrier2 = new CyclicBarrier(2,new RunnableBarrierActionExample(2));

		CyclicBarrierRunnableExample barrierRunnable = new CyclicBarrierRunnableExample(barrier,barrier2);
		CyclicBarrierRunnableExample barrierRunnable2 = new CyclicBarrierRunnableExample(barrier,barrier2);
		
		new Thread(barrierRunnable).start();
		new Thread(barrierRunnable2).start();
		
	}

	static class RunnableBarrierActionExample implements Runnable{

		int id;
		
		public RunnableBarrierActionExample(int id) {
			super();
			this.id = id;
		}

		@Override
		public void run() {
			System.out.println(this.getClass().getSimpleName()+" " + id +" executed");
			
		}
		
	}
	
	
	static class CyclicBarrierRunnableExample implements Runnable{

		CyclicBarrier barrier1 = null;
		CyclicBarrier barrier2 = null;
		
		
		public CyclicBarrierRunnableExample(CyclicBarrier barrier1, CyclicBarrier barrier2) {
			super();
			this.barrier1 = barrier1;
			this.barrier2 = barrier2;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(2000);
				
				System.out.println(Thread.currentThread().getName() + " waiting at barrier 1");
				
				this.barrier1.await();
				
				
				Thread.sleep(2000);
				
				System.out.println(Thread.currentThread().getName() + " waiting at barrier 2");
				
				this.barrier2.await();
				
				System.out.println(Thread.currentThread().getName() + " is done");
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
