package usingexecutors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorExample {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		executorService.execute(new ExampleRunnable("hola"));
		
		executorService.execute(new ExampleRunnable("Mundo"));
		
		System.out.println(executorService.submit(new ExampleCallable()).get());
		
		
		executorService.awaitTermination(5000, TimeUnit.MILLISECONDS);
	}
	
	static class ExampleRunnable implements Runnable{
		
		String string;
		
			
		public ExampleRunnable(String string) {
			super();
			this.string = string;
		}


		@Override
		public void run() {
			// TODO Auto-generated method stub
//			try {
//				Thread.sleep(20000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			System.out.println(string);
		}
		
		
	}
	static class ExampleCallable implements Callable<String>{
		String resultfromService;
		

		@Override
		public String call() throws Exception {
			Thread.sleep(2500);
			resultfromService= "{'id':'01'}";
			return resultfromService;
		}
		
	}
}
