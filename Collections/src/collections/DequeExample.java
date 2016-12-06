package collections;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeExample {

	public static void main(String[] args) {
		Deque<String> aDeque = new ArrayDeque<>();
		//LIFO
		aDeque.push("One");

		aDeque.push("Two");

		aDeque.push("Three");
		
		
		for(String word : aDeque){
			System.out.println(word);
		}
		System.out.println("----");
		Deque<String> anotherDeque = new ArrayDeque<>();
		
		
		//FIFO
		anotherDeque.add("Four");
		anotherDeque.add("Five");
		anotherDeque.add("Six");
		
		
		for(String word : anotherDeque){
			System.out.println(word);
			
		}
	}
}
