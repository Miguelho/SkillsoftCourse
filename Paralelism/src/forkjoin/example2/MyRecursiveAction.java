package forkjoin.example2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MyRecursiveAction extends RecursiveAction {

	private long workLoad = 0;
	
	
	
	public MyRecursiveAction(long workLoad) {
		super();
		this.workLoad = workLoad;
	}



	@Override
	protected void compute() {
		if ( this.workLoad > 16 ) {
			System.out.println("Splitting workload: " + this.workLoad);
			
			List<MyRecursiveAction> subtasks = new ArrayList<>();
			
			subtasks.addAll(createSubtasks());
			
			for (MyRecursiveAction action : subtasks) 
				action.fork();
				
				
		}else{
			System.out.println("Doing workLoad by myself "+ this.workLoad);
		}
		
	}



	private Collection<? extends MyRecursiveAction> createSubtasks() {
		
		List<MyRecursiveAction> subtasks = new ArrayList<MyRecursiveAction>();
		
		MyRecursiveAction subtask1 = new MyRecursiveAction(this.workLoad/2);

		MyRecursiveAction subtask2 = new MyRecursiveAction(this.workLoad/2);
		
		subtasks.add(subtask1);

		subtasks.add(subtask2);
		
		return subtasks;
	}
	
	public static void main (String args[]){
		
		ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		
		forkJoinPool.invoke(new MyRecursiveAction(24));
		
		forkJoinPool.shutdown();
	}
	

}
