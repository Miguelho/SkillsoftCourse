package concurrentlists;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArrayListTest implements Runnable{
	
	private CopyOnWriteArrayList<String> wordList = new CopyOnWriteArrayList<>();
//	private ArrayList<String> wordList = new ArrayList<>();
	
	public static void main(String[] args) {
		
		ArrayListTest arrayListTest = new ArrayListTest();
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		executorService.submit(arrayListTest);
		executorService.submit(arrayListTest);
		executorService.submit(arrayListTest);
		executorService.submit(arrayListTest);
		executorService.submit(arrayListTest);
		executorService.submit(arrayListTest);
		executorService.submit(arrayListTest);
		executorService.submit(arrayListTest);
		executorService.submit(arrayListTest);
		executorService.submit(arrayListTest);
		executorService.submit(arrayListTest);
		executorService.submit(arrayListTest);

		executorService.shutdown();
		for (int i = 0; i < arrayListTest.wordList.size(); i++) {
			System.out.println(arrayListTest.wordList.get(i));
	
		}
		
	}

	@Override
	public void run() {
		wordList.add("A");
		
		wordList.add("B");
		
		wordList.add("C");
		
	}
	

}
