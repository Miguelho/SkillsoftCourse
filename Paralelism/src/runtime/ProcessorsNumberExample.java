package runtime;

public class ProcessorsNumberExample {

	public static void main(String[] args) {
		
		System.out.println(Runtime.getRuntime().availableProcessors());

		System.out.println(Runtime.getRuntime().freeMemory());
		
		String memoryEater = "MEEEEEEEEEEMORYYYYYYYYYYYYYYYYYYYYYYYYYYYYY";
		memoryEater.getBytes();
		
		System.out.println(Runtime.getRuntime().freeMemory());
	}

}
